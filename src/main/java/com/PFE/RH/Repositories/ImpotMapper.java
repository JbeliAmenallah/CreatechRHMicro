package com.PFE.RH.Mappers;

import com.PFE.RH.DTO.ImpotDTO;
import com.PFE.RH.Entities.Annee;
import com.PFE.RH.Entities.Impot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = AnneeMapper.class)
public interface ImpotMapper {

    ImpotMapper INSTANCE = Mappers.getMapper(ImpotMapper.class);

    @Mapping(source = "anneeDTO", target = "annee")
    Impot toImpot(ImpotDTO impotDTO);

    @Mapping(source = "annee", target = "anneeDTO")
    ImpotDTO toImpotDTO(Impot impot);

    // Corrected method name for mapping AnneeDTO to Annee
    Annee toAnnee(ImpotDTO impotDTO);

    // Corrected method name for mapping Annee to AnneeDTO
    ImpotDTO toAnneeDTO(Annee annee);
}
