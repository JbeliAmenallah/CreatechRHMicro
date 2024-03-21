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
    private List<AbsenceWithHiddenContactIdDTO> absences;
    private List<PrimeWithoutTypeAndContactDTO> primes;
    private List<AutorisationWithoutContactDTO> autorisations;
    private List<CongeWithHiddenContactIdDTO> conges;
    private List<CotisationWithHiddenContactIdDTO> cotisations;
    private EntrepriseWithoutContactsDTO entreprise;
    //private List<ImpotDTO> impots;
    // Getters and setters
    // Omitted for brevity
}
