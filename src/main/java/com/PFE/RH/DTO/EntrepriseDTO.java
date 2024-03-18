package com.PFE.RH.DTO;

import lombok.Data;

import java.util.List;

@Data
public class EntrepriseDTO {
    private Long entrepriseId;
    private String nom;
    private String matricule;
    private String siegesociale;
    private List<ContactDTO> contacts; // List of ContactDTO associated with this Entreprise

    // Getters and setters
    // Omitted for brevity
}
