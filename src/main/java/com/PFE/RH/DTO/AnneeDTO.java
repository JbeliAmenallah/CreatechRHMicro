package com.PFE.RH.DTO;

import lombok.Data;

import java.util.Set;

@Data
public class AnneeDTO {
    private Long id;
    private String dateDebutExercice;
    private String libele;
    private Set<JourFerieWithoutAnneeDTO> jourFerieDTOs; // Include this field
}
