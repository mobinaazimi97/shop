package com.mftplus.shop.role;

import com.mftplus.shop.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class RoleService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public Role save(Role role) {
        return roleRepository.save(role);
    }
    @Transactional
    public Role update(Role role) {
        return roleRepository.save(role);
    }

    @Transactional
    public Role findById(Long roleId) {
        return roleRepository.findById(roleId).orElse(null);

    }

    @Transactional
    public List<Role> findAll() {
        logger.debug("Fetching all roles from database");
        List<Role> roles = roleRepository.findAll();
        logger.debug("Retrieved {} roles", roles.size());
        return roles;    }

    @Transactional
    public void delete(Long roleId) {
        roleRepository.deleteById(roleId);
    }

}
