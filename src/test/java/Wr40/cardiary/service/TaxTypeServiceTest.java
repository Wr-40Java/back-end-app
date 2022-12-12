package Wr40.cardiary.service;


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

    @Test
    public void whenUpdateTaxType_shouldUpdateGivenTaxType(){
        TaxType taxType = new TaxType();
        TaxType taxType1 = new TaxType();
        taxType1.setDescription("Something");
        Mockito.when(taxTypeRepository.findById(taxType.getId())).thenReturn(Optional.of(taxType));
        Mockito.when(taxTypeRepository.save(taxType)).thenReturn(taxType1);
        TaxType updatedTaxType = taxTypeService.updateTaxType(taxType1);
        Assertions.assertEquals("Something",updatedTaxType.getDescription());
    }

    @Test
    public void whenDeleteTaxType_givenWrongId_shouldThrowException(){
        TaxType taxType = new TaxType();
        Mockito.when(taxTypeRepository.findById(taxType.getId())).thenThrow(new NoSuchEntityFound());
        Assertions.assertThrows(NoSuchEntityFound.class, () -> taxTypeService.deleteTaxType(taxType.getId()));
    }
}
