package br.com.drnavalha.web.controller;

import br.com.drnavalha.mapper.UserMapper;
import br.com.drnavalha.service.UserService;
import br.com.drnavalha.web.dto.UserRequest;
import br.com.drnavalha.web.dto.UserResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody @Valid UserRequest request) {
        return UserMapper.toUserResponse(userService.createUser(request));
    }

}
