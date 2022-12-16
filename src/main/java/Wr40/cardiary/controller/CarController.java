package Wr40.cardiary.controller;

import Wr40.cardiary.model.entity.Car;
import Wr40.cardiary.model.dto.CarDTO;
import Wr40.cardiary.service.CarService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/car")
public class CarController {

    CarService carService;
    ModelMapper modelMapper;

    @PreAuthorize("hasRole('USER')")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Car saveCar(@Valid @RequestBody CarDTO dto) {
        Car mappedCar = modelMapper.map(dto, Car.class);
        return carService.saveCar(mappedCar);
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{vin}")
    @ResponseStatus(HttpStatus.OK)
    public Car getCar(@PathVariable String vin){
        return carService.getCar(vin);
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<Car> getAllCars(){
        return carService.getAllCars();
    }
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{vin}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCar(@PathVariable String vin){
        carService.deleteCar(vin);
    }
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllCars(){
        carService.deleteAllCars();
    }
    @PreAuthorize("hasRole('USER')")
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public Car updateCar(@Valid @RequestBody CarDTO carDTO){
        Car mappedCar = modelMapper.map(carDTO, Car.class);
        return carService.updateCar(mappedCar);
    }




}
