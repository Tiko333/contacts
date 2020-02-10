package com.contacts.controller;

import com.contacts.model.CountryDto;
import com.contacts.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @PostMapping
    public CountryDto create(@RequestBody CountryDto countryDto) {
        return countryService.create(countryDto);
    }

    @GetMapping("/{id}")
    public CountryDto findById(@PathVariable Long id) {
        return countryService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        countryService.delete(id);
    }

    @PutMapping("/{id}")
    public CountryDto update(@RequestBody CountryDto countryDto, @PathVariable Long id) {
        countryDto.setId(id);
        return countryService.update(countryDto);
    }

    @GetMapping
    public List<CountryDto> findAll() {
        return countryService.findAll();
    }

}
