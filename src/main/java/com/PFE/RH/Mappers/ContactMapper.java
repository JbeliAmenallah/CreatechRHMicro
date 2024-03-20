package com.PFE.RH.Mappers;

import com.PFE.RH.DTO.CongeWithHiddenContactIdDTO;
import com.PFE.RH.Entities.Conge;
import com.PFE.RH.Entities.Contact;
import com.PFE.RH.DTO.ContactDTO;
import com.PFE.RH.DTO.EntrepriseWithoutContactsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = EntrepriseWithoutContactsMapper.class)
public interface ContactMapper {

    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    @Mapping(source = "contact.absences", target = "absences")
    @Mapping(source = "contact.primes", target = "primes")
    @Mapping(source = "contact.autorisations", target = "autorisations")
    @Mapping(source = "contact.contactId", target = "contactId")
    @Mapping(source = "contact.name", target = "name")
    @Mapping(source = "contact.username", target = "username")
    @Mapping(source = "contact.email", target = "email")
    @Mapping(source = "contact.location", target = "location")
    @Mapping(source = "contact.phone", target = "phone")
    @Mapping(source = "contact.conges", target = "conges")
    @Mapping(source = "contact.cotisations", target = "cotisations")
    @Mapping(source = "contact.entreprise", target = "entreprise")
    ContactDTO contactToContactDTO(Contact contact);

    @Mapping(target = "absences", ignore = true)
    @Mapping(target = "primes", ignore = true)
    @Mapping(target = "autorisations", ignore = true)
    @Mapping(target = "conges", ignore = true)
    @Mapping(source = "cotisations", target = "cotisations")
    Contact contactDTOToContact(ContactDTO contactDTO);
    @Mappings({
            @Mapping(source = "congeId", target = "congeId"),
            @Mapping(source = "startDate", target = "startDate"),
            @Mapping(source = "endDate", target = "endDate"),
            @Mapping(source = "state", target = "state"),
    })
    CongeWithHiddenContactIdDTO congeToCongeWithHiddenContactIdDTO(Conge conge);

}
