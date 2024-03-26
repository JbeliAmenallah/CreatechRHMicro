package com.PFE.RH.Services;

import com.PFE.RH.DTO.FinanceConfigurationDTO;
import com.PFE.RH.Entities.FinanceConfiguration;
import com.PFE.RH.Mappers.FinanceConfigurationMapper;
import com.PFE.RH.Repositories.FinanceConfigurationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FinanceConfigurationService {

    private final FinanceConfigurationRepository financeConfigurationRepository;
    private final FinanceConfigurationMapper financeConfigurationMapper;

    public FinanceConfigurationService(FinanceConfigurationRepository financeConfigurationRepository, FinanceConfigurationMapper financeConfigurationMapper) {
        this.financeConfigurationRepository = financeConfigurationRepository;
        this.financeConfigurationMapper = financeConfigurationMapper;
    }

    public List<FinanceConfigurationDTO> getAllFinanceConfigurations() {
        List<FinanceConfiguration> financeConfigurations = financeConfigurationRepository.findAll();
        return financeConfigurations.stream()
                .map(financeConfigurationMapper::financeConfigurationToDto)
                .collect(Collectors.toList());
    }

    public FinanceConfigurationDTO getFinanceConfigurationById(Long id) {
        FinanceConfiguration financeConfiguration = financeConfigurationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Finance Configuration not found with ID: " + id));
        return financeConfigurationMapper.financeConfigurationToDto(financeConfiguration);
    }

    public FinanceConfigurationDTO createFinanceConfiguration(FinanceConfigurationDTO financeConfigurationDTO) {
        FinanceConfiguration financeConfiguration = financeConfigurationMapper.dtoToFinanceConfiguration(financeConfigurationDTO);
        financeConfiguration = financeConfigurationRepository.save(financeConfiguration);
        return financeConfigurationMapper.financeConfigurationToDto(financeConfiguration);
    }

    public FinanceConfigurationDTO updateFinanceConfiguration(Long id, FinanceConfigurationDTO financeConfigurationDTO) {
        FinanceConfiguration existingFinanceConfiguration = financeConfigurationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Finance Configuration not found with ID: " + id));

        financeConfigurationMapper.updateFinanceConfigurationFromDto(financeConfigurationDTO, existingFinanceConfiguration);

        FinanceConfiguration updatedFinanceConfiguration = financeConfigurationRepository.save(existingFinanceConfiguration);
        return financeConfigurationMapper.financeConfigurationToDto(updatedFinanceConfiguration);
    }

    public FinanceConfigurationDTO patchFinanceConfiguration(Long id, FinanceConfigurationDTO financeConfigurationDTO) {
        FinanceConfiguration existingFinanceConfiguration = financeConfigurationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Finance Configuration not found with ID: " + id));

        if (financeConfigurationDTO.getLibele() != "") {
            existingFinanceConfiguration.setLibele(financeConfigurationDTO.getLibele());
        }
        if (financeConfigurationDTO.getYear() != 0) {
            existingFinanceConfiguration.setYear(financeConfigurationDTO.getYear());
        }
        if (financeConfigurationDTO.getTaux() != 0) {
            existingFinanceConfiguration.setTaux(financeConfigurationDTO.getTaux());
        }

        FinanceConfiguration patchedFinanceConfiguration = financeConfigurationRepository.save(existingFinanceConfiguration);
        return financeConfigurationMapper.financeConfigurationToDto(patchedFinanceConfiguration);
    }

    public void deleteFinanceConfiguration(Long id) {
        financeConfigurationRepository.deleteById(id);
    }
}
