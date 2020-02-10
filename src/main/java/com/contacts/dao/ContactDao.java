package com.contacts.dao;

import com.contacts.domain.Contact;

import java.util.List;

public interface ContactDao {

    Contact create(Contact contact);

    Contact findById(Long id);

    void delete(Long id);

    Contact update(Contact contact);

    List<Contact> findAll();

}
