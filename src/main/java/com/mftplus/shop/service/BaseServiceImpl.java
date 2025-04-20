package com.mftplus.shop.service;

import com.mftplus.shop.mapper.BaseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class BaseServiceImpl<E, D, ID> implements BaseService<D, ID> {

    private final JpaRepository<E, ID> repository;
    private final BaseMapper<E, D> mapper;


    public BaseServiceImpl(JpaRepository<E, ID> repository, BaseMapper<E, D> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public D findById(ID id) {
        E entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found!!!"));
        log.info(entity.toString());
        return mapper.toDto(entity);
    }

    @Override
    public List<D> findAll() {
        log.info(repository.findAll().toString());
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public D save(D d) {
        E entity = mapper.toEntity(d);
        log.info(entity.toString());
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public List<D> saveAll(List<D> list) {
        List<E> entites = list
                .stream()
                .map(mapper::toEntity)
                .collect(Collectors.toList());
        List<E> saved = repository.saveAll(entites);
        log.info(saved.toString());
        return saved.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public D update(ID id, D d) {
        E entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found!!!"));
        mapper.updateFromDto(d, entity);
        log.info(entity.toString());
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public void delete(ID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("not found!!!");
        }
        log.info(repository.findById(id).toString());
        repository.deleteById(id);
    }
}
