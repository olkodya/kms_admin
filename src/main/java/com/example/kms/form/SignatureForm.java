package com.example.kms.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignatureForm {
    @NotBlank
    private Integer image_id;
    @NotBlank
    private Integer operation_id;
    @NotBlank
    private boolean give;
}
