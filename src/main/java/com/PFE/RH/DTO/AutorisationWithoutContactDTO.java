package com.PFE.RH.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AutorisationWithoutContactDTO {
    private Long autorisationId;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin; // New attribute
    private String state;

}
