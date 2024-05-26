package com.example.kms.service;

import com.example.kms.entity.Employee;
import com.example.kms.entity.EmployeeId;
import com.example.kms.form.EmployeeIdForm;
import com.example.kms.repository.EmployeeIdRepository;
import com.example.kms.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeIdService {
    private final EmployeeIdRepository employeeIdRepository;
    private final EmployeeRepository employeeRepository;

    public EmployeeId createEmployeeId(EmployeeIdForm form){
        Employee employee = employeeRepository.findById(form.getEmployee_id())
                .orElseThrow(() -> new RuntimeException("Not found employee with id = " + form.getEmployee_id()));
        return employeeIdRepository.save(new EmployeeId(form.getNumber(), form.getStart_date(), form.getEnd_date(), form.getNot_expired(), employee));
    }

    public List<EmployeeId> getAllEmployeeIds(){
        return employeeIdRepository.findAll();
    }

    public EmployeeId getEmployeeIdById(Integer id){
        return employeeIdRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found EmployeeId with id = " + id));
    }

    public EmployeeId getEmployeeIdByEmployeeId(Integer employeeId){
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Not found employee with id = " + employeeId));
        List<EmployeeId> employeeIds = employeeIdRepository.findAllByEmployee(employee);
        for (EmployeeId eId: employeeIds) {
            if (eId.isNot_expired()) {
                return eId;
            }
        }
        return null;
    }

    public EmployeeId updateEmployeeId(Integer id, EmployeeIdForm form){
        EmployeeId employeeId = employeeIdRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found EmployeeId with id = " + id));
        if (form.getEmployee_id() != null) {
            Employee employee = employeeRepository.findById(form.getEmployee_id())
                    .orElseThrow(() -> new RuntimeException("Not found employee with id = " + form.getEmployee_id()));
            employeeId.setEmployee(employee);
        }
        if (form.getNumber() != null)
            employeeId.setNumber(form.getNumber());
        if (form.getStart_date() != null)
            employeeId.setStart_date(form.getStart_date());
        if (form.getEnd_date() != null)
            employeeId.setEnd_date(form.getEnd_date());
        if (form.getNot_expired() != null)
            employeeId.setNot_expired(form.getNot_expired());
        return employeeIdRepository.save(employeeId);
    }
}
