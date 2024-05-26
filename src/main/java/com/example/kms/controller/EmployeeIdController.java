package com.example.kms.controller;

import com.example.kms.entity.EmployeeId;
import com.example.kms.form.EmployeeIdForm;
import com.example.kms.service.EmployeeIdService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:8081", "https://kmsadmin-production.up.railway.app"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EmployeeIdController {
    private final EmployeeIdService service;

    @Operation(summary = "Get all employee ids", description = "Returns employee ids data")
    @GetMapping("/employeeIds")
    public ResponseEntity<List<EmployeeId>> getAllEmployeeIds() {
        var employeeIds = service.getAllEmployeeIds();
        if (employeeIds.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employeeIds, HttpStatus.OK);
    }
    @Operation(summary = "Employee id creation", description = "Returns employee id data after successful creation")
    @PostMapping("/employeeIds")
    public ResponseEntity<EmployeeId> createEmployeeId(@RequestBody EmployeeIdForm form) {
        return new ResponseEntity<>(service.createEmployeeId(form), HttpStatus.CREATED);
    }
    @Operation(summary = "Update employee id", description = "Returns updated employee id data")
    @PutMapping("/employeeIds/{id}")
    public ResponseEntity<EmployeeId> updateEmployeeId(@PathVariable("id") Integer id, @RequestBody EmployeeIdForm form) {
        return new ResponseEntity<>(service.updateEmployeeId(id, form), HttpStatus.OK);
    }

    @Operation(summary = "Get employee id by id", description = "Returns employee id data")
    @GetMapping("/employeeIds/{id}")
    public ResponseEntity<EmployeeId> getEmployeeIdById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(service.getEmployeeIdById(id), HttpStatus.OK);
    }

    @Operation(summary = "Get employee id by employee id", description = "Returns employee data")
    @GetMapping("/employees/{employeeId}/employeeIds")
    public ResponseEntity<EmployeeId> getEmployeeIdByEmployeeId(@PathVariable("employeeId") Integer employeeId) {
        return new ResponseEntity<>(service.getEmployeeIdByEmployeeId(employeeId), HttpStatus.OK);
    }
}
