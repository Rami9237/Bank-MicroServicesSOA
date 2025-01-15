package com.example.barememicroservice.mapper.Impl;

import com.example.barememicroservice.dto.BaremeDto;
import com.example.barememicroservice.entity.Bareme;
import com.example.barememicroservice.mapper.GenericMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface BaremeMapper extends GenericMapper<Bareme, BaremeDto> {
    Bareme toEntity(BaremeDto baremeDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Bareme partialUpdate(BaremeDto baremeDto, @MappingTarget Bareme bareme);
}
