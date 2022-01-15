package dev.gxsoft.blogrestapi2.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.gxsoft.blogrestapi2.model.User;
import dev.gxsoft.blogrestapi2.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Test
    void testLoginUser() {
        User user = new User();
        user.setComments(new ArrayList<>());
        user.setDeactivated(true);
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setLikedPosts(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRegisteredDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUserId(123L);
        Optional<User> ofResult = Optional.of(user);
        when(this.userRepository.findUserByEmailAndPassword((String) any(), (String) any())).thenReturn(ofResult);
        assertSame(user, this.userServiceImpl.loginUser("jane.doe@example.org", "iloveyou"));
        verify(this.userRepository).findUserByEmailAndPassword((String) any(), (String) any());
        assertTrue(this.userServiceImpl.getAllUsers().isEmpty());
    }

    @Test
    void testLoginUser2() {
        when(this.userRepository.findUserByEmailAndPassword((String) any(), (String) any()))
                .thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> this.userServiceImpl.loginUser("jane.doe@example.org", "iloveyou"));
        verify(this.userRepository).findUserByEmailAndPassword((String) any(), (String) any());
    }

    @Test
    void testLoginUser3() {
        when(this.userRepository.findUserByEmailAndPassword((String) any(), (String) any())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> this.userServiceImpl.loginUser("jane.doe@example.org", "iloveyou"));
        verify(this.userRepository).findUserByEmailAndPassword((String) any(), (String) any());
    }

    @Test
    void testRegisterUser() {
        User user = new User();
        user.setComments(new ArrayList<>());
        user.setDeactivated(true);
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setLikedPosts(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRegisteredDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUserId(123L);
        when(this.userRepository.save((User) any())).thenReturn(user);

        User user1 = new User();
        user1.setComments(new ArrayList<>());
        user1.setDeactivated(true);
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setLastName("Doe");
        user1.setLikedPosts(new ArrayList<>());
        user1.setPassword("iloveyou");
        user1.setPosts(new ArrayList<>());
        user1.setRegisteredDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user1.setUserId(123L);
        assertEquals("User registered", this.userServiceImpl.registerUser(user1));
        verify(this.userRepository).save((User) any());
        assertTrue(this.userServiceImpl.getAllUsers().isEmpty());
    }

    @Test
    void testRegisterUser2() {
        when(this.userRepository.save((User) any())).thenThrow(new RuntimeException("An error occurred"));

        User user = new User();
        user.setComments(new ArrayList<>());
        user.setDeactivated(true);
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setLikedPosts(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRegisteredDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUserId(123L);
        assertThrows(RuntimeException.class, () -> this.userServiceImpl.registerUser(user));
        verify(this.userRepository).save((User) any());
    }

    @Test
    void testDeactivateUser() {
        User user = new User();
        user.setComments(new ArrayList<>());
        user.setDeactivated(true);
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setLikedPosts(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRegisteredDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUserId(123L);
        Optional<User> ofResult = Optional.of(user);
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);

        User user1 = new User();
        user1.setComments(new ArrayList<>());
        user1.setDeactivated(true);
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setLastName("Doe");
        user1.setLikedPosts(new ArrayList<>());
        user1.setPassword("iloveyou");
        user1.setPosts(new ArrayList<>());
        user1.setRegisteredDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user1.setUserId(123L);
        assertEquals("User has already been Deactivated", this.userServiceImpl.deactivateUser(user1));
        verify(this.userRepository).findById((Long) any());
        assertTrue(this.userServiceImpl.getAllUsers().isEmpty());
    }

    @Test
    void testDeactivateUser2() {
        when(this.userRepository.findById((Long) any())).thenThrow(new RuntimeException("An error occurred"));

        User user = new User();
        user.setComments(new ArrayList<>());
        user.setDeactivated(true);
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setLikedPosts(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRegisteredDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUserId(123L);
        assertThrows(RuntimeException.class, () -> this.userServiceImpl.deactivateUser(user));
        verify(this.userRepository).findById((Long) any());
    }

    @Test
    void testDeactivateUser3() {
        User user = new User();
        user.setComments(new ArrayList<>());
        user.setDeactivated(false);
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setLikedPosts(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRegisteredDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUserId(123L);
        Optional<User> ofResult = Optional.of(user);

        User user1 = new User();
        user1.setComments(new ArrayList<>());
        user1.setDeactivated(true);
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setLastName("Doe");
        user1.setLikedPosts(new ArrayList<>());
        user1.setPassword("iloveyou");
        user1.setPosts(new ArrayList<>());
        user1.setRegisteredDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user1.setUserId(123L);
        when(this.userRepository.save((User) any())).thenReturn(user1);
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);

        User user2 = new User();
        user2.setComments(new ArrayList<>());
        user2.setDeactivated(true);
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        user2.setLikedPosts(new ArrayList<>());
        user2.setPassword("iloveyou");
        user2.setPosts(new ArrayList<>());
        user2.setRegisteredDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user2.setUserId(123L);
        assertEquals("User Saved", this.userServiceImpl.deactivateUser(user2));
        verify(this.userRepository).findById((Long) any());
        verify(this.userRepository).save((User) any());
        assertTrue(user2.isDeactivated());
        assertTrue(this.userServiceImpl.getAllUsers().isEmpty());
    }

    @Test
    void testDeactivateUser4() {
        User user = new User();
        user.setComments(new ArrayList<>());
        user.setDeactivated(false);
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setLikedPosts(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRegisteredDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUserId(123L);
        Optional<User> ofResult = Optional.of(user);
        when(this.userRepository.save((User) any())).thenThrow(new RuntimeException("An error occurred"));
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);

        User user1 = new User();
        user1.setComments(new ArrayList<>());
        user1.setDeactivated(true);
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setLastName("Doe");
        user1.setLikedPosts(new ArrayList<>());
        user1.setPassword("iloveyou");
        user1.setPosts(new ArrayList<>());
        user1.setRegisteredDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user1.setUserId(123L);
        assertThrows(RuntimeException.class, () -> this.userServiceImpl.deactivateUser(user1));
        verify(this.userRepository).findById((Long) any());
        verify(this.userRepository).save((User) any());
    }

    @Test
    void testDeactivateUser5() {
        User user = new User();
        user.setComments(new ArrayList<>());
        user.setDeactivated(true);
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setLikedPosts(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRegisteredDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUserId(123L);
        when(this.userRepository.save((User) any())).thenReturn(user);
        when(this.userRepository.findById((Long) any())).thenReturn(Optional.empty());

        User user1 = new User();
        user1.setComments(new ArrayList<>());
        user1.setDeactivated(true);
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setLastName("Doe");
        user1.setLikedPosts(new ArrayList<>());
        user1.setPassword("iloveyou");
        user1.setPosts(new ArrayList<>());
        user1.setRegisteredDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user1.setUserId(123L);
        assertEquals("User does not exist", this.userServiceImpl.deactivateUser(user1));
        verify(this.userRepository).findById((Long) any());
        assertTrue(this.userServiceImpl.getAllUsers().isEmpty());
    }

    @Test
    void testFindUserById() {
        User user = new User();
        user.setComments(new ArrayList<>());
        user.setDeactivated(true);
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setLikedPosts(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRegisteredDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUserId(123L);
        Optional<User> ofResult = Optional.of(user);
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(user, this.userServiceImpl.findUserById(123L));
        verify(this.userRepository).findById((Long) any());
        assertTrue(this.userServiceImpl.getAllUsers().isEmpty());
    }

    @Test
    void testFindUserById2() {
        when(this.userRepository.findById((Long) any())).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> this.userServiceImpl.findUserById(123L));
        verify(this.userRepository).findById((Long) any());
    }

    @Test
    void testFindUserById3() {
        when(this.userRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> this.userServiceImpl.findUserById(123L));
        verify(this.userRepository).findById((Long) any());
    }

    @Test
    void testUpdateUser() {
        User user = new User();
        user.setComments(new ArrayList<>());
        user.setDeactivated(true);
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setLikedPosts(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRegisteredDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUserId(123L);
        Optional<User> ofResult = Optional.of(user);

        User user1 = new User();
        user1.setComments(new ArrayList<>());
        user1.setDeactivated(true);
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setLastName("Doe");
        user1.setLikedPosts(new ArrayList<>());
        user1.setPassword("iloveyou");
        user1.setPosts(new ArrayList<>());
        user1.setRegisteredDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user1.setUserId(123L);
        when(this.userRepository.save((User) any())).thenReturn(user1);
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);

        User user2 = new User();
        user2.setComments(new ArrayList<>());
        user2.setDeactivated(true);
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        user2.setLikedPosts(new ArrayList<>());
        user2.setPassword("iloveyou");
        user2.setPosts(new ArrayList<>());
        user2.setRegisteredDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user2.setUserId(123L);
        assertSame(user1, this.userServiceImpl.updateUser(user2));
        verify(this.userRepository).findById((Long) any());
        verify(this.userRepository).save((User) any());
        assertTrue(this.userServiceImpl.getAllUsers().isEmpty());
    }

    @Test
    void testUpdateUser2() {
        User user = new User();
        user.setComments(new ArrayList<>());
        user.setDeactivated(true);
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setLikedPosts(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRegisteredDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUserId(123L);
        when(this.userRepository.save((User) any())).thenReturn(user);
        when(this.userRepository.findById((Long) any())).thenReturn(Optional.empty());

        User user1 = new User();
        user1.setComments(new ArrayList<>());
        user1.setDeactivated(true);
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setLastName("Doe");
        user1.setLikedPosts(new ArrayList<>());
        user1.setPassword("iloveyou");
        user1.setPosts(new ArrayList<>());
        user1.setRegisteredDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user1.setUserId(123L);
        assertThrows(RuntimeException.class, () -> this.userServiceImpl.updateUser(user1));
        verify(this.userRepository).findById((Long) any());
    }

    @Test
    void testGetAllUsers() {
        ArrayList<User> userList = new ArrayList<>();
        when(this.userRepository.findAll()).thenReturn(userList);
        List<User> actualAllUsers = this.userServiceImpl.getAllUsers();
        assertSame(userList, actualAllUsers);
        assertTrue(actualAllUsers.isEmpty());
        verify(this.userRepository).findAll();
    }

    @Test
    void testGetAllUsers2() {
        when(this.userRepository.findAll()).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> this.userServiceImpl.getAllUsers());
        verify(this.userRepository).findAll();
    }

    @Test
    void testDeleteAllUsers() {
        when(this.userRepository.findAll()).thenReturn(new ArrayList<>());
        this.userServiceImpl.deleteAllUsers();
        verify(this.userRepository).findAll();
        assertTrue(this.userServiceImpl.getAllUsers().isEmpty());
    }

    @Test
    void testDeleteAllUsers2() {
        when(this.userRepository.findAll()).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> this.userServiceImpl.deleteAllUsers());
        verify(this.userRepository).findAll();
    }

    @Test
    void testDeleteAllUsers3() {
        User user = new User();
        user.setComments(new ArrayList<>());
        user.setDeactivated(true);
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setLikedPosts(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRegisteredDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUserId(123L);

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        userList.addAll(new ArrayList<>());
        doNothing().when(this.userRepository).deleteAll();
        when(this.userRepository.findAll()).thenReturn(userList);
        this.userServiceImpl.deleteAllUsers();
        verify(this.userRepository).deleteAll();
        verify(this.userRepository).findAll();
        assertEquals(1, this.userServiceImpl.getAllUsers().size());
    }

    @Test
    void testDeleteAllUsers4() {
        User user = new User();
        user.setComments(new ArrayList<>());
        user.setDeactivated(true);
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setLikedPosts(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRegisteredDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUserId(123L);

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        userList.addAll(new ArrayList<>());
        doThrow(new RuntimeException("An error occurred")).when(this.userRepository).deleteAll();
        when(this.userRepository.findAll()).thenReturn(userList);
        assertThrows(RuntimeException.class, () -> this.userServiceImpl.deleteAllUsers());
        verify(this.userRepository).deleteAll();
        verify(this.userRepository).findAll();
    }
}

