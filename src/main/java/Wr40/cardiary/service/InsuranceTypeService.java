package Wr40.cardiary.service;

import Wr40.cardiary.exception.NoSuchInsuranceTypeException;
import Wr40.cardiary.model.dto.insurance.InsuranceTypeDTO;
import Wr40.cardiary.model.entity.InsuranceType;
import Wr40.cardiary.repo.InsuranceTypeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Transactional
public class InsuranceTypeService {

    private InsuranceTypeRepository insuranceTypeRepository;
    private ModelMapper modelMapper;
    public InsuranceTypeDTO saveInsuranceType(InsuranceTypeDTO insuranceTypeDTO) {
        InsuranceType insuranceType = modelMapper.map(insuranceTypeDTO, InsuranceType.class);
        InsuranceType savedInsuranceType = insuranceTypeRepository.save(insuranceType);
        InsuranceTypeDTO savedInsuranceTypeDTO = modelMapper.map(savedInsuranceType, InsuranceTypeDTO.class);
        return savedInsuranceTypeDTO;
    }

    public Integer updateInsuranceType(InsuranceTypeDTO insuranceTypeDTO) {
        InsuranceType oldInsuranceType = insuranceTypeRepository.findByType(insuranceTypeDTO.getType()).orElseThrow(NoSuchInsuranceTypeException::new);
        InsuranceType insuranceTypeToSave = mapOldInsuranceTypeToNewOne(oldInsuranceType, modelMapper.map(insuranceTypeDTO, InsuranceType.class));
        int nrOfRowsUpdated = insuranceTypeRepository.UpdateInsuranceTypeTuple(insuranceTypeToSave.getDescription(), insuranceTypeToSave.getCostsPerYear(),
                insuranceTypeToSave.getCoveredCompensation(), insuranceTypeToSave.getType());
        return nrOfRowsUpdated;
    }

    private InsuranceType mapOldInsuranceTypeToNewOne(InsuranceType oldInsuranceType, InsuranceType insuranceType) {
        oldInsuranceType.setDescription(insuranceType.getDescription())
                .setCostsPerYear(insuranceType.getCostsPerYear()).setCoveredCompensation(insuranceType.getCoveredCompensation());
        return oldInsuranceType;
    }
}
