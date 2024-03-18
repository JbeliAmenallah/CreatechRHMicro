package com.PFE.RH.Services;

import com.PFE.RH.DTO.AutorisationDTO;
import com.PFE.RH.Entities.Autorisation;
import com.PFE.RH.Mappers.AutorisationMapper;
import com.PFE.RH.Repositories.AutorisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public AutorisationDTO saveAutorisation(AutorisationDTO autorisationDTO) {
        Autorisation autorisation = autorisationMapper.autorisationDTOToAutorisation(autorisationDTO);
        Autorisation savedAutorisation = autorisationRepository.save(autorisation);
        return autorisationMapper.autorisationToAutorisationDTO(savedAutorisation);
    }

    public AutorisationDTO updateAutorisation(Long autorisationId, AutorisationDTO autorisationDTO) {
        Autorisation autorisation = autorisationRepository.findById(autorisationId)
                .orElseThrow(() -> new RuntimeException("Autorisation not found with id: " + autorisationId));

        autorisation.setDate(autorisationDTO.getDate());
        autorisation.setState(autorisationDTO.getState()); // Update state

        Autorisation updatedAutorisation = autorisationRepository.save(autorisation);
        return autorisationMapper.autorisationToAutorisationDTO(updatedAutorisation);
    }

    public void deleteAutorisation(Long autorisationId) {
        autorisationRepository.deleteById(autorisationId);
    }
}
