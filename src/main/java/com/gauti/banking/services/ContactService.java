package com.gauti.banking.services;

import java.util.List;

import com.gauti.banking.dto.ContactDto;

public interface ContactService extends AbstractService<ContactDto> {
    List<ContactDto> findAllByUserId(Integer userId);
}
