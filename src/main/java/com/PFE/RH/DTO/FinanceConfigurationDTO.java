package com.PFE.RH.DTO;

import lombok.Data;

@Data
public class FinanceConfigurationDTO {

    private Long id;
    private String libele;
    private int year;
    private double taux;

    // Getters and setters
    // Omitted for brevity
}
