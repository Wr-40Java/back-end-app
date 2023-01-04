package Wr40.cardiary.repo;

import Wr40.cardiary.model.entity.TaxType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaxTypeRepository extends JpaRepository<TaxType, Long> {
    @Query(value = "select CONCAT (round(SUM(tax.cost_of_transaction),2), ';', tax_type.name, ';', tax_type.description, ';', tax_type.institution_to_pay_for) AS \"info\" from tax left join tax_type on tax.tax_type_id = tax_type.id\n" +
            "group by tax_type.name, tax_type.description, tax_type.institution_to_pay_for\n" +
            "order by round(SUM(tax.cost_of_transaction),2)", nativeQuery = true)
    List<String> getTaxStatsWithSumOfTransactions();


//    @Query(value = "select CONCAT (round(AVG(tax.cost_of_transaction),2), ';', tax_type.name, ';', tax_type.description, ';', tax_type.institution_to_pay_for) AS \"info\" from tax left join tax_type on tax.tax_type_id = tax_type.id\n" +
//            "group by tax_type.name, tax_type.description, tax_type.institution_to_pay_for\n" +
//            "order by round(AVG(tax.cost_of_transaction),2)", nativeQuery = true)

//    List<String> getTaxStatsWithAVGOfTransactions();



}

