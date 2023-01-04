package Wr40.cardiary.controller;

import Wr40.cardiary.model.dto.insurance.InsuranceCompanyDTO;
import Wr40.cardiary.model.entity.InsuranceCompany;

import java.util.ArrayList;
import java.util.List;

public interface InsuranceCompanySamples {
    static List<InsuranceCompanyDTO> getInsCompList() {
        InsuranceCompanyDTO insuranceCompanyDTO = new InsuranceCompanyDTO();
        insuranceCompanyDTO.setName("AutoCasco");
        insuranceCompanyDTO.setDescription("for accident");
        insuranceCompanyDTO.setPhoneNumber(123L);

        InsuranceCompanyDTO insuranceCompanyDTO2 = new InsuranceCompanyDTO();
        insuranceCompanyDTO2.setName("AutoCasco2");
        insuranceCompanyDTO2.setDescription("for accident2");
        insuranceCompanyDTO2.setPhoneNumber(123L);

        List<InsuranceCompanyDTO> insuranceCompanyDTOList = new ArrayList<>();
        insuranceCompanyDTOList.add(insuranceCompanyDTO);
        insuranceCompanyDTOList.add(insuranceCompanyDTO2);

        return insuranceCompanyDTOList;
    }
}
