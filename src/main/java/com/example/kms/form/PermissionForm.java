package com.example.kms.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PermissionForm {
    @NotBlank
    private String name;
}
