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

import java.util.List;

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

    @GetMapping("/list/{vin_number}")
    @ResponseStatus(HttpStatus.OK)
    public List<InsuranceCompanyWithTypeDTO> getAllInsuranceCompanyWithTypeForCar(@PathVariable(name = "vin_number", required = true) String vinNumber) {
        return insuranceService.getInsuranceCompWithType(vinNumber);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public InsuranceCompanyDTO saveInsuranceCompany(@Valid @RequestBody InsuranceCompanyDTO insuranceCompanyDTO) {
        return insuranceService.saveInsurenceCompany(insuranceCompanyDTO);
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<InsuranceCompanyDTO> getAllInsuranceCompany(@Valid @RequestBody InsuranceCompanyDTO insuranceCompanyDTO) {
        return insuranceService.getAllInsuranceCompanies();
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteInsuranceCompany(@PathVariable Integer id) {
        insuranceService.deleteInsuranceCompById(id);
        return String.format("Company by given %d id was successfully deleted!", id);
    }

    @PutMapping("update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InsuranceCompanyDTO updateInsuranceCompany(@PathVariable Integer id, @Valid @RequestBody InsuranceCompanyDTO insuranceCompanyDTO) {
        return insuranceService.updateInsuranceCompany(insuranceCompanyDTO, id);
    }

}
