package Wr40.cardiary.controller;

import Wr40.cardiary.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cardiary/user")
public class UserController {

    UserService userService;
}
