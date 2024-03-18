package com.PFE.RH.Services;

import com.PFE.RH.DTO.EntrepriseDTO;
import com.PFE.RH.Entities.Entreprise;
import com.PFE.RH.Mappers.EntrepriseMapper;
import com.PFE.RH.Repositories.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EntrepriseService {

    private final EntrepriseRepository entrepriseRepository;
    private final EntrepriseMapper entrepriseMapper;

    @Autowired
    public EntrepriseService(EntrepriseRepository entrepriseRepository, EntrepriseMapper entrepriseMapper) {
        this.entrepriseRepository = entrepriseRepository;
        this.entrepriseMapper = entrepriseMapper;
    }

    public List<EntrepriseDTO> getAllEntreprises() {
        List<Entreprise> entreprises = entrepriseRepository.findAll();
        return entreprises.stream()
                .map(entrepriseMapper::entrepriseToEntrepriseDTO)
                .collect(Collectors.toList());
    }

    public EntrepriseDTO getEntrepriseById(Long id) {
        Optional<Entreprise> entrepriseOptional = entrepriseRepository.findById(id);
        if (entrepriseOptional.isPresent()) {
            return entrepriseMapper.entrepriseToEntrepriseDTO(entrepriseOptional.get());
        } else {
            throw new RuntimeException("Entreprise not found with ID: " + id);
        }
    }

    public EntrepriseDTO saveEntreprise(EntrepriseDTO entrepriseDTO) {
        Entreprise entreprise = entrepriseMapper.entrepriseDTOToEntreprise(entrepriseDTO);
        Entreprise savedEntreprise = entrepriseRepository.save(entreprise);
        return entrepriseMapper.entrepriseToEntrepriseDTO(savedEntreprise);
    }

    public EntrepriseDTO updateEntreprise(Long id, EntrepriseDTO updatedEntrepriseDTO) {
        Optional<Entreprise> optionalEntreprise = entrepriseRepository.findById(id);
        if (optionalEntreprise.isPresent()) {
            Entreprise existingEntreprise = optionalEntreprise.get();
            existingEntreprise.setNom(updatedEntrepriseDTO.getNom());
            existingEntreprise.setMatricule(updatedEntrepriseDTO.getMatricule());
            existingEntreprise.setSiegesociale(updatedEntrepriseDTO.getSiegesociale());

            Entreprise updatedEntreprise = entrepriseRepository.save(existingEntreprise);
            return entrepriseMapper.entrepriseToEntrepriseDTO(updatedEntreprise);
        } else {
            throw new RuntimeException("Entreprise not found with ID: " + id);
        }
    }

    public boolean deleteEntreprise(Long id) {
        Optional<Entreprise> optionalEntreprise = entrepriseRepository.findById(id);
        if (optionalEntreprise.isPresent()) {
            entrepriseRepository.deleteById(id);
            return true;
        } else {
            throw new RuntimeException("Entreprise not found with ID: " + id);
        }
    }
}
