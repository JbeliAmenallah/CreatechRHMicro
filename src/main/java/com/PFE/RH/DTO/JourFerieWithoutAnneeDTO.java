package com.PFE.RH.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class JourFerieWithoutAnneeDTO {
    private Long id;
    private int jour;
    private int mois;
    private int year;
    private String libele;
}
