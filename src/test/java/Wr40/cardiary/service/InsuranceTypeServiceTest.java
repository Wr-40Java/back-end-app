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

    @Test
    public void whenUpdatingInsuranceType_shouldReturnSavedObjectDTO() {

        //given
        Integer rowsToBeAffected = 1;
        InsuranceTypeDTO newInsuranceTypeDTO = new InsuranceTypeDTO();
        newInsuranceTypeDTO.setType("new - personal").setDescription("NEW - for family - hospital eventual costs")
                .setCoveredCompensation(BigDecimal.valueOf(40000)).setCostsPerYear(BigDecimal.valueOf(3000));

        InsuranceType newInsuranceType = new InsuranceType();
        newInsuranceType.setType("new - personal").setDescription("NEW - for family - hospital eventual costs")
                .setCoveredCompensation(BigDecimal.valueOf(40000)).setCostsPerYear(BigDecimal.valueOf(3000));

        InsuranceType insuranceType = new InsuranceType();
        insuranceType.setType("personal").setDescription("for hospital eventual costs")
                .setCoveredCompensation(BigDecimal.valueOf(40000)).setCostsPerYear(BigDecimal.valueOf(3000));

        Mockito.when(insuranceTypeRepository.findByType(newInsuranceTypeDTO.getType())).thenReturn(Optional.of(insuranceType));
        Mockito.when(modelMapper.map(newInsuranceTypeDTO, InsuranceType.class)).thenReturn(newInsuranceType);
        Mockito.when(insuranceTypeRepository.UpdateInsuranceTypeTuple(newInsuranceType.getDescription(), newInsuranceType.getCostsPerYear(),
                newInsuranceType.getCoveredCompensation(), insuranceType.getType())).thenReturn(rowsToBeAffected);

        //when
        //then
        Integer rowsAffected = insuranceTypeService.updateInsuranceType(newInsuranceTypeDTO);
        Mockito.verify(insuranceTypeRepository).UpdateInsuranceTypeTuple(newInsuranceType.getDescription(), newInsuranceType.getCostsPerYear(),
                newInsuranceType.getCoveredCompensation(), insuranceType.getType());
        Assertions.assertEquals(rowsToBeAffected, rowsAffected);
    }

    @Test
    public void whenUpdatingInsuranceTypeWithTypeNotExisting_shouldThrowException() {
        InsuranceTypeDTO insuranceTypeDTO = new InsuranceTypeDTO();
        insuranceTypeDTO.setType("testing");

        Mockito.when(insuranceTypeRepository.findByType(insuranceTypeDTO.getType())).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchInsuranceTypeException.class, () -> insuranceTypeService.updateInsuranceType(insuranceTypeDTO));
    }

}