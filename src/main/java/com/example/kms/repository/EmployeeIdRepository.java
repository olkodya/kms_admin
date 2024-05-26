package com.example.kms.repository;

import com.example.kms.entity.Employee;
import com.example.kms.entity.EmployeeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeIdRepository extends JpaRepository<EmployeeId, Integer> {
    List<EmployeeId> findAllByEmployee(Employee employee);
}
