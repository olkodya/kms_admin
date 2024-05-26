package com.example.kms.repository;

import com.example.kms.entity.Key;
import com.example.kms.entity.Operation;
import com.example.kms.entity.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Integer> {
    List<Operation> findAllByShift(Shift shift);
    List<Operation> findAllByKey(Key key);
}
