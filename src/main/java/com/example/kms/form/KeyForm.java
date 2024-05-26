package com.example.kms.form;

import com.example.kms.entity.KeyState;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class KeyForm {
    @NotBlank
    private Integer audience_id;
    @NotBlank
    private KeyState key_state = KeyState.RETURNED;
    @NotBlank
    private Boolean main;
}
