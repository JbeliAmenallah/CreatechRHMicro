package com.PFE.RH.Services;

import com.PFE.RH.DTO.AnneeDTO;
import com.PFE.RH.DTO.ImpotDTO;
import com.PFE.RH.Entities.Annee;
import com.PFE.RH.Entities.Impot;
import com.PFE.RH.Mappers.ImpotMapper;
import com.PFE.RH.Repositories.AnneeRepository;
import com.PFE.RH.Repositories.ImpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImpotService {

    @Autowired
    private ImpotRepository impotRepository;
    @Autowired
    private AnneeRepository anneeRepository; // Add AnneeRepository dependency
    @Autowired
    private ImpotMapper impotMapper;

    public List<ImpotDTO> getAllImpots() {
        List<Impot> impots = impotRepository.findAll();
        return impots.stream()
                .map(impotMapper::toImpotDTO)
                .collect(Collectors.toList());
    }

    public ImpotDTO saveImpot(ImpotDTO impotDTO) {
        Impot impot = impotMapper.toImpot(impotDTO);

        // Fetch Annee using provided AnneeDTO
        Annee annee = null;
        AnneeDTO anneeDTO = impotDTO.getAnneeDTO();
        if (anneeDTO != null && anneeDTO.getId() != null) {
            annee = anneeRepository.findById(anneeDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Annee not found with id: " + anneeDTO.getId()));
        }

        impot.setAnnee(annee);

        Impot savedImpot = impotRepository.save(impot);
        return impotMapper.toImpotDTO(savedImpot);
    }

    public ImpotDTO updateImpot(Long id, ImpotDTO updatedImpotDTO) {
        Impot existingImpot = impotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Impot not found with id: " + id));

        existingImpot.setLibele(updatedImpotDTO.getLibele());
        existingImpot.setTaux(updatedImpotDTO.getTaux());
        existingImpot.setAnnee(impotMapper.toAnnee(updatedImpotDTO));

        Impot updatedImpot = impotRepository.save(existingImpot);
        return impotMapper.toImpotDTO(updatedImpot);
    }

    public boolean deleteImpot(Long id) {
        if (impotRepository.existsById(id)) {
            impotRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public ImpotDTO getImpotById(Long id) {
        Impot impot = impotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Impot not found with id: " + id));
        return impotMapper.toImpotDTO(impot);
    }
}
