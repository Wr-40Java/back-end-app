package Wr40.cardiary.service;

import Wr40.cardiary.exception.*;
import Wr40.cardiary.model.dto.insurance.InsuranceCompanyDTO;
import Wr40.cardiary.model.dto.insurance.InsuranceCompanyWithTypeDTO;
import Wr40.cardiary.model.dto.insurance.InsuranceTypeDTO;
import Wr40.cardiary.model.entity.Car;
import Wr40.cardiary.model.entity.InsuranceCompany;
import Wr40.cardiary.model.entity.InsuranceType;
import Wr40.cardiary.model.entity.User;
import Wr40.cardiary.repo.CarRepository;
import Wr40.cardiary.repo.InsuranceRepository;
import Wr40.cardiary.repo.InsuranceTypeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

@Service
@AllArgsConstructor
@Transactional
public class InsuranceService {

    private CarRepository carRepository;
    private InsuranceRepository insuranceRepository;
    private InsuranceTypeRepository insuranceTypeRepository;
    private ModelMapper modelMapper;

    public InsuranceCompanyWithTypeDTO saveInsuranceWithTypeToTheCar(InsuranceCompanyWithTypeDTO insuranceCompanyWithTypeDTO, String VINNumber) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String usernameFromSecurityContext = null;
        if (principal instanceof UserDetails) {
            usernameFromSecurityContext = ((UserDetails) principal).getUsername();
        } else {
            usernameFromSecurityContext = principal.toString();
        }

        Car car = carRepository.findByVINnumber(VINNumber).orElseThrow(NoSuchCarFoundException::new);
        User userPosessingCar = car.getUsers();

        if (usernameFromSecurityContext.equals(userPosessingCar.getUsername())) {
            Optional<InsuranceCompany> insuranceCompanyOptional = insuranceRepository.findByName(insuranceCompanyWithTypeDTO.getName());
            if (insuranceCompanyOptional.isPresent()) {
                throw new InsuranceCompanyAlreadyExistsException();
            }
            InsuranceType insuranceType = modelMapper.map(insuranceCompanyWithTypeDTO.getInsuranceTypeDTO(), InsuranceType.class);
            InsuranceType savedInsuranceType = insuranceTypeRepository.save(insuranceType);

            InsuranceCompany insuranceCompany = modelMapper.map(insuranceCompanyWithTypeDTO, InsuranceCompany.class);
//        insuranceCompany.setInsuranceType(modelMapper.map(insuranceCompanyWithTypeDTO.getInsuranceTypeDTO(), InsuranceType.class));

            insuranceCompany.setInsuranceType(savedInsuranceType);
            car.addInsuranceCompany(insuranceCompany);
            InsuranceCompany savedInsuranceCompany = insuranceRepository.save(insuranceCompany);
            carRepository.save(car);
            InsuranceCompanyWithTypeDTO mappedInsCompanyDTO = modelMapper.map(savedInsuranceCompany, InsuranceCompanyWithTypeDTO.class);
            mappedInsCompanyDTO.setInsuranceTypeDTO(modelMapper.map(savedInsuranceCompany.getInsuranceType(), InsuranceTypeDTO.class));
            return mappedInsCompanyDTO;
        } else {
            throw new NotYourCarException("Can not save this isnurance under other user's car");
        }
    }

    public List<InsuranceCompanyWithTypeDTO> getInsuranceCompWithType(String VINNumber) {
        List<InsuranceCompany> allIncuranceCompaniesWithType = new ArrayList<>();
        Car car = carRepository.findByVINnumber(VINNumber).orElseThrow(NoSuchCarFoundException::new);
        Set<InsuranceCompany> insuranceCompanies = car.getInsuranceCompanies();
        for (InsuranceCompany insuranceCompany : insuranceCompanies) {
            allIncuranceCompaniesWithType.add(insuranceCompany);
        }
//        List<InsuranceCompany> allIncuranceCompaniesWithType = insuranceRepository.getAllIncuranceCompaniesWithType(VINNumber);
        List<InsuranceCompanyWithTypeDTO> mappedInsCompanyDTO = allIncuranceCompaniesWithType.stream()
                .map(obj -> modelMapper.map(obj, InsuranceCompanyWithTypeDTO.class)).toList();
        List<InsuranceCompanyWithTypeDTO> mappedInsCompanyWithTypeDTO = mappedInsCompanyDTO.stream()
                .map(obj -> obj.setInsuranceTypeDTO(mapInsuranceTypeToDTO(allIncuranceCompaniesWithType.stream()
                        .filter(obj2 -> obj.getPhoneNumber().equals(obj2.getPhoneNumber()))
                        .map(obj1 -> obj1.getInsuranceType()).findFirst().get()))).toList();
        return mappedInsCompanyWithTypeDTO;
    }

    public InsuranceCompanyWithTypeDTO linkCarWithInsuranceCompanyAndInsuranceType(String VINNumber, Integer InsCompId, Integer InsTypeId) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String usernameFromSecurityContext = null;
        if (principal instanceof UserDetails) {
            usernameFromSecurityContext = ((UserDetails) principal).getUsername();
        } else {
            usernameFromSecurityContext = principal.toString();
        }

        Car car = carRepository.findByVINnumber(VINNumber).orElseThrow(NoSuchCarFoundException::new);
        User userPosessingCar = car.getUsers();

        if (usernameFromSecurityContext.equals(userPosessingCar.getUsername())) {
            InsuranceCompany insuranceCompany = insuranceRepository.findById(Long.valueOf(InsCompId)).orElseThrow(NoSuchInsuranceCompanyException::new);
            InsuranceType insuranceType = insuranceTypeRepository.findById(Long.valueOf(InsTypeId)).orElseThrow(NoSuchInsuranceTypeException::new);

/**
 * After checking if linking is going on for valid username (check database with security Principal object)
 * just save foreign key wothout adding new Insurance type etc as it is only a parent to which we want to link new children instead of creating new copy of entities
 */

            insuranceCompany.setInsuranceType(insuranceType);
            insuranceRepository.save(insuranceCompany);
            car.addInsuranceCompany(insuranceCompany);
            Car savedCar = carRepository.save(car);
            InsuranceCompanyWithTypeDTO savedInsuranceCompany = modelMapper.map(savedCar.getInsuranceCompanies().stream().findFirst().get(), InsuranceCompanyWithTypeDTO.class);

            savedInsuranceCompany.setInsuranceTypeDTO(
                    modelMapper.map(savedCar.getInsuranceCompanies().stream().map(obj -> obj.getInsuranceType()).findFirst().get(), InsuranceTypeDTO.class));
            return savedInsuranceCompany;
        }
        throw new NotYourCarException("You can not link this car with other data because it is posessed by another user");
    }

    public InsuranceCompanyWithTypeDTO updateLinkInsuranceCompanyWithTypeAndCar(String VINNumber, Integer InsCompId, Integer InsTypeId) {
        Car car = carRepository.findByVINnumber(VINNumber).orElseThrow(NoSuchCarFoundException::new);
//        Optional<InsuranceCompany> insuranceCompanyOptional = insuranceRepository.findById(Long.valueOf(OldInsCompId));
//        if(!insuranceCompanyOptional.isPresent()) {
//            throw new NoSuchInsuranceCompanyException();
//        }
        InsuranceCompany toAssignInsuranceCompany = insuranceRepository.findById(Long.valueOf(InsCompId)).orElseThrow(NoSuchInsuranceCompanyException::new);

        InsuranceType toAssignInsuranceType = insuranceTypeRepository.findById(Long.valueOf(InsTypeId)).orElseThrow(NoSuchInsuranceTypeException::new);
        toAssignInsuranceCompany.setInsuranceType(toAssignInsuranceType);

        InsuranceCompany savedInsuranceCompany = insuranceRepository.save(toAssignInsuranceCompany);

//        car.removeInsuranceCompany(insuranceCompanyOptional.get());
        car.addInsuranceCompany(toAssignInsuranceCompany);
        carRepository.save(car);

        InsuranceCompanyWithTypeDTO mappedInsCompanyDTO = modelMapper.map(savedInsuranceCompany, InsuranceCompanyWithTypeDTO.class);
        mappedInsCompanyDTO.setInsuranceTypeDTO(modelMapper.map(savedInsuranceCompany.getInsuranceType(), InsuranceTypeDTO.class));
        return mappedInsCompanyDTO;
    }

    public String deleteLinkInsuranceCompanyWithTypeAndCar(String VINNumber, Integer InsCompId, Integer InsTypeId) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String usernameFromSecurityContext = null;
        if (principal instanceof UserDetails) {
            usernameFromSecurityContext = ((UserDetails) principal).getUsername();
        } else {
            usernameFromSecurityContext = principal.toString();
        }

        Car car = carRepository.findByVINnumber(VINNumber).orElseThrow(NoSuchCarFoundException::new);
        User userPosessingCar = car.getUsers();

        if (usernameFromSecurityContext.equals(userPosessingCar.getUsername())) {
            Iterator<InsuranceCompany> insuranceCompaniesIterator = car.getInsuranceCompanies().iterator();
            while (insuranceCompaniesIterator.hasNext()) {
                InsuranceCompany insuranceComp = insuranceCompaniesIterator.next();
                if (insuranceComp.getId().equals(Long.valueOf(InsCompId))) {
                    insuranceCompaniesIterator.remove();
                }
            }

            for (InsuranceCompany insuranceCompany : car.getInsuranceCompanies()) {
                if (insuranceCompany.getId().equals(Long.valueOf(InsCompId))) {
                    car.removeInsuranceCompany(insuranceCompany);
                }
            }
            insuranceRepository.deleteById(Long.valueOf(InsCompId));
            return "Successfully deleted insurance! You can still pick this company for different type of it.";
        } else {
            throw new NotYourCarException("Can not delete other user's property!");
        }
    }

    private InsuranceTypeDTO mapInsuranceTypeToDTO(InsuranceType insuranceType) {
        InsuranceTypeDTO insuranceTypeDTO = new InsuranceTypeDTO();
        insuranceTypeDTO.setType(insuranceType.getType()).setDescription(insuranceType.getDescription()).setCostsPerYear(insuranceType.getCostsPerYear())
                .setCoveredCompensation(insuranceType.getCoveredCompensation()).setId(insuranceType.getId());
        return insuranceTypeDTO;
    }

    public InsuranceCompanyDTO saveInsurenceCompany(InsuranceCompanyDTO insuranceCompanyDTO) {
        Optional<InsuranceCompany> insuranceCompanyOptional = insuranceRepository.findByName(insuranceCompanyDTO.getName());
        if (insuranceCompanyOptional.isPresent()) {
            throw new InsuranceCompanyAlreadyExistsException();
        }
        InsuranceCompany insuranceCompany = modelMapper.map(insuranceCompanyDTO, InsuranceCompany.class);
        InsuranceCompany savedInsuranceCompany = insuranceRepository.save(insuranceCompany);
        InsuranceCompanyDTO mappedInCompanyDTO = modelMapper.map(savedInsuranceCompany, InsuranceCompanyDTO.class);
        return mappedInCompanyDTO;
    }

    public List<InsuranceCompanyDTO> getAllInsuranceCompanies() {
        List<InsuranceCompany> allInsuranceCompanies = insuranceRepository.getAllInsuranceCompanies();
        return allInsuranceCompanies.stream().map(obj -> modelMapper.map(obj, InsuranceCompanyDTO.class)).toList();
    }

    public void deleteInsuranceCompById(Integer id) {
        InsuranceCompany insuranceCompany = insuranceRepository.findById(Long.valueOf(id)).orElseThrow(NoSuchInsuranceCompanyException::new);
        if (insuranceCompany.getCars().size() > 0) {
            throw new SQLExceptionCustomized("This insurance company is already connected with car, delete linked relationship first, then delete this position alone");
        }
        insuranceRepository.deleteById(insuranceCompany.getId());
    }

    public InsuranceCompanyDTO updateInsuranceCompany(InsuranceCompanyDTO insuranceCompanyDTO, Integer id) {
        InsuranceCompany insuranceCompany = insuranceRepository.findById(Long.valueOf(id)).orElseThrow(NoSuchInsuranceCompanyException::new);
        insuranceCompany.setName(insuranceCompanyDTO.getName()).setDescription(insuranceCompanyDTO.getDescription())
                .setPhoneNumber(insuranceCompanyDTO.getPhoneNumber());
        InsuranceCompany savedInsuranceCompany = insuranceRepository.save(insuranceCompany);
        return modelMapper.map(savedInsuranceCompany, InsuranceCompanyDTO.class);
    }
}
