package com.gauti.banking.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gauti.banking.models.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

    List<Contact> findAllByUserId(Integer userId);

}
