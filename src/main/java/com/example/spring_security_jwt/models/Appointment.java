package com.example.spring_security_jwt.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private String time;
    private int tel;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)  // Foreign key to the 'users' table
    private User user;
}
