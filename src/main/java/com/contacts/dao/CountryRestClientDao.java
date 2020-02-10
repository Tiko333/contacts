package com.contacts.dao;

import com.contacts.domain.Country;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CountryRestClientDao implements CountryDao {

    @Autowired
    private Logger logger;

    @Override
    public Country create(Country country) {
        logger.info("Creating country: " + country.getName() + " into remote server.");
        return new Country();
    }

    @Override
    public Country findById(Long id) {
        logger.info("Finding country by id: " + id + " from remote server.");
        return new Country();
    }

    @Override
    public void delete(Long id) {
        logger.info("Deleting country by id: " + id + " from remote server.");
    }

    @Override
    public Country update(Country country) {
        logger.info("Updating country: " + country.getName() + " into remote server.");
        return new Country();
    }

    @Override
    public List<Country> findAll() {
        logger.info("Finding all countries f" +
                "rom remote server.");
        return new ArrayList<>(Arrays.asList(new Country()));
    }
}
