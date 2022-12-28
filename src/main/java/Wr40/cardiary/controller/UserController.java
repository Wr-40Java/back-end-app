package Wr40.cardiary.controller;

import Wr40.cardiary.model.dto.UserDTO;
import Wr40.cardiary.model.entity.User;
import Wr40.cardiary.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    UserService userService;
    ModelMapper modelMapper;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@Valid @RequestBody UserDTO dto) {
        User mappedUser = modelMapper.map(dto, User.class);
        return userService.saveUser(mappedUser);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @DeleteMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @DeleteMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllUsers() {
        userService.deleteAllUsers();
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable String username) {
        return userService.getUser(username);

    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@Valid @RequestBody User user) {
        return userService.updateUser(user);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PutMapping("/addCar/{userName}/{vin}")
    @ResponseStatus(HttpStatus.OK)
    public void addCarToUserByVinNumber(@PathVariable String userName, @PathVariable String vin) {
        userService.addCarToUserByVin(userName, vin);
    }
}
