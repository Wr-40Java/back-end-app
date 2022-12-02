package Wr40.cardiary.service;

import Wr40.cardiary.exception.NoSuchCarFoundException;
import Wr40.cardiary.model.entity.Car;
import Wr40.cardiary.repo.CarRepository;
import Wr40.cardiary.exception.CarAlreadyExistException;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


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

    public Car getCar(String vin) {
        return carRepository.findByVINnumber(vin).orElseThrow(NoSuchCarFoundException::new);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public void deleteCar(String vin) {
        Car car = carRepository.findByVINnumber(vin).orElseThrow(NoSuchCarFoundException::new);
        carRepository.delete(car);

    }

    public void deleteAllCars() {
        carRepository.deleteAll();
    }

    public Car updateCar(Car car) {
        Car carToUpdate = carRepository.findByVINnumber(car.getVINnumber()).orElseThrow(NoSuchCarFoundException::new);
        carToUpdate.setModel(car.getModel());
        carToUpdate.setBrand(car.getBrand());
        carToUpdate.setEngineType(car.getEngineType());
        carToUpdate.setBodyType(car.getBodyType());
        carToUpdate.setColor(car.getColor());
        carToUpdate.setProductionYear(car.getProductionYear());
        carToUpdate.setHorsePower(car.getHorsePower());
        return carRepository.save(carToUpdate);
    }

}
