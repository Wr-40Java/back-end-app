package Wr40.cardiary.service;

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

    public User updateUser(User user) {
        User userToUpdate = userRepository.findByUserName(user.getUsername()).orElseThrow(UserNotFoundException::new);
        userToUpdate.setName(user.getName());
        userToUpdate.setSurname(user.getSurname());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPassword(user.getPassword());
        updateEmailIfMatchTemplate(user, userToUpdate);
        userToUpdate.setPhoneNumber(user.getPhoneNumber());
        return userRepository.save(userToUpdate);
    }

    private void updateEmailIfMatchTemplate(User user, User userToUpdate) {
        if (user.getEmail().equals("^(.+)@(\\\\S+)$")){
            userToUpdate.setEmail(user.getEmail());
        }
        else throw new WrongEmailAddressException();
    }
}
