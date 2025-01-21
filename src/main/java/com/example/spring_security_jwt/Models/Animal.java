package com.example.spring_security_jwt.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "animal", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Vaccination> vaccinations = new ArrayList<>();

    @OneToMany(mappedBy = "animal", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "animal-medical-report")
    private List<MedicalReport> medicalReports = new ArrayList<>();


}
