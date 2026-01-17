package com.lntu.digitalhuman.init;

import com.lntu.digitalhuman.entity.User;
import com.lntu.digitalhuman.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer {
    
    private final UserService userService;
    
    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        log.info("开始初始化测试数据...");
        
        // 创建管理员用户
        createAdminUser();
        
        // 创建测试用户
        createTestUser();
        
        log.info("测试数据初始化完成");
    }
    
    private void createAdminUser() {
        String username = "admin";
        String password = "admin123";
        String email = "admin@example.com";
        
        Optional<User> existingUser = userService.findByUsername(username);
        if (existingUser.isPresent()) {
            log.info("管理员用户已存在: {}", username);
            return;
        }
        
        try {
            User user = userService.registerUser(username, password, email);
            log.info("✅ 管理员用户创建成功: {} (ID: {})", username, user.getId());
        } catch (Exception e) {
            log.error("❌ 创建管理员用户失败: {}", e.getMessage());
        }
    }
    
    private void createTestUser() {
        String username = "testuser";
        String password = "test123";
        String email = "test@example.com";
        
        Optional<User> existingUser = userService.findByUsername(username);
        if (existingUser.isPresent()) {
            log.info("测试用户已存在: {}", username);
            return;
        }
        
        try {
            User user = userService.registerUser(username, password, email);
            log.info("✅ 测试用户创建成功: {} (ID: {})", username, user.getId());
        } catch (Exception e) {
            log.error("❌ 创建测试用户失败: {}", e.getMessage());
        }
    }
}