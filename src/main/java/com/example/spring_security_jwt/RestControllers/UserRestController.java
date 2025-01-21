package com.example.spring_security_jwt.RestControllers;

import com.example.spring_security_jwt.Models.User;
import com.example.spring_security_jwt.Services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserRestController {

    @Autowired
    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        logger.debug("Fetching all users");
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            logger.warn("No users found");
            return ResponseEntity.noContent().build();
        }
        logger.debug("Retrieved {} users", users.size());
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        logger.debug("Fetching user by ID: {}", id);
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            logger.debug("User found: {}", user.get());
            return ResponseEntity.ok(user.get());
        } else {
            logger.warn("User with ID {} not found", id);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        logger.debug("Creating user with username: {}", user.getUsername());

        if (userService.existsByUsername(user.getUsername())) {
            logger.warn("Username '{}' already exists", user.getUsername());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }

        if (userService.existsByEmail(user.getEmail())) {
            logger.warn("Email '{}' already exists", user.getEmail());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }

        User savedUser = userService.createUser(user);
        logger.debug("User created successfully with ID: {}", savedUser.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        logger.debug("Updating user with ID: {}", id);
        try {
            User user = userService.updateUser(id, updatedUser);
            logger.debug("User updated successfully: {}", user);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            logger.error("Error updating user with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        logger.debug("Attempting to delete user with ID: {}", id);
        try {
            userService.deleteUser(id);
            logger.debug("User with ID {} deleted successfully", id);
            return ResponseEntity.ok("User with ID " + id + " has been successfully deleted.");
        } catch (Exception e) {
            logger.error("Error occurred while deleting user with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete user with ID " + id + ": " + e.getMessage());
        }
    }

}