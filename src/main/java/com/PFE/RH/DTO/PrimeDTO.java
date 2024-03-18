package com.PFE.RH.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PrimeDTO {

    private Long primeId;
    private Long contactId; // Include contactId
    private int year;
    private int month;
    private Double montant;
    private String motif;
    private Long typePrimeId; // Include typePrimeId
    // Getters and setters
}
