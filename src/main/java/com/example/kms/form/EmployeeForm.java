package com.example.kms.form;

import com.example.kms.entity.EmployeeStatus;
import com.example.kms.entity.EmployeeType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmployeeForm {
    @NotBlank
    private String first_name;
    @NotBlank
    private String second_name;
    @NotBlank
    private String middle_name;
    @NotBlank
    private Integer image_id;
    @NotBlank
    private EmployeeType employee_type;
    @NotBlank
    private EmployeeStatus employee_status = EmployeeStatus.WORKS;

}
