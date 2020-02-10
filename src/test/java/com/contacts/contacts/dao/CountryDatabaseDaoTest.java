package com.contacts.contacts.dao;

import com.contacts.dao.CountryDao;
import com.contacts.dao.CountryDatabaseDao;
import com.contacts.domain.Country;
import com.contacts.repository.CountryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.of;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CountryDatabaseDaoTest {

    public static final long TEST_ID = 1L;
    public static final String TEST_NAME = "testName";
    public static final String TEST_CODE = "testCode";

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private Logger logger;

    @InjectMocks
    private CountryDao countryDao = new CountryDatabaseDao();

    @Test
    public void createTest() {
        Country country = createCountry();
        when(countryRepository.save(any())).thenReturn(country);

        Country createdContact = countryDao.create(country);

        verify(countryRepository, times(1)).save(any());

        assertNotNull(createdContact);
        assertEquals(country.getId(), createdContact.getId());
        assertEquals(country.getName(), createdContact.getName());
        assertEquals(country.getCountryCode(), createdContact.getCountryCode());
    }

    @Test
    public void findByIdTest() {
        Country country = createCountry();
        when(countryRepository.findById(anyLong())).thenReturn(of(country));

        Country createdCountry = countryDao.findById(anyLong());

        verify(countryRepository, times(1)).findById(anyLong());

        assertNotNull(createdCountry);
        assertEquals(country.getId(), createdCountry.getId());
        assertEquals(country.getName(), createdCountry.getName());
        assertEquals(country.getCountryCode(), createdCountry.getCountryCode());
    }

    @Test
    public void deleteTest() {
        countryDao.delete(anyLong());
        verify(countryRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void updateTest() {
        Country country = createCountry();
        when(countryRepository.save(any())).thenReturn(country);

        Country updatedCountry = countryDao.update(country);

        verify(countryRepository, times(1)).save(any());

        assertNotNull(updatedCountry);
        assertEquals(country.getId(), updatedCountry.getId());
        assertEquals(country.getName(), updatedCountry.getName());
        assertEquals(country.getCountryCode(), updatedCountry.getCountryCode());

    }

    @Test
    public void findAllTest() {
        List<Country> countries = createCountries();
        when(countryRepository.findAll()).thenReturn(countries);

        List<Country> allCountries = countryDao.findAll();

        verify(countryRepository, times(1)).findAll();
        for (int i = 0; i < countries.size(); i++) {
            assertEquals(countries.get(i).getId(), allCountries.get(i).getId());
            assertEquals(countries.get(i).getName(), allCountries.get(i).getName());
            assertEquals(countries.get(i).getCountryCode(), allCountries.get(i).getCountryCode());
        }
    }

    private ArrayList<Country> createCountries() {
        ArrayList<Country> countries = new ArrayList<>();
        Country country = createCountry();

        countries.add(country);
        return countries;
    }

    private Country createCountry() {
        Country country = new Country();
        country.setId(TEST_ID);
        country.setName(TEST_NAME);
        country.setCountryCode(TEST_CODE);

        return country;
    }

}
