package com.PFE.RH.Services;

import com.PFE.RH.DTO.CongeDTO;
import com.PFE.RH.Entities.Conge;
import com.PFE.RH.Entities.Contact;
import com.PFE.RH.Mappers.CongeMapper;
import com.PFE.RH.Repositories.CongeRepository;
import com.PFE.RH.Repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CongeService {

    @Autowired
    private CongeRepository congeRepository;

    @Autowired
    private CongeMapper congeMapper;

    @Autowired
    private ContactRepository contactRepository;

    public CongeDTO saveConge(CongeDTO congeDTO) {
        return contactRepository.findById(congeDTO.getContactId())
                .map(contact -> {
                    Conge conge = congeMapper.congeDTOToConge(congeDTO);
                    conge.setContact(contact);
                    contact.getConges().add(conge);
                    return congeMapper.congeToCongeDTO(congeRepository.save(conge));
                })
                .orElse(null);
    }

    public CongeDTO getCongeById(Long congeId) {
        Optional<Conge> congeOptional = congeRepository.findById(congeId);
        return congeOptional.map(congeMapper::congeToCongeDTO).orElse(null);
    }

    public CongeDTO updateConge(Long congeId, CongeDTO updatedCongeDTO) {
        Optional<Conge> congeOptional = congeRepository.findById(congeId);
        if (congeOptional.isPresent()) {
            Conge conge = congeOptional.get();
            conge.setStartDate(updatedCongeDTO.getStartDate());
            conge.setEndDate(updatedCongeDTO.getEndDate());
            conge.setState(updatedCongeDTO.getState());
            Optional<Contact> contactOptional = contactRepository.findById(updatedCongeDTO.getContactId());
            if (contactOptional.isPresent()) {
                Contact contact = contactOptional.get();
                conge.setContact(contact);
                contactRepository.save(contact);
            }
            congeRepository.save(conge);
            return congeMapper.congeToCongeDTO(conge);
        }
        return null;
    }

    public List<CongeDTO> getAllConges() {
        List<Conge> conges = congeRepository.findAll();
        return conges.stream()
                .map(congeMapper::congeToCongeDTO)
                .collect(Collectors.toList());
    }

    public boolean deleteConge(Long congeId) {
        Optional<Conge> congeOptional = congeRepository.findById(congeId);
        if (congeOptional.isPresent()) {
            Conge conge = congeOptional.get();
            conge.getContact().getConges().remove(conge);
            congeRepository.deleteById(congeId);
            return true;
        }
        return false;
    }

    public List<CongeDTO> getCongesByContactId(Long contactId) {
        List<Conge> conges = congeRepository.findByContactContactId(contactId);
        return conges.stream()
                .map(congeMapper::congeToCongeDTO)
                .collect(Collectors.toList());
    }
    public CongeDTO patchConge(Long congeId, CongeDTO patchedCongeDTO) {
        Optional<Conge> congeOptional = congeRepository.findById(congeId);
        if (congeOptional.isPresent()) {
            Conge conge = congeOptional.get();

            if (patchedCongeDTO.getStartDate() != null) {
                conge.setStartDate(patchedCongeDTO.getStartDate());
            }
            if (patchedCongeDTO.getEndDate() != null) {
                conge.setEndDate(patchedCongeDTO.getEndDate());
            }
            if (patchedCongeDTO.getState() != null) {
                conge.setState(patchedCongeDTO.getState());
            }

            congeRepository.save(conge);
            return congeMapper.congeToCongeDTO(conge);
        }
        return null;
    }
}
