package com.mftplus.shop.person.mapper;

import com.mftplus.shop.config.CentralMapperConfig;
import com.mftplus.shop.mapper.BaseMapper;
import com.mftplus.shop.mapper.DataMapper;
import com.mftplus.shop.person.entity.Person;
import com.mftplus.shop.person.dto.PersonDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CentralMapperConfig.class, uses = DataMapper.class)
public interface PersonMapper extends BaseMapper<Person, PersonDto> {
//    @Override
//    @Mapping(source = "person.gender", target = "gender", qualifiedByName = "genderToString")
//    PersonDto toDto(Person person);
//
//    @Override
//    @Mapping(source = "gender", target = "gender", qualifiedByName = "stringToGender")
//    Person toEntity(PersonDto personDto);

}
