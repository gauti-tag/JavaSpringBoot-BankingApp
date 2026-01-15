package com.gauti.banking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gauti.banking.models.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
