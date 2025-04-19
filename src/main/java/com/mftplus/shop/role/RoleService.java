package com.mftplus.shop.role;

import com.mftplus.shop.mapper.BaseMapper;
import com.mftplus.shop.role.dto.RoleDto;
import com.mftplus.shop.service.BaseServiceImpl;
import com.mftplus.shop.user.service.UserService_old;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class RoleService extends BaseServiceImpl<Role, RoleDto, Long> {

    public RoleService(JpaRepository<Role, Long> repository, BaseMapper<Role, RoleDto> mapper) {
        super(repository, mapper);
    }
}
