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

    // Getters and setters
    // Omitted for brevity

    // Constructors, etc.
}
