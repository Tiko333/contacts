package com.contacts.contacts.validator;

import com.contacts.exception.CountryValidationException;
import com.contacts.model.CountryDto;
import com.contacts.validator.CountryValidator;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CountryValidatorTest {

    public static final Long TEST_ID = 1L;
    public static final String TEST_COUNTRY_CODE = "111";
    public static final String TEST_COUNTRY_NAME = "Armenia";

    @Mock
    private Logger logger;

    @Mock
    private CountryValidator countryValidatorMock;

    @InjectMocks
    private CountryValidator countryValidator = new CountryValidator();

    @Test
    public void validateSuccessTest() {
        CountryDto countryDto = createCountryDto(
                TEST_COUNTRY_NAME,
                TEST_COUNTRY_CODE
        );

        countryValidatorMock.validate(countryDto);

        verify(countryValidatorMock, times(1)).validate(any());
    }

    @Test
    public void validateNullCountryNameTest() {
        CountryDto countryDto = createCountryDto(
                null,
                TEST_COUNTRY_CODE
        );

        assertThrows(CountryValidationException.class, () -> countryValidator.validate(countryDto));
    }

    @Test
    public void validateNullCountryCodeTest() {
        CountryDto countryDto = createCountryDto(
                TEST_COUNTRY_NAME,
                null
        );

        assertThrows(CountryValidationException.class, () -> countryValidator.validate(countryDto));
    }

    private CountryDto createCountryDto(String countryName, String countryCode) {
        CountryDto countryDto = new CountryDto();

        if (countryName != null && countryCode != null) {
            countryDto.setId(TEST_ID);
            countryDto.setCountryCode(TEST_COUNTRY_CODE);
            countryDto.setName(TEST_COUNTRY_NAME);

            return countryDto;
        }

        return countryDto;
    }

}
