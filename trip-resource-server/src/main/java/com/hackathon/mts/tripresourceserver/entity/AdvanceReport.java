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
    private long id;

    @JoinColumn(name = "application_id")
    private long applicationId;

    @Column(name = "description")
    private String description;
}
