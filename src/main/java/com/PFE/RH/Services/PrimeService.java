package com.PFE.RH.Services;

import com.PFE.RH.DTO.PrimeDTO;
import com.PFE.RH.Entities.Contact;
import com.PFE.RH.Entities.Prime;
import com.PFE.RH.Entities.TypePrime;
import com.PFE.RH.Mappers.PrimeMapper;
import com.PFE.RH.Repositories.ContactRepository;
import com.PFE.RH.Repositories.PrimeRepository;
import com.PFE.RH.Repositories.TypePrimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrimeService {

    @Autowired
    private PrimeRepository primeRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private TypePrimeRepository typePrimeRepository;

    @Autowired
    private PrimeMapper primeMapper;

    public List<PrimeDTO> getAllPrimes() {
        List<Prime> primes = primeRepository.findAll();
        return primes.stream()
                .map(primeMapper::primeToPrimeDTO)
                .collect(Collectors.toList());
    }

    public PrimeDTO savePrime(PrimeDTO primeDTO) {
        Prime prime = primeMapper.primeDTOToPrime(primeDTO);

        // Fetch the Contact
        Optional<Contact> contactOptional = contactRepository.findById(primeDTO.getContactId());
        if (contactOptional.isPresent()) {
            Contact contact = contactOptional.get();
            prime.setContact(contact);
            contact.getPrimes().add(prime);
        } else {
            throw new NoSuchElementException("Contact not found with ID: " + primeDTO.getContactId());
        }

        // Fetch the TypePrime if typePrimeId is provided
        if (primeDTO.getTypePrimeId() != null) {
            Optional<TypePrime> typePrimeOptional = typePrimeRepository.findById(primeDTO.getTypePrimeId());
            if (typePrimeOptional.isPresent()) {
                TypePrime typePrime = typePrimeOptional.get();
                prime.setTypePrime(typePrime);
            } else {
                throw new NoSuchElementException("TypePrime not found with ID: " + primeDTO.getTypePrimeId());
            }
        }

        Prime savedPrime = primeRepository.save(prime);
        return primeMapper.primeToPrimeDTO(savedPrime);
    }

    public PrimeDTO getPrimeById(Long id) {
        Optional<Prime> optionalPrime = primeRepository.findById(id);
        // Or throw an exception if needed
        return optionalPrime.map(prime -> primeMapper.primeToPrimeDTO(prime)).orElse(null);
    }

    public boolean deletePrime(Long id) {
        Optional<Prime> optionalPrime = primeRepository.findById(id);
        if (optionalPrime.isPresent()) {
            primeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public PrimeDTO patchPrime(Long id, PrimeDTO patchedPrimeDTO) {
        Optional<Prime> optionalPrime = primeRepository.findById(id);
        if (optionalPrime.isPresent()) {
            Prime prime = optionalPrime.get();

            if (patchedPrimeDTO.getYear() != 0) {
                prime.setYear(patchedPrimeDTO.getYear());
            }
            if (patchedPrimeDTO.getMonth() != 0) {
                prime.setMonth(patchedPrimeDTO.getMonth());
            }
            if (patchedPrimeDTO.getMontant() != null) {
                prime.setMontant(patchedPrimeDTO.getMontant());
            }
            if (patchedPrimeDTO.getMotif() != null) {
                prime.setMotif(patchedPrimeDTO.getMotif());
            }
            if (patchedPrimeDTO.getTypePrimeId() != null) {
                Optional<TypePrime> typePrimeOptional = typePrimeRepository.findById(patchedPrimeDTO.getTypePrimeId());
                if (typePrimeOptional.isPresent()) {
                    prime.setTypePrime(typePrimeOptional.get());
                } else {
                    throw new NoSuchElementException("TypePrime not found with ID: " + patchedPrimeDTO.getTypePrimeId());
                }
            }

            Prime updatedPrime = primeRepository.save(prime);
            return primeMapper.primeToPrimeDTO(updatedPrime);
        } else {
            throw new NoSuchElementException("Prime not found with ID: " + id);
        }
    }

    public List<PrimeDTO> getPrimesByContactId(Long contactId) {
        List<Prime> primes = primeRepository.findByContactContactId(contactId);
        return primes.stream()
                .map(primeMapper::primeToPrimeDTO)
                .collect(Collectors.toList());
    }
}
