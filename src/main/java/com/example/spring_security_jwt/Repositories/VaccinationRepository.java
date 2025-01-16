package com.example.spring_security_jwt.Repositories;

import com.example.spring_security_jwt.Models.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccinationRepository extends JpaRepository<Vaccination, Long> {
    List<Vaccination> findByAnimalId(Long animalId);
}