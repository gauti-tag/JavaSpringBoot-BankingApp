package com.gauti.banking.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import com.gauti.banking.dto.AccountDto;
import com.gauti.banking.models.Account;
import com.gauti.banking.repositories.AccountRepository;
import com.gauti.banking.services.AccountService;
import com.gauti.banking.validators.ObjectsValidator;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;
    private final ObjectsValidator<AccountDto> validator; 

    @Override
    public Integer save(AccountDto dto) {

        // Bloc account update -> iban can not be changed 
        /*if(dto.getId() != null){
            throw new OperationNonPermittedException(
                "Account can not be updated",
                "save account",
                "Account",
                "update not permitted"
            );
        }*/

        validator.validate(dto);
        Account account = AccountDto.toEntity(dto);
        // Generate random IBAN when creating new account else do not update the iban
        if(dto.getId() == null){
            account.setIban(generateRandomIban());
        }
        return repository.save(account).getId();
        
    }

    @Override
    public List<AccountDto> findAll() {
       return repository.findAll()
            .stream()
            .map(AccountDto::fromEntity)
            .collect(Collectors.toList());
    }

    @Override
    public AccountDto findById(Integer id) {
        return repository.findById(id)
            .map(AccountDto::fromEntity)
            .orElseThrow(() -> new EntityNotFoundException("No Account was found with the provided ID :" + id));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private String generateRandomIban(){
        // genrerate an iban

        String iban = Iban.random(CountryCode.DE).toFormattedString();

        //check if the iban already exists

        boolean ibanExists = repository.findByIban(iban).isPresent();

        // if exists -> generate new random iban 
        if(ibanExists){
            generateRandomIban();
        }

        // if not exists -> return generated random iban

        return iban;
    }

}
