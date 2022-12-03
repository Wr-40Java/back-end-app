package Wr40.cardiary.repo;

import Wr40.cardiary.model.entity.TaxType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxTypeRepository extends JpaRepository<TaxType,Long> {

}
