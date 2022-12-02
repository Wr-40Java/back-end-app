package Wr40.cardiary.test.service;


import Wr40.cardiary.exception.NoSuchEntityFound;
import Wr40.cardiary.exception.TaxTypeAlreadyExistsException;
import Wr40.cardiary.model.entity.TaxType;
import Wr40.cardiary.repo.TaxTypeRepository;
import Wr40.cardiary.service.TaxTypeService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class TaxTypeServiceTest {

    @Mock
    TaxTypeRepository taxTypeRepository;

    @InjectMocks
    TaxTypeService taxTypeService;

    @Test
    public void whenSave_shouldSaveCar(){
        TaxType taxType = new TaxType();
        Mockito.when(taxTypeRepository.save(taxType)).thenReturn(taxType);
        TaxType savedTaxType = taxTypeService.saveTaxType(taxType);
        Assertions.assertEquals(taxType,savedTaxType);
        Mockito.verify(taxTypeRepository).save(taxType);
    }

    @Test
    public void whenSaving_shouldNotSaveAlreadySavedTaxType_throwsException(){
        TaxType taxType = new TaxType();
        Mockito.when(taxTypeRepository.save(taxType)).thenThrow(new TaxTypeAlreadyExistsException());
        Assertions.assertThrows(TaxTypeAlreadyExistsException.class, () -> taxTypeService.saveTaxType(taxType));
        Mockito.verify(taxTypeRepository).save(taxType);
    }

    @Test
    public void whenFindingById_shouldGetTaxTypeWithGivenId(){
        TaxType taxType = new TaxType();
        Mockito.when(taxTypeRepository.findById(taxType.getId())).thenReturn(Optional.of(taxType));
        TaxType taxType1 = taxTypeService.getTaxTypeById(taxType.getId());
        Assertions.assertEquals(taxType,taxType1);
    }

    @Test
    public void whenFindingById_shouldNotGetTaxTypeWithWrongGivenId_throwsException(){
        TaxType taxType = new TaxType();
        Mockito.when(taxTypeRepository.findById(taxType.getId())).thenThrow(new NoSuchEntityFound());
        Assertions.assertThrows(NoSuchEntityFound.class, () -> taxTypeService.getTaxTypeById(taxType.getId()));
    }
}
