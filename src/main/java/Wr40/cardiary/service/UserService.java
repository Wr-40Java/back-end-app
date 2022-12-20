package Wr40.cardiary.service;

import Wr40.cardiary.exception.UserNotFoundException;
import Wr40.cardiary.model.entity.Car;
import Wr40.cardiary.model.entity.User;
import Wr40.cardiary.repo.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
@AllArgsConstructor
public class UserService {
    UserRepository userRepository;
    CarService carService;


    public List<User> getAllUsers() {
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
    public List<Car> getUserCars(String username){
        List<Car> cars;
        try{
            User user = userRepository.findUserByUsername(username).orElseThrow(UserNotFoundException::new);
            cars = user.getCars();
            return cars;
        }catch (UserNotFoundException exception){
            return null;
        }
    }
    public User deleteFromUserByVin(String userName,String vin){
        User userFromDeleteTheCar = userRepository.findUserByUsername(userName).orElseThrow(UserNotFoundException::new);
        Car car = carService.getCar(vin);

        int indexOfCarToDelete = userFromDeleteTheCar.getCars().indexOf(car);
        userFromDeleteTheCar.getCars().remove(indexOfCarToDelete);

        return userRepository.save(userFromDeleteTheCar);
    }
}
