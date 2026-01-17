package com.lntu.digitalhuman.controller;

import com.lntu.digitalhuman.dto.LoginRequest;
import com.lntu.digitalhuman.dto.LoginResponse;
import com.lntu.digitalhuman.dto.RegisterRequest;
import com.lntu.digitalhuman.entity.User;
import com.lntu.digitalhuman.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final UserService userService;
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            Optional<User> userOptional = userService.authenticateByEmail(
                loginRequest.getEmail(), 
                loginRequest.getPassword()
            );
            
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                // 这里简化处理，实际项目中应该生成JWT token
                String token = generateSimpleToken(user.getUsername());
                
                LoginResponse response = new LoginResponse(token, user.getUsername(), user.getRole());
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest()
                    .body(new LoginResponse(false, "用户名或密码错误"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(new LoginResponse(false, "登录失败: " + e.getMessage()));
        }
    }
    
    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@RequestBody RegisterRequest registerRequest) {
        try {
            User user = userService.registerUser(
                registerRequest.getUsername(),
                registerRequest.getPassword(),
                registerRequest.getEmail()
            );
            
            String token = generateSimpleToken(user.getUsername());
            LoginResponse response = new LoginResponse(token, user.getUsername(), user.getRole());
            return ResponseEntity.ok(response);
            
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                .body(new LoginResponse(false, "注册失败: " + e.getMessage()));
        }
    }
    
    @GetMapping("/check-username/{username}")
    public ResponseEntity<Boolean> checkUsername(@PathVariable String username) {
        Optional<User> user = userService.findByUsername(username);
        return ResponseEntity.ok(user.isPresent());
    }
    
    private String generateSimpleToken(String username) {
        // 简化版token生成，实际项目中应该使用JWT
        return "token_" + username + "_" + System.currentTimeMillis();
    }
}