package com.smarty.system.dto;

import com.smarty.system.models.User;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder

public class UserDto {

    private Integer id;

    @NotNull(message = "le nom ne doit pas etre vide")
    @NotEmpty(message = "le nom ne doit pas etre vide")
    @NotBlank(message = "le nom ne doit pas etre vide")
    private String firstname;

    @NotNull(message = "le prenom ne doit pas etre vide")
    @NotEmpty(message = "le prenom ne doit pas etre vide")
    @NotBlank(message = "le prenom ne doit pas etre vide")
    private String lastname;

    @NotNull
    @NotEmpty
    @NotBlank
    @Email(message="email ")
    private String email;

    @NotNull(message = "le mot de passe ne doit pas etre vide")
    @NotEmpty(message = "le mot de passe ne doit pas etre vide")
    @NotBlank(message = "le mot de passe ne doit pas etre vide")
    @Size(min=8,max=16, message="le mot de passe doit etre entre 8 et 16 caracteres ")
    private String password;

    private String iban;
    private boolean active;

    public static UserDto fromEntity(User user){
        return UserDto.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .iban(user.getAccount() == null ? "" : user.getAccount().getIban())
                .active(user.isActive())
                .build();

    }

    public static User toEntity(UserDto user){
        return User.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();

    }

}
