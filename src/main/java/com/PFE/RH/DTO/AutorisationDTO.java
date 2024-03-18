package com.PFE.RH.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.time.LocalDate;

@Data
public class AutorisationDTO {
    private Long autorisationId;
    private LocalDate date;
    private Long contactId;
    @JsonIgnore
    private String contactName;
    private String state;
    // Getters and Setters (Omitted for brevity)
}
