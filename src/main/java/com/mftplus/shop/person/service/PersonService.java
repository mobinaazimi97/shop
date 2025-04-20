package com.mftplus.shop.person.service;

import com.mftplus.shop.config.CacheEvictLevel;
import com.mftplus.shop.config.CacheableLevel;
import com.mftplus.shop.person.dto.PersonDto;
import com.mftplus.shop.person.entity.Person;
import com.mftplus.shop.service.BaseServiceImpl;
import com.mftplus.shop.person.mapper.PersonMapper;
import com.mftplus.shop.person.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
//@CacheableLevel(cacheName = "person_cache")
//@CacheEvictLevel(cacheNames = "person_cache")
public class PersonService extends BaseServiceImpl<Person, PersonDto, Long>{
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
        super(personRepository, personMapper);
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }
}

