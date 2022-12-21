package Wr40.cardiary.service;

import Wr40.cardiary.exception.NoSuchInsuranceTypeException;
import Wr40.cardiary.model.dto.insurance.InsuranceTypeDTO;
import Wr40.cardiary.model.entity.InsuranceType;
import Wr40.cardiary.repo.InsuranceTypeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class InsuranceTypeService {

    private InsuranceTypeRepository insuranceTypeRepository;
    private ModelMapper modelMapper;
    public InsuranceTypeDTO saveInsuranceType(InsuranceTypeDTO insuranceTypeDTO) {
        TypeMap<InsuranceTypeDTO, InsuranceType> mapDTOtoObjectWithoutId = modelMapper.typeMap(InsuranceTypeDTO.class, InsuranceType.class)
                .addMappings(mapper -> mapper.skip(InsuranceType::setId));

        InsuranceType insuranceType = mapDTOtoObjectWithoutId.map(insuranceTypeDTO);
        InsuranceType savedInsuranceType = insuranceTypeRepository.save(insuranceType);
        InsuranceTypeDTO savedInsuranceTypeDTO = modelMapper.map(savedInsuranceType, InsuranceTypeDTO.class);
        return savedInsuranceTypeDTO;
    }

    public InsuranceTypeDTO updateInsuranceType(InsuranceTypeDTO insuranceTypeDTO) {
        InsuranceType oldInsuranceType = insuranceTypeRepository.findByType(insuranceTypeDTO.getType()).orElseThrow(NoSuchInsuranceTypeException::new);

        TypeMap<InsuranceTypeDTO, InsuranceType> mapDTOtoObjectWithoutId = modelMapper.typeMap(InsuranceTypeDTO.class, InsuranceType.class)
                .addMappings(mapper -> mapper.skip(InsuranceType::setId));
        InsuranceType insuranceType = mapDTOtoObjectWithoutId.map(insuranceTypeDTO);

        InsuranceType insuranceTypeToUpdate = oldInsuranceType.setType(insuranceType.getType()).setDescription(insuranceType.getDescription())
                .setCostsPerYear(insuranceType.getCostsPerYear())
                .setCoveredCompensation(insuranceType.getCoveredCompensation());

        InsuranceType savedObject = insuranceTypeRepository.save(insuranceTypeToUpdate);

        return modelMapper.map(savedObject, InsuranceTypeDTO.class);
    }

    public List<InsuranceTypeDTO> getInsuranceTypes() {
        List<InsuranceTypeDTO> allInsTypeDTOs = new ArrayList<>();
        List<InsuranceType> allInsTypes = insuranceTypeRepository.findAll();
        allInsTypes.stream().map(obj -> modelMapper.map(obj, InsuranceTypeDTO.class)).forEach(allInsTypeDTOs::add);
        return allInsTypeDTOs;
    }

    public Integer deleteInsuranceType(String type) {
        insuranceTypeRepository.findByType(type).orElseThrow(NoSuchInsuranceTypeException::new);
        return insuranceTypeRepository.deleteByType(type);
    }
}
