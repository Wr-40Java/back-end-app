package Wr40.cardiary.controller;

import Wr40.cardiary.model.dto.UserDTO;
import Wr40.cardiary.model.entity.User;
import Wr40.cardiary.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cardiary/user")
public class UserController {

    UserService userService;
    ModelMapper modelMapper;

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
    public User getUser(@PathVariable String username){
        return userService.getUser(username);
    }
}
