package com.gauti.banking.services;

import com.gauti.banking.dto.UserDto;

public interface UserService extends AbstractService<UserDto> {
    Integer validateAccount(Integer id);
    Integer invalidateAccount(Integer id);
}
