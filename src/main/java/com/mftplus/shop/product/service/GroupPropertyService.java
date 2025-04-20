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
public class GroupPropertyService extends BaseServiceImpl<GroupProperty,GroupPropertyDto,Long> {
    private final GroupPropertyRepository groupPropertyRepository;
    private final GroupPropertyMapper groupPropertyMapper;

    public GroupPropertyService(GroupPropertyRepository groupPropertyRepository, GroupPropertyMapper groupPropertyMapper) {
        super(groupPropertyRepository,groupPropertyMapper);
        this.groupPropertyRepository = groupPropertyRepository;
        this.groupPropertyMapper = groupPropertyMapper;
    }


//    public GroupPropertyService(GroupPropertyRepository groupPropertyRepository, GroupPropertyMapper groupPropertyMapper) {
//        super(groupPropertyRepository,groupPropertyMapper);
//        this.groupPropertyRepository = groupPropertyRepository;
//        this.groupPropertyMapper = groupPropertyMapper;
//    }

//    public GroupPropertyService(JpaRepository<GroupProperty, Long> repository, BaseMapper<GroupProperty, GroupPropertyDto> mapper, GroupPropertyRepository groupPropertyRepository, GroupPropertyMapper groupPropertyMapper) {
//        super(repository, mapper);
//        this.groupPropertyRepository = groupPropertyRepository;
//        this.groupPropertyMapper = groupPropertyMapper;
//    }


//    public GroupPropertyService(GroupPropertyRepository groupPropertyRepository) {
//        this.groupPropertyRepository = groupPropertyRepository;
//    }
//    @Transactional
//    @CacheEvict(cacheNames = "group_property_tbl", allEntries = true)
//    public GroupProperty save(GroupProperty groupProperty) {
//        return groupPropertyRepository.save(groupProperty);
//    }
//    @Transactional
//    @CacheEvict(cacheNames = "group_property_tbl", allEntries = true)
//    public GroupProperty update(GroupProperty groupProperty) {
//        return groupPropertyRepository.save(groupProperty);
//    }
//    @Transactional
//    @CacheEvict(cacheNames = "group_property_tbl", allEntries = true)
//    public List<GroupProperty> findAll() {
//        return groupPropertyRepository.findAll();
//    }
//    @Transactional
//    @CacheEvict(cacheNames = "group_property_tbl", allEntries = true)
//    public GroupProperty findById(Long id) {
//        return groupPropertyRepository.findById(id).orElse(null);
//    }
//    public void deleteById(Long id) {
//        groupPropertyRepository.deleteById(id);
//    }
}
