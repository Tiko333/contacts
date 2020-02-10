package com.contacts.contacts.dao;

import com.contacts.dao.ContactDao;
import com.contacts.dao.ContactDatabaseDao;
import com.contacts.domain.Address;
import com.contacts.domain.Company;
import com.contacts.domain.Contact;
import com.contacts.domain.Country;
import com.contacts.repository.ContactsRepository;
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
import static org.mockito.Mockito.*;

@SpringBootTest
public class ContactDatabaseDaoTest {

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
    private ContactsRepository contactsRepository;

    @Mock
    private Logger logger;

    @InjectMocks
    private ContactDao contactDao = new ContactDatabaseDao();

    @Test
    public void createTest() {
        Contact contact = createContact();
        when(contactsRepository.save(any())).thenReturn(contact);

        Contact createdContact = contactDao.create(contact);

        verify(contactsRepository, times(1)).save(any());

        assertNotNull(createdContact);
        assertEquals(contact.getId(), createdContact.getId());
        assertEquals(contact.getCompany(), createdContact.getCompany());
        assertEquals(contact.getEmail(), createdContact.getEmail());
        assertEquals(contact.getFax(), createdContact.getFax());
        assertEquals(contact.getPhone(), createdContact.getPhone());
    }

    @Test
    public void findByIdTest() {
        Contact contact = createContact();
        when(contactsRepository.findById(any())).thenReturn(of(contact));

        Contact createdContact = contactDao.findById(anyLong());

        verify(contactsRepository, times(1)).findById(anyLong());

        assertNotNull(createdContact);
        assertEquals(contact.getId(), createdContact.getId());
        assertEquals(contact.getCompany(), createdContact.getCompany());
        assertEquals(contact.getEmail(), createdContact.getEmail());
        assertEquals(contact.getFax(), createdContact.getFax());
        assertEquals(contact.getPhone(), createdContact.getPhone());
    }

    @Test
    public void deleteTest() {
        contactDao.delete(anyLong());
        verify(contactsRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void updateTest() {
        Contact contact = createContact();
        when(contactsRepository.save(any())).thenReturn(contact);

        Contact updatedContact = contactDao.update(contact);

        verify(contactsRepository, times(1)).save(any());

        assertNotNull(updatedContact);
        assertEquals(contact.getId(), updatedContact.getId());
        assertEquals(contact.getPhone(), updatedContact.getPhone());
        assertEquals(contact.getFax(), updatedContact.getFax());
        assertEquals(contact.getEmail(), updatedContact.getEmail());
        assertEquals(contact.getCompany(), updatedContact.getCompany());
    }

    @Test
    public void findAllTest() {
        List<Contact> contacts = createContacts();
        when(contactsRepository.findAll()).thenReturn(contacts);

        List<Contact> allContacts = contactDao.findAll();

        verify(contactsRepository, times(1)).findAll();
        for (int i = 0; i < contacts.size(); i++) {
            assertEquals(contacts.get(i).getId(), allContacts.get(i).getId());
            assertEquals(contacts.get(i).getFax(), allContacts.get(i).getFax());
            assertEquals(contacts.get(i).getEmail(), allContacts.get(i).getEmail());
            assertEquals(contacts.get(i).getCompany(), allContacts.get(i).getCompany());
            assertEquals(contacts.get(i).getPhone(), allContacts.get(i).getPhone());
        }
    }

    private ArrayList<Contact> createContacts() {
        ArrayList<Contact> contacts = new ArrayList<>();
        Contact contact = createContact();

        contacts.add(contact);
        return contacts;
    }

    private Contact createContact() {
        Contact contact = new Contact();
        contact.setId(TEST_ID);
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
}
