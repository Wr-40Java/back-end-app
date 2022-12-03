package Wr40.cardiary.service;

import Wr40.cardiary.exception.UserNotFoundException;
import Wr40.cardiary.model.entity.User;
import Wr40.cardiary.exception.UserAlreadyExistedException;
import Wr40.cardiary.exception.WrongEmailAddressException;
import Wr40.cardiary.repo.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class UserService {
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUser(String username){
        return userRepository.findUserByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    public User saveUser(User user) {


        return userRepository.save(user);
    }

    public void deleteUser(String username) {
        User user = userRepository.findUserByUsername(username).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
}
