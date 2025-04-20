package com.mftplus.shop.product.service;

import com.mftplus.shop.config.CacheEvictLevel;
import com.mftplus.shop.config.CacheableLevel;
import com.mftplus.shop.product.dto.GroupPropertyDto;
import com.mftplus.shop.product.mapper.GroupPropertyMapper;
import com.mftplus.shop.product.repository.GroupPropertyRepository;
import com.mftplus.shop.product.entity.GroupProperty;
import com.mftplus.shop.service.BaseServiceImpl;
import org.springframework.stereotype.Service;


@Service
@CacheableLevel(cacheName = "groupProperty_cache")
@CacheEvictLevel(cacheNames = "groupProperty_cache")
public class GroupPropertyService extends BaseServiceImpl<GroupProperty, GroupPropertyDto, Long> {
    private final GroupPropertyRepository groupPropertyRepository;
    private final GroupPropertyMapper groupPropertyMapper;

    public GroupPropertyService(GroupPropertyRepository groupPropertyRepository, GroupPropertyMapper groupPropertyMapper) {
        super(groupPropertyRepository, groupPropertyMapper);
        this.groupPropertyRepository = groupPropertyRepository;
        this.groupPropertyMapper = groupPropertyMapper;
    }
}
