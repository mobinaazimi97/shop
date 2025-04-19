package com.mftplus.shop.service;


import java.util.List;

public interface BaseService<D, ID> {
    D findById(ID id);
    List<D> findAll();
    D save(D d);
    D update(ID id,D d);
    void delete(ID id);
}
