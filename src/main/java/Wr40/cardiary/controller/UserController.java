package Wr40.cardiary.controller;

import Wr40.cardiary.model.dto.UserDTO;
import Wr40.cardiary.model.entity.Car;
import Wr40.cardiary.model.entity.User;
import Wr40.cardiary.repo.CarRepository;
import Wr40.cardiary.repo.UserRepository;
import Wr40.cardiary.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cardiary/user")
public class UserController {

    UserService userService;

    ModelMapper modelMapper;
    UserRepository userRepository;
    CarRepository carRepository;

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@Valid @RequestBody UserDTO userDTO) {
        User mappedUser = modelMapper.map(userDTO, User.class);
        return userService.updateUser(mappedUser);
    }

    @PutMapping("/addCar/{userName}/{vin}")
    public void addCarToUserByVinNumber(@PathVariable String userName, @PathVariable String vin) {
        Optional<User> userToUpdate = userRepository.findUserByUsername(userName);
        Optional<Car> car = carRepository.findByVINnumber(vin);
        userToUpdate.get().getCars().add(car.get());
    }
}
