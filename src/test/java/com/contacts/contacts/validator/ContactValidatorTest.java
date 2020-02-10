package com.contacts.contacts.validator;

import com.contacts.exception.ContactValidationException;
import com.contacts.model.AddressDto;
import com.contacts.model.CompanyDto;
import com.contacts.model.ContactDto;
import com.contacts.model.CountryDto;
import com.contacts.validator.ContactValidator;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ContactValidatorTest {

    public static final Long TEST_ID = 1L;
    public static final String TEST_CONTACT_EMAIL = "test@gmail.com";
    public static final String TEST_FAX = "+37415146546816";
    public static final String TEST_CONTACT_PHONE = "+3745555555";
    public static final String TEST_COMPANY_NAME = "TestName";
    public static final String TEST_CITY = "Yerevan";
    public static final String TEST_STREET = "Estonakan";
    public static final String TEST_COUNTRY_CODE = "111";
    public static final String TEST_COUNTRY_NAME = "Armenia";
    public static final String TEST_ADDRESS_NAME = "testAddressName";

    @Mock
    private Logger logger;

    @Mock
    private ContactValidator contactValidatorMock;

    @InjectMocks
    private ContactValidator contactValidator = new ContactValidator();

    @Test
    public void validateSuccessTest() {
        CompanyDto companyDto = new CompanyDto();
        AddressDto addressDto = new AddressDto();
        CountryDto countryDto = new CountryDto();

        ContactDto contactDto = createContactDto(
                companyDto,
                TEST_COMPANY_NAME,
                TEST_CONTACT_EMAIL,
                TEST_CONTACT_PHONE,
                addressDto,
                TEST_ADDRESS_NAME,
                countryDto,
                TEST_COUNTRY_NAME,
                TEST_COUNTRY_CODE
        );

        contactValidatorMock.validate(contactDto);

        verify(contactValidatorMock, times(1)).validate(any());
    }

    @Test
    public void validateNullCompanyTest() {
        AddressDto addressDto = new AddressDto();
        CountryDto countryDto = new CountryDto();

        ContactDto contactDto = createContactDto(
                null,
                TEST_COMPANY_NAME,
                TEST_CONTACT_EMAIL,
                TEST_CONTACT_PHONE,
                addressDto,
                TEST_ADDRESS_NAME,
                countryDto,
                TEST_COUNTRY_NAME,
                TEST_COUNTRY_CODE
        );

        assertThrows(ContactValidationException.class, () -> contactValidator.validate(contactDto));
    }

    @Test
    public void validateNullCompanyNameTest() {
        AddressDto addressDto = new AddressDto();
        CompanyDto companyDto = new CompanyDto();
        CountryDto countryDto = new CountryDto();

        ContactDto contactDto = createContactDto(
                companyDto,
                null,
                TEST_CONTACT_EMAIL,
                TEST_CONTACT_PHONE,
                addressDto,
                TEST_ADDRESS_NAME,
                countryDto,
                TEST_COUNTRY_NAME,
                TEST_COUNTRY_CODE
        );

        assertThrows(ContactValidationException.class, () -> contactValidator.validate(contactDto));
    }

    @Test
    public void validateNullCompanyEmailTest() {
        CompanyDto companyDto = new CompanyDto();
        AddressDto addressDto = new AddressDto();
        CountryDto countryDto = new CountryDto();

        ContactDto contactDto = createContactDto(
                companyDto,
                TEST_COMPANY_NAME,
                null,
                TEST_CONTACT_PHONE,
                addressDto,
                TEST_ADDRESS_NAME,
                countryDto,
                TEST_COUNTRY_NAME,
                TEST_COUNTRY_CODE
        );

        assertThrows(ContactValidationException.class, () -> contactValidator.validate(contactDto));
    }

    @Test
    public void validateNullCompanyPhoneTest() {
        CompanyDto companyDto = new CompanyDto();
        AddressDto addressDto = new AddressDto();
        CountryDto countryDto = new CountryDto();

        ContactDto contactDto = createContactDto(
                companyDto,
                TEST_COMPANY_NAME,
                TEST_CONTACT_EMAIL,
                null,
                addressDto,
                TEST_ADDRESS_NAME,
                countryDto,
                TEST_COUNTRY_NAME,
                TEST_COUNTRY_CODE
        );

        assertThrows(ContactValidationException.class, () -> contactValidator.validate(contactDto));
    }

    @Test
    public void validateNullAddressTest() {
        CompanyDto companyDto = new CompanyDto();
        AddressDto addressDto = new AddressDto();
        CountryDto countryDto = new CountryDto();

        ContactDto contactDto = createContactDto(
                companyDto,
                TEST_COMPANY_NAME,
                TEST_CONTACT_EMAIL,
                TEST_CONTACT_PHONE,
                null,
                TEST_ADDRESS_NAME,
                countryDto,
                TEST_COUNTRY_NAME,
                TEST_COUNTRY_CODE
        );

        assertThrows(ContactValidationException.class, () -> contactValidator.validate(contactDto));
    }


    @Test
    public void validateNullAddressNameTest() {
        CompanyDto companyDto = new CompanyDto();
        AddressDto addressDto = new AddressDto();
        CountryDto countryDto = new CountryDto();

        ContactDto contactDto = createContactDto(
                companyDto,
                TEST_COMPANY_NAME,
                TEST_CONTACT_EMAIL,
                TEST_CONTACT_PHONE,
                addressDto,
                null,
                countryDto,
                TEST_COUNTRY_NAME,
                TEST_COUNTRY_CODE
        );

        assertThrows(ContactValidationException.class, () -> contactValidator.validate(contactDto));
    }

    @Test
    public void validateNullCountryTest() {
        CompanyDto companyDto = new CompanyDto();
        AddressDto addressDto = new AddressDto();
        CountryDto countryDto = new CountryDto();

        ContactDto contactDto = createContactDto(
                companyDto,
                TEST_COMPANY_NAME,
                TEST_CONTACT_EMAIL,
                TEST_CONTACT_PHONE,
                addressDto,
                TEST_ADDRESS_NAME,
                null,
                TEST_COUNTRY_NAME,
                TEST_COUNTRY_CODE
        );

        assertThrows(ContactValidationException.class, () -> contactValidator.validate(contactDto));
    }

    @Test
    public void validateNullCountryNameTest() {
        CompanyDto companyDto = new CompanyDto();
        AddressDto addressDto = new AddressDto();
        CountryDto countryDto = new CountryDto();

        ContactDto contactDto = createContactDto(
                companyDto,
                TEST_COMPANY_NAME,
                TEST_CONTACT_EMAIL,
                TEST_CONTACT_PHONE,
                addressDto,
                TEST_ADDRESS_NAME,
                countryDto,
                null,
                TEST_COUNTRY_CODE
        );

        assertThrows(ContactValidationException.class, () -> contactValidator.validate(contactDto));
    }

    @Test
    public void validateNullCountryCodeTest() {
        CompanyDto companyDto = new CompanyDto();
        AddressDto addressDto = new AddressDto();
        CountryDto countryDto = new CountryDto();

        ContactDto contactDto = createContactDto(
                companyDto,
                TEST_COMPANY_NAME,
                TEST_CONTACT_EMAIL,
                TEST_CONTACT_PHONE,
                addressDto,
                TEST_ADDRESS_NAME,
                countryDto,
                TEST_COUNTRY_NAME,
                null
        );

        assertThrows(ContactValidationException.class, () -> contactValidator.validate(contactDto));
    }


    private ContactDto createContactDto(CompanyDto companyDto,
                                        String companyName,
                                        String companyEmail,
                                        String companyPhone,
                                        AddressDto addressDto,
                                        String addressName,
                                        CountryDto countryDto,
                                        String countryName,
                                        String countryCode) {
        ContactDto contact = new ContactDto();
        if (companyEmail != null && companyPhone != null) {
            contact.setCompanyDto(createCompanyDto(companyDto, companyName, addressDto, addressName, countryDto, countryName, countryCode));
            contact.setEmail(companyEmail);
            contact.setFax(TEST_FAX);
            contact.setPhone(companyPhone);

            return contact;
        }

        return contact;
    }

    private CompanyDto createCompanyDto(CompanyDto companyDto, String companyName, AddressDto addressDto, String addressName, CountryDto countryDto, String countryName, String countryCode) {
        if (companyDto != null && companyName != null) {
            companyDto.setId(TEST_ID);
            companyDto.setAddressDto(createAddressDto(addressDto, addressName, countryDto, countryName, countryCode));
            companyDto.setName(companyName);

            return companyDto;
        }

        return null;
    }

    private AddressDto createAddressDto(AddressDto addressDto, String addressName, CountryDto countryDto, String countryName, String countryCode) {
        if (addressDto != null && addressName != null) {
            addressDto.setId(TEST_ID);
            addressDto.setCity(TEST_CITY);
            addressDto.setStreet(TEST_STREET);
            addressDto.setCountryDto(createCountryDto(countryDto, countryName, countryCode));
            return addressDto;
        }

        return null;
    }

    private CountryDto createCountryDto(CountryDto countryDto, String countryName, String countryCode) {
        if (countryDto != null && countryName != null && countryCode != null) {
            countryDto.setId(TEST_ID);
            countryDto.setCountryCode(TEST_COUNTRY_CODE);
            countryDto.setName(TEST_COUNTRY_NAME);

            return countryDto;
        }

        return null;
    }

}
