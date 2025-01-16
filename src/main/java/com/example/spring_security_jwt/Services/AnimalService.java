package com.example.spring_security_jwt.Services;

import com.example.spring_security_jwt.Models.Animal;
import com.example.spring_security_jwt.Repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    public Optional<Animal> getAnimalById(Long id) {
        return animalRepository.findById(id);
    }

    public List<Animal> getAnimalsByUserId(Long userId) {
        return animalRepository.findByUserId(userId);
    }

    public Animal createAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    public Animal updateAnimal(Long id, Animal updatedAnimal) {
        return animalRepository.findById(id).map(animal -> {
            animal.setName(updatedAnimal.getName());
            animal.setAge(updatedAnimal.getAge());
            animal.setRace(updatedAnimal.getRace());
            animal.setImage(updatedAnimal.getImage());
            animal.setGender(updatedAnimal.getGender());
            animal.setWeight(updatedAnimal.getWeight());
            return animalRepository.save(animal);
        }).orElseThrow(() -> new RuntimeException("Animal not found"));
    }

    public void deleteAnimal(Long id) {
        animalRepository.deleteById(id);
    }
}