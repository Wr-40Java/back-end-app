package Wr40.cardiary.service;

import Wr40.cardiary.exception.NoSuchEntityFound;
import Wr40.cardiary.exception.TaxNotFoundException;
import Wr40.cardiary.model.entity.Tax;
import Wr40.cardiary.model.entity.TaxType;
import Wr40.cardiary.repo.TaxRepository;
import Wr40.cardiary.repo.TaxTypeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class TaxService {

    private TaxRepository taxRepository;
    private TaxTypeRepository taxTypeRepository;


    @Autowired
    public TaxService(TaxRepository taxRepository) {
        this.taxRepository = taxRepository;
    }

    public Tax saveTax(Tax tax) {
        return taxRepository.save(tax);
    }

    public Tax getTax(Long id) {
        return taxRepository.findById(id).orElseThrow(TaxNotFoundException::new);
    }

    public List<Tax> getAllTaxes() {
        return taxRepository.findAll();
    }

    public void deleteTax(Long id) {
        Tax tax = taxRepository.findById(id).orElseThrow(TaxNotFoundException::new);
        taxRepository.delete(tax);
    }

    public void deleteAllTaxes() {
        taxRepository.deleteAll();
    }

    public Tax updateTax(Long id, Tax tax) {
        Tax taxToUpdate = taxRepository.findById(id).orElseThrow(TaxNotFoundException::new);
        taxToUpdate.setCostOfTransaction(tax.getCostOfTransaction());
        return taxRepository.save(taxToUpdate);
    }

    public Tax linkTaxTypeToTax(Long taxId, Long taxTypeId) {
        Tax tax = taxRepository.findById(taxId).orElseThrow(TaxNotFoundException::new);
        TaxType taxType = taxTypeRepository.findById(taxTypeId).orElseThrow(NoSuchEntityFound::new);

        Tax updatedTax = new Tax();
        updatedTax.setCostOfTransaction(tax.getCostOfTransaction());
        updatedTax.setTaxType(taxType);
        return taxRepository.save(updatedTax);
    }
}
