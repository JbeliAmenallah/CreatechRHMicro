package com.PFE.RH.Entities;

import com.PFE.RH.DTO.ContactDTO;
import lombok.Data;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long entrepriseId;
    private String nom;
    private String matricule;
    private String siegesociale;

    @OneToMany(mappedBy = "entreprise", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();

    // Getters and setters
    // Omitted for brevity
}
