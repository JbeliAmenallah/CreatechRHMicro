package com.PFE.RH.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AbsenceDTO {
    private Long absenceId;
    private Long contactId;
    private LocalDate dateOfAbsence;
    private String reason;
    @JsonIgnore
    private String message; // Add this field for messages to use on feedbacks

    // Getters and setters
    // Omitted for brevity

    // Method to generate the message
    public String generateMessage(String contactName) {
        return contactName + " was absent on " + dateOfAbsence + " due to " + reason + ".";
    }
}
