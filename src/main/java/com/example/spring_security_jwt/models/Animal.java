package com.example.spring_security_jwt.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Data // Generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor
@AllArgsConstructor
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private String race;
    private String image;
    private String gender;
    private int weight;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)  // Foreign key to the 'users' table
    private User user;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vaccination> vaccinations = new ArrayList<>();

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicalReport> medicalReports = new ArrayList<>();

}
