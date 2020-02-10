package com.contacts.mapper;

import com.contacts.domain.Address;
import com.contacts.model.AddressDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address toEntity(AddressDto addressDto);

    AddressDto toDto(Address address);

}
