package com.PFE.RH.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ContactDTO {
    private Long contactId;
    private String name;
    private String username;
    private String email;
    private String location;
    private String phone;
    private List<AbsenceDTO> absences;
    private List<PrimeDTO> primes;
    private List<AutorisationDTO> autorisations;
    private List<CongeDTO> conges;
    private List<CotisationDTO> cotisations;
    private EntrepriseDTO entreprise; // Include entreprise mapping

    // Getters and setters
    // Omitted for brevity
}
