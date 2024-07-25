package com.milkyway.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.milkyway.dto.MilkCollectionDto;
import com.milkyway.entity.MilkCollection;

@Mapper
public interface MilkCollectionMapper {
    MilkCollectionMapper INSTANCE = Mappers.getMapper(MilkCollectionMapper.class);

    MilkCollectionDto toDto(MilkCollection entity);
    MilkCollection toEntity(MilkCollectionDto dto);
}
