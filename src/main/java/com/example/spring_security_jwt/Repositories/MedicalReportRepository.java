package com.example.spring_security_jwt.Repositories;

import com.example.spring_security_jwt.Models.MedicalReport;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalReportRepository extends JpaRepository<MedicalReport, Long> {
    List<MedicalReport> findByUserId(Long userId);
    List<MedicalReport> findByAnimalId(Long animalId);
    @Modifying
    @Transactional
    @Query("DELETE FROM MedicalReport m WHERE m.animal.id = :animalId")
    void deleteByAnimalId(Long animalId);

}