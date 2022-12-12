package Wr40.cardiary.service;

import Wr40.cardiary.exception.NoSuchTechnicalServiceFoundException;
import Wr40.cardiary.exception.TechnicalServiceAlreadyExistsException;
import Wr40.cardiary.exception.UnableToDeleteTechnicalServiceException;
import Wr40.cardiary.model.dto.technicalService.TechnicalServiceResponseDTO;
import Wr40.cardiary.model.entity.MaintenanceHistory;
import Wr40.cardiary.model.entity.TechnicalService;
import Wr40.cardiary.repo.TechnicalServiceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    public TechnicalServiceResponseDTO updateTechnicalService(Long technicalServiceId, TechnicalService technicalService) {
        if (!technicalServiceRepository.existsById(technicalServiceId)) {
            throw new NoSuchTechnicalServiceFoundException();
        }
        technicalService.setId(technicalServiceId);
        TechnicalService technicalServiceSaved = technicalServiceRepository.save(technicalService);

        return modelMapper.map(technicalServiceSaved, TechnicalServiceResponseDTO.class);
    }

    public TechnicalServiceResponseDTO getTechnicalService(Long technicalServiceId) {

        TechnicalService technicalService = technicalServiceRepository.findById(technicalServiceId)
                .orElseThrow(NoSuchTechnicalServiceFoundException::new);

        return modelMapper.map(technicalService, TechnicalServiceResponseDTO.class);

    }

    public List<TechnicalServiceResponseDTO> getAllMaintenanceEvent() {
        return technicalServiceRepository.findAll()
                .stream()
                .map(ts -> modelMapper.map(ts, TechnicalServiceResponseDTO.class))
                .toList();
    }

    public void deleteTechnicalService(Long technicalServiceId) {
        TechnicalService technicalService = technicalServiceRepository.findById(technicalServiceId)
                .orElseThrow(NoSuchTechnicalServiceFoundException::new);
        technicalServiceRepository.delete(technicalService);
        if(technicalServiceRepository.existsById(technicalServiceId)) {
            throw new UnableToDeleteTechnicalServiceException();
        }
    }

    public void deleteAllTechnicalService() {
        technicalServiceRepository.deleteAll();
        if (!technicalServiceRepository.findAll().isEmpty()) {
            throw new UnableToDeleteTechnicalServiceException();
        }
    }
}
