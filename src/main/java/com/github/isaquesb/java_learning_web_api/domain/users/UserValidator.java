package com.github.isaquesb.java_learning_web_api.domain.users;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class UserValidator {
    private List<String> errors = new LinkedList<>();

    public Boolean isValid(User user) {
        errors.clear();
        if (null == user) {
            errors.add("User cannot be null");
            return false;
        }

        if (user.getName().length() < 3) {
            errors.add("Name must be at least 3 characters long");
        }

        return errors.isEmpty();
    }

    public List<String> getErrors() {
        return errors;
    }
}
