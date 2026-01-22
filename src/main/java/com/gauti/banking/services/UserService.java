package com.gauti.banking.services;

import com.gauti.banking.dto.AuthenticationRequest;
import com.gauti.banking.dto.AuthenticationResponse;
import com.gauti.banking.dto.UserDto;

public interface UserService extends AbstractService<UserDto> {
    Integer validateAccount(Integer id);
    Integer invalidateAccount(Integer id);
    AuthenticationResponse register(UserDto user);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
