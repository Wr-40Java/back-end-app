package Wr40.cardiary.repo;

import Wr40.cardiary.model.entity.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaxRepository extends JpaRepository<Tax, Long> {
    @Query(value = "select AVG(t.costOfTransaction) as round, tt.name as name, tt.description as description, tt.institutionToPayFor as institutionToPayFor from Tax t left join t.taxType tt " +
            "group by tt.name, tt.description, tt.institutionToPayFor " +
            "order by AVG(t.costOfTransaction)", nativeQuery = false)
    List<StatsDTO> getTaxStatsWithAVGOfTransactions();

    @Query(value = "select SUM(t.costOfTransaction) as round, tt.name as name, tt.description as description, tt.institutionToPayFor as institutionToPayFor from Tax t left join t.taxType tt " +
            "group by tt.name, tt.description, tt.institutionToPayFor " +
            "order by AVG(t.costOfTransaction)", nativeQuery = false)
    List<StatsDTO> getTaxStatsWithSUMOfTransactions();
}
