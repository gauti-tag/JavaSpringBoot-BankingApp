package com.gauti.banking.dto;

import com.gauti.banking.models.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserDto {

   
    private Integer id; 

    @NotNull
    @NotEmpty
    @NotBlank
    private String firstname; 

    @NotNull(message= "le prenom ne doit pas être null")
    @NotEmpty(message= "le prenom ne doit pas être vide")
    @NotBlank(message= "le prenom ne doit pas être vide")
    private String lastname; 


    @NotNull
    @NotEmpty
    @NotBlank
    @Email
    private String email; 

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min= 8, max=16)
    private String password; 

    public static UserDto fromEntity(User user){
        // check null 
        return UserDto.builder()
            .id(user.getId())
            .firstname(user.getFirstname())
            .lastname(user.getLastname())
            .email(user.getEmail())
            .password(user.getPassword())
            .build();
    }

    public static User toEntity(UserDto user){
        // check null 
        return User.builder()
            .id(user.getId())
            .firstname(user.getFirstname())
            .lastname(user.getLastname())
            .email(user.getEmail())
            .password(user.getPassword())
            .build();
    }
}
