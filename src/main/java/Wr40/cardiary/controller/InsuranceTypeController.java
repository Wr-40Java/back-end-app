package Wr40.cardiary.controller;

import Wr40.cardiary.model.dto.insurance.InsuranceCompanyWithTypeDTO;
import Wr40.cardiary.model.dto.insurance.InsuranceTypeDTO;
import Wr40.cardiary.model.entity.InsuranceCompany;
import Wr40.cardiary.model.entity.InsuranceType;
import Wr40.cardiary.service.InsuranceService;
import Wr40.cardiary.service.InsuranceTypeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cardiary/insurancetype")
@AllArgsConstructor
public class InsuranceTypeController {

    private ModelMapper modelMapper;
    private InsuranceTypeService insuranceTypeService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public InsuranceTypeDTO saveInsuranceType(@Valid @RequestBody InsuranceTypeDTO insuranceTypeDTO) {
        return insuranceTypeService.saveInsuranceType(insuranceTypeDTO);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public String updateInsuranceType(@Valid @RequestBody InsuranceTypeDTO insuranceTypeDTO) {
        Integer rowsUpdated = insuranceTypeService.updateInsuranceType(insuranceTypeDTO);
        return String.format("Congratulations, you updated %d row(s)", rowsUpdated);
    }

}
