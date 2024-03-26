package com.PFE.RH.Mappers;

import com.PFE.RH.DTO.FinanceConfigurationDTO;
import com.PFE.RH.Entities.FinanceConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FinanceConfigurationMapper {

    FinanceConfigurationMapper INSTANCE = Mappers.getMapper(FinanceConfigurationMapper.class);

    @Mapping(target = "id", ignore = true)
    FinanceConfiguration financeConfigurationFromDto(FinanceConfigurationDTO financeConfigurationDTO);

    FinanceConfigurationDTO financeConfigurationToDto(FinanceConfiguration financeConfiguration);

    @Mapping(target = "id", ignore = true)
    void updateFinanceConfigurationFromDto(FinanceConfigurationDTO financeConfigurationDTO, @MappingTarget FinanceConfiguration financeConfiguration);

    @Mapping(target = "id", ignore = true)
    FinanceConfiguration dtoToFinanceConfiguration(FinanceConfigurationDTO financeConfigurationDTO);
}
