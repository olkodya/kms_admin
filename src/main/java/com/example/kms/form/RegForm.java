package com.example.kms.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegForm {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private Integer employee_id;
}

