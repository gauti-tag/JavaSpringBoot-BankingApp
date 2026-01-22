package com.gauti.banking.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gauti.banking.config.JwtUtils;
import com.gauti.banking.dto.AccountDto;
import com.gauti.banking.dto.AuthenticationRequest;
import com.gauti.banking.dto.AuthenticationResponse;
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
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authManager;

    @Override
    public Integer save(UserDto dto) {
        //throw new UnsupportedOperationException("Not supported yet.");
        validator.validate(dto);
        User user = UserDto.toEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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

    @Override
    public AuthenticationResponse register(UserDto userDto) {
        validator.validate(userDto);
        User user = UserDto.toEntity(userDto); // transform json to Spring object entity 
        String hashPassword = passwordEncoder.encode(user.getPassword()); // Encoder le password
        user.setPassword(hashPassword);

        var saveUser = repository.save(user);

        // Ajouter d'autre infos dans le token ( user infos )
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", saveUser.getId());
        claims.put("fullname", saveUser.getFirstname() + " " + saveUser.getLastname());
        String token = jwtUtils.generateToken(saveUser, claims);

        return AuthenticationResponse.builder() // Construire l'objet de reponse
            .token(token)
            .build(); 
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        // find the user and get the ID
        final User user = repository.findByEmail(request.getEmail()).get(); // Get Id of the object

        // Ajouter d'autre infos dans le token ( user infos )
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("fullname", user.getFirstname() + " " + user.getLastname());

        String token = jwtUtils.generateToken(user, claims); // Generate the token after finding the user
        return AuthenticationResponse.builder()
                .token(token)
                .build();
        
    }
}
