package Wr40.cardiary.service;

import Wr40.cardiary.exception.NoSuchInsuranceTypeException;
import Wr40.cardiary.model.dto.insurance.InsuranceTypeDTO;
import Wr40.cardiary.model.entity.InsuranceType;
import Wr40.cardiary.repo.InsuranceTypeRepository;
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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class InsuranceTypeServiceTest {

    @Mock
    private InsuranceTypeRepository insuranceTypeRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private InsuranceTypeService insuranceTypeService;

    @Test
    public void whenSavingNewInsuranceType_shouldReturnSavedObjectDTO() {

        //given
        InsuranceTypeDTO insuranceTypeDTO = new InsuranceTypeDTO();
        insuranceTypeDTO.setType("personal").setDescription("for hospital eventual costs")
                .setCoveredCompensation(BigDecimal.valueOf(40000)).setCostsPerYear(BigDecimal.valueOf(3000));

        InsuranceType insuranceType = new InsuranceType();
        insuranceType.setType("personal").setDescription("for hospital eventual costs")
                .setCoveredCompensation(BigDecimal.valueOf(40000)).setCostsPerYear(BigDecimal.valueOf(3000));

        Mockito.when(insuranceTypeRepository.save(insuranceType)).thenReturn(insuranceType);
        Mockito.when(modelMapper.map(insuranceType, InsuranceTypeDTO.class)).thenReturn(insuranceTypeDTO);
        Mockito.when(modelMapper.map(insuranceTypeDTO, InsuranceType.class)).thenReturn(insuranceType);

        //when
        //then
        InsuranceTypeDTO fromServiceInsuranceTypeDTO = insuranceTypeService.saveInsuranceType(insuranceTypeDTO);
        Mockito.verify(insuranceTypeRepository).save(insuranceType);
        Assertions.assertEquals(fromServiceInsuranceTypeDTO, insuranceTypeDTO);
    }

//    @Test
//    public void whenUpdatingInsuranceType_shouldReturnSavedObjectDTO() {
//
//        //given
//        Integer rowsToBeAffected = 1;
//        InsuranceTypeDTO newInsuranceTypeDTO = new InsuranceTypeDTO();
//        newInsuranceTypeDTO.setType("new - personal").setDescription("NEW - for family - hospital eventual costs")
//                .setCoveredCompensation(BigDecimal.valueOf(40000)).setCostsPerYear(BigDecimal.valueOf(3000));
//
//        InsuranceType newInsuranceType = new InsuranceType();
//        newInsuranceType.setType("new - personal").setDescription("NEW - for family - hospital eventual costs")
//                .setCoveredCompensation(BigDecimal.valueOf(40000)).setCostsPerYear(BigDecimal.valueOf(3000));
//
//        InsuranceType insuranceType = new InsuranceType();
//        insuranceType.setType("personal").setDescription("for hospital eventual costs")
//                .setCoveredCompensation(BigDecimal.valueOf(40000)).setCostsPerYear(BigDecimal.valueOf(3000));
//
//        Mockito.when(insuranceTypeRepository.findByType(newInsuranceTypeDTO.getType())).thenReturn(Optional.of(insuranceType));
//        Mockito.when(modelMapper.map(newInsuranceTypeDTO, InsuranceType.class)).thenReturn(newInsuranceType);
//        Mockito.when(insuranceTypeRepository.UpdateInsuranceTypeTuple(newInsuranceType.getDescription(), newInsuranceType.getCostsPerYear(),
//                newInsuranceType.getCoveredCompensation(), insuranceType.getType())).thenReturn(rowsToBeAffected);
//
//        //when
//        //then
//        Integer rowsAffected = insuranceTypeService.updateInsuranceType(newInsuranceTypeDTO);
//        Mockito.verify(insuranceTypeRepository).UpdateInsuranceTypeTuple(newInsuranceType.getDescription(), newInsuranceType.getCostsPerYear(),
//                newInsuranceType.getCoveredCompensation(), insuranceType.getType());
//        Assertions.assertEquals(rowsToBeAffected, rowsAffected);
//    }
//
//    @Test
//    public void whenUpdatingInsuranceTypeWithTypeNotExisting_shouldThrowException() {
//        InsuranceTypeDTO insuranceTypeDTO = new InsuranceTypeDTO();
//        insuranceTypeDTO.setType("testing");
//
//        Mockito.when(insuranceTypeRepository.findByType(insuranceTypeDTO.getType())).thenReturn(Optional.empty());
//
//        Assertions.assertThrows(NoSuchInsuranceTypeException.class, () -> insuranceTypeService.updateInsuranceType(insuranceTypeDTO));
//    }
    @Test
    public void whenGetAllInsuranceTypes_shouldReturnObjects() {
        InsuranceType insuranceType = new InsuranceType();
        insuranceType.setType("testing");
        InsuranceType insuranceType2 = new InsuranceType();
        insuranceType2.setType("testing");
        InsuranceType insuranceType3 = new InsuranceType();
        insuranceType3.setType("testing");
        ArrayList<InsuranceType> insTypes = new ArrayList<>();
        insTypes.add(insuranceType);
        insTypes.add(insuranceType2);
        insTypes.add(insuranceType3);

        InsuranceTypeDTO insuranceTypeDTO = new InsuranceTypeDTO();
        insuranceType.setType("testing");
        InsuranceTypeDTO insuranceType2DTO = new InsuranceTypeDTO();
        insuranceType2.setType("testing");
        InsuranceTypeDTO insuranceType3DTO = new InsuranceTypeDTO();
        insuranceType3.setType("testing");
        ArrayList<InsuranceTypeDTO> insTypeDTOs = new ArrayList<>();
        insTypeDTOs.add(insuranceTypeDTO);
        insTypeDTOs.add(insuranceType2DTO);
        insTypeDTOs.add(insuranceType3DTO);

        Mockito.when(insuranceTypeRepository.findAll()).thenReturn(insTypes);
        Mockito.when(modelMapper.map(insuranceType, InsuranceTypeDTO.class)).thenReturn(insuranceTypeDTO);
        Mockito.when(modelMapper.map(insuranceType2, InsuranceTypeDTO.class)).thenReturn(insuranceType2DTO);
        Mockito.when(modelMapper.map(insuranceType3, InsuranceTypeDTO.class)).thenReturn(insuranceType3DTO);

        //when
        List<InsuranceTypeDTO> ireturnedFromServiceIsuranceTypes = insuranceTypeService.getInsuranceTypes();

        //then
        Mockito.verify(insuranceTypeRepository).findAll();
        Assertions.assertEquals(insTypeDTOs, ireturnedFromServiceIsuranceTypes);
    }

    @Test
    public void whenDeleteInsuranceType_shouldReturnNrOfRowsDeleted() {
        String typeToDelete = "deletion";
        Integer nrOfRowsDeleted = 1;

        Mockito.when(insuranceTypeRepository.findByType(typeToDelete)).thenReturn(Optional.of(new InsuranceType()));
        Mockito.when(insuranceTypeRepository.deleteByType(typeToDelete)).thenReturn(nrOfRowsDeleted);

        //when
        Integer nrOfDeletedRows = insuranceTypeService.deleteInsuranceType(typeToDelete);

        //then
        Mockito.verify(insuranceTypeRepository).findByType(typeToDelete);
        Mockito.verify(insuranceTypeRepository).deleteByType(typeToDelete);
        Assertions.assertEquals(nrOfRowsDeleted, nrOfDeletedRows);
    }

    @Test
    public void whenDeletingInsuranceTypeWithTypeNotExisting_shouldThrowException() {
        InsuranceTypeDTO insuranceTypeDTO = new InsuranceTypeDTO();
        insuranceTypeDTO.setType("testing");

        Mockito.when(insuranceTypeRepository.findByType(insuranceTypeDTO.getType())).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchInsuranceTypeException.class, () -> insuranceTypeService.deleteInsuranceType(insuranceTypeDTO.getType()));
    }


}