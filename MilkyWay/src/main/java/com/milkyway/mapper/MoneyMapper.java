package com.milkyway.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.milkyway.dto.MoneyDto;
import com.milkyway.entity.Money;

@Mapper
public interface MoneyMapper {
    MoneyMapper INSTANCE = Mappers.getMapper(MoneyMapper.class);

    MoneyDto toDto(Money entity);
    Money toEntity(MoneyDto dto);
}
