package Wr40.cardiary.controller;

import Wr40.cardiary.model.dto.UserDTO;
import Wr40.cardiary.model.entity.User;
import Wr40.cardiary.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cardiary/user")
public class UserController {

    UserService userService;

    ModelMapper modelMapper;

    public User updateUser(@Valid @RequestBody UserDTO userDTO){
        User mappedUser = modelMapper.map(userDTO, User.class);
        return userService.updateUser(mappedUser);
    }
}
