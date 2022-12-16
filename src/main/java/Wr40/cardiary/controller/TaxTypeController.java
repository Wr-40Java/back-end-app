package Wr40.cardiary.controller;

import Wr40.cardiary.model.dto.TaxTypeDTO;
import Wr40.cardiary.model.entity.TaxType;
import Wr40.cardiary.service.TaxTypeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/taxtype")
public class TaxTypeController {

    TaxTypeService taxTypeService;

    ModelMapper modelMapper;
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public TaxType saveTaxType(@Valid @RequestBody TaxTypeDTO dto) {
        TaxType mappedTaxType = modelMapper.map(dto, TaxType.class);
        return taxTypeService.saveTaxType(mappedTaxType);
    }
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaxType getTaxType(@PathVariable Long id){
        return taxTypeService.getTaxTypeById(id);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public TaxType updateTaxType(@Valid @RequestBody TaxTypeDTO dto){
        TaxType mappedTaxType = modelMapper.map(dto, TaxType.class);
        return taxTypeService.updateTaxType(mappedTaxType);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTaxType(@PathVariable Long id) {
        taxTypeService.deleteTaxType(id);
    }
}
