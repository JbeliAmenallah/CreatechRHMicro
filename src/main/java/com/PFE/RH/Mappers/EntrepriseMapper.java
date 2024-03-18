package com.PFE.RH.Mappers;

import com.PFE.RH.Entities.Entreprise;
import com.PFE.RH.DTO.EntrepriseDTO;
import com.PFE.RH.Entities.Contact;
import com.PFE.RH.DTO.ContactDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface EntrepriseMapper {

    EntrepriseMapper INSTANCE = Mappers.getMapper(EntrepriseMapper.class);

    @Mapping(source = "entrepriseId", target = "entrepriseId")
    @Mapping(source = "nom", target = "nom")
    @Mapping(source = "matricule", target = "matricule")
    @Mapping(source = "siegesociale", target = "siegesociale")
    @Mapping(source = "contacts", target = "contacts")
    EntrepriseDTO entrepriseToEntrepriseDTO(Entreprise entreprise);

    @Mapping(source = "entrepriseId", target = "entrepriseId")
    @Mapping(source = "nom", target = "nom")
    @Mapping(source = "matricule", target = "matricule")
    @Mapping(source = "siegesociale", target = "siegesociale")
    @Mapping(source = "contacts", target = "contacts")
    Entreprise entrepriseDTOToEntreprise(EntrepriseDTO entrepriseDTO);

    default List<ContactDTO> mapContactsToContactDTOs(List<Contact> contacts) {
        if (contacts == null) {
            return null;
        }
        return contacts.stream()
                .map(contact -> {
                    ContactDTO contactDTO = new ContactDTO();
                    contactDTO.setContactId(contact.getContactId());
                    contactDTO.setName(contact.getName());
                    // Map other fields as needed
                    return contactDTO;
                })
                .collect(Collectors.toList());
    }
}
