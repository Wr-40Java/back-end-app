package Wr40.cardiary.service;

import Wr40.cardiary.exception.UserAlreadyExistedException;
import Wr40.cardiary.exception.UserNotFoundException;
import Wr40.cardiary.model.entity.Car;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Test
    public void whenFindAll_shouldReturnListOFSizeOne_IfOneUserAdded(){
        List<User> list = new ArrayList<>();
        list.add(new User());
        Mockito.when(userRepository.findAll()).thenReturn(list);
        List<User> allUsers = userService.getAllUsers();
        Assertions.assertEquals(1,allUsers.size());

    }

    @Test
    public void whenFindAll_shouldReturnListOFSizeZero_IfNoUserAdded(){
        List<User> list = new ArrayList<>();
        Mockito.when(userRepository.findAll()).thenReturn(list);
        List<User> allUsers = userService.getAllUsers();
        Assertions.assertEquals(0,allUsers.size());
    }

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

    @Test
    public void whenDeletingExistingUserShouldDelete(){
        User user = new User();
        Mockito.when(userRepository.findUserByUsername(user.getUsername())).thenReturn(Optional.of(user));
        userService.deleteUser(user.getUsername());
        verify(userRepository).delete(user);
    }

    @Test
    public void whenDeletingNotExistingUserShouldThrowException(){
        User user = new User();
        Mockito.when(userRepository.findUserByUsername(user.getUsername())).thenThrow(new UserNotFoundException());
        Assertions.assertThrows(UserNotFoundException.class, () -> userService.deleteUser(user.getUsername()));
    }

    @Test
    public void whenDeleteAllUsersShouldDeleteAll(){
        userService.deleteAllUsers();
        verify(userRepository).deleteAll();
    }

    @Test
    public void whenWeFindingUserByUsername(){
        User user = new User();
        Mockito.when(userRepository.findUserByUsername(user.getUsername())).thenReturn(Optional.of(user));
        User sameUsername = userService.getUser(user.getUsername());
        Assertions.assertEquals(user,sameUsername);
        verify(userRepository).findUserByUsername(user.getUsername());
    }

    @Test
    public void whenDontFindUserByUsername(){
        User user = new User();
        Mockito.when(userRepository.findUserByUsername(user.getUsername())).thenThrow(new UserNotFoundException());
        Assertions.assertThrows(UserNotFoundException.class, () -> userService.getUser(user.getUsername()));
        verify(userRepository).findUserByUsername(user.getUsername());
    }

    @Test
    public void whenUpdateUser_shouldUpdateUser() {
        User user = new User();
        User user2 = new User();
        user2.setName("Buick");
        Mockito.when(userRepository.findUserByUsername(user.getName())).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(user)).thenReturn(user2);
        User updatedUser = userService.updateUser(user);
        Assertions.assertEquals("Buick", updatedUser.getName());
    }
}