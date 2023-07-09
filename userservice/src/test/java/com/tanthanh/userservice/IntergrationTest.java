package com.tanthanh.userservice;

import com.google.gson.Gson;
import com.tanthanh.userservice.data.User;
import com.tanthanh.userservice.data.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "application.properties")
class IntergrationTest {
    @LocalServerPort
    private int port;

    private static RestTemplate restTemplate;

    @MockBean
    UserRepository userRepository;

    private User user;
    Gson gson = new Gson();
    private String baseUrl = "http://localhost";
    @BeforeAll
    public static void init(){
        restTemplate = new RestTemplate();
    }
    @BeforeEach
    public void setUp(){
        user = new User(1L,"dev@gmail.com","123456","employeeID");
        baseUrl = baseUrl.concat(":").concat(port+"").concat("/api/v1/users");
    }
    @Test
    void ShouldGetAllUser(){
        List<User> users = new ArrayList<>();
        users.add(user);
        when(userRepository.findAll()).thenReturn(users);
        ResponseEntity<List> response = restTemplate.getForEntity(baseUrl.concat("/listUser"),List.class);
        System.out.println(gson.toJson(response.getBody()));
        System.out.println(response.getStatusCode());
        Assertions.assertEquals(gson.toJson(users),gson.toJson(response.getBody()));
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
    }
}
