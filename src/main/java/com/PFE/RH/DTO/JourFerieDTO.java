package com.PFE.RH.DTO;

import lombok.Data;

@Data
public class JourFerieDTO {
    private Long id;
    private int jour;
    private int mois;
    private int year;
    private String libele;
    private Long anneeId; // To hold the ID of associated Annee
}
