package com.contacts.mapper;

import com.contacts.domain.Country;
import com.contacts.model.CountryDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    Country toEntity(CountryDto countryDto);

    CountryDto toDto(Country country);

    void updatedEntity(CountryDto countryDto, @MappingTarget Country country);

}
