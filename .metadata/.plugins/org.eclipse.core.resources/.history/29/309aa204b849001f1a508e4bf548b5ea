package com.milkyway.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.milkyway.dto.CustomerDto;
import com.milkyway.entity.Customer;

@Mapper(uses = {MilkCollectionMapper.class, MoneyMapper.class})
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(source = "milkCollection", target = "milkCollectionDto")
    @Mapping(source = "moneyDetails", target = "moneyDetailsDto")
    CustomerDto toDto(Customer entity);
    
    @Mapping(source = "milkCollectionDto", target = "milkCollection")
    @Mapping(source = "moneyDetailsDto", target = "moneyDetails")
    Customer toEntity(CustomerDto dto);
}
