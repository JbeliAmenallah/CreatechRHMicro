package com.PFE.RH.DTO;

import lombok.Data;

@Data
public class DeductionDTO {
    private Long id;
    private int annee;
    private double salaire;
    private double montant;
    private int nombre;
    private TypeDeductionDTO typeDeduction;
}
