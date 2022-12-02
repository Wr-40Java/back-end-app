package Wr40.cardiary.test.service;

import Wr40.cardiary.exception.UserAlreadyExistedException;
import Wr40.cardiary.model.entity.User;
import Wr40.cardiary.repo.UserRepository;
import Wr40.cardiary.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Test
    public void whenSavingUserShouldSave() {
        User user = new User();
        user.setEmail("my_email@gmail.com");
        Mockito.when(userRepository.save(user)).thenReturn(user);
        User savedUser = userService.saveUser(user);
        Assertions.assertEquals(savedUser, user);
        verify(userRepository).save(user);
    }

    @Test
    public void whenSavingUserThatAlreadyExistShouldThrowException() {
        User user = new User();
        user.setEmail("my_email@gmail.com");
        Mockito.when(userRepository.save(user)).thenThrow(new UserAlreadyExistedException());
        Assertions.assertThrows(UserAlreadyExistedException.class, () -> userService.saveUser(user));
        verify(userRepository).save(user);
    }
}