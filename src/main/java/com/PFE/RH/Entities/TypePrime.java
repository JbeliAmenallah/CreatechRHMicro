package com.PFE.RH.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
public class TypePrime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long typePrimeId;

    private String code;
    private String libele;
    private String cnss;
    private Double impo;
    private Double montant;
    private String type;

    @Column(columnDefinition = "BOOLEAN")
    private Boolean abasedesalaire;

    private String categorie;
    private String grp;
    private String grade;

    @Column(columnDefinition = "BOOLEAN")
    private Boolean obligatoire;

    // Getters and setters
    // Omitted for brevity
}
