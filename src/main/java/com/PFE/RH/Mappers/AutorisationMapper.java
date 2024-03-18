package com.PFE.RH.Mappers;

import com.PFE.RH.DTO.AutorisationDTO;
import com.PFE.RH.Entities.Autorisation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AutorisationMapper {

    AutorisationMapper INSTANCE = Mappers.getMapper(AutorisationMapper.class);

    @Mappings({
            @Mapping(source = "autorisationId", target = "autorisationId"),
            @Mapping(source = "date", target = "date"),
            @Mapping(source = "contact.contactId", target = "contactId"),
            @Mapping(target = "contactName", ignore = true), // Ignore mapping for contactName
            @Mapping(source = "state", target = "state")
    })
    AutorisationDTO autorisationToAutorisationDTO(Autorisation autorisation);

    @Mappings({
            @Mapping(source = "autorisationDTO.autorisationId", target = "autorisationId"),
            @Mapping(source = "autorisationDTO.date", target = "date"),
            @Mapping(source = "autorisationDTO.contactId", target = "contact.contactId"),
            @Mapping(target = "contact", ignore = true), // Ignore mapping for Contact
            @Mapping(source = "autorisationDTO.state", target = "state")
    })
    Autorisation autorisationDTOToAutorisation(AutorisationDTO autorisationDTO);
}
