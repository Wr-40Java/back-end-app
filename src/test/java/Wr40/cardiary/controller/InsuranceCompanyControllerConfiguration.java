package Wr40.cardiary.controller;

import Wr40.cardiary.handler.GlobalExceptionHandler;
import Wr40.cardiary.model.dto.insurance.InsuranceCompanyDTO;
import Wr40.cardiary.model.entity.InsuranceCompany;
import Wr40.cardiary.repo.CarRepository;
import Wr40.cardiary.repo.InsuranceRepository;
import Wr40.cardiary.repo.InsuranceTypeRepository;
import Wr40.cardiary.service.InsuranceService;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(SecurityTestConfig.class)
public class InsuranceCompanyControllerConfiguration implements InsuranceCompanySamples {

    @Bean
    GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    @Bean
    InsuranceService insuranceService() {
        ModelMapper mockedModelMapper = Mockito.mock(ModelMapper.class);
        CarRepository mockedCarRepo = Mockito.mock(CarRepository.class);
        InsuranceRepository mockedInsuranceRepo = Mockito.mock(InsuranceRepository.class);
        InsuranceTypeRepository mockedInsuranceTypeRepo = Mockito.mock(InsuranceTypeRepository.class);

        return new InsuranceService(mockedCarRepo, mockedInsuranceRepo, mockedInsuranceTypeRepo, mockedModelMapper) {
            @Override
            public List<InsuranceCompanyDTO> getAllInsuranceCompanies() {
                return InsuranceCompanySamples.getInsCompList();
            }
        };
    }

    @Bean
    InsuranceController insuranceController() {
        ModelMapper mockedModelMapper = Mockito.mock(ModelMapper.class);
        return new InsuranceController(mockedModelMapper, insuranceService());
    }
}
