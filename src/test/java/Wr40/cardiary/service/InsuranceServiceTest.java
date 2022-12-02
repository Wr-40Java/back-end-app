package Wr40.cardiary.service;

import Wr40.cardiary.exception.InsuranceCompanyAlreadyExistsException;
import Wr40.cardiary.exception.NoSuchInsuranceCompanyException;
import Wr40.cardiary.model.dto.insurance.InsuranceCompanyDTO;
import Wr40.cardiary.model.dto.insurance.InsuranceCompanyWithTypeDTO;
import Wr40.cardiary.model.dto.insurance.InsuranceTypeDTO;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

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

        InsuranceCompanyWithTypeDTO insuranceCompanyWithTypeDTO = new InsuranceCompanyWithTypeDTO();
        insuranceCompanyWithTypeDTO.setName("NewSafeLvl").setDescription("For me the best so far").setPhoneNumber(19027883L).setInsuranceTypeDTO(insuranceTypeDTO);

        InsuranceType insuranceType = new InsuranceType();
        insuranceType.setType("Collision insurance").setDescription("Keep me solvable in case something happend on the road").setCostsPerYear(BigDecimal.valueOf(900));

        InsuranceCompany insuranceCompany = new InsuranceCompany();
        insuranceCompany.setName("NewSafeLvl").setDescription("For me the best so far").setPhoneNumber(19027883L).setInsuranceType(insuranceType);
        Car car = new Car();
        HashSet<InsuranceCompany> objects = new HashSet<>();
        objects.add(new InsuranceCompany());
        car.setInsuranceCompanies(objects);

        Mockito.when(carRepository.findByVINnumber(VINNumber)).thenReturn(Optional.of(car));
        Mockito.when(insuranceRepository.findByName(insuranceCompanyWithTypeDTO.getName())).thenReturn((Optional.empty()));
        Mockito.when(insuranceRepository.save(insuranceCompany)).thenReturn(insuranceCompany);
        Mockito.when(carRepository.save(car)).thenReturn(car);

        Mockito.when(modelMapper.map(insuranceCompanyWithTypeDTO, InsuranceCompany.class)).thenReturn(insuranceCompany);
        Mockito.when(modelMapper.map(insuranceCompany, InsuranceCompanyWithTypeDTO.class)).thenReturn(insuranceCompanyWithTypeDTO);
        Mockito.when(modelMapper.map(insuranceTypeDTO, InsuranceType.class)).thenReturn(insuranceType);
        Mockito.when(modelMapper.map(insuranceType, InsuranceTypeDTO.class)).thenReturn(insuranceTypeDTO);

        //when
        InsuranceCompanyWithTypeDTO savedInsuranceCompanyWithTypeDTO = insuranceService.saveInsuranceWithTypeToTheCar(insuranceCompanyWithTypeDTO, VINNumber);

        //then
        Assertions.assertEquals(savedInsuranceCompanyWithTypeDTO, insuranceCompanyWithTypeDTO);
        Mockito.verify(carRepository).save(car);
        Mockito.verify(insuranceRepository).findByName(insuranceCompanyWithTypeDTO.getName());
        Mockito.verify(insuranceRepository).save(insuranceCompany);
    }

    @Test
    public void whenSavingInsuranceCompanyAlreadyExisting_shouldThrowException() {

        //given
        String VINNumber = "SRC1000";
        InsuranceTypeDTO insuranceTypeDTO = new InsuranceTypeDTO();
        insuranceTypeDTO.setType("Collision insurance").setDescription("Keep me solvable in case something happend on the road").setCostsPerYear(BigDecimal.valueOf(900))
                .setCoveredCompensation(BigDecimal.valueOf(200_000));

        InsuranceCompanyWithTypeDTO insuranceCompanyWithTypeDTO = new InsuranceCompanyWithTypeDTO();
        insuranceCompanyWithTypeDTO.setName("NewSafeLvl").setDescription("For me the best so far").setPhoneNumber(19027883L).setInsuranceTypeDTO(insuranceTypeDTO);

        InsuranceType insuranceType = new InsuranceType();
        insuranceType.setType("Collision insurance").setDescription("Keep me solvable in case something happend on the road").setCostsPerYear(BigDecimal.valueOf(900));

        InsuranceCompany insuranceCompany = new InsuranceCompany();
        insuranceCompany.setName("NewSafeLvl").setDescription("For me the best so far").setPhoneNumber(19027883L).setInsuranceType(insuranceType);
        Car car = new Car();
        HashSet<InsuranceCompany> objects = new HashSet<>();
        objects.add(new InsuranceCompany());
        car.setInsuranceCompanies(objects);

        Mockito.when(carRepository.findByVINnumber(VINNumber)).thenReturn(Optional.of(car));
        Mockito.when(insuranceRepository.findByName(insuranceCompanyWithTypeDTO.getName())).thenReturn((Optional.of(insuranceCompany)));

        //when
        //then
        Assertions.assertThrows(InsuranceCompanyAlreadyExistsException.class, () -> insuranceService.saveInsuranceWithTypeToTheCar(insuranceCompanyWithTypeDTO, VINNumber));
    }

    @Test
    public void whenSavingInsuranceCompanyAlone_shouldReturnSavedObjectMappedToDTO() {
        //given
        InsuranceCompanyDTO insuranceCompanyDTO = new InsuranceCompanyDTO();
        insuranceCompanyDTO.setName("NewSafeLvl").setDescription("For me the best so far").setPhoneNumber(19027883L);

        InsuranceCompany insuranceCompany = new InsuranceCompany();
        insuranceCompany.setName("NewSafeLvl").setDescription("For me the best so far").setPhoneNumber(19027883L);

        Mockito.when(modelMapper.map(insuranceCompanyDTO, InsuranceCompany.class)).thenReturn(insuranceCompany);
        Mockito.when(modelMapper.map(insuranceCompany, InsuranceCompanyDTO.class)).thenReturn(insuranceCompanyDTO);
        Mockito.when(insuranceRepository.save(insuranceCompany)).thenReturn(insuranceCompany);

        //when
        InsuranceCompanyDTO savedInsuranceCompanyDTO = insuranceService.saveInsurenceCompany(insuranceCompanyDTO);

        //then
        Assertions.assertEquals(savedInsuranceCompanyDTO, insuranceCompanyDTO);
        Mockito.verify(insuranceRepository).save(insuranceCompany);
    }

    @Test
    public void whenSavingInsuranceCompanyAloneAlreadyExisting_shouldThrowException() {
        //given
        Integer id = 1;
        InsuranceCompany insuranceCompany = new InsuranceCompany();
        insuranceCompany.setName("NewSafeLvl").setDescription("For me the best so far").setPhoneNumber(19027883L);

        Mockito.when(insuranceRepository.findById(Long.valueOf(id))).thenReturn((Optional.of(insuranceCompany)));

        //when
        //then
        insuranceService.deleteInsuranceCompById(id);
        Mockito.verify(insuranceRepository).deleteById(insuranceCompany.getId());
    }

    @Test
    public void whenDeletingNotExistingInsuranceCompanyAlone_shouldThrowException() {
        //given
        Integer id = 1;
        InsuranceCompany insuranceCompany = new InsuranceCompany();

        Mockito.when(insuranceRepository.findById(Long.valueOf(id))).thenReturn((Optional.empty()));

        //when
        //then
        Assertions.assertThrows(NoSuchInsuranceCompanyException.class, () -> insuranceService.deleteInsuranceCompById(id));
    }

    @Test
    public void whenGetAllInsuranceCompaniesInvoked_shouldReturnListOfThose() {

        //given
        List<InsuranceCompany> insuranceCompanyList = new ArrayList<>();
        InsuranceCompany insuranceCompany = new InsuranceCompany();
        insuranceCompany.setName("NewSafeLvl").setDescription("For me the best so far").setPhoneNumber(19027883L);
        InsuranceCompany insuranceCompany2 = new InsuranceCompany();
        insuranceCompany.setName("BeingSafe").setDescription("Not bad, may be useful").setPhoneNumber(18101132L);
        insuranceCompanyList.add(insuranceCompany);
        insuranceCompanyList.add(insuranceCompany2);

        //when
        Mockito.when(insuranceRepository.getAllInsuranceCompanies()).thenReturn(insuranceCompanyList);

        //then
        Assertions.assertEquals(insuranceCompanyList, insuranceRepository.getAllInsuranceCompanies());
        Assertions.assertEquals(insuranceRepository.getAllInsuranceCompanies().size(), 2);
    }
}