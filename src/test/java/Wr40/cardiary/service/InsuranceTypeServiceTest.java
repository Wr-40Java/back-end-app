package Wr40.cardiary.service;

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

}