package com.smarty.system.dto;

import lombok.Data;

@Data

public class AuthenticationRequest {
    private String email;
    private String password;
}
