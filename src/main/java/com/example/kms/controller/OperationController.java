package com.example.kms.controller;

import com.example.kms.entity.Operation;
import com.example.kms.form.OperationForm;
import com.example.kms.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:8081", "https://kmsadmin-production.up.railway.app"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OperationController {
    private final OperationService service;

    @io.swagger.v3.oas.annotations.Operation(summary = "Get all operations", description = "Returns operations data")
    @GetMapping("/operations")
    public ResponseEntity<List<Operation>> getAllOperations(){
        var operations = service.getAllOperations();
        if (operations.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(operations, HttpStatus.OK);
    }

    @io.swagger.v3.oas.annotations.Operation(summary = "Operation creation", description = "Returns operation data after successful creation")
    @PostMapping("/operations")
    public ResponseEntity<Operation> createOperation(@RequestBody OperationForm form) {
        var operation = service.createOperation(form);
        if (operation == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(operation, HttpStatus.CREATED);
    }

    @io.swagger.v3.oas.annotations.Operation(summary = "Get all operations by shift id", description = "Returns operations data")
    @GetMapping("shifts/{shiftId}/operations")
    public ResponseEntity<List<Operation>> getAllOperationsByShiftId(@PathVariable("shiftId") Integer shiftId){
        var operations = service.getAllOperationsByShiftId(shiftId);
        if (operations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(operations, HttpStatus.OK);
    }

    @io.swagger.v3.oas.annotations.Operation(summary = "Get operation by id", description = "Returns operation data")
    @GetMapping("/operations/{id}")
    public ResponseEntity<Operation> getOperationById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(service.getOperationById(id), HttpStatus.OK);
    }

    @io.swagger.v3.oas.annotations.Operation(summary = "Get operation by key id", description = "Returns operation data")
    @GetMapping("/keys/{keyId}/operations")
    public ResponseEntity<Operation> getLastOperationByKeyId(@PathVariable("keyId") Integer keyId) {
        return new ResponseEntity<>(service.getLastOperationByKeyId(keyId), HttpStatus.OK);
    }

    @PutMapping("/operations/{id}")
    @io.swagger.v3.oas.annotations.Operation(summary = "Add operation return date and time (finish operation)", description = "Returns updated operation data")
    public ResponseEntity<Operation> endOperation(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(service.endOperation(id), HttpStatus.OK);
    }
}
