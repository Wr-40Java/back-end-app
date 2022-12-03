package Wr40.cardiary.controller;

import Wr40.cardiary.model.dto.TaxDTO;
import Wr40.cardiary.model.entity.Tax;
import Wr40.cardiary.service.TaxService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cardiary/tax")
public class TaxController {

    TaxService taxService;
    ModelMapper modelMapper;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Tax saveTax(@Valid @RequestBody TaxDTO dto) {
        Tax mappedTax = modelMapper.map(dto, Tax.class);
        return taxService.saveTax(mappedTax);
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Tax getTax(@PathVariable Long id) {
        return taxService.getTax(id);
    }

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<Tax> getAllTaxes() {
        return taxService.getAllTaxes();
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTax(@PathVariable Long id){
        taxService.deleteTax(id);
    }

    @DeleteMapping("/delete/all")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllTaxes(){
        taxService.deleteAllTaxes();
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Tax updateTax(@PathVariable Long id, @Valid @RequestBody TaxDTO dto){
        Tax mappedTax = modelMapper.map(dto, Tax.class);
        return taxService.updateTax(id, mappedTax);
    }
}
