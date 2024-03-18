package com.PFE.RH.DTO;



import lombok.Data;

import java.time.LocalDate;

@Data
public class AbsenceWithHiddenContactIdDTO {
    private Long absenceId;
    private LocalDate dateOfAbsence;
    private String reason;

    // Getters and setters
    // Omitted for brevity

    // Method to generate the message
}

