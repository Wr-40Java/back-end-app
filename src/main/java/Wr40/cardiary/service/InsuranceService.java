package Wr40.cardiary.service;

import Wr40.cardiary.exception.InsuranceCompanyAlreadyExistsException;
import Wr40.cardiary.exception.NoSuchCarFoundException;
import Wr40.cardiary.model.dto.InsuranceCompanyDTO;
import Wr40.cardiary.model.dto.InsuranceTypeDTO;
import Wr40.cardiary.model.entity.Car;
import Wr40.cardiary.model.entity.InsuranceCompany;
import Wr40.cardiary.model.entity.InsuranceType;
import Wr40.cardiary.repo.CarRepository;
import Wr40.cardiary.repo.InsuranceRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class InsuranceService {

    private CarRepository carRepository;
    private InsuranceRepository insuranceRepository;
    private ModelMapper modelMapper;
    public InsuranceCompanyDTO saveInsuranceWithTypeToTheCar(InsuranceCompanyDTO insuranceCompanyDTO, String VINNumber) {
        Car car = carRepository.findByVINnumber(VINNumber).orElseThrow(NoSuchCarFoundException::new);
        Optional<InsuranceCompany> insuranceCompanyOptional = insuranceRepository.findByName(insuranceCompanyDTO.getName());
        if(insuranceCompanyOptional.isPresent()) {
            throw new InsuranceCompanyAlreadyExistsException();
        }
        InsuranceCompany insuranceCompany = modelMapper.map(insuranceCompanyDTO, InsuranceCompany.class);
        insuranceCompany.setInsuranceType(modelMapper.map(insuranceCompanyDTO.getInsuranceTypeDTO(), InsuranceType.class));
        car.addInsuranceCompany(insuranceCompany);
        InsuranceCompany savedInsuranceCompany = insuranceRepository.save(insuranceCompany);
        carRepository.save(car);
        InsuranceCompanyDTO mappedInsCompanyDTO = modelMapper.map(savedInsuranceCompany, InsuranceCompanyDTO.class);
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
}
