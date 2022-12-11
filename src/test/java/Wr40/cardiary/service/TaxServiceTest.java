package Wr40.cardiary.service;

import Wr40.cardiary.exception.NoSuchEntityFound;
import Wr40.cardiary.exception.TaxNotFoundException;
import Wr40.cardiary.model.entity.*;
import Wr40.cardiary.repo.TaxRepository;
import Wr40.cardiary.repo.TaxTypeRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TaxServiceTest {

    @Mock
    private TaxRepository taxRepository;
    @Mock
    private TaxTypeRepository taxTypeRepository;
    @InjectMocks
    private TaxService taxService;

    @Test
    public void whenSavingTaxShouldSave(){
        Tax tax = new Tax();
        Mockito.when(taxRepository.save(tax)).thenReturn(tax);
        Tax savedTax = taxService.saveTax(tax);
        Assertions.assertEquals(savedTax,tax);
        verify(taxRepository).save(tax);
    }

    @Test
    public void shouldFindTaxWithGivenValidId(){
        Tax tax = new Tax();
        Mockito.when(taxRepository.findById(tax.getId())).thenReturn(Optional.of(tax));
        Tax tax2 = taxService.getTax(tax.getId());
        Assertions.assertEquals(tax,tax2);
        verify(taxRepository).findById(tax.getId());
    }

    @Test
    public void shouldThrowExceptionFotTaxWithGivenInvalidId(){
        Tax tax = new Tax();
        Mockito.when(taxRepository.findById(tax.getId())).thenThrow(new TaxNotFoundException());
        Assertions.assertThrows(TaxNotFoundException.class, () -> taxService.getTax(tax.getId()));
        verify(taxRepository).findById(tax.getId());
    }

    @Test
    public void shouldFindListWithTwoTaxes(){
        List<Tax> list = new ArrayList<>();
        list.add(new Tax());
        list.add(new Tax());
        Mockito.when(taxRepository.findAll()).thenReturn(list);
        List<Tax> allTaxes = taxService.getAllTaxes();
        Assertions.assertEquals(2,allTaxes.size());
    }

    @Test
    public void shouldFindEmptyListOfTaxes(){
        List<Tax> list = new ArrayList<>();
        Mockito.when(taxRepository.findAll()).thenReturn(list);
        List<Tax> allTaxes = taxService.getAllTaxes();
        Assertions.assertEquals(0,allTaxes.size());
    }

    @Test
    public void withValidIdShouldDeleteTax(){
        Tax tax = new Tax();
        Mockito.when(taxRepository.findById(tax.getId())).thenReturn(Optional.of(tax));
        taxService.deleteTax(tax.getId());
        verify(taxRepository).delete(tax);
    }

    @Test
    public void withInvalidTaxIdShouldThrowException(){
        Tax tax = new Tax();
        Mockito.when(taxRepository.findById(tax.getId())).thenThrow(new TaxNotFoundException());
        Assertions.assertThrows(TaxNotFoundException.class, () -> taxService.deleteTax(tax.getId()));
    }

    @Test
    public void shouldDeleteAllTaxes(){
        taxService.deleteAllTaxes();
        verify(taxRepository).deleteAll();
    }

    @Test
    public void shouldUpdateTaxWithGivenId(){
        Tax tax = new Tax();
        Tax tax2 = new Tax();
        tax2.setCostOfTransaction(BigDecimal.valueOf(50));
        Mockito.when(taxRepository.findById(tax.getId())).thenReturn(Optional.of(tax));
        Mockito.when(taxRepository.save(tax)).thenReturn(tax2);
        Tax updatedCar = taxService.updateTax(tax.getId(), tax);
        Assertions.assertEquals(BigDecimal.valueOf(50),updatedCar.getCostOfTransaction());
    }

    @Test
    public void whenLinkingNotExistingTax_ShouldThrowException() {
        Mockito.when(taxRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(TaxNotFoundException.class, () -> taxService.linkTaxTypeToTax(1L, 1L));
    }

    @Test
    public void whenLinkingInsuranceCompanyNotExisting_shouldThrowException() {
        Mockito.when(taxRepository.findById(1L)).thenReturn(Optional.of(new Tax()));
        Mockito.when(taxTypeRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchEntityFound.class, () -> taxService.linkTaxTypeToTax(1L, 1L));
    }
}


