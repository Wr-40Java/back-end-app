package Wr40.cardiary.test.service;

import Wr40.cardiary.exception.CarAlreadyExistException;
import Wr40.cardiary.model.entity.Car;
import Wr40.cardiary.repo.CarRepository;
import Wr40.cardiary.service.CarService;
import Wr40.cardiary.DatabaseConfig;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = DatabaseConfig.class)
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;
    @InjectMocks
    private CarService carService;

    @Test
    public void shouldSaveCar(){
        Car car = new Car();
        Mockito.when(carRepository.save(car)).thenReturn(car);
        Car savedCar = carService.saveCar(car);
        Assertions.assertEquals(savedCar,car);
        Mockito.verify(carRepository).save(car);
    }

    @Test
    public void shouldNotSaveAlreadySavedCar_ThrowsException(){
        Car car = new Car();
        Mockito.when(carRepository.save(car)).thenThrow(new CarAlreadyExistException());
        Assertions.assertThrows(CarAlreadyExistException.class, () -> carService.saveCar(car));
        Mockito.verify(carRepository).save(car);
    }

}
