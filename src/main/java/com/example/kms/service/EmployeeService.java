package com.example.kms.service;

import com.example.kms.entity.Division;
import com.example.kms.entity.Employee;
import com.example.kms.entity.Image;
import com.example.kms.entity.Permission;
import com.example.kms.form.EmployeeForm;
import com.example.kms.repository.DivisionRepository;
import com.example.kms.repository.EmployeeRepository;
import com.example.kms.repository.ImageRepository;
import com.example.kms.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DivisionRepository divisionRepository;
    private final PermissionRepository permissionRepository;
    private final ImageRepository imageRepository;
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeRepository.findAll());
    }

    public Employee createEmployee(EmployeeForm employee) {
        Image image = imageRepository.findById(employee.getImage_id())
                .orElseThrow(() -> new RuntimeException("Not found image with id = " + employee.getImage_id()));
        return employeeRepository.save(new Employee(employee.getFirst_name(), employee.getSecond_name(),
                employee.getMiddle_name(), image, employee.getEmployee_type(), employee.getEmployee_status()));
    }

    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found employee with id = " + id));
    }

    public Employee getEmployeeByQR(String QR) {
        return employeeRepository.findByQR(QR);
    }

    public Employee updateEmployee(Integer id, EmployeeForm employee) {
        Employee _employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found employee with id = " + id));
        if (employee.getFirst_name() != null)
            _employee.setFirstName(employee.getFirst_name());
        if (employee.getSecond_name() != null)
            _employee.setSecondName(employee.getSecond_name());
        if (employee.getMiddle_name() != null)
            _employee.setMiddleName(employee.getMiddle_name());
        if (employee.getImage_id() != null){
            Image image = imageRepository.findById(employee.getImage_id())
                    .orElseThrow(() -> new RuntimeException("Not found image with id = " + employee.getImage_id()));
            _employee.setImage(image);
        }
        if (employee.getEmployee_type() != null)
            _employee.setEmployeeType(employee.getEmployee_type());
        if (employee.getEmployee_status() != null)
            _employee.setEmployeeStatus(employee.getEmployee_status());
        return employeeRepository.save(_employee);
    }

    public Employee generateQRForEmployee(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found employee with id = " + id));
        String hash = "{employee_id = " + employee.getEmployeeId() + ", second_name = " + employee.getSecondName() + "}";
        employee.setQR(hash);
        return employeeRepository.save(employee);
    }

    public void deleteEmployeeFromDivision(Integer divisionId, Integer employeeId) {
        /*Division division = divisionRepository.findById(divisionId)
                .orElseThrow(() -> new RuntimeException("Not found division with id = " + divisionId));
        division.removeEmployee(employeeId);
        divisionRepository.save(division);*/
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Not found employee with id = " + employeeId));
        employee.removeDivision(divisionId);
        employeeRepository.save(employee);
    }

    public Employee addEmployeeToDivision(Integer divisionId, Integer employeeId) {
        /*return divisionRepository.findById(divisionId).map(division -> {
            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new RuntimeException("Not found employee with id = " + employeeId));
            division.addEmployee(employee);
            divisionRepository.save(division);
            return employee;
        }).orElseThrow(() -> new RuntimeException("Not found division with id = " + divisionId));*/
        return employeeRepository.findById(employeeId).map(employee -> {
            Division division = divisionRepository.findById(divisionId)
                    .orElseThrow(() -> new RuntimeException("Not found division with id = " + divisionId));
            employee.addDivision(division);
            employeeRepository.save(employee);
            return employee;
        }).orElseThrow(() -> new RuntimeException("Not found employee with id = " + employeeId));
    }

    public void deletePermissionFromEmployee(Integer permissionId, Integer employeeId) {
        /*Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new RuntimeException("Not found permission with id = " + permissionId));
        permission.removeEmployee(employeeId);
        permissionRepository.save(permission);*/
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Not found employee with id = " + employeeId));
        employee.removePermission(permissionId);
        employeeRepository.save(employee);
    }

    public Employee addPermissionToEmployee(Integer permissionId, Integer employeeId) {
        /*return permissionRepository.findById(permissionId).map(permission -> {
            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new RuntimeException("Not found employee with id = " + employeeId));
            permission.addEmployee(employee);
            permissionRepository.save(permission);
            return employee;
        }).orElseThrow(() -> new RuntimeException("Not found permission with id = " + permissionId));*/
        return employeeRepository.findById(employeeId).map(employee -> {
            Permission permission = permissionRepository.findById(permissionId)
                    .orElseThrow(() -> new RuntimeException("Not found permission with id = " + permissionId));
            employee.addPermission(permission);
            employeeRepository.save(employee);
            return employee;
        }).orElseThrow(() -> new RuntimeException("Not found employee with id = " + employeeId));
    }

}
