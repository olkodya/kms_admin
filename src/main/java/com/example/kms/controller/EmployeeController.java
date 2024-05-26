package com.example.kms.controller;

import com.example.kms.entity.Employee;
import com.example.kms.form.EmployeeForm;
import com.example.kms.service.EmployeeService;

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
public class EmployeeController {
    private final EmployeeService service;

    @Operation(summary = "Get all employees", description = "Returns employees data")
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        var employees = service.getAllEmployees();
        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @Operation(summary = "Get employee by id", description = "Returns employee data")
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(service.getEmployeeById(id), HttpStatus.OK);
    }

    @PutMapping("/employees/{id}")
    @Operation(summary = "Update employee data (and fire employee if needed)", description = "Returns updated employee data")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Integer id, @RequestBody EmployeeForm employee) {
        return new ResponseEntity<>(service.updateEmployee(id, employee), HttpStatus.OK);
    }

    @PutMapping("/employees/{employeeId}/QRs")
    @Operation(summary = "Generate QR for employee", description = "Returns updated employee data")
    public ResponseEntity<Employee> generateQRForEmployee(@PathVariable("employeeId") Integer employeeId) {
        return new ResponseEntity<>(service.generateQRForEmployee(employeeId), HttpStatus.OK);
    }

    @Operation(summary = "Get employee by QR", description = "Returns employee data")
    @GetMapping("/QRs/{QR}/employees")
    public ResponseEntity<Employee> getEmployeeByQR(@PathVariable("QR") String QR) {
        var employee = service.getEmployeeByQR(QR);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @Operation(summary = "Employee creation", description = "Returns employee data after successful creation")
    @PostMapping("/employees")
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeForm employee) {
        return new ResponseEntity<>(service.createEmployee(employee), HttpStatus.CREATED);
    }

    @PutMapping("/divisions/{divisionId}/employees/{employeeId}")
    @Operation(summary = "Add employee to division by id", description = "Returns updated employee data")
    public ResponseEntity<Employee> addEmployeeToDivision(@PathVariable(value = "divisionId") Integer divisionId, @PathVariable(value = "employeeId") Integer employeeId) {
        return new ResponseEntity<>(service.addEmployeeToDivision(divisionId, employeeId), HttpStatus.OK);
    }

    @DeleteMapping("/divisions/{divisionId}/employees/{employeeId}")
    @Operation(summary = "Delete employee from division by id")
    public ResponseEntity<HttpStatus> deleteEmployeeFromDivision(@PathVariable(value = "divisionId") Integer divisionId, @PathVariable(value = "employeeId") Integer employeeId) {
        service.deleteEmployeeFromDivision(divisionId, employeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/permissions/{permissionId}/employees/{employeeId}")
    @Operation(summary = "Add permission to employee by id", description = "Returns updated employee data")
    public ResponseEntity<Employee> addPermissionToEmployee(@PathVariable(value = "permissionId") Integer permissionId, @PathVariable(value = "employeeId") Integer employeeId) {
        return new ResponseEntity<>(service.addPermissionToEmployee(permissionId, employeeId), HttpStatus.OK);
    }

    @DeleteMapping("/permissions/{permissionId}/employees/{employeeId}")
    @Operation(summary = "Delete permission from employee by id")
    public ResponseEntity<HttpStatus> deletePermissionFromEmployee(@PathVariable(value = "permissionId") Integer permissionId, @PathVariable(value = "employeeId") Integer employeeId) {
        service.deletePermissionFromEmployee(permissionId, employeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
