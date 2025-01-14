package com.example.desicion_service.mapper.Impl;


import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import com.example.desicion_service.entity.DesicionEntity;
import com.example.desicion_service.dto.DecisionDto;
import com.example.desicion_service.mapper.GenericMapper;;


 
@Mapper(componentModel = "spring",  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DecisionMapper extends GenericMapper<DesicionEntity, DecisionDto> {
}

