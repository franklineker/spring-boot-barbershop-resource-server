package com.drnavalhabarbershop.resourceserver.service;

import com.drnavalhabarbershop.resourceserver.domain.Client;
import com.drnavalhabarbershop.resourceserver.domain.User;
import com.drnavalhabarbershop.resourceserver.mapper.ClientMapper;
import com.drnavalhabarbershop.resourceserver.mapper.UserMapper;
import com.drnavalhabarbershop.resourceserver.repository.UserRepository;
import com.drnavalhabarbershop.resourceserver.web.dto.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(UserRequest request) {

        if (Optional.ofNullable(userRepository.findByEmail(request.getEmail())).isPresent()){
            throw new RuntimeException("User already exists");
        }

        User user = UserMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCreatedDateTime(LocalDateTime.now());

        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByUserId(String id) {

        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found."));
    }

}
