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

    @NotNull(message= "le nom ne doit pas être null")
    @NotEmpty(message= "le nom ne doit pas être vide")
    @NotBlank(message= "le nom ne doit pas être vide")
    private String firstname; 

    @NotNull(message= "le prenom ne doit pas être null")
    @NotEmpty(message= "le prenom ne doit pas être vide")
    @NotBlank(message= "le prenom ne doit pas être vide")
    private String lastname; 


    @NotNull(message= "l'email ne doit pas être null")
    @NotEmpty(message= "l'email ne doit pas être vide")
    @NotBlank(message= "l'email ne doit pas être vide")
    @Email(message = "l'email n'est pas conforme")
    private String email; 

    @NotNull(message= "le mot de passe ne doit pas être null")
    @NotEmpty(message= "le mot de passe ne doit pas être vide")
    @NotBlank(message= "le mot de passe ne doit pas être vide")
    @Size(min= 8, max=16, message = "le mot de passe doit être entre 8 et 16 caractères")
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
