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

    public void deleteInsuranceCompById(Integer id) {
        InsuranceCompany insuranceCompany = insuranceRepository.findById(Long.valueOf(id)).orElseThrow(NoSuchInsuranceCompanyException::new);
        insuranceRepository.deleteById(insuranceCompany.getId());
    }

    public List<InsuranceCompanyDTO> getAllInsuranceCompanies() {
        List<InsuranceCompany> allInsuranceCompanies = insuranceRepository.getAllInsuranceCompanies();
        return allInsuranceCompanies.stream().map(obj -> modelMapper.map(obj, InsuranceCompanyDTO.class)).toList();
    }

    public InsuranceCompanyDTO updateInsuranceCompany(InsuranceCompanyDTO insuranceCompanyDTO, Integer id) {
        InsuranceCompany insuranceCompany = insuranceRepository.findById(Long.valueOf(id)).orElseThrow(NoSuchInsuranceCompanyException::new);
        insuranceCompany.setName(insuranceCompanyDTO.getName()).setDescription(insuranceCompanyDTO.getDescription())
                .setPhoneNumber(insuranceCompanyDTO.getPhoneNumber());
        InsuranceCompany savedInsuranceCompany = insuranceRepository.save(insuranceCompany);
        return modelMapper.map(savedInsuranceCompany, InsuranceCompanyDTO.class);
    }
}
