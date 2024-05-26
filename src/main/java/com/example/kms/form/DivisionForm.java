package com.example.kms.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DivisionForm {
    @NotBlank
    private String name;
}
