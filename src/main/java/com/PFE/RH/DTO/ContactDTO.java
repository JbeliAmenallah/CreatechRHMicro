package com.PFE.RH.DTO;

import com.PFE.RH.Entities.Impot;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ContactDTO {
    private Long contactId;

    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Name must contain only letters and spaces")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Username is required")
    @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_]*$", message = "Username must contain only letters, numbers, and underscores")
    private String username;

    @NotNull
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Location is required")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "Location must contain only letters, numbers, and spaces")
    private String location;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp="\\d{8}", message="Phone number must be 8 digits")
    private String phone;

    @NotBlank(message = "Fax is required")
    private String fax;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String password;

    @NotBlank(message = "Roles are required")
    private String roles;

    @NotNull(message = "Number of Children is required")
    @Min(value = 0, message = "Number of Children must be at least 0")
    private Integer nbEnfant;

    @NotBlank(message = "Regime is required")
    private String regime;

    private boolean chefDefamille;

    @NotNull(message = "Base Salary is required")
    @Min(value = 0, message = "Base Salary must be at least 0")
    private Double salaireDeBASE;

    @NotBlank(message = "Account Number is required")
    private String numCompte;

    @NotBlank(message = "Payment Method is required")
    private String modeDePaiement;

    @NotNull(message = "Recruitment Date is required")
    private LocalDate dateRecrutemnt;

    private List<AbsenceWithHiddenContactIdDTO> absences;
    private List<PrimeWithoutTypeAndContactDTO> primes;
    private List<AutorisationWithoutContactDTO> autorisations;
    private List<CongeWithHiddenContactIdDTO> conges;
    private List<CotisationWithHiddenContactIdDTO> cotisations;
    private EntrepriseWithoutContactsDTO entreprise;
    private List<ImpotProjectionDTO> impots;
    private List<DeductionDTO> deductions;

    // Getters and setters
    // Omitted for brevity

    // Add method to add ImpotProjectionDTO to impots list
    public void addImpot(ImpotProjectionDTO impotProjectionDTO) {
        this.impots.add(impotProjectionDTO);
    }
}
