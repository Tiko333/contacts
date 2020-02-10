package com.contacts.mapper;

import com.contacts.domain.Company;
import com.contacts.model.CompanyDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    Company toEntity(CompanyDto companyDto);

    CompanyDto toDto(Company company);

}
