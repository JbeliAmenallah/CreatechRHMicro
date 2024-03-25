package com.PFE.RH.DTO;

import lombok.Data;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Data
public class EntrepriseDTO {
    private Long entrepriseId;

    @NotBlank(message = "Nom is required")
    @Size(min = 2, max = 50, message = "Nom must be between 2 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "Nom must contain only letters, numbers, and spaces")
    private String nom;

    @NotBlank(message = "Matricule is required")
    @Size(min = 2, max = 20, message = "Matricule must be between 2 and 20 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Matricule must contain only letters and numbers")
    private String matricule;

    @NotBlank(message = "Siege Sociale is required")
    @Size(min = 2, max = 100, message = "Siege Sociale must be between 2 and 100 characters")
    private String siegesociale;
    private List<ContactDTO> contacts; // List of ContactDTO associated with this Entreprise
    private List<GradeDTO> grades = new ArrayList<>();
    private List<GroupeDTO> groupes = new ArrayList<>();
    private List<CategoryDTO> categories = new ArrayList<>();
    // Getters and setters
    // Omitted for brevity
}
