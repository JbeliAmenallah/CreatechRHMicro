package com.PFE.RH.DTO;

import lombok.Data;

@Data
public class ImpotDTO {
    private Long id;
    private String libele;
    private double taux;
    private AnneeDTO anneeDTO;
}
