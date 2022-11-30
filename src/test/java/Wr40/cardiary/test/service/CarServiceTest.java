package Wr40.cardiary.test.service;

import Wr40.cardiary.exception.CarAlreadyExistException;
import Wr40.cardiary.exception.NoSuchCarFoundException;
import Wr40.cardiary.model.dto.CarDTO;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;
    @InjectMocks
    private CarService carService;

    @Test
    public void whenSaving_shouldSaveCar(){
        Car car = new Car();
        Mockito.when(carRepository.save(car)).thenReturn(car);
        Car savedCar = carService.saveCar(car);
        Assertions.assertEquals(savedCar,car);
        verify(carRepository).save(car);
    }

    @Test
    public void whenSaving_shouldNotSaveAlreadySavedCar_ThrowsException(){
        Car car = new Car();
        Mockito.when(carRepository.save(car)).thenThrow(new CarAlreadyExistException());
        Assertions.assertThrows(CarAlreadyExistException.class, () -> carService.saveCar(car));
        verify(carRepository).save(car);
    }

    
    @Test
    public void henFindingCarByVIN_shouldGetCarWithGivenVIN(){
        Car car = new Car();
        Mockito.when(carRepository.findByVINnumber(car.getVINnumber())).thenReturn(Optional.of(car));
        Car vinCar = carService.getCar(car.getVINnumber());
        Assertions.assertEquals(car,vinCar);
        verify(carRepository).findByVINnumber(car.getVINnumber());
    }

    @Test
    public void whenFindingCarByVIN_shouldNotGetCar_IfVINDoesntMatch_ThrowException(){
        Car car = new Car();
        Mockito.when(carRepository.findByVINnumber(car.getVINnumber())).thenThrow(new NoSuchCarFoundException());
        Assertions.assertThrows(NoSuchCarFoundException.class, () -> carService.getCar(car.getVINnumber()));
        verify(carRepository).findByVINnumber(car.getVINnumber());
    }

    @Test
    public void whenFindAll_shouldReturnListOfSizeOne_IfOneCarAdded(){
        List<Car> list = new ArrayList<>();
        list.add(new Car());
        Mockito.when(carRepository.findAll()).thenReturn(list);
        List<Car> allCars = carService.getAllCars();
        Assertions.assertEquals(1,allCars.size());
    }

    @Test
    public void whenFindAll_shouldReturnListOfSizeZero_IfNoCarsAdded(){
        List<Car> list = new ArrayList<>();
        Mockito.when(carRepository.findAll()).thenReturn(list);
        List<Car> allCars = carService.getAllCars();
        Assertions.assertEquals(0,allCars.size());
    }

    @Test
    public void whenGivenVin_shouldDeleteCar_ifFound(){
        Car car = new Car();
        Mockito.when(carRepository.findByVINnumber(car.getVINnumber())).thenReturn(Optional.of(car));
        carService.deleteCar(car.getVINnumber());
        verify(carRepository).delete(car);
    }

    @Test
    public void whenGivenVin_shouldThrowException_IfNotFound(){
        Car car = new Car();
        Mockito.when(carRepository.findByVINnumber(car.getVINnumber())).thenThrow(new NoSuchCarFoundException());
        Assertions.assertThrows(NoSuchCarFoundException.class, () -> carService.deleteCar(car.getVINnumber()));
    }

    @Test
    public void whenDeleteAll_shouldDeleteAllCars(){
        carService.deleteAllCars();
        verify(carRepository).deleteAll();
    }

    @Test
    public void whenUpdateCar_ShouldUpdateGivenCar(){
        Car car = new Car();
        Car car2 = new Car();
        car2.setHorsePower((short) 100);
        Mockito.when(carRepository.findByVINnumber(car.getVINnumber())).thenReturn(Optional.of(car));
        Mockito.when(carRepository.save(car)).thenReturn(car2);
        Car updatedCar = carService.updateCar(car);
        Assertions.assertEquals(100,updatedCar.getHorsePower());
    }

}
