package com.contacts.service;

import com.contacts.dao.ContactDao;
import com.contacts.domain.Contact;
import com.contacts.mapper.ContactMapper;
import com.contacts.model.ContactDto;
import com.contacts.validator.ContactValidator;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ContactServiceImpl implements ContactService {

    private static final String DATABASE_DAO = "contactDatabaseDao";
    private static final String REST_CLIENT_DAO = "contactRestClientDao";

    @Autowired
    @Qualifier(DATABASE_DAO)
    private ContactDao contactDao;

    @Autowired
    private ContactValidator contactValidator;

    @Autowired
    private ContactMapper contactMapper;

    @Autowired
    private Logger logger;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public ContactDto create(ContactDto contactDto) {

        logger.info("Starting contact validation.");
        contactValidator.validate(contactDto);
        logger.info("Validation have been successfully done.");

        Contact contact = contactMapper.toEntity(contactDto);

        Contact savedContact = contactDao.create(contact);
        logger.info("Contact successfully created.");
        return contactMapper.toDto(savedContact);

    }

    @Override
    public ContactDto findById(Long id) {

        Contact contact = contactDao.findById(id);
        logger.info("Contact has been found.");
        return contactMapper.toDto(contact);

    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public void delete(Long id) {

        contactDao.delete(id);
        logger.info("Contact deleted.");

    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public ContactDto update(ContactDto contactDto) {

        logger.info("Starting contact validation.");
        contactValidator.validate(contactDto);
        logger.info("Validation have been successfully done.");

        Contact contact = contactDao.findById(contactDto.getId());

        contactMapper.updateEntity(contactDto, contact);

        Contact updatedContact = contactDao.update(contact);
        logger.info("Contact successfully updated.");
        return contactMapper.toDto(updatedContact);

    }

    @Override
    public List<ContactDto> findAll() {

        List<Contact> contacts = contactDao.findAll();
        List<ContactDto> savedContacts = new ArrayList<>();

        contacts.forEach(contact -> {
            ContactDto contactDto = contactMapper.toDto(contact);
            savedContacts.add(contactDto);
        });

        logger.info("Contacts have been successfully found.");
        return savedContacts;

    }
}
