# Digital Human Project

## Project Overview

This is a digital human management system based on Spring Boot and MongoDB, providing user authentication, permission management, and digital human-related functions.

## Technology Stack

- **Backend Framework**: Spring Boot 3.0.2

- **Database**: MongoDB

- **Security Framework**: Spring Security

- **Build Tool**: Maven

- **Java Version**: 17


## Project Structure

```plaintext
src/main/java/com/lntu/digitalhuman/
├── DigitalHumanApplication.java     # Application startup class
├── config/                          # Configuration class directory
│   ├── SecurityConfig.java          # Security configuration
│   └── CorsConfig.java              # CORS configuration
├── controller/                      # Controller directory
│   └── AuthController.java          # Authentication controller
├── dto/                             # Data Transfer Object directory
│   ├── LoginRequest.java            # Login request DTO
│   ├── LoginResponse.java           # Login response DTO
│   └── RegisterRequest.java         # Registration request DTO
├── entity/                          # Entity class directory
│   └── User.java                    # User entity
├── init/                            # Initialization class directory
│   └── DataInitializer.java         # Data initialization class
├── repository/                      # Data access layer directory
│   └── UserRepository.java          # User repository
└── service/                         # Business logic layer directory
    └── UserService.java             # User service
```

## Core Function Modules

### 1. User Authentication Module

- **User Registration**: Supports registration with username, password, and email

- **User Login**: Authentication based on username and password

- **Permission Management**: Role-Based Access Control (RBAC)


### 2. Security Configuration Module

- **Spring Security Integration**: Provides a complete security authentication mechanism

- **CORS Configuration**: Supports cross-origin request access

- **Stateless Session**: Uses STATELESS session management

- **Password Encryption**: BCrypt password encryption and storage


### 3. Data Persistence Module

- **MongoDB Integration**: Uses Spring Data MongoDB

- **User Data Storage**: User information is stored in MongoDB

- **Data Initialization**: Automatically initializes test data when the application starts


## Special Notes

- **Default Users**: The application initializes two default users when starting (one login method: Username: admin, Password: admin123). For details, please refer to `init/DataInitializer.java`

- **Password Encryption**: All user passwords are encrypted and stored using BCrypt


## API Documentation

### Authentication-Related Interfaces

#### User Login

- **URL**: `POST /api/auth/login`

- **Request Body**:

  ```
    `{
  ```

  "username": "string",
  "password": "string"
  }`

- **Response**:

  ```
    `{
  ```

  "token": "string",
  "username": "string",
  "role": "string",
  "success": true
  }`


#### User Registration

- **URL**: `POST /api/auth/register`

- **Request Body**:

  ```
    `{
  ```

  "username": "string",
  "password": "string",
  "email": "string"
  }`


#### Username Check

- **URL**: `GET /api/auth/check-username/{username}`

- **Response**: `boolean` (Indicates whether the username already exists)


## Security Configuration Instructions

### Authentication Strategy

- Uses Spring Security for authentication

- Supports authentication based on username and password

- Uses BCrypt for password encryption


### Permission Control

- `/api/auth/**` - Public access, no authentication required

- `/api/public/**` - Public access, no authentication required

- All other requests require authentication


### CORS Configuration

- Allows cross-origin requests from all sources

- Supports all HTTP methods (GET, POST, PUT, DELETE, OPTIONS, PATCH)

- Allows all request headers

- Supports credential carrying


## Database Configuration

```yaml
spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/digital_human_db
```

## Startup Instructions

### Environment Requirements

- Java 17+

- MongoDB 4.4+

- Maven 3.6+


### Startup Steps

1. **Start MongoDB Service**`mongod --dbpath=data --logpath=db_log/mongodb.log`

2. **Compile the Project**`mvn clean compile`

3. **Run the Application**`mvn spring-boot:run`

4. **Access the Application**Application Address: http://localhost:8080

5. API Documentation: Accessible via Swagger UI (if configured)


## Development Instructions

### Project Features

- **Modular Design**: Clear hierarchical structure for easy maintenance and expansion

- **High Security**: Complete authentication and authorization mechanism

- **RESTful API**: Standard REST API design

- **Cross-Platform Support**: Supports Windows, Linux, and macOS


### Expansion Suggestions

- Integrate JWT Token authentication

- Add Swagger API documentation

- Implement file upload function

- Add logging and monitoring

- Integrate caching mechanism (Redis)


## Troubleshooting

### Common Issues

1. **403 Forbidden Error**Check if CORS configuration is correct

2. Verify that the request header contains necessary information

3. **Database Connection Failure**Ensure the MongoDB service is started

4. Check the database connection configuration

5. **Authentication Failure**Verify that the username and password are correct

6. Check the user role and permission settings


## Contribution Guidelines

Contributions are welcome via Issues and Pull Requests to improve the project.

## License

This project is licensed under the MIT License.

---

**Project Maintainer**: LNTU Digital Human Team
**Last Updated**: December 2025
**Version**: 1.0.0

---
**FROM THIS BELOW IS THE CHINESE VERSION OF THE README.md FILE**

**By the way this README.md file is made by David Neen, but all guys can redistribute it and modify it. Thanks guys.**

**顺带一提，这个README.md文件是宁德瑞搞的，但是所有的人都可以重新提交它并修改它。射射。**

**从这里往下是中文版本的README.md文件**

# 数字人项目 (Digital Human)

## 项目概述

这是一个基于Spring Boot和MongoDB的数字人管理系统，提供用户认证、权限管理和数字人相关功能。

## 技术栈

- **后端框架**: Spring Boot 3.0.2
- **数据库**: MongoDB
- **安全框架**: Spring Security
- **构建工具**: Maven
- **Java版本**: 17

## 项目结构

```
src/main/java/com/lntu/digitalhuman/
├── DigitalHumanApplication.java     # 应用启动类
├── config/                          # 配置类目录
│   ├── SecurityConfig.java          # 安全配置
│   └── CorsConfig.java              # 跨域配置
├── controller/                      # 控制器目录
│   └── AuthController.java          # 认证控制器
├── dto/                            # 数据传输对象
│   ├── LoginRequest.java           # 登录请求
│   ├── LoginResponse.java          # 登录响应
│   └── RegisterRequest.java        # 注册请求
├── entity/                         # 实体类
│   └── User.java                   # 用户实体
├── init/                           # 初始化类
│   └── DataInitializer.java        # 数据初始化
├── repository/                     # 数据访问层
│   └── UserRepository.java         # 用户仓库
└── service/                        # 业务逻辑层
    └── UserService.java            # 用户服务
```

## 核心功能模块

### 1. 用户认证模块

- **用户注册**: 支持用户名、密码、邮箱注册
- **用户登录**: 基于用户名和密码的认证
- **权限管理**: 基于角色的访问控制

### 2. 安全配置模块

- **Spring Security集成**: 提供完整的安全认证机制
- **CORS配置**: 支持跨域请求访问
- **无状态会话**: 使用STATELESS会话管理
- **密码加密**: BCrypt密码加密存储

### 3. 数据持久化模块

- **MongoDB集成**: 使用Spring Data MongoDB
- **用户数据存储**: 用户信息存储在MongoDB中
- **数据初始化**: 应用启动时自动初始化测试数据

## 特别说明
- **默认用户**: 应用启动时会初始化两个默认用户（其中一个登录方式:用户名：admin，密码：admin123）详情请查看`init/DataInitializer.java`
- **密码加密**: 所有用户密码均采用BCrypt加密存储

## API接口文档

### 认证相关接口

#### 用户登录
- **URL**: `POST /api/auth/login`
- **请求体**:
```json
{
    "username": "string",
    "password": "string"
}
```
- **响应**:
```json
{
    "token": "string",
    "username": "string",
    "role": "string",
    "success": true
}
```

#### 用户注册
- **URL**: `POST /api/auth/register`
- **请求体**:
```json
{
    "username": "string",
    "password": "string",
    "email": "string"
}
```

#### 用户名检查
- **URL**: `GET /api/auth/check-username/{username}`
- **响应**: `boolean` (用户名是否已存在)

## 安全配置说明

### 认证策略
- 使用Spring Security进行身份验证
- 支持基于用户名和密码的认证
- 使用BCrypt进行密码加密

### 权限控制
- `/api/auth/**` - 公开访问，无需认证
- `/api/public/**` - 公开访问，无需认证
- 其他所有请求需要认证

### 跨域配置
- 允许所有来源的跨域请求
- 支持所有HTTP方法（GET、POST、PUT、DELETE、OPTIONS、PATCH）
- 允许所有请求头
- 支持凭证携带

## 数据库配置

```yaml
spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/digital_human_db
```

## 启动说明

### 环境要求
- Java 17+
- MongoDB 4.4+
- Maven 3.6+

### 启动步骤

1. **启动MongoDB服务**
   ```bash
   mongod --dbpath=data --logpath=db_log/mongodb.log
   ```

2. **编译项目**
   ```bash
   mvn clean compile
   ```

3. **运行应用**
   ```bash
   mvn spring-boot:run
   ```

4. **访问应用**
   - 应用地址: http://localhost:8080
   - API文档: 可通过Swagger UI访问（如配置）

## 开发说明

### 项目特点
- **模块化设计**: 清晰的层次结构，便于维护和扩展
- **安全性强**: 完整的认证和授权机制
- **RESTful API**: 标准的REST API设计
- **跨平台支持**: 支持Windows、Linux、macOS

### 扩展建议
- 集成JWT Token认证
- 添加Swagger API文档
- 实现文件上传功能
- 添加日志记录和监控
- 集成缓存机制（Redis）

## 故障排除

### 常见问题

1. **403 Forbidden错误**
   - 检查CORS配置是否正确
   - 验证请求头是否包含必要信息

2. **数据库连接失败**
   - 确认MongoDB服务已启动
   - 检查数据库连接配置

3. **认证失败**
   - 验证用户名和密码是否正确
   - 检查用户角色权限设置

## 贡献指南

欢迎提交Issue和Pull Request来改进项目。

## 许可证

本项目采用MIT许可证。

---

**项目维护者**: LNTU Digital Human Team  
**最后更新**: 2025年12月  
**版本**: 1.0.0