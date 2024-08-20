
package com.github.isaquesb.java_learning_web_api.domain.users;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
class UserServiceDeleteTest {

    @Spy
    private UserRepository userRepository;

    @Autowired
    @InjectMocks
    private UserService userService;

    @Test
    void delete() {
        userService.delete(1);
        Mockito.verify(userRepository, Mockito.times(1)).delete(1);
    }
}
