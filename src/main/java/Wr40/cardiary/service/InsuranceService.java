package Wr40.cardiary.service;

import Wr40.cardiary.exception.InsuranceCompanyAlreadyExistsException;
import Wr40.cardiary.exception.NoSuchCarFoundException;
import Wr40.cardiary.exception.NoSuchInsuranceCompanyException;
import Wr40.cardiary.exception.NoSuchInsuranceTypeException;
import Wr40.cardiary.model.dto.insurance.InsuranceCompanyDTO;
import Wr40.cardiary.model.dto.insurance.InsuranceCompanyWithTypeDTO;
import Wr40.cardiary.model.dto.insurance.InsuranceTypeDTO;
import Wr40.cardiary.model.entity.Car;
import Wr40.cardiary.model.entity.InsuranceCompany;
import Wr40.cardiary.model.entity.InsuranceType;
import Wr40.cardiary.repo.CarRepository;
import Wr40.cardiary.repo.InsuranceRepository;
import Wr40.cardiary.repo.InsuranceTypeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
@Transactional
public class InsuranceService {

    private CarRepository carRepository;
    private InsuranceRepository insuranceRepository;
    private InsuranceTypeRepository insuranceTypeRepository;
    private ModelMapper modelMapper;
    public InsuranceCompanyWithTypeDTO saveInsuranceWithTypeToTheCar(InsuranceCompanyWithTypeDTO insuranceCompanyWithTypeDTO, String VINNumber) {
        Car car = carRepository.findByVINnumber(VINNumber).orElseThrow(NoSuchCarFoundException::new);
        Optional<InsuranceCompany> insuranceCompanyOptional = insuranceRepository.findByName(insuranceCompanyWithTypeDTO.getName());
        if(insuranceCompanyOptional.isPresent()) {
            throw new InsuranceCompanyAlreadyExistsException();
        }
        InsuranceType insuranceType = modelMapper.map(insuranceCompanyWithTypeDTO, InsuranceType.class);
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
    }

    public List<InsuranceCompanyWithTypeDTO> getInsuranceCompWithType(String VINNumber) {
        List<InsuranceCompany> allIncuranceCompaniesWithType = new ArrayList<>();
        Car car = carRepository.findByVINnumber(VINNumber).orElseThrow(NoSuchCarFoundException::new);
        Set<InsuranceCompany> insuranceCompanies = car.getInsuranceCompanies();
        for (InsuranceCompany insuranceCompany : insuranceCompanies) {
            allIncuranceCompaniesWithType.add(insuranceCompany);
        }
//        List<InsuranceCompany> allIncuranceCompaniesWithType = insuranceRepository.getAllIncuranceCompaniesWithType(VINNumber);
        allIncuranceCompaniesWithType.stream().map(o->o.getInsuranceType()).forEach(System.out::println);
        List<InsuranceCompanyWithTypeDTO> mappedInsCompanyDTO = allIncuranceCompaniesWithType.stream()
                .map(obj -> modelMapper.map(obj, InsuranceCompanyWithTypeDTO.class)).toList();
        List<InsuranceCompanyWithTypeDTO> mappedInsCompanyWithTypeDTO = mappedInsCompanyDTO.stream()
                .map(obj -> obj.setInsuranceTypeDTO(mapInsuranceTypeToDTO(allIncuranceCompaniesWithType.stream()
                                .filter(obj2 -> obj.getPhoneNumber().equals(obj2.getPhoneNumber()))
                                .map(obj1 -> obj1.getInsuranceType()).findFirst().get()))).toList();
        return mappedInsCompanyWithTypeDTO;
    }

    public InsuranceCompanyWithTypeDTO linkCarWithInsuranceCompanyAndInsuranceType(String VINNumber, Integer InsCompId, Integer InsTypeId) {
        Car car = carRepository.findByVINnumber(VINNumber).orElseThrow(NoSuchCarFoundException::new);
        InsuranceCompany insuranceCompany = insuranceRepository.findById(Long.valueOf(InsCompId)).orElseThrow(NoSuchInsuranceCompanyException::new);
        InsuranceType insuranceType = insuranceTypeRepository.findById(Long.valueOf(InsTypeId)).orElseThrow(NoSuchInsuranceTypeException::new);

/**
        The aim of deep copy with InsuranceCompany table is to leave not related tables as base for next linking of data for user,
        after adding linking between tables( composition of data with new Id ) save as new objects
*/

        InsuranceCompany insuranceCompanyDeepCopy = new InsuranceCompany();
        insuranceCompanyDeepCopy.setName(insuranceCompany.getName());
        insuranceCompanyDeepCopy.setDescription(insuranceType.getDescription());
        insuranceCompanyDeepCopy.setPhoneNumber(insuranceCompany.getPhoneNumber());

        insuranceCompanyDeepCopy.setInsuranceType(insuranceType);
        insuranceRepository.save(insuranceCompanyDeepCopy);

        car.addInsuranceCompany(insuranceCompanyDeepCopy);
        Car savedCar = carRepository.save(car);
        InsuranceCompanyWithTypeDTO savedInsuranceCompany = modelMapper.map(savedCar.getInsuranceCompanies().stream().findFirst().get(), InsuranceCompanyWithTypeDTO.class);

        savedInsuranceCompany.setInsuranceTypeDTO(
                modelMapper.map(savedCar.getInsuranceCompanies().stream().map(obj -> obj.getInsuranceType()).findFirst().get(), InsuranceTypeDTO.class));
        return savedInsuranceCompany;
    }

    public InsuranceCompanyWithTypeDTO updateLinkInsuranceCompanyWithTypeAndCar(String VINNumber, Integer OldInsCompId, Integer InsCompId, Integer InsTypeId) {
        Car car = carRepository.findByVINnumber(VINNumber).orElseThrow(NoSuchCarFoundException::new);
        Optional<InsuranceCompany> insuranceCompanyOptional = insuranceRepository.findById(Long.valueOf(OldInsCompId));
        if(!insuranceCompanyOptional.isPresent()) {
            throw new NoSuchInsuranceCompanyException();
        }
        InsuranceCompany toAssignInsuranceCompany = insuranceRepository.findById(Long.valueOf(InsCompId)).orElseThrow(NoSuchInsuranceCompanyException::new);

        InsuranceType toAssignInsuranceType = insuranceTypeRepository.findById(Long.valueOf(InsTypeId)).orElseThrow(NoSuchInsuranceTypeException::new);
        toAssignInsuranceCompany.setInsuranceType(toAssignInsuranceType);

        InsuranceCompany savedInsuranceCompany = insuranceRepository.save(toAssignInsuranceCompany);

        car.removeInsuranceCompany(insuranceCompanyOptional.get());
        car.addInsuranceCompany(toAssignInsuranceCompany);
        carRepository.save(car);

        InsuranceCompanyWithTypeDTO mappedInsCompanyDTO = modelMapper.map(savedInsuranceCompany, InsuranceCompanyWithTypeDTO.class);
        mappedInsCompanyDTO.setInsuranceTypeDTO(modelMapper.map(savedInsuranceCompany.getInsuranceType(), InsuranceTypeDTO.class));
        return mappedInsCompanyDTO;
    }

    public String deleteLinkInsuranceCompanyWithTypeAndCar(String VINNumber, Integer InsCompId, Integer InsTypeId) {
        Car car = carRepository.findByVINnumber(VINNumber).orElseThrow(NoSuchCarFoundException::new);
        for (InsuranceCompany insuranceCompany : car.getInsuranceCompanies()) {
            if(insuranceCompany.getId().equals(Long.valueOf(InsCompId))) {
                car.removeInsuranceCompany(insuranceCompany);
            }
        }
        insuranceRepository.deleteById(Long.valueOf(InsCompId));
        return "Successfully deleted insurance! You can still pick this company for different type of it.";
    }

    private InsuranceTypeDTO mapInsuranceTypeToDTO(InsuranceType insuranceType) {
        InsuranceTypeDTO insuranceTypeDTO = new InsuranceTypeDTO();
        insuranceTypeDTO.setType(insuranceType.getType()).setDescription(insuranceType.getDescription()).setCostsPerYear(insuranceType.getCostsPerYear())
                .setCoveredCompensation(insuranceType.getCoveredCompensation());
        return insuranceTypeDTO;
    }

    public InsuranceCompanyDTO saveInsurenceCompany(InsuranceCompanyDTO insuranceCompanyDTO) {
        Optional<InsuranceCompany> insuranceCompanyOptional = insuranceRepository.findByName(insuranceCompanyDTO.getName());
        if(insuranceCompanyOptional.isPresent()) {
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
