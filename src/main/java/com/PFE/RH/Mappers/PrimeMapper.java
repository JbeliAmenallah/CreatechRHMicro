package com.PFE.RH.Mappers;

import com.PFE.RH.DTO.PrimeDTO;
import com.PFE.RH.Entities.Prime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PrimeMapper {

    @Mapping(source = "primeId", target = "primeId")
    @Mapping(source = "contact.contactId", target = "contactId")
    @Mapping(source = "montant", target = "montant")
    @Mapping(source = "year", target = "year")
    @Mapping(source = "typePrime.typePrimeId", target = "typePrimeId") // Include TypePrimeId
    PrimeDTO primeToPrimeDTO(Prime prime);

    @Mapping(source = "primeDTO.primeId", target = "primeId")
    @Mapping(source = "primeDTO.contactId", target = "contact.contactId")
    @Mapping(source = "primeDTO.montant", target = "montant")
    @Mapping(source = "primeDTO.year", target = "year")
    Prime primeDTOToPrime(PrimeDTO primeDTO);

}
