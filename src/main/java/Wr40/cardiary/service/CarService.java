package Wr40.cardiary.service;

import Wr40.cardiary.model.entity.Car;
import Wr40.cardiary.repo.CarRepository;
import Wr40.cardiary.exception.CarAlreadyExistException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
@Transactional
public class CarService {

    CarRepository carRepository;

    public Car saveCar(Car car) {
        if (carRepository.findByVINnumber(car.getVINnumber()).isPresent()) {
            throw new CarAlreadyExistException();
        }
        return carRepository.save(car);
    }

}
