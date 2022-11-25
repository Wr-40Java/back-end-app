package Wr40.cardiary.repo;

import Wr40.cardiary.model.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car,Long> {
    Optional<Car> findByVINnumber(String VINnumber);



}
