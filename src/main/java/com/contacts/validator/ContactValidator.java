package com.contacts.validator;

import com.contacts.exception.ContactValidationException;
import com.contacts.model.ContactDto;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ContactValidator {

    @Autowired
    private Logger logger;

    public void validate(ContactDto contactDto) {

        if (contactDto.getCompanyDto() == null) {
            validationFailed("Contact's company");
        }

        if (StringUtils.isEmpty(contactDto.getPhone())) {
            validationFailed("Contact's phone");
        }

        if (StringUtils.isEmpty(contactDto.getEmail())) {
            validationFailed("Contact's email");
        }

        if (contactDto.getCompanyDto().getAddressDto() == null) {
            validationFailed("Company's address");
        }

        if (StringUtils.isEmpty(contactDto.getCompanyDto().getName())) {
            validationFailed("Company's name");
        }

        if (contactDto.getCompanyDto().getAddressDto().getCountryDto() == null) {
            validationFailed("Address's country");
        }

        if (StringUtils.isEmpty(contactDto.getCompanyDto().getAddressDto().getCity())) {
            validationFailed("Address's city");
        }

        if (StringUtils.isEmpty(contactDto.getCompanyDto().getAddressDto().getStreet())) {
            validationFailed("Address's street");
        }

        if (StringUtils.isEmpty(contactDto.getCompanyDto().getAddressDto().getCountryDto().getName())) {
            validationFailed("Country's name");
        }

        if (StringUtils.isEmpty(contactDto.getCompanyDto().getAddressDto().getCountryDto().getCountryCode())) {
            validationFailed("Country's countryCode");
        }

    }

    private void validationFailed(String reason) {
        logger.error("Validation failed: " + reason + " field is null.");
        throw new ContactValidationException();
    }

}
