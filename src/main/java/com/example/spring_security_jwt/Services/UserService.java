package com.example.spring_security_jwt.Services;

import com.example.spring_security_jwt.Models.User;
import com.example.spring_security_jwt.Repositories.AnimalRepository;
import com.example.spring_security_jwt.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AnimalRepository animalRepository;

    @Autowired
    public UserService(UserRepository userRepository, AnimalRepository animalRepository) {
        this.userRepository = userRepository;
        this.animalRepository = animalRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAllWithAnimals();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user.setResetToken(updatedUser.getResetToken());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void deleteUser(Long id) {
        animalRepository.deleteByUserId(id);
        userRepository.deleteById(id);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}