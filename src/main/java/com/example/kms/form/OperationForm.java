package com.example.kms.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OperationForm {
    @NotBlank
    private Integer key_id;
    @NotBlank
    private Integer employee_id;
    @NotBlank
    private Integer shift_id;
}
