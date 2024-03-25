package com.PFE.RH.Services;

import com.PFE.RH.DTO.ContactDTO;
import com.PFE.RH.DTO.ImpotDTO;
import com.PFE.RH.DTO.ImpotProjectionDTO;
import com.PFE.RH.Entities.Contact;
import com.PFE.RH.Entities.Entreprise;
import com.PFE.RH.Entities.Impot;
import com.PFE.RH.Mappers.ContactMapper;
import com.PFE.RH.Mappers.ImpotProjectionMapper;
import com.PFE.RH.Repositories.ContactRepository;
import com.PFE.RH.Repositories.EntrepriseRepository;
import com.PFE.RH.Repositories.ImpotRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;
    private final EntrepriseRepository entrepriseRepository;
    private final ImpotRepository impotRepository;
    private final ImpotProjectionMapper impotProjectionMapper;
    @Autowired
    public ContactService(ContactRepository contactRepository,ImpotProjectionMapper impotProjectionMapper ,ContactMapper contactMapper,ImpotRepository impotRepository, EntrepriseRepository entrepriseRepository) {
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
        this.entrepriseRepository = entrepriseRepository;
        this.impotRepository=impotRepository;
        this.impotProjectionMapper=impotProjectionMapper;
    }

    public List<ContactDTO> getAllContacts() {
        List<Contact> contacts = contactRepository.findAll();
        return contacts.stream()
                .map(contactMapper::contactToContactDTO)
                .collect(Collectors.toList());
    }

    public ContactDTO saveContact(ContactDTO contactDTO) {
        Contact contact = contactMapper.contactDTOToContact(contactDTO);

        // Get the specific enterprise by ID (e.g., ID 1)
        Optional<Entreprise> optionalEntreprise = entrepriseRepository.findById(1L);

        if (optionalEntreprise.isPresent()) {
            Entreprise entreprise = optionalEntreprise.get();
            contact.setEntreprise(entreprise); // Set the contact's entreprise
        } else {
            // Handle the case where entreprise with ID 1 does not exist
            throw new NoSuchElementException("Entreprise with ID 1 not found");
        }

        Contact savedContact = contactRepository.save(contact);
        return contactMapper.contactToContactDTO(savedContact);
    }

    public ContactDTO updateContact(Long id, ContactDTO updatedContactDTO) {
        Optional<Contact> optionalContact = contactRepository.findById(id);
        if (optionalContact.isPresent()) {
            Contact existingContact = optionalContact.get();
            existingContact.setName(updatedContactDTO.getName());
            existingContact.setUsername(updatedContactDTO.getUsername());
            existingContact.setEmail(updatedContactDTO.getEmail());
            existingContact.setLocation(updatedContactDTO.getLocation());
            existingContact.setPhone(updatedContactDTO.getPhone());

            Contact updatedContact = contactRepository.save(existingContact);
            return contactMapper.contactToContactDTO(updatedContact);
        } else {
            throw new NoSuchElementException("Contact not found with ID: " + id);
        }
    }

    // PATCH method to update specific fields
    public ContactDTO patchContact(Long id,ContactDTO patchedContactDTO) {
        Optional<Contact> optionalContact = contactRepository.findById(id);
        if (optionalContact.isPresent()) {
            Contact contact = optionalContact.get();

            if (patchedContactDTO.getName() != null) {
                contact.setName(patchedContactDTO.getName());
            }
            if (patchedContactDTO.getUsername() != null) {
                contact.setUsername(patchedContactDTO.getUsername());
            }
            if (patchedContactDTO.getEmail() != null) {
                contact.setEmail(patchedContactDTO.getEmail());
            }
            if (patchedContactDTO.getLocation() != null) {
                contact.setLocation(patchedContactDTO.getLocation());
            }
            if (patchedContactDTO.getPhone() != null) {
                contact.setPhone(patchedContactDTO.getPhone());
            }
            if (patchedContactDTO.getFax() != null) {
                contact.setFax(patchedContactDTO.getFax());
            }
            if (patchedContactDTO.getPassword() != null) {
                contact.setPassword(patchedContactDTO.getPassword());
            }
            if (patchedContactDTO.getRoles() != null) {
                contact.setRoles(patchedContactDTO.getRoles());
            }
            if (patchedContactDTO.getNbEnfant() != null) {
                contact.setNbEnfant(patchedContactDTO.getNbEnfant());
            }
            if (patchedContactDTO.getRegime() != null) {
                contact.setRegime(patchedContactDTO.getRegime());
            }

                contact.setChefDefamille(patchedContactDTO.isChefDefamille());

            if (patchedContactDTO.getSalaireDeBASE() != null) {
                contact.setSalaireDeBASE(patchedContactDTO.getSalaireDeBASE());
            }
            if (patchedContactDTO.getNumCompte() != null) {
                contact.setNumCompte(patchedContactDTO.getNumCompte());
            }
            if (patchedContactDTO.getModeDePaiement() != null) {
                contact.setModeDePaiement(patchedContactDTO.getModeDePaiement());
            }
            if (patchedContactDTO.getDateRecrutemnt() != null) {
                contact.setDateRecrutemnt(patchedContactDTO.getDateRecrutemnt());
            }

            Contact patchedContact = contactRepository.save(contact);
            return contactMapper.contactToContactDTO(patchedContact);
        } else {
            throw new NoSuchElementException("Contact not found with ID: " + id);
        }
    }
    public ContactDTO addImpotToContact(Long contactId, Long impotId) {
        Optional<Contact> optionalContact = contactRepository.findById(contactId);
        Optional<Impot> optionalImpot = impotRepository.findById(impotId);

        if (optionalContact.isPresent() && optionalImpot.isPresent()) {
            Contact contact = optionalContact.get();
            Impot impot = optionalImpot.get();

            ImpotProjectionDTO impotDTO = impotProjectionMapper.toDto(impot);
            contact.addImpot(impotDTO); // Add the impot to the contact's impots list
            contactRepository.save(contact);

            return contactMapper.contactToContactDTO(contact);
        } else {
            throw new NoSuchElementException("Contact or Impot not found");
        }
    }
    public ContactDTO getContactById(Long id) {
        Optional<Contact> optionalContact = contactRepository.findById(id);
        if (optionalContact.isPresent()) {
            Contact contact = optionalContact.get();
            return contactMapper.contactToContactDTO(contact);
        } else {
            throw new NoSuchElementException("Contact not found with ID: " + id);
        }
    }

    public boolean deleteContact(Long id) {
        try {
            contactRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            throw new NoSuchElementException("Contact not found with ID: " + id);
        }
    }
}
