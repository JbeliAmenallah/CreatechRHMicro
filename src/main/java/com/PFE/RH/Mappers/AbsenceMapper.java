package com.PFE.RH.Mappers;

import com.PFE.RH.DTO.AbsenceDTO;
import com.PFE.RH.Entities.Absence;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface    AbsenceMapper {

    @Mapping(source = "absenceId", target = "absenceId")
    @Mapping(source = "contact.contactId", target = "contactId")
    AbsenceDTO absenceToAbsenceDTO(Absence absence);

    @Mapping(source = "absenceDTO.absenceId", target = "absenceId")
    @Mapping(source = "absenceDTO.contactId", target = "contact.contactId")
    Absence absenceDTOToAbsence(AbsenceDTO absenceDTO);
}
