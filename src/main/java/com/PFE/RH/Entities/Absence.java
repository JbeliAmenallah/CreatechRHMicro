package com.PFE.RH.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Data
@Table(name = "absences")
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long absenceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id")
    private Contact contact;

    private Date dateDebutAbsence;
    private Date dateFinAbsence;
    private String reason;

    // Getters and setters
    // Omitted for brevity
}
