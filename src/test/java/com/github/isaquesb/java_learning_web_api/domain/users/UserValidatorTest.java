package com.github.isaquesb.java_learning_web_api.domain.users;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserValidatorTest {

    private final UserValidator userValidator;

    public UserValidatorTest() {
        userValidator = new UserValidator();
    }

    @Test
    void isValidName() {

        User user = new User();
        user.setName("Isaque");

        assertTrue(userValidator.isValid(user));
        assertEquals(0, userValidator.getErrors().size());
    }

    @Test
    void isInvalid() {
        User user = new User();
        user.setName("Is");

        assertFalse(userValidator.isValid(user));
        assertEquals(1, userValidator.getErrors().size());
        assertEquals("Name must be at least 3 characters long", userValidator.getErrors().get(0));
    }

    @Test
    void getErrors() {

        User user = new User();
        user.setName("Isaque");

        assertTrue(userValidator.isValid(user));
        assertEquals(0, userValidator.getErrors().size());

        user.setName("Is");
        assertFalse(userValidator.isValid(user));
        assertEquals(1, userValidator.getErrors().size());
        assertEquals("Name must be at least 3 characters long", userValidator.getErrors().get(0));
    }
}
