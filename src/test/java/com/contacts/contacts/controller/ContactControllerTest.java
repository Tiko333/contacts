package com.contacts.contacts.controller;

import com.contacts.controller.ContactController;
import com.contacts.model.AddressDto;
import com.contacts.model.CompanyDto;
import com.contacts.model.ContactDto;
import com.contacts.model.CountryDto;
import com.contacts.service.ContactService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ContactControllerTest {

    public static final String TEST_EMAIL = "test@gmail.com";
    public static final String TEST_FAX = "+37415146546816";
    public static final String TEST_PHONE = "+3745555555";
    public static final String TEST_COMPANY_NAME = "TestName";
    public static final String TEST_CITY = "Yerevan";
    public static final String TEST_STREET = "Estonakan";
    public static final String TEST_COUNTRY_CODE = "111";
    public static final String TEST_COUNTRY_NAME = "Armenia";
    public static final Long TEST_ID = 1L;

    @Mock
    private ContactService contactService;

    @InjectMocks
    private ContactController contactController = new ContactController();

    @Test
    public void createTest() {
        ContactDto contactDto = createContactDto();
        when(contactService.create(any())).thenReturn(contactDto);

        ContactDto createdContactDto = contactController.create(any());

        verify(contactService, times(1)).create(any());

        assertNotNull(createdContactDto);
        assertEquals(contactDto.getId(), createdContactDto.getId());
        assertEquals(contactDto.getPhone(), createdContactDto.getPhone());
        assertEquals(contactDto.getCompanyDto(), createdContactDto.getCompanyDto());
        assertEquals(contactDto.getEmail(), createdContactDto.getEmail());
        assertEquals(contactDto.getFax(), createdContactDto.getFax());
    }

    @Test
    public void findByIdTest() {
        ContactDto contactDto = createContactDto();
        when(contactService.findById(any())).thenReturn(contactDto);

        ContactDto contactById = contactController.findById(anyLong());

        verify(contactService, times(1)).findById(anyLong());
        assertEquals(contactDto.getId(), contactById.getId());
        assertEquals(contactDto.getPhone(), contactById.getPhone());
        assertEquals(contactDto.getCompanyDto(), contactById.getCompanyDto());
        assertEquals(contactDto.getEmail(), contactById.getEmail());
        assertEquals(contactDto.getFax(), contactById.getFax());
    }

    @Test
    public void deleteTest() {
        contactController.delete(anyLong());
        verify(contactService, times(1)).delete(anyLong());
    }

    @Test
    public void updateTest() {
        ContactDto contactDto = createContactDto();
        when(contactService.update(any())).thenReturn(contactDto);

        ContactDto updatedContact = contactController.update(contactDto, anyLong());

        verify(contactService, times(1)).update(any());
        assertEquals(contactDto.getId(), updatedContact.getId());
        assertEquals(contactDto.getPhone(), updatedContact.getPhone());
        assertEquals(contactDto.getCompanyDto(), updatedContact.getCompanyDto());
        assertEquals(contactDto.getEmail(), updatedContact.getEmail());
        assertEquals(contactDto.getFax(), updatedContact.getFax());
    }

    @Test
    public void findAllTest() {
        ContactDto contactDto = createContactDto();
        List<ContactDto> contactsDto = createContactsDto(contactDto);
        when(contactService.findAll()).thenReturn(contactsDto);

        List<ContactDto> allContacts = contactController.findAll();

        verify(contactService, times(1)).findAll();
        assertNotNull(allContacts);
        for (int i = 0; i < contactsDto.size(); i++) {
            assertEquals(contactsDto.get(i).getId(), allContacts.get(i).getId());
            assertEquals(contactsDto.get(i).getFax(), allContacts.get(i).getFax());
            assertEquals(contactsDto.get(i).getEmail(), allContacts.get(i).getEmail());
            assertEquals(contactsDto.get(i).getCompanyDto(), allContacts.get(i).getCompanyDto());
            assertEquals(contactsDto.get(i).getPhone(), allContacts.get(i).getPhone());
        }
    }

    private ContactDto createContactDto() {
        ContactDto contactDto = new ContactDto();
        contactDto.setId(TEST_ID);
        contactDto.setCompanyDto(createCompanyDto());
        contactDto.setEmail(TEST_EMAIL);
        contactDto.setFax(TEST_FAX);
        contactDto.setPhone(TEST_PHONE);

        return contactDto;
    }

    private CompanyDto createCompanyDto() {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setId(TEST_ID);
        companyDto.setAddressDto(createAddressDto());
        companyDto.setName(TEST_COMPANY_NAME);

        return companyDto;
    }

    private AddressDto createAddressDto() {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(TEST_ID);
        addressDto.setCity(TEST_CITY);
        addressDto.setStreet(TEST_STREET);
        addressDto.setCountryDto(createCountryDto());

        return addressDto;
    }

    private CountryDto createCountryDto() {
        CountryDto countryDto = new CountryDto();
        countryDto.setId(TEST_ID);
        countryDto.setCountryCode(TEST_COUNTRY_CODE);
        countryDto.setName(TEST_COUNTRY_NAME);

        return countryDto;
    }

    private ArrayList<ContactDto> createContactsDto(ContactDto contactDto) {
        ArrayList<ContactDto> contactsDto = new ArrayList<>();

        contactsDto.add(contactDto);
        return contactsDto;
    }

}
