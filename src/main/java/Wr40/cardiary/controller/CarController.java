package Wr40.cardiary.controller;

import Wr40.cardiary.model.dto.CarDTO;
import Wr40.cardiary.model.entity.Car;
import Wr40.cardiary.service.CarService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/api/cardiary/car")
public class CarController {

    CarService carService;
    ModelMapper modelMapper;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Car saveCar(@Valid @RequestBody CarDTO dto) {
        Car mappedCar = modelMapper.map(dto, Car.class);
        return carService.saveCar(mappedCar);
    }



}
