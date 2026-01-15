package com.gauti.banking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gauti.banking.models.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
