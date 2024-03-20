package com.PFE.RH.Mappers;

import com.PFE.RH.DTO.ContactDTO;
import com.PFE.RH.Entities.Contact;
import com.PFE.RH.Entities.Entreprise;
import com.PFE.RH.DTO.EntrepriseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {EntrepriseWithoutContactsMapper.class, ContactMapper.class})
public interface EntrepriseMapper {

    EntrepriseMapper INSTANCE = Mappers.getMapper(EntrepriseMapper.class);

    @Mapping(source = "entrepriseId", target = "entrepriseId")
    @Mapping(source = "nom", target = "nom")
    @Mapping(source = "matricule", target = "matricule")
    @Mapping(source = "siegesociale", target = "siegesociale")
    @Mapping(source = "contacts", target = "contacts", ignore = true)
    EntrepriseDTO entrepriseToEntrepriseDTO(Entreprise entreprise);

    @Mapping(source = "entrepriseId", target = "entrepriseId")
    @Mapping(source = "nom", target = "nom")
    @Mapping(source = "matricule", target = "matricule")
    @Mapping(source = "siegesociale", target = "siegesociale")
    @Mapping(source = "contacts", target = "contacts", ignore = true)
    Entreprise entrepriseDTOToEntreprise(EntrepriseDTO entrepriseDTO);

    default List<ContactDTO> mapContactsToContactDTOs(List<Contact> contacts) {
        if (contacts == null) {
            return null;
        }
        return contacts.stream()
                .map(ContactMapper.INSTANCE::contactToContactDTO)
                .collect(Collectors.toList());
    }

    @Named("stringToEntrepriseDTO")
    default EntrepriseDTO stringToEntrepriseDTO(String nom) {
        if (nom == null) {
            return null;
        }
        EntrepriseDTO entrepriseDTO = new EntrepriseDTO();
        entrepriseDTO.setNom(nom);
        return entrepriseDTO;
    }

}
