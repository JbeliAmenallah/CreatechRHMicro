package com.PFE.RH.Services;

import com.PFE.RH.DTO.AutorisationDTO;
import com.PFE.RH.Entities.Autorisation;
import com.PFE.RH.Mappers.AutorisationMapper;
import com.PFE.RH.Repositories.AutorisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorisationService {

    @Autowired
    private AutorisationRepository autorisationRepository;

    @Autowired
    private AutorisationMapper autorisationMapper;

    public List<AutorisationDTO> getAllAutorisations() {
        List<Autorisation> autorisations = autorisationRepository.findAll();
        return autorisations.stream()
                .map(autorisationMapper::autorisationToAutorisationDTO)
                .collect(Collectors.toList());
    }

    public AutorisationDTO saveAutorisation(@Valid @NotNull AutorisationDTO autorisationDTO) {
        try {
            Autorisation autorisation = autorisationMapper.autorisationDTOToAutorisation(autorisationDTO);
            Autorisation savedAutorisation = autorisationRepository.save(autorisation);
            return autorisationMapper.autorisationToAutorisationDTO(savedAutorisation);
        } catch (ConstraintViolationException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public AutorisationDTO updateAutorisation(Long autorisationId, @Valid @NotNull AutorisationDTO updatedAutorisationDTO) {
        Autorisation autorisation = autorisationRepository.findById(autorisationId)
                .orElseThrow(() -> new RuntimeException("Autorisation not found with id: " + autorisationId));

        try {
            validateAutorisationDTO(updatedAutorisationDTO);
        } catch (ConstraintViolationException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        autorisation.setDateDebut(updatedAutorisationDTO.getDateDebut());
        autorisation.setDateFin(updatedAutorisationDTO.getDateFin());
        autorisation.setState(updatedAutorisationDTO.getState()); // Update state

        Autorisation updatedAutorisation = autorisationRepository.save(autorisation);
        return autorisationMapper.autorisationToAutorisationDTO(updatedAutorisation);
    }

    public void deleteAutorisation(Long autorisationId) {
        autorisationRepository.deleteById(autorisationId);
    }

    public AutorisationDTO patchAutorisation(Long autorisationId, AutorisationDTO updatedAutorisationDTO) {
        Autorisation autorisation = autorisationRepository.findById(autorisationId)
                .orElseThrow(() -> new RuntimeException("Autorisation not found with id: " + autorisationId));

        if (updatedAutorisationDTO.getDateDebut() != null) {
            autorisation.setDateDebut(updatedAutorisationDTO.getDateDebut());
        }

        if (updatedAutorisationDTO.getDateFin() != null) {
            autorisation.setDateFin(updatedAutorisationDTO.getDateFin());
        }

        if (updatedAutorisationDTO.getState() != null) {
            autorisation.setState(updatedAutorisationDTO.getState());
        }

        Autorisation patchedAutorisation = autorisationRepository.save(autorisation);
        return autorisationMapper.autorisationToAutorisationDTO(patchedAutorisation);
    }

    public List<AutorisationDTO> findByContactId(Long contactId) {
        List<Autorisation> autorisations = autorisationRepository.findByContact_ContactId(contactId);
        return autorisations.stream()
                .map(autorisationMapper::autorisationToAutorisationDTO)
                .collect(Collectors.toList());
    }

    public List<AutorisationDTO> findAutorisationsWithDurationMoreThanTwoHours() {
        List<Autorisation> autorisations = autorisationRepository.findAll();
        return autorisations.stream()
                .filter(this::hasDurationMoreThanTwoHours)
                .map(autorisationMapper::autorisationToAutorisationDTO)
                .collect(Collectors.toList());
    }

    private boolean hasDurationMoreThanTwoHours(Autorisation autorisation) {
        LocalDateTime dateDebut = autorisation.getDateDebut();
        LocalDateTime dateFin = autorisation.getDateFin();
        if (dateDebut != null && dateFin != null) {
            Duration duration = Duration.between(dateDebut, dateFin);
            return duration.toHours() > 2;
        }
        return false;
    }

    private void validateAutorisationDTO(@Valid @NotNull AutorisationDTO autorisationDTO) {
        if (autorisationDTO.getDateFin() != null && autorisationDTO.getDateFin().isBefore(autorisationDTO.getDateDebut())) {
            throw new IllegalArgumentException("End date (dateFin) must be after Start date (dateDebut)");
        }
    }
}
