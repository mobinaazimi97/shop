package com.mftplus.shop.person.controller;

import com.mftplus.shop.person.dto.PersonDto;
import com.mftplus.shop.person.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @GetMapping
    public List<PersonDto> getPersons() {
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public PersonDto getPerson(@PathVariable long id) {
        return personService.findById(id);
    }

    @PostMapping
    public PersonDto createPerson(@RequestBody @Valid PersonDto personDto) {
        return personService.save(personDto);
    }

    @PutMapping("/{id}")
    public PersonDto updatePerson(@PathVariable long id, @RequestBody PersonDto personDto) {
        return personService.update(id, personDto);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable long id) {
        personService.delete(id);
    }

}
