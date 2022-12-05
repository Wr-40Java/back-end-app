package Wr40.cardiary.service;

import Wr40.cardiary.model.dto.insurance.InsuranceTypeDTO;
import Wr40.cardiary.model.entity.InsuranceType;
import Wr40.cardiary.repo.InsuranceTypeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InsuranceTypeService {

    private InsuranceTypeRepository insuranceTypeRepository;
    private ModelMapper modelMapper;
    public InsuranceTypeDTO saveInsuranceType(InsuranceTypeDTO insuranceTypeDTO) {
        InsuranceType insuranceType = modelMapper.map(insuranceTypeDTO, InsuranceType.class);
        InsuranceType savedInsuranceType = insuranceTypeRepository.save(insuranceType);
        InsuranceTypeDTO savedInsuranceTypeDTO = modelMapper.map(savedInsuranceType, InsuranceTypeDTO.class);
        return savedInsuranceTypeDTO;
    }
}
