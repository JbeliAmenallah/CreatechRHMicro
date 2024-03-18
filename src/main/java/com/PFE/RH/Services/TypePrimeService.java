package com.PFE.RH.Services;

import com.PFE.RH.DTO.TypePrimeDTO;
import com.PFE.RH.Entities.TypePrime;
import com.PFE.RH.Mappers.TypePrimeMapper;
import com.PFE.RH.Repositories.TypePrimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypePrimeService {

    @Autowired
    private TypePrimeRepository typePrimeRepository;

    @Autowired
    private TypePrimeMapper typePrimeMapper;

    public List<TypePrimeDTO> getAllTypePrimes() {
        List<TypePrime> typePrimes = typePrimeRepository.findAll();
        return typePrimes.stream()
                .map(typePrimeMapper::typePrimeToTypePrimeDTO)
                .collect(Collectors.toList());
    }

    public TypePrimeDTO saveTypePrime(TypePrimeDTO typePrimeDTO) {
        TypePrime typePrime = typePrimeMapper.typePrimeDTOToTypePrime(typePrimeDTO);
        TypePrime savedTypePrime = typePrimeRepository.save(typePrime);
        return typePrimeMapper.typePrimeToTypePrimeDTO(savedTypePrime);
    }

    public TypePrimeDTO updateTypePrime(Long id, TypePrimeDTO updatedTypePrimeDTO) {
        TypePrime typePrime = typePrimeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TypePrime not found with ID: " + id));

        // Update the fields
        typePrime.setCode(updatedTypePrimeDTO.getCode());
        typePrime.setLibele(updatedTypePrimeDTO.getLibele());
        typePrime.setCnss(updatedTypePrimeDTO.getCnss());
        typePrime.setImpo(updatedTypePrimeDTO.getImpo());
        typePrime.setMontant(updatedTypePrimeDTO.getMontant());
        typePrime.setType(updatedTypePrimeDTO.getType());
        typePrime.setAbasedesalaire(updatedTypePrimeDTO.getAbasedesalaire());
        typePrime.setCategorie(updatedTypePrimeDTO.getCategorie());
        typePrime.setGrp(updatedTypePrimeDTO.getGrp());
        typePrime.setGrade(updatedTypePrimeDTO.getGrade());
        typePrime.setObligatoire(updatedTypePrimeDTO.getObligatoire());

        TypePrime updatedTypePrime = typePrimeRepository.save(typePrime);
        return typePrimeMapper.typePrimeToTypePrimeDTO(updatedTypePrime);
    }
}
