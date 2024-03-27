package com.PFE.RH.Services;

import com.PFE.RH.DTO.FinanceConfigurationDTO;
import com.PFE.RH.Entities.FinanceConfiguration;
import com.PFE.RH.Repositories.FinanceConfigurationRepository;
import com.PFE.RH.Repositories.AnneeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FinanceConfigurationService {

    private final FinanceConfigurationRepository financeConfigurationRepository;
    private final AnneeRepository anneeRepository;

    public FinanceConfigurationService(FinanceConfigurationRepository financeConfigurationRepository,
                                       AnneeRepository anneeRepository) {
        this.financeConfigurationRepository = financeConfigurationRepository;
        this.anneeRepository = anneeRepository;
    }

    public List<FinanceConfigurationDTO> getAllFinanceConfigurations() {
        List<FinanceConfiguration> financeConfigurations = financeConfigurationRepository.findAll();
        return financeConfigurations.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public FinanceConfigurationDTO getFinanceConfigurationById(Long id) {
        FinanceConfiguration financeConfiguration = financeConfigurationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Finance Configuration not found with ID: " + id));
        return mapToDto(financeConfiguration);
    }

    public FinanceConfigurationDTO createFinanceConfiguration(FinanceConfigurationDTO financeConfigurationDTO) {
        FinanceConfiguration financeConfiguration = mapToEntity(financeConfigurationDTO);
        financeConfiguration = financeConfigurationRepository.save(financeConfiguration);
        return mapToDto(financeConfiguration);
    }

    public FinanceConfigurationDTO updateFinanceConfiguration(Long id, FinanceConfigurationDTO financeConfigurationDTO) {
        FinanceConfiguration existingFinanceConfiguration = financeConfigurationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Finance Configuration not found with ID: " + id));

        mapToEntity(financeConfigurationDTO, existingFinanceConfiguration);

        FinanceConfiguration updatedFinanceConfiguration = financeConfigurationRepository.save(existingFinanceConfiguration);
        return mapToDto(updatedFinanceConfiguration);
    }

    public FinanceConfigurationDTO patchFinanceConfiguration(Long id, FinanceConfigurationDTO financeConfigurationDTO) {
        FinanceConfiguration existingFinanceConfiguration = financeConfigurationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Finance Configuration not found with ID: " + id));

        if (financeConfigurationDTO.getLibele() != null && !financeConfigurationDTO.getLibele().isEmpty()) {
            existingFinanceConfiguration.setLibele(financeConfigurationDTO.getLibele());
        }
        if (financeConfigurationDTO.getYear() != 0) {
            existingFinanceConfiguration.setYear(financeConfigurationDTO.getYear());
        }
        if (financeConfigurationDTO.getTaux() != 0) {
            existingFinanceConfiguration.setTaux(financeConfigurationDTO.getTaux());
        }

        // Fetch and set Annee based on anneeID
        if (financeConfigurationDTO.getAnneeID() != null) {
            existingFinanceConfiguration.setAnnee(anneeRepository.findById(financeConfigurationDTO.getAnneeID())
                    .orElseThrow(() -> new RuntimeException("Annee not found with ID: " + financeConfigurationDTO.getAnneeID())));
        }

        FinanceConfiguration patchedFinanceConfiguration = financeConfigurationRepository.save(existingFinanceConfiguration);
        return mapToDto(patchedFinanceConfiguration);
    }

    public void deleteFinanceConfiguration(Long id) {
        financeConfigurationRepository.deleteById(id);
    }

    private FinanceConfigurationDTO mapToDto(FinanceConfiguration financeConfiguration) {
        FinanceConfigurationDTO dto = new FinanceConfigurationDTO();
        dto.setId(financeConfiguration.getId());
        dto.setLibele(financeConfiguration.getLibele());
        dto.setYear(financeConfiguration.getYear());
        dto.setTaux(financeConfiguration.getTaux());
        if (financeConfiguration.getAnnee() != null) {
            dto.setAnneeID(financeConfiguration.getAnnee().getId());
        }
        return dto;
    }

    private FinanceConfiguration mapToEntity(FinanceConfigurationDTO dto) {
        FinanceConfiguration financeConfiguration = new FinanceConfiguration();
        financeConfiguration.setLibele(dto.getLibele());
        financeConfiguration.setYear(dto.getYear());
        financeConfiguration.setTaux(dto.getTaux());

        // Fetch and set Annee based on anneeID
        if (dto.getAnneeID() != null) {
            financeConfiguration.setAnnee(anneeRepository.findById(dto.getAnneeID())
                    .orElseThrow(() -> new RuntimeException("Annee not found with ID: " + dto.getAnneeID())));
        }

        return financeConfiguration;
    }

    private void mapToEntity(FinanceConfigurationDTO dto, FinanceConfiguration entity) {
        if (dto.getLibele() != null && !dto.getLibele().isEmpty()) {
            entity.setLibele(dto.getLibele());
        }
        if (dto.getYear() != 0) {
            entity.setYear(dto.getYear());
        }
        if (dto.getTaux() != 0) {
            entity.setTaux(dto.getTaux());
        }

        // Fetch and set Annee based on anneeID
        if (dto.getAnneeID() != null) {
            entity.setAnnee(anneeRepository.findById(dto.getAnneeID())
                    .orElseThrow(() -> new RuntimeException("Annee not found with ID: " + dto.getAnneeID())));
        }
    }
}
