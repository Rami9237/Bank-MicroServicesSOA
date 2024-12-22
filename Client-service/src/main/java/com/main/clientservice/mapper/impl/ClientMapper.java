package com.main.clientservice.mapper.impl;

import com.main.clientservice.dto.ClientEntityDto;
import com.main.clientservice.entity.ClientEntity;
import com.main.clientservice.mapper.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper extends GenericMapper<ClientEntity, ClientEntityDto> {
}
