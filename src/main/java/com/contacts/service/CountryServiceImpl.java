package com.contacts.service;

import com.contacts.dao.CountryDao;
import com.contacts.domain.Country;
import com.contacts.mapper.CountryMapper;
import com.contacts.model.CountryDto;
import com.contacts.validator.CountryValidator;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CountryServiceImpl implements CountryService {

    private static final String DATABASE_DAO = "countryDatabaseDao";
    private static final String REST_CLIENT_DAO = "countryRestClientDao";

    @Autowired
    @Qualifier(DATABASE_DAO)
    private CountryDao countryDao;

    @Autowired
    private CountryValidator countryValidator;

    @Autowired
    private CountryMapper countryMapper;

    @Autowired
    private Logger logger;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public CountryDto create(CountryDto countryDto) {

        logger.info("Starting country validation.");
        countryValidator.validate(countryDto);
        logger.info("Validation have been successfully done.");

        Country country = countryMapper.toEntity(countryDto);

        Country savedCountryDto = countryDao.create(country);

        logger.info("Country: " + countryDto.getName() + " successfully created.");
        return countryMapper.toDto(savedCountryDto);
    }

    @Override
    public CountryDto findById(Long id) {
        Country country = countryDao.findById(id);

        logger.info("Country has been found: " + country.getName());
        return countryMapper.toDto(country);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public void delete(Long id) {
        countryDao.delete(id);
        logger.info("Country deleted.");
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public CountryDto update(CountryDto countryDto) {

        logger.info("Starting country validation.");
        countryValidator.validate(countryDto);
        logger.info("Validation have been successfully done.");

        Country country = countryDao.findById(countryDto.getId());

        countryMapper.updatedEntity(countryDto, country);

        Country updatedCountry = countryDao.update(country);

        logger.info("Country: " + updatedCountry.getName() + " successfully updated.");
        return countryMapper.toDto(country);
    }

    @Override
    public List<CountryDto> findAll() {

        List<Country> contacts = countryDao.findAll();
        List<CountryDto> savedContacts = new ArrayList<>();

        contacts.forEach(contact -> {
            CountryDto countryDto = countryMapper.toDto(contact);
            savedContacts.add(countryDto);
        });

        logger.info("Countries have been successfully found.");
        return savedContacts;
    }
}
