package com.PFE.RH.Entities;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
public class Groupe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String libele;

    // Constructors, getters, and setters
}
