package com.example.spring_security_jwt.Repositories;

import com.example.spring_security_jwt.Models.Animal;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByUserId(Long userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Animal a WHERE a.user.id = :userId")
    void deleteByUserId(Long userId);
}
