package com.example.kms.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class EmployeeIdForm {
    @NotBlank
    private String number;
    @NotBlank
    private Date start_date;
    @NotBlank
    private Date end_date;
    @NotBlank
    private Boolean not_expired;
    @NotBlank
    private Integer employee_id;
}
