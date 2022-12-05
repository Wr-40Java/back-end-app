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
    public InsuranceTypeDTO saveInsuranceCompanyWithTypeAndCar(@Valid @RequestBody InsuranceTypeDTO insuranceTypeDTO) {
        InsuranceType insuranceType = modelMapper.map(insuranceTypeDTO, InsuranceType.class);
        return insuranceTypeService.saveInsuranceType(insuranceTypeDTO);
    }
}
