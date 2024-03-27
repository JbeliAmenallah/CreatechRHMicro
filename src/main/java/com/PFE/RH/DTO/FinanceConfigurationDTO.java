package com.PFE.RH.DTO;

import com.PFE.RH.Entities.Annee;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class FinanceConfigurationDTO {

    private Long id;
    private String libele;
    private int year;
    private double taux;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "annee_id")
    private Long anneeID; // This will be the ID of Annee    // Getters and setters
    // Omitted for brevity
}
