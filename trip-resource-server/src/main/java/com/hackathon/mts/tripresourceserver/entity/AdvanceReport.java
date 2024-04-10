package com.hackathon.mts.tripresourceserver.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "advance_reports")
@Data
@NoArgsConstructor
public class AdvanceReport {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "application_id")
    private int applicationId;

    @Column(name = "description")
    private String description;
}
