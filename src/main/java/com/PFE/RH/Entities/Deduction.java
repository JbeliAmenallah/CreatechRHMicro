package com.PFE.RH.Entities;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
@Table(name = "deduction")
public class Deduction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int annee;
    private double salaire;
    private double montant;
    private int nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_deduction_id")
    private TypeDeduction typeDeduction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id")
    private Contact contact;

    // Getters and Setters (Omitted for brevity)
}
