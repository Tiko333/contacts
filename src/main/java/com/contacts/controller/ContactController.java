package com.contacts.controller;

import com.contacts.model.ContactDto;
import com.contacts.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    public ContactDto create(@RequestBody ContactDto contactDto) {
        return contactService.create(contactDto);
    }

    @GetMapping("/{id}")
    public ContactDto findById(@PathVariable Long id) {
        return contactService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        contactService.delete(id);
    }

    @PutMapping("/{id}")
    public ContactDto update(@RequestBody ContactDto contactDto, @PathVariable Long id) {
        contactDto.setId(id);
        return contactService.update(contactDto);
    }

    @GetMapping
    public List<ContactDto> findAll(){
        return contactService.findAll();
    }
}
