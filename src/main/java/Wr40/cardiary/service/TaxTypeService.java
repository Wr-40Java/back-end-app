package Wr40.cardiary.service;

import Wr40.cardiary.exception.NoSuchEntityFound;
import Wr40.cardiary.exception.TaxTypeAlreadyExistsException;
import Wr40.cardiary.model.entity.TaxType;
import Wr40.cardiary.repo.TaxTypeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Transactional
public class TaxTypeService {

    TaxTypeRepository taxTypeRepository;

    public TaxType saveTaxType(TaxType mappedTaxType) {
        if(taxTypeRepository.existsById(mappedTaxType.getId())){
            throw new TaxTypeAlreadyExistsException();
        }
        return taxTypeRepository.save(mappedTaxType);
    }

    public TaxType getTaxTypeById(Long id) {
        return taxTypeRepository.findById(id).orElseThrow(NoSuchEntityFound::new);
    }

    public TaxType updateTaxType(Long id, TaxType mappedTaxType) {
        TaxType foundTaxType = taxTypeRepository.findById(id).orElseThrow(NoSuchEntityFound::new);
        foundTaxType.setName(mappedTaxType.getName());
        foundTaxType.setDescription(mappedTaxType.getDescription());
        foundTaxType.setInstitutionToPayFor(mappedTaxType.getInstitutionToPayFor());
        foundTaxType.setInstitutionToPayForPhoneNumber(mappedTaxType.getInstitutionToPayForPhoneNumber());
        return taxTypeRepository.save(foundTaxType);
    }

    public void deleteTaxType(Long id) {
        TaxType taxType = taxTypeRepository.findById(id).orElseThrow(NoSuchEntityFound::new);
        taxTypeRepository.delete(taxType);
    }
}
