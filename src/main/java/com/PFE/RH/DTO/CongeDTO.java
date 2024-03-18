package com.PFE.RH.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CongeDTO {

    private Long congeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String state;
    private Long contactId; // Include contactId

    // Getters and setters
}
