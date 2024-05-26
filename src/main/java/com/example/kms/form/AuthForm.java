package com.example.kms.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthForm {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}

