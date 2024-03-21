package com.PFE.RH.Services;

import com.PFE.RH.DTO.ContactDTO;
import com.PFE.RH.Entities.Contact;
import com.PFE.RH.Entities.Entreprise;
import com.PFE.RH.Mappers.ContactMapper;
import com.PFE.RH.Repositories.ContactRepository;
import com.PFE.RH.Repositories.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;
    private final EntrepriseRepository entrepriseRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository, ContactMapper contactMapper, EntrepriseRepository entrepriseRepository) {
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
        this.entrepriseRepository = entrepriseRepository;
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
        Optional<Entreprise> optionalEntreprise = entrepriseRepository.findById(3L);

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
    public ContactDTO patchContact(Long id, ContactDTO patchedContactDTO) {
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

            Contact patchedContact = contactRepository.save(contact);
            return contactMapper.contactToContactDTO(patchedContact);
        } else {
            throw new NoSuchElementException("Contact not found with ID: " + id);
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
