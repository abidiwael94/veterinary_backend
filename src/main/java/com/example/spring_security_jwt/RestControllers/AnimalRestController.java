package com.example.spring_security_jwt.RestControllers;

import com.example.spring_security_jwt.Models.Animal;
import com.example.spring_security_jwt.Services.AnimalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/animals")
public class AnimalRestController {

    private static final Logger logger = LoggerFactory.getLogger(AnimalRestController.class);

    @Autowired
    private final AnimalService animalService;

    public AnimalRestController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public ResponseEntity<List<Animal>> getAllAnimals() {
        logger.debug("Fetching all animals");
        List<Animal> animals = animalService.getAllAnimals();
        if (animals.isEmpty()) {
            logger.warn("No animals found");
            return ResponseEntity.noContent().build();
        }
        logger.debug("Retrieved {} animals", animals.size());
        return ResponseEntity.ok(animals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable Long id) {
        logger.debug("Fetching animal by ID: {}", id);
        Optional<Animal> animal = animalService.getAnimalById(id);
        if (animal.isPresent()) {
            logger.debug("Animal found: {}", animal.get());
            return ResponseEntity.ok(animal.get());
        } else {
            logger.warn("Animal with ID {} not found", id);
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public ResponseEntity<Animal> createAnimal(@RequestBody Animal animal) {
        logger.debug("Creating animal with name: {}", animal.getName());

        Animal savedAnimal = animalService.createAnimal(animal);
        logger.debug("Animal created successfully with ID: {}", savedAnimal.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAnimal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> updateAnimal(@PathVariable Long id, @RequestBody Animal updatedAnimal) {
        logger.debug("Updating animal with ID: {}", id);
        try {
            Animal animal = animalService.updateAnimal(id, updatedAnimal);
            logger.debug("Animal updated successfully: {}", animal);
            return ResponseEntity.ok(animal);
        } catch (RuntimeException e) {
            logger.error("Error updating animal with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAnimal(@PathVariable Long id) {
        logger.debug("Attempting to delete animal with ID: {}", id);
        try {
            animalService.deleteAnimal(id);
            logger.debug("Animal with ID {} deleted successfully", id);
            return ResponseEntity.ok("Animal with ID " + id + " has been successfully deleted.");
        } catch (Exception e) {
            logger.error("Error occurred while deleting animal with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete animal with ID " + id + ": " + e.getMessage());
        }
    }
}