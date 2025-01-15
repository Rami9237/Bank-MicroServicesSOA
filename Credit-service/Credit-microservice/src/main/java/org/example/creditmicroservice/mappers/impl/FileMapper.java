package org.example.creditmicroservice.mappers.impl;

import org.example.creditmicroservice.DTOs.CreditFileDto;
import org.example.creditmicroservice.entities.CreditFile;
import org.example.creditmicroservice.mappers.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface FileMapper extends GenericMapper<CreditFile, CreditFileDto> {
}
