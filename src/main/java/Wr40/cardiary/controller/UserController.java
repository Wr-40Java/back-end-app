package Wr40.cardiary.controller;

import Wr40.cardiary.model.dto.UserDTO;
import Wr40.cardiary.model.entity.User;
import Wr40.cardiary.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cardiary/user")
public class UserController {

    UserService userService;
    ModelMapper modelMapper;

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@Valid @RequestBody UserDTO dto) {
        User mappedUser = modelMapper.map(dto, User.class);
        return userService.saveUser(mappedUser);
    }

    @DeleteMapping("/delete/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
    }

    @DeleteMapping("/delete/all")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllUsers() {
        userService.deleteAllUsers();
    }

    @GetMapping("/get/{username}")
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable String username) {
        return userService.getUser(username);

    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@Valid @RequestBody UserDTO userDTO) {
        User mappedUser = modelMapper.map(userDTO, User.class);
        return userService.updateUser(mappedUser);
    }

    @PutMapping("/addCar/{userName}/{vin}")
    @ResponseStatus(HttpStatus.OK)
    public void addCarToUserByVinNumber(@PathVariable String userName, @PathVariable String vin) {
        userService.addCarToUserByVin(userName, vin);
    }
}
