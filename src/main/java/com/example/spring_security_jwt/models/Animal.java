package com.example.spring_security_jwt.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data // Generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor
@AllArgsConstructor
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String animalName;

    private int age;

    private String race;

    private String image;

    private String sexe;

    private int poids;
}
