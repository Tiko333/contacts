package com.contacts.service;

import com.contacts.model.CountryDto;

import java.util.List;

public interface CountryService {

    CountryDto create(CountryDto countryDto);

    CountryDto findById(Long id);

    void delete(Long id);

    CountryDto update(CountryDto countryDto);

    List<CountryDto> findAll();

}
