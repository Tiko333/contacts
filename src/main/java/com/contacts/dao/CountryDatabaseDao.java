package com.contacts.dao;

import com.contacts.domain.Country;
import com.contacts.exception.CountryNotFoundException;
import com.contacts.repository.CountryRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryDatabaseDao implements CountryDao {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private Logger logger;

    @Override
    public Country create(Country country) {
        logger.info("Creating country: " + country.getName() + " into database.");
        return countryRepository.save(country);
    }

    @Override
    public Country findById(Long id) {
        logger.info("Finding country by id: " + id + " from database.");
        return countryRepository.findById(id).orElseThrow(CountryNotFoundException::new);
    }

    @Override
    public void delete(Long id) {
        logger.info("Deleting country by id: " + id + " from database.");
        countryRepository.deleteById(id);
    }

    @Override
    public Country update(Country country) {
        logger.info("Updating country: " + country.getName() + " into database.");
        return countryRepository.save(country);
    }

    @Override
    public List<Country> findAll() {
        logger.info("Finding all countries from database.");
        List<Country> countries = new ArrayList<>();
        countryRepository.findAll().forEach(countries::add);

        return countries;
    }
}
