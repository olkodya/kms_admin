package com.example.kms.controller;

import com.example.kms.entity.Division;
import com.example.kms.form.DivisionForm;
import com.example.kms.service.DivisionService;
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
public class DivisionController {
    private final DivisionService service;
    @Operation(summary = "Get all divisions", description = "Returns divisions data")
    @GetMapping("/divisions")
    public ResponseEntity<List<Division>> getAllDivisions() {
        var divisions = service.getAllDivisions();
        if (divisions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(divisions, HttpStatus.OK);
    }
    @Operation(summary = "Get division by id", description = "Returns division data")
    @GetMapping("/divisions/{id}")
    public ResponseEntity<Division> getDivisionById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(service.getDivisionById(id), HttpStatus.OK);
    }

    @Operation(summary = "Update division by id (change name)", description = "Returns updated division data")
    @PutMapping("/divisions/{id}")
    public ResponseEntity<Division> updateDivision(@PathVariable("id") Integer id, @RequestBody DivisionForm form) {
        return new ResponseEntity<>(service.updateDivision(id, form), HttpStatus.OK);
    }

    @Operation(summary = "Division creation", description = "Returns division data after successful creation")
    @PostMapping("/divisions")
    public ResponseEntity<Division> createDivision(@RequestBody DivisionForm form) {
        return new ResponseEntity<>(service.createDivision(form), HttpStatus.CREATED);
    }

    @Operation(summary = "Add permission to division by id", description = "Returns updated employee data")
    @PutMapping("/permissions/{permissionId}/divisions/{divisionId}")
    public ResponseEntity<Division> addPermissionToDivision(@PathVariable(value = "permissionId") Integer permissionId, @PathVariable(value = "divisionId") Integer divisionId) {
        return new ResponseEntity<>(service.addPermissionToDivision(permissionId, divisionId), HttpStatus.OK);
    }

    @Operation(summary = "Delete permission from division by id")
    @DeleteMapping("/permissions/{permissionId}/divisions/{divisionId}")
    public ResponseEntity<HttpStatus> deletePermissionFromDivision(@PathVariable(value = "permissionId") Integer permissionId, @PathVariable(value = "divisionId") Integer divisionId) {
        service.deletePermissionFromDivision(permissionId, divisionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
