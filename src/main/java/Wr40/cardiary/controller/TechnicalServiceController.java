package Wr40.cardiary.controller;

import Wr40.cardiary.model.dto.technicalService.TechnicalServiceDTO;
import Wr40.cardiary.model.dto.technicalService.TechnicalServiceResponseDTO;
import Wr40.cardiary.model.entity.TechnicalService;
import Wr40.cardiary.service.TechnicalServiceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/technical_service")
@AllArgsConstructor
public class TechnicalServiceController {

    private TechnicalServiceService technicalServiceService;
    private ModelMapper modelMapper;

    @PostMapping("/maintenance_history/{maintenance_history_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public TechnicalServiceResponseDTO saveTechnicalService(
            @Valid @RequestBody TechnicalServiceDTO technicalServiceDTO,
            @PathVariable(name = "maintenance_history_id") Long mHistoryId) {
        TechnicalService technicalService = modelMapper.map(technicalServiceDTO, TechnicalService.class);
        return technicalServiceService.saveTechnicalService(mHistoryId, technicalService);
    }

    @PutMapping("/{technical_service_id}")
    @ResponseStatus(HttpStatus.OK)
    public TechnicalServiceResponseDTO updateTechnicalService(@Valid @RequestBody TechnicalServiceDTO technicalServiceDTO,
                                                              @PathVariable(name = "technical_service_id")
                                                              Long technicalServiceId) {
        TechnicalService technicalService = modelMapper.map(technicalServiceDTO, TechnicalService.class);
        return technicalServiceService.updateTechnicalService(technicalServiceId, technicalService);

    }

}
