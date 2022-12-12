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
@RequestMapping("api/insurance")
@AllArgsConstructor
public class InsuranceController {

    private ModelMapper modelMapper;
    private InsuranceService insuranceService;

    @PostMapping("/{vin_number}")
    @ResponseStatus(HttpStatus.CREATED)
    public InsuranceCompanyWithTypeDTO saveInsuranceCompanyWithTypeAndCar(@PathVariable(name = "vin_number", required = false) String vinNumber,
                                                                          @Valid @RequestBody InsuranceCompanyWithTypeDTO insuranceCompanyWithTypeDTO) {
        return insuranceService.saveInsuranceWithTypeToTheCar(insuranceCompanyWithTypeDTO, vinNumber);
    }

    @GetMapping("/{vin_number}")
    @ResponseStatus(HttpStatus.OK)
    public List<InsuranceCompanyWithTypeDTO> getAllInsuranceCompanyWithTypeForCar(@PathVariable(name = "vin_number", required = true) String vinNumber) {
        return insuranceService.getInsuranceCompWithType(vinNumber);
    }

    @PostMapping("/link")
    @ResponseStatus(HttpStatus.CREATED)
    public InsuranceCompanyWithTypeDTO linkInsuranceCompanyWithTypeAndCar(@RequestParam(name = "vin_number", required = true) String vinNumber,
                                                                          @RequestParam(name = "ic_id", required = true) Integer InsCompId,
                                                                          @RequestParam(name = "it_id", required = true) Integer InsTypeId) {
        return insuranceService.linkCarWithInsuranceCompanyAndInsuranceType(vinNumber, InsCompId, InsTypeId);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public InsuranceCompanyWithTypeDTO updateLinkedInsuranceCompanyWithTypeAndCar(@RequestParam(name = "vin_number", required = true) String vinNumber,
//                                                             @PathVariable(name = "old_ic_id", required = true) Integer OldInsCompId,
                                                             @RequestParam(name = "ic_id", required = true) Integer InsCompId,
                                                             @RequestParam(name = "it_id", required = true) Integer InsTypeId) {
        return insuranceService.updateLinkInsuranceCompanyWithTypeAndCar(vinNumber, InsCompId, InsTypeId);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public String deleteLinkedInsuranceCompanyWithTypeAndCar(@RequestParam(name = "vin_number", required = true) String vinNumber,
                                                                          @RequestParam(name = "ic_id", required = true) Integer InsCompId,
                                                                          @RequestParam(name = "it_id", required = false) Integer InsTypeId) {
        return insuranceService.deleteLinkInsuranceCompanyWithTypeAndCar(vinNumber, InsCompId, InsTypeId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InsuranceCompanyDTO saveInsuranceCompany(@Valid @RequestBody InsuranceCompanyDTO insuranceCompanyDTO) {
        return insuranceService.saveInsurenceCompany(insuranceCompanyDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InsuranceCompanyDTO> getAllInsuranceCompany() {
        return insuranceService.getAllInsuranceCompanies();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteInsuranceCompany(@PathVariable Integer id) {
        insuranceService.deleteInsuranceCompById(id);
        return String.format("Company by given %d id was successfully deleted!", id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InsuranceCompanyDTO updateInsuranceCompany(@PathVariable Integer id, @Valid @RequestBody InsuranceCompanyDTO insuranceCompanyDTO) {
        return insuranceService.updateInsuranceCompany(insuranceCompanyDTO, id);
    }

}
