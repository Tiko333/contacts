package com.contacts.dao;

import com.contacts.domain.Country;

import java.util.List;

public interface CountryDao {
    Country create(Country country);

    Country findById(Long id);

    void delete(Long id);

    Country update(Country country);

    List<Country> findAll();
}
