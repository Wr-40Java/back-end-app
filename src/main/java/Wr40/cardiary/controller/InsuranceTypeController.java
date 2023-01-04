package Wr40.cardiary.controller;

import Wr40.cardiary.model.dto.insurance.InsuranceTypeDTO;
import Wr40.cardiary.service.InsuranceTypeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/insurancetype")
@AllArgsConstructor
public class InsuranceTypeController {

    private ModelMapper modelMapper;
    private InsuranceTypeService insuranceTypeService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public InsuranceTypeDTO saveInsuranceType(@Valid @RequestBody InsuranceTypeDTO insuranceTypeDTO) {
        return insuranceTypeService.saveInsuranceType(insuranceTypeDTO);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public InsuranceTypeDTO updateInsuranceType(@Valid @RequestBody InsuranceTypeDTO insuranceTypeDTO) {
        return insuranceTypeService.updateInsuranceType(insuranceTypeDTO);
//        return String.format("Congratulations, you updated %d row(s)", rowsUpdated);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<InsuranceTypeDTO> getInsuranceTypes() {
        return insuranceTypeService.getInsuranceTypes();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{type}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteInsuranceType(@PathVariable(name = "type") String type) {
        Integer deletedRows = insuranceTypeService.deleteInsuranceType(type);
        return String.format("Successfully deleted %d rows", deletedRows);
    }

}
