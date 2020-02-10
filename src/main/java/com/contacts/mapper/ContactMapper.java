package com.contacts.mapper;

import com.contacts.domain.Contact;
import com.contacts.model.ContactDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    @Mapping(source = "contactDto.companyDto", target = "company")
    @Mapping(source = "contactDto.companyDto.addressDto", target = "company.address")
    @Mapping(source = "contactDto.companyDto.addressDto.countryDto", target = "company.address.country")
    Contact toEntity(ContactDto contactDto);

    @Mapping(source = "contact.company", target = "companyDto")
    @Mapping(source = "contact.company.address", target = "companyDto.addressDto")
    @Mapping(source = "contact.company.address.country", target = "companyDto.addressDto.countryDto")
    ContactDto toDto(Contact contact);

    @Mapping(source = "contactDto.companyDto", target = "company")
    @Mapping(source = "contactDto.companyDto.addressDto", target = "company.address")
    @Mapping(source = "contactDto.companyDto.addressDto.countryDto", target = "company.address.country")
    void updateEntity(ContactDto contactDto, @MappingTarget Contact contact);

}
