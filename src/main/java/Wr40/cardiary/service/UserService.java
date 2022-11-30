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

    public void deleteUser(String username) {
        User user = userRepository.findByUserName(username).orElseThrow();
        userRepository.delete(user);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
}
