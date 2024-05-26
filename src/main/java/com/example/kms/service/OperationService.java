package com.example.kms.service;

import com.example.kms.entity.*;
import com.example.kms.form.OperationForm;
import com.example.kms.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationService {

    private final OperationRepository operationRepository;
    private final ShiftRepository shiftRepository;
    private final KeyRepository keyRepository;
    private final EmployeeRepository employeeRepository;
    public Operation createOperation(OperationForm form) {
        Key key = keyRepository.findById(form.getKey_id())
                .orElseThrow(() -> new RuntimeException("Not found key with id = " + form.getKey_id()));
        Employee employee = employeeRepository.findById(form.getEmployee_id())
                .orElseThrow(() -> new RuntimeException("Not found employee with id = " + form.getEmployee_id()));
        Shift shift = shiftRepository.findById(form.getShift_id())
                .orElseThrow(() -> new RuntimeException("Not found shift with id = " + form.getShift_id()));
        if (shift.getEnd_date_time() != null || key.getKey_state() != KeyState.RETURNED) {
            return null;
        }
        return operationRepository.save(new Operation(key, employee, shift, new Timestamp(System.currentTimeMillis()), null));
    }

    public List<Operation> getAllOperations() {
        List<Operation> operations = operationRepository.findAll();
        return reverseList(operations);
    }

    public List<Operation> getAllOperationsByShiftId(Integer shiftId) {
        Shift shift = shiftRepository.findById(shiftId)
                .orElseThrow(() -> new RuntimeException("Not found shift with id = " + shiftId));
        List<Operation> operations = operationRepository.findAllByShift(shift);
        return reverseList(operations);
    }

    private List<Operation> reverseList(List<Operation> operations) {
        List<Operation> reverseOperations = operations.subList(0, operations.size());
        Collections.reverse(reverseOperations);
        return reverseOperations;
    }

    public Operation getOperationById(Integer id) {
        return operationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found operation with id = " + id));
    }

    public Operation getLastOperationByKeyId(Integer keyId) {
        Key key = keyRepository.findById(keyId)
                .orElseThrow(() -> new RuntimeException("Not found key with id = " + keyId));
        List<Operation> operations = operationRepository.findAllByKey(key);
        Operation operation = new Operation();
        Integer id = 0;
        for (Operation op: operations) {
            if (id < op.getOperation_id()) {
                id = op.getOperation_id();
                operation = op;
            }
        }
        return operation;
    }

    public Operation endOperation(Integer operationId) {
        Operation operation = operationRepository.findById(operationId)
                .orElseThrow(() -> new RuntimeException("Not found operation with id = " + operationId));
        if (operation.getReturn_date_time() != null)
            throw new RuntimeException("Operation has already ended");
        operation.setReturn_date_time(new Timestamp(System.currentTimeMillis()));
        return operationRepository.save(operation);
    }

}
