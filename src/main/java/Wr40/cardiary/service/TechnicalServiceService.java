package Wr40.cardiary.service;

import Wr40.cardiary.exception.TechnicalServiceAlreadyExistsException;
import Wr40.cardiary.model.dto.technicalService.TechnicalServiceDTO;
import Wr40.cardiary.model.dto.technicalService.TechnicalServiceResponseDTO;
import Wr40.cardiary.model.entity.MaintenanceHistory;
import Wr40.cardiary.model.entity.TechnicalService;
import Wr40.cardiary.repo.TechnicalServiceRepository;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import java.util.List;

import static Wr40.cardiary.util.Calculations.calculateTechnicalServiceCost;

@Service
@AllArgsConstructor
public class TechnicalServiceService {
    private MaintenanceHistoryService maintenanceHistoryService;
    private TechnicalServiceRepository technicalServiceRepository;
    private ModelMapper modelMapper;

    @Transactional
    public TechnicalServiceResponseDTO saveTechnicalService(Long technicalServiceId, TechnicalService technicalService) {
        MaintenanceHistory maintenanceHistory = maintenanceHistoryService.getMaintenanceHistory(technicalServiceId);
        if (maintenanceHistory.getTechnicalService() != null) {
            throw new TechnicalServiceAlreadyExistsException();
        }

        TechnicalService savedTechnicalService = technicalServiceRepository.save(technicalService);
        maintenanceHistory.setTechnicalService(savedTechnicalService);
        calculateTechnicalServiceCost(maintenanceHistory);
        maintenanceHistoryService.updateMH(technicalServiceId, maintenanceHistory);

        return modelMapper.map(savedTechnicalService, TechnicalServiceResponseDTO.class);
    }


}
