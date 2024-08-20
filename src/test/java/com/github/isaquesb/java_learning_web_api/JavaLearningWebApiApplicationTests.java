package com.github.isaquesb.java_learning_web_api;

import com.github.isaquesb.java_learning_web_api.app.controllers.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JavaLearningWebApiApplicationTests {

    @Autowired
    private UserController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }
}
