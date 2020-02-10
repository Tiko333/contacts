package com.contacts.contacts.service;

import com.contacts.dao.CountryDao;
import com.contacts.domain.Country;
import com.contacts.exception.CountryValidationException;
import com.contacts.mapper.CountryMapper;
import com.contacts.model.CountryDto;
import com.contacts.service.CountryService;
import com.contacts.service.CountryServiceImpl;
import com.contacts.validator.CountryValidator;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CountryServiceImplTest {

    public static final String TEST_COUNTRY_CODE = "123";
    public static final String TEST_COUNTRY_DTO_NAME = "TestCountryName";

    @Mock
    private CountryDao countryDao;

    @Mock
    private CountryMapper countryMapper;

    @Mock
    private CountryValidator countryValidator;

    @Mock
    private Logger logger;

    @InjectMocks
    private CountryService countryService = new CountryServiceImpl();

    @Test
    public void createTest() {
        CountryDto countryDto = createCountryDto();
        Country country = createCountry();

        when(countryMapper.toEntity(any())).thenReturn(country);
        when(countryDao.create(any())).thenReturn(country);
        when(countryMapper.toDto(any())).thenReturn(countryDto);

        CountryDto createdCountryDto = countryService.create(countryDto);

        verify(countryValidator, times(1)).validate(any());
        verify(countryMapper, times(1)).toEntity(any());
        verify(countryDao, times(1)).create(any());
        verify(countryMapper, times(1)).toDto(any());

        assertNotNull(createdCountryDto);
        assertEquals(countryDto.getId(), createdCountryDto.getId());
        assertEquals(countryDto.getName(), createdCountryDto.getName());
        assertEquals(countryDto.getCountryCode(), createdCountryDto.getCountryCode());

    }

    @Test
    public void validationFailTest() {
        doThrow(CountryValidationException.class).when(countryValidator).validate(any());

        assertThrows(CountryValidationException.class, () -> countryService.create(any()));
    }

    @Test
    public void FindByIdTest() {
        CountryDto countryDto = createCountryDto();
        Country country = createCountry();

        when(countryDao.findById(anyLong())).thenReturn(country);
        when(countryMapper.toDto(any())).thenReturn(countryDto);

        CountryDto countryDtoById = countryService.findById(anyLong());

        verify(countryDao, times(1)).findById(any());
        verify(countryMapper, times(1)).toDto(any());

        assertNotNull(countryDtoById);
        assertEquals(countryDto.getId(), countryDtoById.getId());
        assertEquals(countryDto.getCountryCode(), countryDtoById.getCountryCode());
        assertEquals(countryDto.getName(), countryDtoById.getName());
    }

    @Test
    public void deleteTest() {
        countryService.delete(anyLong());
        verify(countryDao, times(1)).delete(anyLong());
    }

    @Test
    public void updateTest() {
        CountryDto countryDto = createCountryDto();
        Country country = createCountry();

        when(countryDao.findById(anyLong())).thenReturn(country);
        when(countryDao.update(any())).thenReturn(country);
        when(countryMapper.toDto(any())).thenReturn(countryDto);

        CountryDto updatedCountryDto = countryService.update(countryDto);

        verify(countryValidator, times(1)).validate(any());
        verify(countryDao, times(1)).findById(anyLong());
        verify(countryMapper, times(1)).updatedEntity(any(), any());
        verify(countryDao, times(1)).update(any());
        verify(countryMapper, times(1)).toDto(any());

        assertNotNull(updatedCountryDto);
        assertEquals(countryDto.getId(), updatedCountryDto.getId());
        assertEquals(countryDto.getName(), updatedCountryDto.getName());
        assertEquals(countryDto.getCountryCode(), updatedCountryDto.getCountryCode());
    }

    @Test
    public void findAllTest() {
        List<Country> countries = createCountries();
        CountryDto countryDto = createCountryDto();
        List<CountryDto> countriesDto = createCountriesDto(countryDto);

        when(countryDao.findAll()).thenReturn(countries);
        when(countryMapper.toDto(any())).thenReturn(countryDto);

        List<CountryDto> allContacts = countryService.findAll();

        verify(countryDao, times(1)).findAll();
        verify(countryMapper, times(allContacts.size())).toDto(any());

        assertNotNull(allContacts);
        for (int i = 0; i < countriesDto.size(); i++) {
            assertEquals(countriesDto.get(i).getId(), allContacts.get(i).getId());
            assertEquals(countriesDto.get(i).getCountryCode(), allContacts.get(i).getCountryCode());
            assertEquals(countriesDto.get(i).getName(), allContacts.get(i).getName());
        }
    }

    private List<CountryDto> createCountriesDto(CountryDto countryDto) {
        ArrayList<CountryDto> countriesDto = new ArrayList<>();

        countriesDto.add(countryDto);
        return countriesDto;
    }

    private List<Country> createCountries() {
        ArrayList<Country> countries = new ArrayList<>();
        Country country = createCountry();

        countries.add(country);
        return countries;
    }

    private CountryDto createCountryDto() {
        CountryDto countryDto = new CountryDto();
        countryDto.setId(1);
        countryDto.setCountryCode(TEST_COUNTRY_CODE);
        countryDto.setName(TEST_COUNTRY_DTO_NAME);
        return countryDto;
    }

    private Country createCountry() {
        return new Country();
    }

}
