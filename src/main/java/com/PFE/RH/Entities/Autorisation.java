package com.PFE.RH.Entities;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "autorisation")
public class Autorisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long autorisationId;

    private LocalDateTime dateDebut;
    private LocalDateTime dateFin; // New attribute

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id")
    private Contact contact;

    private String state;

    // Getters and Setters (Omitted for brevity)
}
