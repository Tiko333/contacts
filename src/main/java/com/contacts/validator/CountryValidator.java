package com.contacts.validator;

import com.contacts.exception.CountryValidationException;
import com.contacts.model.CountryDto;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CountryValidator {

    @Autowired
    private Logger logger;

    public void validate(CountryDto countryDto) {

        if (StringUtils.isEmpty(countryDto.getName())) {
            validationFailed("name");
        }

        if (StringUtils.isEmpty(countryDto.getCountryCode())) {
            validationFailed("countryCode");
        }

    }

    private void validationFailed(String reason) {
        logger.error("Validation failed: " + reason + " field is null.");
        throw new CountryValidationException();
    }

}
