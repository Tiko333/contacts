package com.contacts.contacts.controller;

import com.contacts.controller.CountryController;
import com.contacts.model.CountryDto;
import com.contacts.service.CountryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CountryControllerTest {

    public static final long TEST_ID = 1L;
    public static final String TEST_NAME = "TEST_NAME";
    public static final String COUNTRY_CODE = "000";
    @Mock
    private CountryService countryService;

    @InjectMocks
    private CountryController countryController = new CountryController();

    @Test
    public void createTest() {
        CountryDto countryDto = createCountryDto();
        when(countryService.create(any())).thenReturn(countryDto);

        CountryDto createdCountryDto = countryController.create(any());

        verify(countryService, times(1)).create(any());

        assertNotNull(createdCountryDto);
        assertEquals(countryDto.getId(), createdCountryDto.getId());
        assertEquals(countryDto.getCountryCode(), createdCountryDto.getCountryCode());
        assertEquals(countryDto.getName(), createdCountryDto.getName());
    }

    @Test
    public void findByIdTest() {
        CountryDto countryDto = createCountryDto();
        when(countryService.findById(any())).thenReturn(countryDto);

        CountryDto countryDtoBId = countryController.findById(anyLong());

        verify(countryService, times(1)).findById(anyLong());
        assertEquals(countryDto.getId(), countryDtoBId.getId());
        assertEquals(countryDto.getName(), countryDtoBId.getName());
        assertEquals(countryDto.getCountryCode(), countryDtoBId.getCountryCode());
    }

    @Test
    public void deleteTest() {
        countryController.delete(anyLong());
        verify(countryService, times(1)).delete(anyLong());
    }

    @Test
    public void updateTest() {
        CountryDto countryDto = createCountryDto();
        when(countryService.update(any())).thenReturn(countryDto);

        CountryDto updatedCountryDto = countryController.update(countryDto, anyLong());

        verify(countryService, times(1)).update(any());
        assertEquals(countryDto.getId(), updatedCountryDto.getId());
        assertEquals(countryDto.getCountryCode(), updatedCountryDto.getCountryCode());
        assertEquals(countryDto.getName(), updatedCountryDto.getName());
    }

    @Test
    public void findAllTest() {
        CountryDto countryDto = createCountryDto();
        List<CountryDto> countriesDto = createCountriesDto(countryDto);
        when(countryService.findAll()).thenReturn(countriesDto);

        List<CountryDto> allcountries = countryController.findAll();

        verify(countryService, times(1)).findAll();
        assertNotNull(allcountries);
        for (int i = 0; i < countriesDto.size(); i++) {
            assertEquals(countriesDto.get(i).getId(), allcountries.get(i).getId());
            assertEquals(countriesDto.get(i).getCountryCode(), allcountries.get(i).getCountryCode());
            assertEquals(countriesDto.get(i).getName(), allcountries.get(i).getName());
        }
    }

    private CountryDto createCountryDto() {
        CountryDto countryDto = new CountryDto();
        countryDto.setId(TEST_ID);
        countryDto.setName(TEST_NAME);
        countryDto.setCountryCode(COUNTRY_CODE);

        return countryDto;
    }

    private ArrayList<CountryDto> createCountriesDto(CountryDto contactDto) {
        ArrayList<CountryDto> countriesDto = new ArrayList<>();

        countriesDto.add(contactDto);
        return countriesDto;
    }
}
