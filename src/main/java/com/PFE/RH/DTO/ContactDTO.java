package com.PFE.RH.DTO;

import com.PFE.RH.Entities.Impot;
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
    private List<ImpotProjectionDTO> impots;
    private List<DeductionDTO> deductions; // Added DeductionDTO list


    // Getters and setters
    // Omitted for brevity

    // Add method to add ImpotProjectionDTO to impots list
    public void addImpot(ImpotProjectionDTO impotProjectionDTO) {
        this.impots.add(impotProjectionDTO);
    }

}
