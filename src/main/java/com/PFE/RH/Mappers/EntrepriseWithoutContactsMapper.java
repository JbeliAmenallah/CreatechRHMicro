package com.PFE.RH.Mappers;

import com.PFE.RH.Entities.Entreprise;
import com.PFE.RH.DTO.EntrepriseWithoutContactsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EntrepriseWithoutContactsMapper {

    EntrepriseWithoutContactsMapper INSTANCE = Mappers.getMapper(EntrepriseWithoutContactsMapper.class);

    @Mapping(source = "entrepriseId", target = "entrepriseId")
    @Mapping(source = "nom", target = "nom")
    @Mapping(source = "matricule", target = "matricule")
    @Mapping(source = "siegesociale", target = "siegesociale")
    EntrepriseWithoutContactsDTO entrepriseToEntrepriseWithoutContactsDTO(Entreprise entreprise);
}
