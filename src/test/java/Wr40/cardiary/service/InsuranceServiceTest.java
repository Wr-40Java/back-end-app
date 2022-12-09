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
import org.checkerframework.checker.units.qual.C;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;

@RunWith(MockitoJUnitRunner.class)
public class InsuranceServiceTest {

    @Mock
    private CarRepository carRepository;
    @Mock
    private InsuranceRepository insuranceRepository;

    @Mock
    private InsuranceTypeRepository insuranceTypeRepository;
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
    public void whenDeletingInsuranceCompanyAlone_shouldDeleteObject() {
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

    @Test
    public void whenUpdatingInsuranceCompanyAlone_shouldReturnUpdatedObjectMappedToDTO() {
        //given
        InsuranceCompanyDTO insuranceCompanyDTO = new InsuranceCompanyDTO();
        insuranceCompanyDTO.setName("Changed").setDescription("Changed description").setPhoneNumber(100000000L);

        InsuranceCompany insuranceCompany = new InsuranceCompany();
        insuranceCompany.setId(1L).setName("NewSafeLvl").setDescription("For me the best so far").setPhoneNumber(19027883L);

        Mockito.when(modelMapper.map(insuranceCompanyDTO, InsuranceCompany.class)).thenReturn(insuranceCompany);
        Mockito.when(modelMapper.map(insuranceCompany, InsuranceCompanyDTO.class)).thenReturn(insuranceCompanyDTO);
        Mockito.when(insuranceRepository.save(insuranceCompany)).thenReturn(insuranceCompany);

        //when
        InsuranceCompanyDTO savedInsuranceCompanyDTO = insuranceService.saveInsurenceCompany(insuranceCompanyDTO);

        //then
        Assertions.assertEquals(savedInsuranceCompanyDTO.getDescription(), insuranceCompanyDTO.getDescription());
        org.assertj.core.api.Assertions.assertThat(savedInsuranceCompanyDTO.getDescription()).containsSubsequence("Changed description");
        org.assertj.core.api.Assertions.assertThat(savedInsuranceCompanyDTO.getName()).containsSubsequence("Changed");
        Mockito.verify(insuranceRepository).save(insuranceCompany);
    }

    @Test
    public void whenUpdatingInsuranceCompanyAloneNotExisting_shouldThrowException() {
        //given
        Integer id = 1;
        InsuranceCompanyDTO insuranceCompanyDTO = new InsuranceCompanyDTO();
        insuranceCompanyDTO.setName("Changed").setDescription("Changed description").setPhoneNumber(100000000L);

        //when
        Mockito.when(insuranceRepository.findById(Long.valueOf(id))).thenReturn(Optional.empty());

        //then
        Assertions.assertThrows(NoSuchInsuranceCompanyException.class, () -> insuranceService.updateInsuranceCompany(insuranceCompanyDTO, id));

    }

    @Test
    public void whenGetAllInsuranceCompaniesWithTypeForCarInvoked_shouldReturnListOfThose() {

        //given
        List<InsuranceCompany> insuranceCompanyList = new ArrayList<>();
        List<InsuranceType> insuranceTypeList = new ArrayList<>();

        InsuranceCompany insuranceCompany = new InsuranceCompany();
        insuranceCompany.setName("NewSafeLvl").setDescription("For me the best so far").setPhoneNumber(19027883L);
        InsuranceCompany insuranceCompany2 = new InsuranceCompany();
        insuranceCompany.setName("BeingSafe").setDescription("Not bad, may be useful").setPhoneNumber(18101132L);

        InsuranceType insuranceType = new InsuranceType();
        insuranceType.setType("AUTOCASCO").setDescription("obligatory").setCoveredCompensation(BigDecimal.valueOf(200_000)).setCostsPerYear(BigDecimal.valueOf(2000));
        InsuranceType insuranceType2 = new InsuranceType();
        insuranceType2.setType("AUTOCASCO").setDescription("obligatory").setCoveredCompensation(BigDecimal.valueOf(200_000)).setCostsPerYear(BigDecimal.valueOf(2000));

        insuranceCompany.setInsuranceType(insuranceType);
        insuranceCompany2.setInsuranceType(insuranceType2);

        insuranceCompanyList.add(insuranceCompany);
        insuranceCompanyList.add(insuranceCompany2);

        //when
        Mockito.when(insuranceRepository.getAllInsuranceCompanies()).thenReturn(insuranceCompanyList);

        //then
        Assertions.assertEquals(insuranceCompanyList, insuranceRepository.getAllInsuranceCompanies());
        Assertions.assertEquals(insuranceRepository.getAllInsuranceCompanies().size(), 2);
    }

//    @Test
//    public void whenLinkingCarInsuranceCompanyAndType_shouldLinkEntitesAndSave() {
//
//        //given
//        String VINNumber = "SRC1000";
//        Long InsCompId = 1L;
//        Long InsTypeId = 1L;
//        Car car = new Car();
//        InsuranceTypeDTO insuranceTypeDTO = new InsuranceTypeDTO();
//        insuranceTypeDTO.setType("Collision insurance").setDescription("Keep me solvable in case something happend on the road").setCostsPerYear(BigDecimal.valueOf(900))
//                .setCoveredCompensation(BigDecimal.valueOf(200_000));
//
//        InsuranceCompanyWithTypeDTO insuranceCompanyWithTypeDTO = new InsuranceCompanyWithTypeDTO();
//        insuranceCompanyWithTypeDTO.setName("NewSafeLvl").setDescription("For me the best so far").setPhoneNumber(19027883L).setInsuranceTypeDTO(insuranceTypeDTO);
//
//        InsuranceType insuranceType = new InsuranceType();
//        insuranceType.setType("Collision insurance").setDescription("Keep me solvable in case something happend on the road").setCostsPerYear(BigDecimal.valueOf(900));
//
//        InsuranceCompany insuranceCompany = new InsuranceCompany();
//        insuranceCompany.setName("NewSafeLvl").setDescription("For me the best so far").setPhoneNumber(19027883L).setInsuranceType(insuranceType);
//        car.addInsuranceCompany(insuranceCompany);
//
//        Mockito.when(carRepository.findByVINnumber(VINNumber)).thenReturn(Optional.of(car));
//        Mockito.when(insuranceRepository.findById(InsCompId)).thenReturn((Optional.of(insuranceCompany)));
//        Mockito.when(insuranceTypeRepository.findById(InsTypeId)).thenReturn((Optional.of(insuranceType)));
//
//        HashSet<InsuranceCompany> objects = new HashSet<>();
//        insuranceCompany.setInsuranceType(insuranceType);
//        objects.add(insuranceCompany);
//        car.setInsuranceCompanies(objects);
//        Mockito.when(carRepository.save(car)).thenReturn(car);
//
//        Mockito.when(modelMapper.map(insuranceCompany, InsuranceCompanyWithTypeDTO.class)).thenReturn(insuranceCompanyWithTypeDTO);
//        Mockito.when(modelMapper.map(insuranceType, InsuranceTypeDTO.class)).thenReturn(insuranceTypeDTO);
//
//        //when
//        InsuranceCompanyWithTypeDTO savedInsuranceCompanyWithTypeDTO = insuranceService.linkCarWithInsuranceCompanyAndInsuranceType(VINNumber, 1, 1);
//
//        //then
//        Assertions.assertEquals(savedInsuranceCompanyWithTypeDTO, insuranceCompanyWithTypeDTO);
//        Mockito.verify(carRepository).save(car);
//        Mockito.verify(insuranceTypeRepository).findById(InsTypeId);
//        Mockito.verify(insuranceRepository).findById(InsCompId);
//    }

    @Test
    public void whenLinkingCarNotExisting_shouldThrowException() {

        //given
        String VINNumber = "SRC1000";
        Mockito.when(carRepository.findByVINnumber(VINNumber)).thenReturn(Optional.empty());

        //when
        //then
        Assertions.assertThrows(NoSuchCarFoundException.class, () -> insuranceService.linkCarWithInsuranceCompanyAndInsuranceType(VINNumber, 1, 1));
    }
    @Test
    public void whenLinkingInsuranceCompanyNotExisting_shouldThrowException() {

        //given
        String VINNumber = "SRC1000";
        Mockito.when(carRepository.findByVINnumber("SRC1000")).thenReturn(Optional.of(new Car()));
        Mockito.when(insuranceRepository.findById(1L)).thenReturn(Optional.empty());

        //when
        //then
        Assertions.assertThrows(NoSuchInsuranceCompanyException.class, () -> insuranceService.linkCarWithInsuranceCompanyAndInsuranceType(VINNumber, 1, 1));
    }
    @Test
    public void whenLinkingInsuranceTypeNotExisting_shouldThrowException() {

        //given
        String VINNumber = "SRC1000";
        Mockito.when(carRepository.findByVINnumber("SRC1000")).thenReturn(Optional.of(new Car()));
        Mockito.when(insuranceRepository.findById(1L)).thenReturn(Optional.of(new InsuranceCompany()));
        Mockito.when(insuranceTypeRepository.findById(1L)).thenReturn(Optional.empty());

        //when
        //then
        Assertions.assertThrows(NoSuchInsuranceTypeException.class, () -> insuranceService.linkCarWithInsuranceCompanyAndInsuranceType(VINNumber, 1, 1));
    }

    @Test
    public void whenDeletingLinkedCarInsuranceCompanyType_shouldReturnPositiveInfo() {
        //given
        String VINNumber = "SRC1000";

        InsuranceCompany insuranceCompany = new InsuranceCompany();
        insuranceCompany.setId(1L);
        Car car = new Car();
        car.setInsuranceCompanies(new HashSet<>());
        car.addInsuranceCompany(insuranceCompany);

        Mockito.when(carRepository.findByVINnumber(VINNumber)).thenReturn((Optional.of(car)));

        //when
        //then
        insuranceService.deleteLinkInsuranceCompanyWithTypeAndCar(VINNumber, 1, 1);
        Mockito.verify(insuranceRepository).deleteById(Long.valueOf(1));
    }

    @Test
    public void whenDeletingLinkingInsuranceTypeWhenGivenCarNotExisting_shouldThrowException() {

        //given
        String VINNumber = "SRC1000";
        Mockito.when(carRepository.findByVINnumber(VINNumber)).thenReturn(Optional.empty());

        //when
        //then
        Assertions.assertThrows(NoSuchCarFoundException.class, () -> insuranceService.deleteLinkInsuranceCompanyWithTypeAndCar(VINNumber, 1, 1));
    }

    @Test
    public void whenUpdatingLinkingCarInsuranceCompanyAndType_shouldAssignEntitesAndSave() {

        //given
        String VINNumber = "SRC1000";
        Long OldInsCompId = 1L;
        Long InsCompId = 10L;
        Long InsTypeId = 5L;
        InsuranceType insuranceType = new InsuranceType();
        insuranceType.setType("Collision insurance").setDescription("Keep me solvable in case something happend on the road").setCostsPerYear(BigDecimal.valueOf(900))
                .setCoveredCompensation(BigDecimal.valueOf(200_000));

        InsuranceType insuranceTypeToAssignToCar = new InsuranceType();
        insuranceTypeToAssignToCar.setType("Collision insurance").setDescription("Keep me solvable in case something happend on the road").setCostsPerYear(BigDecimal.valueOf(900));

        InsuranceCompany insuranceCompany = new InsuranceCompany();
        insuranceCompany.setName("NewSafeLvl").setDescription("For me the best so far").setPhoneNumber(19027883L).setInsuranceType(insuranceType);

        InsuranceCompany insuranceCompanyToAssignToCar = new InsuranceCompany();
        insuranceCompanyToAssignToCar.setName("NewSafeLvl").setDescription("For me the best so far").setPhoneNumber(19027883L).setInsuranceType(insuranceType);

        insuranceCompany.setInsuranceType(insuranceType);
        insuranceCompanyToAssignToCar.setInsuranceType(insuranceTypeToAssignToCar);

        Car car = new Car();
        car.addInsuranceCompany(insuranceCompany);

        Mockito.when(carRepository.findByVINnumber(VINNumber)).thenReturn(Optional.of(car));
        Mockito.when(insuranceRepository.findById(OldInsCompId)).thenReturn((Optional.of(insuranceCompany)));

        Car carNewVersion = new Car();
        carNewVersion.addInsuranceCompany(insuranceCompanyToAssignToCar);

        Mockito.when(insuranceRepository.findById(InsCompId)).thenReturn((Optional.of(insuranceCompanyToAssignToCar)));
        Mockito.when(insuranceTypeRepository.findById(InsTypeId)).thenReturn((Optional.of(insuranceTypeToAssignToCar)));

        Mockito.when(insuranceRepository.save(insuranceCompanyToAssignToCar)).thenReturn(insuranceCompanyToAssignToCar);

        InsuranceTypeDTO insuranceTypeToAssignToCarDTO = new InsuranceTypeDTO();
        insuranceTypeToAssignToCarDTO.setType("Collision insurance").setDescription("Keep me solvable in case something happend on the road").setCostsPerYear(BigDecimal.valueOf(900));
        InsuranceCompanyWithTypeDTO insuranceCompanyToAssignToCarDTO = new InsuranceCompanyWithTypeDTO();
        insuranceCompanyToAssignToCarDTO.setName("NewSafeLvl").setDescription("For me the best so far").setPhoneNumber(19027883L).setInsuranceTypeDTO(insuranceTypeToAssignToCarDTO);
        Mockito.when(modelMapper.map(insuranceCompanyToAssignToCar, InsuranceCompanyWithTypeDTO.class)).thenReturn(insuranceCompanyToAssignToCarDTO);
        Mockito.when(modelMapper.map(insuranceCompanyToAssignToCar.getInsuranceType(), InsuranceTypeDTO.class)).thenReturn(insuranceTypeToAssignToCarDTO);

        //when
        InsuranceCompanyWithTypeDTO updatedInsuranceCompanyWithTypeDTO = insuranceService
                .updateLinkInsuranceCompanyWithTypeAndCar(VINNumber, OldInsCompId.intValue(),InsCompId.intValue(),InsTypeId.intValue());

        //then
        Assertions.assertEquals(updatedInsuranceCompanyWithTypeDTO, insuranceCompanyToAssignToCarDTO);
        Mockito.verify(insuranceRepository).save(insuranceCompanyToAssignToCar);
        Mockito.verify(insuranceTypeRepository).findById(InsTypeId);
        Mockito.verify(insuranceRepository).findById(InsCompId);
    }

    @Test
    public void whenUpdatingLinkingInsuranceTypeWhenGivenCarNotExisting_shouldThrowException() {

        //given
        String VINNumber = "SRC1000";
        Mockito.when(carRepository.findByVINnumber(VINNumber)).thenReturn(Optional.empty());

        //when
        //then
        Assertions.assertThrows(NoSuchCarFoundException.class, () -> insuranceService.updateLinkInsuranceCompanyWithTypeAndCar(VINNumber, 1, 1, 1));
    }
    @Test
    public void whenUpdatingLinkingInsuranceTypeWhenGivenCarNotExisting_shouldThrowException2() {

        //given
        String VINNumber = "SRC1000";
        Mockito.when(carRepository.findByVINnumber(VINNumber)).thenReturn(Optional.of(new Car()));
        Mockito.when(insuranceRepository.findById(1L)).thenReturn(Optional.empty());

        //when
        //then
        Assertions.assertThrows(NoSuchInsuranceCompanyException.class, () -> insuranceService.updateLinkInsuranceCompanyWithTypeAndCar(VINNumber, 1, 1, 1));
    }
    @Test
    public void whenUpdatingLinkingInsuranceTypeWhenGivenCarNotExisting_shouldThrowException3() {

        //given
        String VINNumber = "SRC1000";
        Mockito.when(carRepository.findByVINnumber(VINNumber)).thenReturn(Optional.of(new Car()));
        Mockito.when(insuranceRepository.findById(1L)).thenReturn(Optional.of(new InsuranceCompany()));
        Mockito.when(insuranceTypeRepository.findById(1L)).thenReturn(Optional.empty());

        //when
        //then
        Assertions.assertThrows(NoSuchInsuranceTypeException.class, () -> insuranceService.updateLinkInsuranceCompanyWithTypeAndCar(VINNumber, 1, 1, 1));
    }


}