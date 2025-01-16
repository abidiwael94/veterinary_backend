package com.example.spring_security_jwt.Repositories;

import com.example.spring_security_jwt.Models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByUserId(Long userId);
}
