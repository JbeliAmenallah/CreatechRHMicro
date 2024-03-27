package com.PFE.RH.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "finance_configuration")
public class FinanceConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "libele")
    private String libele;

    @Column(name = "year")
    private int year;

    @Column(name = "taux")
    private double taux;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "annee_id") // Foreign key column
    private Annee annee;

    // Getters and setters
    // Omitted for brevity

    // Constructors, etc.
}
