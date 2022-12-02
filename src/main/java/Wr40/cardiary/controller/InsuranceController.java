package Wr40.cardiary.controller;

import Wr40.cardiary.model.dto.CarDTO;
import Wr40.cardiary.model.dto.InsuranceCompanyDTO;
import Wr40.cardiary.model.entity.Car;
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
    public InsuranceCompanyDTO saveInsuranceCompany(@PathVariable(name = "vin_number", required = false) String vinNumber, @Valid @RequestBody InsuranceCompanyDTO insuranceCompanyDTO) {
        InsuranceCompany insuranceCompany = modelMapper.map(insuranceCompanyDTO, InsuranceCompany.class);
        return insuranceService.saveInsuranceWithTypeToTheCar(insuranceCompanyDTO, vinNumber);
    }

}
