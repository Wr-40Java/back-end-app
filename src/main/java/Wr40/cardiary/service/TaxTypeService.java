package Wr40.cardiary.service;

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
}
