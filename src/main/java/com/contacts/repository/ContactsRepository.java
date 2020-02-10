package com.contacts.repository;

import com.contacts.domain.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactsRepository extends CrudRepository<Contact, Long> {

}
