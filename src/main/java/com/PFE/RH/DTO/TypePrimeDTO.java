package com.PFE.RH.DTO;

import lombok.Data;

@Data
public class TypePrimeDTO {
    private Long typePrimeId;
    private String code;
    private String libele;
    private String cnss;
    private Double impo; // Should be String, not Double
    private Double montant;
    private String type;
    private Boolean abasedesalaire;
    private String categorie;
    private String grp;
    private String grade;
    private Boolean obligatoire;

    // Getters and setters
    // Omitted for brevity

}
