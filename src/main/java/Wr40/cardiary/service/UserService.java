package Wr40.cardiary.service;

import Wr40.cardiary.exception.UserNotFoundException;
import Wr40.cardiary.exception.WrongEmailAddressException;
import Wr40.cardiary.model.entity.Car;
import Wr40.cardiary.model.entity.User;
import Wr40.cardiary.repo.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Transactional
public class UserService {

    UserRepository userRepository;
    CarService carService;

    public User updateUser(User user) {
        User userToUpdate = userRepository.findUserByUsername(user.getUsername()).orElseThrow(UserNotFoundException::new);
        userToUpdate.setName(user.getName());
        userToUpdate.setSurname(user.getSurname());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPassword(user.getPassword());
        updateEmailIfMatchTemplate(user, userToUpdate);
        userToUpdate.setPhoneNumber(user.getPhoneNumber());
        return userRepository.save(userToUpdate);
    }

    public void addCarToUserByVin(String userName, String vin) {
        User userToUpdateWithNewCar = userRepository.findUserByUsername(userName).orElseThrow(UserNotFoundException::new);
        Car car = carService.getCar(vin);

        userToUpdateWithNewCar.getCars().add(car);

        userRepository.save(userToUpdateWithNewCar);
    }

    private void updateEmailIfMatchTemplate(User user, User userToUpdate) {
        if (user.getEmail().equals("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {
            userToUpdate.setEmail(user.getEmail());
        } else throw new WrongEmailAddressException();
    }


}
