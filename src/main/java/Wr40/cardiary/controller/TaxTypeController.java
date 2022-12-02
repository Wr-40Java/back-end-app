package Wr40.cardiary.controller;

import Wr40.cardiary.model.dto.TaxTypeDTO;
import Wr40.cardiary.model.entity.TaxType;
import Wr40.cardiary.service.TaxTypeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cardiary/taxtype")
public class TaxTypeController {

    TaxTypeService taxTypeService;

    ModelMapper modelMapper;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public TaxType saveTaxType(@Valid @RequestBody TaxTypeDTO dto) {
        TaxType mappedTaxType = modelMapper.map(dto, TaxType.class);
        return taxTypeService.saveTaxType(mappedTaxType);
    }

    @PostMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaxType getTaxType(@PathVariable Long id){
        return taxTypeService.getTaxTypeById(id);
    }
}
