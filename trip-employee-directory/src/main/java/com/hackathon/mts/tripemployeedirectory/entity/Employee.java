package com.hackathon.mts.tripemployeedirectory.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String name;
    private String surname;
    private String patronymic;
    private String phone;
    private String email;
    private String passportSeria;
    private String passportNumber;
    private String position;
    @OneToOne
    private Role role;
    @ManyToOne
    private Employee master;
}
