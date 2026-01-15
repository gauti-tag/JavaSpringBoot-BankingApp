package com.gauti.banking.dto;

import com.gauti.banking.models.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserDto {

    private String firstname; 

    private String lastname; 

    private String email; 

    private String password; 

    public static UserDto fromEntity(User user){
        // check null 
        return UserDto.builder()
            .firstname(user.getFirstname())
            .lastname(user.getLastname())
            .email(user.getEmail())
            .password(user.getPassword())
            .build();
    }

    public static User toEntity(UserDto user){
        // check null 
        return User.builder()
            .firstname(user.getFirstname())
            .lastname(user.getLastname())
            .email(user.getEmail())
            .password(user.getPassword())
            .build();
    }
}
