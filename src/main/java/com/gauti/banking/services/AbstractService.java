package com.gauti.banking.services;

// Implémenter une interface générique qui reçois un type (objet) T

import java.util.List;

public interface AbstractService<T> {

    Integer save(T dto);

    List<T> findAll();

    T findById(Integer id);

    void delete(Integer id);
}
