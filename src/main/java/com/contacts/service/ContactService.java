package com.contacts.service;

import com.contacts.model.ContactDto;

import java.util.List;

public interface ContactService {

    ContactDto create(ContactDto contactDto);

    ContactDto findById(Long id);

    void delete(Long id);

    ContactDto update(ContactDto contactDto);

    List<ContactDto> findAll();

}
