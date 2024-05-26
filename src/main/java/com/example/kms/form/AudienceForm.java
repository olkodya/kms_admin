package com.example.kms.form;

import com.example.kms.entity.AudienceType;
import com.example.kms.entity.Signalisation;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AudienceForm {
    @NotBlank
    private Integer number;
    @NotBlank
    private Integer capacity;
    @NotBlank
    private Boolean exist = true;
    @NotBlank
    private Signalisation signalisation = Signalisation.ON;
    @NotBlank
    private AudienceType audience_type;
    @NotBlank
    private Integer image_id;
}
