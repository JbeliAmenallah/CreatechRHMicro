package com.PFE.RH.Entities;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
public class FicheDePaie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double salaireNet;
    private double erpp;
    private double css;
    private double prime;
    private int year;
    private int month;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

    // Constructors, Getters, and Setters (omitted for brevity)
}
