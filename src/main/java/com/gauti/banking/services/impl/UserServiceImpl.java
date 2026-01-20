package com.gauti.banking.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gauti.banking.dto.AccountDto;
import com.gauti.banking.dto.UserDto;
import com.gauti.banking.models.User;
import com.gauti.banking.repositories.UserRepository;
import com.gauti.banking.services.AccountService;
import com.gauti.banking.services.UserService;
import com.gauti.banking.validators.ObjectsValidator;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final AccountService accountService;
    private final ObjectsValidator<UserDto> validator; 

    @Override
    public Integer save(UserDto dto) {
        //throw new UnsupportedOperationException("Not supported yet.");
        validator.validate(dto);
        User user = UserDto.toEntity(dto);
        return repository.save(user).getId();
    }

    @Override
    public List<UserDto> findAll() {
        //throw new UnsupportedOperationException("Not supported yet.");
        return repository.findAll()
            .stream()
            .map(UserDto::fromEntity) // or .map(u -> UserDto.fromEntity(u))
            .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Integer id) {
        //throw new UnsupportedOperationException("Not supported yet.");
        return repository.findById(id)
            .map(UserDto::fromEntity)
            .orElseThrow(() -> new EntityNotFoundException("Non User was found with the provided ID :" + id ));
    }

    @Override
    public void delete(Integer id) {
        //throw new UnsupportedOperationException("Not supported yet.");
        // todo check before delete
        repository.deleteById(id);

    }

    @Override
    @Transactional
    public Integer validateAccount(Integer id) {
        User user = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("No User was found"));
    
        // create a bank account
        AccountDto account = AccountDto.builder()
            .user(UserDto.fromEntity(user))
            .build(); // use Account dto because of generated iban 
        accountService.save(account); // save account 
         
        user.setActive(true);
        repository.save(user); // save user
        return user.getId(); // return user Id
    }

    @Override
    public Integer invalidateAccount(Integer id) {
        User user = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("No User was found"));
        user.setActive(false);
        return repository.save(user).getId();
    }

}
