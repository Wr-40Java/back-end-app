package Wr40.cardiary.service;

import Wr40.cardiary.exception.InsuranceCompanyAlreadyExistsException;
import Wr40.cardiary.exception.NoSuchCarFoundException;
import Wr40.cardiary.exception.NoSuchInsuranceCompanyException;
import Wr40.cardiary.model.dto.insurance.InsuranceCompanyDTO;
import Wr40.cardiary.model.dto.insurance.InsuranceCompanyWithTypeDTO;
import Wr40.cardiary.model.dto.insurance.InsuranceTypeDTO;
import Wr40.cardiary.model.entity.Car;
import Wr40.cardiary.model.entity.InsuranceCompany;
import Wr40.cardiary.model.entity.InsuranceType;
import Wr40.cardiary.repo.CarRepository;
import Wr40.cardiary.repo.InsuranceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class InsuranceService {

    private CarRepository carRepository;
    private InsuranceRepository insuranceRepository;
    private ModelMapper modelMapper;
    public InsuranceCompanyWithTypeDTO saveInsuranceWithTypeToTheCar(InsuranceCompanyWithTypeDTO insuranceCompanyWithTypeDTO, String VINNumber) {
        Car car = carRepository.findByVINnumber(VINNumber).orElseThrow(NoSuchCarFoundException::new);
        Optional<InsuranceCompany> insuranceCompanyOptional = insuranceRepository.findByName(insuranceCompanyWithTypeDTO.getName());
        if(insuranceCompanyOptional.isPresent()) {
            throw new InsuranceCompanyAlreadyExistsException();
        }
        InsuranceCompany insuranceCompany = modelMapper.map(insuranceCompanyWithTypeDTO, InsuranceCompany.class);
        insuranceCompany.setInsuranceType(modelMapper.map(insuranceCompanyWithTypeDTO.getInsuranceTypeDTO(), InsuranceType.class));
        car.addInsuranceCompany(insuranceCompany);
        InsuranceCompany savedInsuranceCompany = insuranceRepository.save(insuranceCompany);
        carRepository.save(car);
        InsuranceCompanyWithTypeDTO mappedInsCompanyDTO = modelMapper.map(savedInsuranceCompany, InsuranceCompanyWithTypeDTO.class);
        mappedInsCompanyDTO.setInsuranceTypeDTO(modelMapper.map(savedInsuranceCompany.getInsuranceType(), InsuranceTypeDTO.class));
        return mappedInsCompanyDTO;
    }

    public List<InsuranceCompanyWithTypeDTO> getInsuranceCompWithType(String VINNumber) {
        List<InsuranceCompany> allIncuranceCompaniesWithType = insuranceRepository.getAllIncuranceCompaniesWithType(VINNumber);
        allIncuranceCompaniesWithType.stream().map(o->o.getInsuranceType()).forEach(System.out::println);
        List<InsuranceCompanyWithTypeDTO> mappedInsCompanyDTO = allIncuranceCompaniesWithType.stream()
                .map(obj -> modelMapper.map(obj, InsuranceCompanyWithTypeDTO.class)).toList();
        List<InsuranceCompanyWithTypeDTO> mappedInsCompanyWithTypeDTO = mappedInsCompanyDTO.stream()
                .map(obj -> obj.setInsuranceTypeDTO(mapInsuranceTypeToDTO(allIncuranceCompaniesWithType.stream()
                                .filter(obj2 -> obj.getPhoneNumber().equals(obj2.getPhoneNumber()))
                                .map(obj1 -> obj1.getInsuranceType()).findFirst().get()))).toList();
        return mappedInsCompanyWithTypeDTO;
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
