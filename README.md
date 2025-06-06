# UserManagementSystem-Secured_Api-s

# 👥 UserSphere - User Management System

Welcome to **UserSphere**, a simple and powerful user management system built with Spring Boot. It’s designed to help you easily handle user registration, login, security, and role-based access control—all running smoothly on an in-memory H2 database. Perfect for quick testing and development without any complicated setup.

---

## What Can You Do With UserSphere?

- Register new users  
- Log in securely with JWT tokens  
- Assign roles like Admin and User, controlling what each can do  
- Store passwords safely with encryption  
- View and manage users easily through REST APIs  
- Run the app quickly without external database dependencies

---

## Core Technologies

Here's a quick peek at what powers UserSphere:

| Layer                | Tools/Technologies                       |
|----------------------|------------------------------------------|
| Programming Language | Java 17                                  |
| Framework            | Spring Boot, Spring Security             |
| Data Access          | Spring Data JPA                          |
| Security             | JWT, BCrypt                              |
| Object Mapping       | ModelMapper                              |
| Database             | H2 (In-Memory)                           |

---

## How the Project Is Organized

Here's a simple overview of the folder structure:

```
com.userSphere
├── controllers       # REST API endpoints
├── entities          # Database entity classes
├── pojos             # Data Transfer Objects (DTOs)
├── repositories      # Database repositories
├── security          # Security configs and JWT setup
├── services          # Service interfaces
├── serviceImplementations # Business logic implementations
├── shared            # Constants for roles and authorities
├── singletonClasses  # Beans like ModelMapper and password encoder
├── constants         # Token headers and dynamic security values
├── exceptionHandler  # Custom exception handling
```

---

## Getting Started — It’s Easy!

### 1. Clone the Repository

Open your terminal and run:

```bash
git clone https://github.com/PURNA1224/UserManagementSystem-Secured_Api-s.git
cd usersphere
```

### 2. Launch the Application

Make sure Java 17+ and Maven are installed. Then start the app with:

```bash
./mvnw spring-boot:run
```

### 3. Access the H2 Database Console

Once the app is running, head over to:

http://localhost:8080/h2-console

Use these connection details:

- JDBC URL: `jdbc:h2:mem:userSphereDB`  
- Username: `sa`  
- Password: *(leave blank)*

---

## Roles & Permissions

- **Roles:**  
  - `ROLE_USER` — regular users  
  - `ROLE_ADMIN` — administrators

- **Authorities:**  
  - `READ_AUTHORITY` — read data  
  - `WRITE_AUTHORITY` — create/update data  
  - `DELETE_AUTHORITY` — delete data

---

## API Endpoints

| Endpoint           | Method | Description                               |
|--------------------|---------|-------------------------------------------|
| `/register`       | POST    | Register a new user                       |
| `/login`          | POST    | Authenticate and receive JWT token        |
| `/users`          | GET     | Get list of all users                     |
| `/users/{id}`     | GET     | Get details of a specific user           |
| `/users/{id}`     | DELETE  | Delete a user by ID                      |


---

## Security Details

- Authentication is handled via JWT tokens, making your app stateless  
- Spring Security manages access control based on user roles and authorities  
- User passwords are securely encrypted with BCrypt
- Custom `UsersPrincipal` class implements user details for security

---

## Configuration & Customization

- All settings are managed through environment variables and the `AppProperties` class  
- ModelMapper is configured with strict matching to avoid errors  

---

## About the Developer

**Kandiboina Purna Sekhar**  
Java Full-Stack Developer  
📧 Email: purnashekar777@gmail.com

---

**That’s it!** You now have everything you need to clone, run, and start exploring UserSphere — your quick and easy user management solution. Happy coding!
