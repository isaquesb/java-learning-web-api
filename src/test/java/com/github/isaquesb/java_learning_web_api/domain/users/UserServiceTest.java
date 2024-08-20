package com.github.isaquesb.java_learning_web_api.domain.users;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Autowired
    @InjectMocks
    private UserService userService;

    @Test
    void save() {
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            user.setId(1);
            return user;
        });

        User user = new User();
        user.setUsername("isaquesb");
        user.setName("Isaque");
        user.setPassword("123456");

        User savedUser = userService.save(user);
        assertNotNull(savedUser.getId());
    }

    @Test
    void findAll() {
        Mockito.when(userRepository.findAll()).thenAnswer(invocation -> new LinkedList<>(Arrays.asList(
            new User(1, "John", "john"),
            new User(2, "Mary", "mary")
        )));
        assertEquals(2, userService.findAll().size());
    }

    @Test
    void findById() {
        Mockito.when(userRepository.findById(1)).thenAnswer(invocation -> new User(1, "John", "john"));

        assertEquals(1, userService.findById(1).getId());
    }
}
