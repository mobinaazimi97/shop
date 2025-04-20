package com.mftplus.shop.person;

import com.mftplus.shop.mapper.BaseMapper;
import com.mftplus.shop.person.dto.PersonDto;
import com.mftplus.shop.service.BaseService;
import com.mftplus.shop.service.BaseServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService extends BaseServiceImpl<Person, PersonDto, Long> {
    public PersonService(JpaRepository<Person, Long> repository, BaseMapper<Person, PersonDto> mapper) {
        super(repository, mapper);
    }
}
