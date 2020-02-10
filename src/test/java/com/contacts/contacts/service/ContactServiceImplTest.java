package com.contacts.contacts.service;

import com.contacts.dao.ContactDao;
import com.contacts.domain.Address;
import com.contacts.domain.Company;
import com.contacts.domain.Contact;
import com.contacts.domain.Country;
import com.contacts.exception.ContactValidationException;
import com.contacts.mapper.ContactMapper;
import com.contacts.model.AddressDto;
import com.contacts.model.CompanyDto;
import com.contacts.model.ContactDto;
import com.contacts.model.CountryDto;
import com.contacts.service.ContactService;
import com.contacts.service.ContactServiceImpl;
import com.contacts.validator.ContactValidator;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ContactServiceImplTest {

    public static final String TEST_EMAIL = "test@gmail.com";
    public static final String TEST_FAX = "TestFax";
    public static final String TEST_PHONE = "TestPhone";
    public static final String TEST_COMPANY_NAME = "TestName";
    public static final String TEST_CITY = "TestCityName";
    public static final String TEST_STREET = "TestStreetName";
    public static final String TEST_COUNTRY_CODE = "111";
    public static final String TEST_COUNTRY_NAME = "TestCountryName";

    @Mock
    private ContactDao contactDao;

    @Mock
    private ContactMapper contactMapper;

    @Mock
    private ContactValidator contactValidator;

    @Mock
    private Logger logger;

    @InjectMocks
    private ContactService contactService = new ContactServiceImpl();

    @Test
    public void createTest() {

        ContactDto contactDto = createContactDto();
        Contact contact = createContact();

        when(contactMapper.toEntity(any())).thenReturn(contact);
        when(contactDao.create(any())).thenReturn(contact);
        when(contactMapper.toDto(any())).thenReturn(contactDto);

        ContactDto createdContact = contactService.create(any());

        verify(contactValidator, times(1)).validate(any());
        verify(contactMapper, times(1)).toEntity(any());
        verify(contactDao, times(1)).create(any());
        verify(contactMapper, times(1)).toDto(any());

        assertNotNull(createdContact);
        assertEquals(contactDto.getId(), createdContact.getId());
        assertEquals(contactDto.getPhone(), createdContact.getPhone());
        assertEquals(contactDto.getCompanyDto(), createdContact.getCompanyDto());
        assertEquals(contactDto.getEmail(), createdContact.getEmail());
        assertEquals(contactDto.getFax(), createdContact.getFax());

    }

    @Test
    public void validationFailTest() {
        doThrow(ContactValidationException.class).when(contactValidator).validate(any());

        assertThrows(ContactValidationException.class, () -> contactService.create(any()));
    }

    @Test
    public void findByIdTest() {
        ContactDto contactDto = createContactDto();
        Contact contact = createContact();

        when(contactDao.findById(any())).thenReturn(contact);
        when(contactMapper.toDto(any())).thenReturn(contactDto);

        ContactDto contactDtoById = contactService.findById(any());

        verify(contactDao, times(1)).findById(any());
        verify(contactMapper, times(1)).toDto(any());

        assertNotNull(contactDtoById);
        assertEquals(contactDto.getId(), contactDtoById.getId());
        assertEquals(contactDto.getFax(), contactDtoById.getFax());
        assertEquals(contactDto.getEmail(), contactDtoById.getEmail());
        assertEquals(contactDto.getCompanyDto(), contactDtoById.getCompanyDto());
        assertEquals(contactDto.getPhone(), contactDtoById.getPhone());
    }

    @Test
    public void deleteTest() {
        contactService.delete(anyLong());
        verify(contactDao, times(1)).delete(any());
    }

    @Test
    public void updateTest() {
        ContactDto contactDto = createContactDto();
        Contact contact = createContact();

        when(contactDao.findById(anyLong())).thenReturn(contact);
        when(contactDao.update(any())).thenReturn(contact);
        when(contactMapper.toDto(any())).thenReturn(contactDto);

        ContactDto updatedDto = contactService.update(contactDto);

        verify(contactValidator, times(1)).validate(any());
        verify(contactDao, times(1)).findById(any());
        verify(contactMapper, times(1)).updateEntity(any(), any());
        verify(contactDao, times(1)).update(any());
        verify(contactMapper, times(1)).toDto(any());

        assertNotNull(updatedDto);
        assertEquals(contactDto.getId(), updatedDto.getId());
        assertEquals(contactDto.getPhone(), updatedDto.getPhone());
        assertEquals(contactDto.getCompanyDto(), updatedDto.getCompanyDto());
        assertEquals(contactDto.getEmail(), updatedDto.getEmail());
        assertEquals(contactDto.getFax(), updatedDto.getFax());
    }

    @Test
    public void findAllTest() {
        List<Contact> contacts = createContacts();
        ContactDto contactDto = createContactDto();
        List<ContactDto> contactsDto = createContactsDto(contactDto);

        when(contactDao.findAll()).thenReturn(contacts);
        when(contactMapper.toDto(any())).thenReturn(contactDto);

        List<ContactDto> allContacts = contactService.findAll();

        verify(contactDao, times(1)).findAll();
        verify(contactMapper, times(contactsDto.size())).toDto(any());

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
        ContactDto contact = new ContactDto();
        contact.setId(1);
        contact.setCompanyDto(createCompanyDto());
        contact.setEmail(TEST_EMAIL);
        contact.setFax(TEST_FAX);
        contact.setPhone(TEST_PHONE);

        return contact;
    }

    private CompanyDto createCompanyDto() {
        CompanyDto company = new CompanyDto();
        company.setId(1);
        company.setAddressDto(createAddressDto());
        company.setName(TEST_COMPANY_NAME);

        return company;
    }

    private AddressDto createAddressDto() {
        AddressDto address = new AddressDto();
        address.setId(1);
        address.setCity(TEST_CITY);
        address.setStreet(TEST_STREET);
        address.setCountryDto(createCountryDto());

        return address;
    }

    private CountryDto createCountryDto() {
        CountryDto country = new CountryDto();
        country.setId(1);
        country.setCountryCode(TEST_COUNTRY_CODE);
        country.setName(TEST_COUNTRY_NAME);

        return country;
    }

    private ArrayList<Contact> createContacts() {
        ArrayList<Contact> contacts = new ArrayList<>();
        Contact contact = createContact();

        contacts.add(contact);
        return contacts;
    }

    private Contact createContact() {
        Contact contact = new Contact();
        contact.setId(1);
        contact.setCompany(createCompany());
        contact.setEmail(TEST_EMAIL);
        contact.setFax(TEST_FAX);
        contact.setPhone(TEST_PHONE);

        return contact;
    }

    private Company createCompany() {
        Company company = new Company();
        company.setId(1);
        company.setAddress(createAddress());
        company.setName(TEST_COMPANY_NAME);

        return company;
    }

    private Address createAddress() {
        Address address = new Address();
        address.setId(1);
        address.setCity(TEST_CITY);
        address.setStreet(TEST_STREET);
        address.setCountry(createCountry());

        return address;
    }

    private Country createCountry() {
        Country country = new Country();
        country.setId(1);
        country.setCountryCode(TEST_COUNTRY_CODE);
        country.setName(TEST_COUNTRY_NAME);

        return country;
    }

    private ArrayList<ContactDto> createContactsDto(ContactDto contactDto) {
        ArrayList<ContactDto> contactsDto = new ArrayList<>();

        contactsDto.add(contactDto);
        return contactsDto;
    }

}
