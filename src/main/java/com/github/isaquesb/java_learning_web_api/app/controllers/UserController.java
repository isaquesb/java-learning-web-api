package com.github.isaquesb.java_learning_web_api.app.controllers;

import com.github.isaquesb.java_learning_web_api.domain.common.exceptions.ValidationException;
import com.github.isaquesb.java_learning_web_api.domain.common.exceptions.NotFoundException;
import com.github.isaquesb.java_learning_web_api.domain.users.User;
import com.github.isaquesb.java_learning_web_api.domain.users.UserService;
import com.github.isaquesb.java_learning_web_api.domain.users.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping
    public List<User> index() {
        return userService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        if (!userValidator.isValid(user)) {
            throw new ValidationException(userValidator.getErrors().toString());
        }
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable("id") Integer id, @RequestBody User user) {
        user.setId(id);
        if (!userValidator.isValid(user)) {
            throw new ValidationException(userValidator.getErrors().toString());
        }
        return userService.save(user);
    }

    @GetMapping("/{id}")
    public User show(@PathVariable("id") Integer id) {
        User user = userService.findById(id);

        if (null == user) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        return user;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        userService.delete(id);
    }
}
