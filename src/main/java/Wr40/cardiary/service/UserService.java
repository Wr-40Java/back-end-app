package Wr40.cardiary.service;

import Wr40.cardiary.exception.UserAlreadyExistedException;
import Wr40.cardiary.exception.WrongEmailAddressException;
import Wr40.cardiary.model.entity.User;
import Wr40.cardiary.repo.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
@Transactional
public class UserService {

    UserRepository userRepository;

    String emailValidationRegexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    public User saveUser(User user) {
        if (userRepository.findByUserName(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistedException();
        }
        if (!Objects.equals(user.getEmail(), emailValidationRegexPattern)) {
            throw new WrongEmailAddressException();
        }
        return userRepository.save(user);
    }
}
