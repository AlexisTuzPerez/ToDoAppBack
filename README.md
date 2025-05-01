## Overview

To-Do App is a task management system designed to help users organize and manage their daily tasks efficiently. This modern, RESTful API solution provides a seamless experience for task management with robust security features.

## Key Features

### API Endpoints
- 🔐 Secure authentication and authorization
- 📝 Comprehensive task management
- 📱 User management system

### Technical Highlights
- 🔐 Robust JWT-based authentication
- 🛡️ Role-based access control
- 📦 Modular architecture for easy maintenance

## Technical Stack

### Backend
- **Framework**: Spring Boot 3.4.2
- **Language**: Java 21
- **Database**: PostgreSQL
- **Security**: Spring Security with JWT
- **Validation**: Spring Validation
- **ORM**: Spring Data JPA
- **Dependency Injection**: Spring IoC

### Key Dependencies
```xml
- Spring Boot Web
- Spring Data JPA
- PostgreSQL Driver
- JWT (Java JWT)
- Lombok
- Spring Security
- Dotenv for environment variables
```

## Getting Started

### Prerequisites
- Java 21 or higher
- Maven 3.8.0 or higher
- PostgreSQL 14

### Installation
1. Clone the repository
```bash
git clone https://github.com/AlexisTuzPerez/ToDoAppBack.git
cd toDoAppBack
```


2. Configure PostgreSQL in `src/main/resources/application.yml`:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/toDoApp
    username: username
    password: password
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: false
    properties:
      hibernate:
        format_sql: true
    database: postgresql
    database-platform: org.hibernate.dialect.PostgreSQLDialect
```

3. Build and run the application
```bash
./mvnw clean install
./mvnw spring-boot:run
```

## Project Structure

```
toDoApp/
├── src/main/java/com/toDoApp/
│   ├── auth/            # Authentication and authorization
│   ├── task/            # Task management
│   ├── user/            # User management
│   ├── config/          # Application configuration
│   └── ToDoAppApplication.java
├── pom.xml
└── .env
```

## API Documentation

### Base URL

http://localhost:8080

### Swagger UI

Access the interactive API documentation at:

http://localhost:8080/swagger-ui/index.html

## License

This project is proprietary and confidential. All rights reserved.
