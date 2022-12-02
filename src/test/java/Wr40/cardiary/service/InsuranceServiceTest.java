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
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
public class InsuranceServiceTest {

    @Mock
    private CarRepository carRepository;
    @Mock
    private InsuranceRepository insuranceRepository;

    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private InsuranceService insuranceService;

    @Test
    public void whenSavingInsuranceCompanyAndType_shouldAddRelationshipsBetweenEntitesAndSave() {

        //given
        String VINNumber = "SRC1000";
        InsuranceTypeDTO insuranceTypeDTO = new InsuranceTypeDTO();
        insuranceTypeDTO.setType("Collision insurance").setDescription("Keep me solvable in case something happend on the road").setCostsPerYear(BigDecimal.valueOf(900))
                .setCoveredCompensation(BigDecimal.valueOf(200_000));

        InsuranceCompanyDTO insuranceCompanyDTO = new InsuranceCompanyDTO();
        insuranceCompanyDTO.setName("NewSafeLvl").setDescription("For me the best so far").setPhoneNumber(19027883L).setInsuranceTypeDTO(insuranceTypeDTO);

        InsuranceType insuranceType = new InsuranceType();
        insuranceType.setType("Collision insurance").setDescription("Keep me solvable in case something happend on the road").setCostsPerYear(BigDecimal.valueOf(900));

        InsuranceCompany insuranceCompany = new InsuranceCompany();
        insuranceCompany.setName("NewSafeLvl").setDescription("For me the best so far").setPhoneNumber(19027883L).setInsuranceType(insuranceType);
        Car car = new Car();
        HashSet<InsuranceCompany> objects = new HashSet<>();
        objects.add(new InsuranceCompany());
        car.setInsuranceCompanies(objects);

        Mockito.when(carRepository.findByVINnumber(VINNumber)).thenReturn(Optional.of(car));
        Mockito.when(insuranceRepository.findByName(insuranceCompanyDTO.getName())).thenReturn((Optional.empty()));
        Mockito.when(insuranceRepository.save(insuranceCompany)).thenReturn(insuranceCompany);
        Mockito.when(carRepository.save(car)).thenReturn(car);

        Mockito.when(modelMapper.map(insuranceCompanyDTO, InsuranceCompany.class)).thenReturn(insuranceCompany);
        Mockito.when(modelMapper.map(insuranceCompany, InsuranceCompanyDTO.class)).thenReturn(insuranceCompanyDTO);
        Mockito.when(modelMapper.map(insuranceTypeDTO, InsuranceType.class)).thenReturn(insuranceType);
        Mockito.when(modelMapper.map(insuranceType, InsuranceTypeDTO.class)).thenReturn(insuranceTypeDTO);

        //when
        InsuranceCompanyDTO savedInsuranceCompanyDTO = insuranceService.saveInsuranceWithTypeToTheCar(insuranceCompanyDTO, VINNumber);

        //then
        Assertions.assertEquals(savedInsuranceCompanyDTO, insuranceCompanyDTO);
        Mockito.verify(carRepository).save(car);
        Mockito.verify(insuranceRepository).findByName(insuranceCompanyDTO.getName());
        Mockito.verify(insuranceRepository).save(insuranceCompany);
    }

    @Test
    public void whenSavingInsuranceCompanyAlreadyExisting_shouldThrowException() {

        //given
        String VINNumber = "SRC1000";
        InsuranceTypeDTO insuranceTypeDTO = new InsuranceTypeDTO();
        insuranceTypeDTO.setType("Collision insurance").setDescription("Keep me solvable in case something happend on the road").setCostsPerYear(BigDecimal.valueOf(900))
                .setCoveredCompensation(BigDecimal.valueOf(200_000));

        InsuranceCompanyDTO insuranceCompanyDTO = new InsuranceCompanyDTO();
        insuranceCompanyDTO.setName("NewSafeLvl").setDescription("For me the best so far").setPhoneNumber(19027883L).setInsuranceTypeDTO(insuranceTypeDTO);

        InsuranceType insuranceType = new InsuranceType();
        insuranceType.setType("Collision insurance").setDescription("Keep me solvable in case something happend on the road").setCostsPerYear(BigDecimal.valueOf(900));

        InsuranceCompany insuranceCompany = new InsuranceCompany();
        insuranceCompany.setName("NewSafeLvl").setDescription("For me the best so far").setPhoneNumber(19027883L).setInsuranceType(insuranceType);
        Car car = new Car();
        HashSet<InsuranceCompany> objects = new HashSet<>();
        objects.add(new InsuranceCompany());
        car.setInsuranceCompanies(objects);

        Mockito.when(carRepository.findByVINnumber(VINNumber)).thenReturn(Optional.of(car));
        Mockito.when(insuranceRepository.findByName(insuranceCompanyDTO.getName())).thenReturn((Optional.of(insuranceCompany)));

        //when
        //then
        Assertions.assertThrows(InsuranceCompanyAlreadyExistsException.class, () -> insuranceService.saveInsuranceWithTypeToTheCar(insuranceCompanyDTO, VINNumber));
    }
}