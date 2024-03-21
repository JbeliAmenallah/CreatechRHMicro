package com.PFE.RH.DTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EntrepriseDTO {
    private Long entrepriseId;
    private String nom;
    private String matricule;
    private String siegesociale;
    private List<ContactDTO> contacts; // List of ContactDTO associated with this Entreprise
    private List<GradeDTO> grades = new ArrayList<>();
    private List<GroupeDTO> groupes = new ArrayList<>();
    private List<CategoryDTO> categories = new ArrayList<>();
    // Getters and setters
    // Omitted for brevity
}
