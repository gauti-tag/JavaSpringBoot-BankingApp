package com.gauti.banking.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gauti.banking.dto.AddressDto;
import com.gauti.banking.models.Address;
import com.gauti.banking.repositories.AddressRepository;
import com.gauti.banking.services.AddressService;
import com.gauti.banking.validators.ObjectsValidator;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository; // Deal with DB
    private final ObjectsValidator<AddressDto> validator;

    @Override
    public Integer save(AddressDto dto) {
        validator.validate(dto);
        Address address = AddressDto.toEntity(dto);
        return repository.save(address).getId();
    }

    @Override
    public List<AddressDto> findAll() {
       return repository.findAll()
            .stream()
            .map(AddressDto::fromEntity)
            .collect(Collectors.toList());
    }

    @Override
    public AddressDto findById(Integer id) {
        return repository.findById(id)
            .map(AddressDto::fromEntity)
            .orElseThrow(() -> new EntityNotFoundException("No Address was found with ID" + id ));
    }

    @Override
    public void delete(Integer id) {
        // todo check and delete
        repository.deleteById(id);
    }

}
