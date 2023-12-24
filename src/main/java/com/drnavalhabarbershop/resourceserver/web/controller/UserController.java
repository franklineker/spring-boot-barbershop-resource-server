package com.drnavalhabarbershop.resourceserver.web.controller;

import com.drnavalhabarbershop.resourceserver.domain.User;
import com.drnavalhabarbershop.resourceserver.mapper.UserMapper;
import com.drnavalhabarbershop.resourceserver.service.UserService;
import com.drnavalhabarbershop.resourceserver.web.dto.UserRequest;
import com.drnavalhabarbershop.resourceserver.web.dto.UserResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody @Valid UserRequest request) {
        return UserMapper.toUserResponse(userService.createUser(request));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<UserResponse> findUsers() {

        List<User> users = userService.findAll();
        System.out.println(users);
        List<UserResponse> userResponses = users.stream().map(user -> UserMapper.toUserResponse(user)).collect(Collectors.toList());
        System.out.println(userResponses);

        return userResponses;

    }

}
