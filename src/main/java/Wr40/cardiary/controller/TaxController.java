package Wr40.cardiary.controller;

import Wr40.cardiary.model.dto.TaxDTO;
import Wr40.cardiary.model.entity.Tax;
import Wr40.cardiary.service.TaxService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/tax")
public class TaxController {

    TaxService taxService;
    ModelMapper modelMapper;
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Tax saveTax(@Valid @RequestBody TaxDTO dto) {
        Tax mappedTax = modelMapper.map(dto, Tax.class);
        return taxService.saveTax(mappedTax);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Tax getTax(@PathVariable Long id) {
        return taxService.getTax(id);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<Tax> getAllTaxes() {
        return taxService.getAllTaxes();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTax(@PathVariable Long id){
        taxService.deleteTax(id);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllTaxes(){
        taxService.deleteAllTaxes();
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public Tax updateTax(@Valid @RequestBody Tax tax){
        return taxService.updateTax(tax);
    }

    @PostMapping("/link/{tax_id}/{tax_type_id}")
    @ResponseStatus(HttpStatus.OK)
    public Tax linkTaxTypeToTax(
            @PathVariable(name = "tax_id") Long taxId,
            @PathVariable(name = "tax_type_id") Long taxTypeId
    ) {
        return taxService.linkTaxTypeToTax(taxId, taxTypeId);
    }
}
