package com.lntu.digitalhuman.service;

import com.lntu.digitalhuman.entity.User;
import com.lntu.digitalhuman.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public User registerUser(String username, String password, String email) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("用户名已存在");
        }
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("邮箱已被注册");
        }
        
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setRole("USER");
        user.setEnabled(true);
        user.setCreatedAt(LocalDateTime.now());
        
        return userRepository.save(user);
    }
    
    public Optional<User> authenticateByEmail(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword()) && user.isEnabled()) {
                user.setLastLoginAt(LocalDateTime.now());
                userRepository.save(user);
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
    
    public Optional<User> authenticateByUsername(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword()) && user.isEnabled()) {
                user.setLastLoginAt(LocalDateTime.now());
                userRepository.save(user);
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
    
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}