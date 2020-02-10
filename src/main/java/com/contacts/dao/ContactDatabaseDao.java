package com.contacts.dao;

import com.contacts.domain.Contact;
import com.contacts.exception.ContactNotFoundException;
import com.contacts.repository.ContactsRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactDatabaseDao implements ContactDao {

    @Autowired
    private ContactsRepository contactsRepository;

    @Autowired
    private Logger logger;

    @Override
    public Contact create(Contact contact) {
        logger.info("Creating contact into database.");
        return contactsRepository.save(contact);
    }

    @Override
    public Contact findById(Long id) {
        logger.info("Finding contact by id: " + id + " from database.");
        return contactsRepository.findById(id).orElseThrow(ContactNotFoundException::new);
    }

    @Override
    public void delete(Long id) {
        logger.info("Deleting contact by id: " + id + " from database.");
        contactsRepository.deleteById(id);
    }

    @Override
    public Contact update(Contact contact) {
        logger.info("Updating contact by id: " + contact.getId() + " into database.");
        return contactsRepository.save(contact);
    }

    @Override
    public List<Contact> findAll() {
        logger.info("Finding all contacts from database.");
        List<Contact> contacts = new ArrayList<>();
        contactsRepository.findAll().forEach(contacts::add);

        return contacts;
    }
}

