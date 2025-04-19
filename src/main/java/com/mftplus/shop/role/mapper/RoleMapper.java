package com.mftplus.shop.role.mapper;


import com.mftplus.shop.config.CentralMapperConfig;
import com.mftplus.shop.mapper.BaseMapper;
import com.mftplus.shop.role.Role;
import com.mftplus.shop.role.dto.RoleDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.io.ObjectInputFilter;

@Mapper(config = CentralMapperConfig.class)
public interface RoleMapper extends BaseMapper<Role, RoleDto> {
}
