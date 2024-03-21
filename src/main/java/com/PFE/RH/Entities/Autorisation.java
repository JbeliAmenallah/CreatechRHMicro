package com.PFE.RH.Entities;

import lombok.Data;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "autorisation")
public class Autorisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long autorisationId;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") // French date and time format
    private LocalDateTime dateDebut;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") // French date and time format
    private LocalDateTime dateFin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id")
    private Contact contact;

    private String state;

    // Getters and Setters (Omitted for brevity)
}
