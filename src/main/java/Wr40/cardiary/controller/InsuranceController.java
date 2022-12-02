package Wr40.cardiary.controller;

import Wr40.cardiary.model.dto.insurance.InsuranceCompanyDTO;
import Wr40.cardiary.model.dto.insurance.InsuranceCompanyWithTypeDTO;
import Wr40.cardiary.model.entity.InsuranceCompany;
import Wr40.cardiary.service.InsuranceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cardiary/insurance")
@AllArgsConstructor
public class InsuranceController {

    private ModelMapper modelMapper;
    private InsuranceService insuranceService;

    @PostMapping("/save/{vin_number}")
    @ResponseStatus(HttpStatus.CREATED)
    public InsuranceCompanyWithTypeDTO saveInsuranceCompanyWithTypeAndCar(@PathVariable(name = "vin_number", required = false) String vinNumber,
                                                                          @Valid @RequestBody InsuranceCompanyWithTypeDTO insuranceCompanyWithTypeDTO) {
        InsuranceCompany insuranceCompany = modelMapper.map(insuranceCompanyWithTypeDTO, InsuranceCompany.class);
        return insuranceService.saveInsuranceWithTypeToTheCar(insuranceCompanyWithTypeDTO, vinNumber);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public InsuranceCompanyDTO saveInsuranceCompany(@Valid @RequestBody InsuranceCompanyDTO insuranceCompanyDTO) {
        return insuranceService.saveInsurenceCompany(insuranceCompanyDTO);
    }

}
