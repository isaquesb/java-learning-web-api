package com.github.isaquesb.java_learning_web_api.domain.users;

import com.github.isaquesb.java_learning_web_api.domain.common.exceptions.NotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Repository
public class UserRepository {
    private List<User> userList = new LinkedList<>(Arrays.asList(
        new User(1, "John", "john"),
        new User(2, "Mary", "mary")
    ));

    public User save(@NotNull User user) {
        if (null == user.getId()) {
            user.setId(userList.size() + 1);
            userList.add(user);
            return user;
        }

        User oldUser = findById(user.getId());

        if (null == oldUser) {
            throw new NotFoundException();
        }

        oldUser.setName(user.getName());
        oldUser.setUsername(user.getUsername());

        return oldUser;
    }

    public List<User> findAll() {
        return userList;
    }

    public User findById(Integer id) {
        return userList.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    public void delete(Integer id) throws IllegalArgumentException {
        User user = findById(id);

        if (null == user) {
            throw new NotFoundException();
        }

        userList = userList.stream().filter(u -> !u.getId().equals(id)).toList();
    }
}
