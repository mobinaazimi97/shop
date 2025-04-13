package com.mftplus.shop.product;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GroupPropertyService {
    private final GroupPropertyRepository groupPropertyRepository;


    public GroupPropertyService(GroupPropertyRepository groupPropertyRepository) {
        this.groupPropertyRepository = groupPropertyRepository;
    }

    public GroupProperty save(GroupProperty groupProperty) {
        return groupPropertyRepository.save(groupProperty);
    }
    public GroupProperty update(GroupProperty groupProperty) {
        return groupPropertyRepository.save(groupProperty);
    }
    public List<GroupProperty> findAll() {
        return groupPropertyRepository.findAll();
    }
    public GroupProperty findById(Long id) {
        return groupPropertyRepository.findById(id).orElse(null);
    }
    public void deleteById(Long id) {
        groupPropertyRepository.deleteById(id);
    }
}
