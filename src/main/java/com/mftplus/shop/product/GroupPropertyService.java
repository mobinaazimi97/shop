package com.mftplus.shop.product;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class GroupPropertyService {
    private final GroupPropertyRepository groupPropertyRepository;


    public GroupPropertyService(GroupPropertyRepository groupPropertyRepository) {
        this.groupPropertyRepository = groupPropertyRepository;
    }
    @Transactional
    @CacheEvict(cacheNames = "group_property_tbl", allEntries = true)
    public GroupProperty save(GroupProperty groupProperty) {
        return groupPropertyRepository.save(groupProperty);
    }
    @Transactional
    @CacheEvict(cacheNames = "group_property_tbl", allEntries = true)
    public GroupProperty update(GroupProperty groupProperty) {
        return groupPropertyRepository.save(groupProperty);
    }
    @Transactional
    @CacheEvict(cacheNames = "group_property_tbl", allEntries = true)
    public List<GroupProperty> findAll() {
        return groupPropertyRepository.findAll();
    }
    @Transactional
    @CacheEvict(cacheNames = "group_property_tbl", allEntries = true)
    public GroupProperty findById(Long id) {
        return groupPropertyRepository.findById(id).orElse(null);
    }
    public void deleteById(Long id) {
        groupPropertyRepository.deleteById(id);
    }
}
