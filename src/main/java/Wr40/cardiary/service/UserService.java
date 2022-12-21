package Wr40.cardiary.service;

import Wr40.cardiary.exception.UserAlreadyExistedException;
import Wr40.cardiary.exception.UserNotFoundException;
import Wr40.cardiary.model.entity.Car;
import Wr40.cardiary.model.entity.Role;
import Wr40.cardiary.model.entity.User;
import Wr40.cardiary.model.entity.UserRole;
import Wr40.cardiary.model.security.SecurityUser;
import Wr40.cardiary.repo.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class UserService implements UserDetailsService {
    UserRepository userRepository;
    CarService carService;
    PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(String username) {
        return userRepository.findUserByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (userRepository.findAll().isEmpty()) {
            user.setRoles(List.of(new Role(UserRole.ADMIN)));
        } else {
            user.setRoles(List.of(new Role(UserRole.USER)));
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistedException();
        }
        log.info("User registrated: {}", user);
        return userRepository.save(user);
    }

    public void deleteUser(String username) {
        User user = userRepository.findUserByUsername(username).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
        log.info("User deleted: {}", user);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public User updateUser(User user) {
        User userToUpdate = userRepository.findUserByUsername(user.getUsername()).orElseThrow(UserNotFoundException::new);
        userToUpdate.setName(user.getName());
        userToUpdate.setSurname(user.getSurname());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPhoneNumber(user.getPhoneNumber());
        return userRepository.save(userToUpdate);
    }

    public void addCarToUserByVin(String userName, String vin) {
        User userToUpdateWithNewCar = userRepository.findUserByUsername(userName).orElseThrow(UserNotFoundException::new);
        Car car = carService.getCar(vin);

        userToUpdateWithNewCar.getCars().add(car);

        userRepository.save(userToUpdateWithNewCar);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username).orElseThrow(UserNotFoundException::new);
        return new SecurityUser(user);
    }
}
