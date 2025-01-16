package com.example.spring_security_jwt.Repositories;

import com.example.spring_security_jwt.Models.MedicalReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalReportRepository extends JpaRepository<MedicalReport, Long> {
    List<MedicalReport> findByUserId(Long userId);
    List<MedicalReport> findByAnimalId(Long animalId);
}