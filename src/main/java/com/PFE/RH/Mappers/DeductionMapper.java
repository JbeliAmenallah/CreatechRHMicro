package com.PFE.RH.Mappers;

import com.PFE.RH.DTO.DeductionDTO;
import com.PFE.RH.Entities.Deduction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeductionMapper {

    DeductionMapper INSTANCE = Mappers.getMapper(DeductionMapper.class);

    @Mapping(target = "typeDeduction", source = "deduction.typeDeduction")
    DeductionDTO toDeductionDTO(Deduction deduction);

    @Mapping(target = "typeDeduction", source = "deductionDTO.typeDeduction")
    Deduction toDeduction(DeductionDTO deductionDTO);
}
