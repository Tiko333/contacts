package com.contacts.dao;

import com.contacts.domain.Contact;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactRestClientDao implements ContactDao {

    @Autowired
    private Logger logger;

    @Override
    public Contact create(Contact contact) {
        logger.info("Creating contact into remote server.");
        return null;
    }

    @Override
    public Contact findById(Long id) {
        logger.info("Finding contact by id: " + id + " from remote server.");
        return null;
    }

    @Override
    public void delete(Long id) {
        logger.info("Deleting contact by id: " + id + " from remote server.");
    }

    @Override
    public Contact update(Contact contact) {
        logger.info("Updating contact by id: " + contact.getId() + " into remote server.");
        return null;
    }

    @Override
    public List<Contact> findAll() {
        logger.info("Finding all contacts from remote server.");
        return null;
    }
}
