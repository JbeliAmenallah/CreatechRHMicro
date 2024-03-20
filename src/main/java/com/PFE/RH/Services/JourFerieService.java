package com.PFE.RH.Services;

import com.PFE.RH.DTO.JourFerieDTO;
import com.PFE.RH.Entities.Annee;
import com.PFE.RH.Entities.JourFerie;
import com.PFE.RH.Mappers.JourFerieMapper;
import com.PFE.RH.Repositories.JourFerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JourFerieService {

    @Autowired
    private JourFerieRepository jourFerieRepository;

    @Autowired
    private JourFerieMapper jourFerieMapper;

    @Autowired
    private AnneeService anneeService;

    public List<JourFerieDTO> getAllJourFeries() {
        List<JourFerie> jourFeries = jourFerieRepository.findAll();
        return jourFeries.stream()
                .map(jourFerieMapper::toJourFerieDTO)
                .collect(Collectors.toList());
    }

    public JourFerieDTO saveJourFerie(JourFerieDTO jourFerieDTO) {
        Annee annee = anneeService.getAnneeById(jourFerieDTO.getAnneeId());
        JourFerie jourFerie = jourFerieMapper.toJourFerie(jourFerieDTO);
        jourFerie.setAnnee(annee);
        JourFerie savedJourFerie = jourFerieRepository.save(jourFerie);
        return jourFerieMapper.toJourFerieDTO(savedJourFerie);
    }

    // Add other service methods for update, delete, etc. if needed
}
