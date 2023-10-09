package br.com.drnavalha.service;

import br.com.drnavalha.domain.User;
import br.com.drnavalha.mapper.UserMapper;
import br.com.drnavalha.repository.UserRepository;
import br.com.drnavalha.web.dto.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

}
