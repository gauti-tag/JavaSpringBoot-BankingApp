package com.gauti.banking.dto;

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
}
