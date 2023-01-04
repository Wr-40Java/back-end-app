package Wr40.cardiary.repo;

import lombok.Getter;

import java.math.BigDecimal;

public interface StatsDTO {
    BigDecimal getRound();
    String getName();
    String getDescription();
    String getInstitutionToPayFor();


}