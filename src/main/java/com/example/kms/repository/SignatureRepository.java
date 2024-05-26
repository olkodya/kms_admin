package com.example.kms.repository;

import com.example.kms.entity.Operation;
import com.example.kms.entity.Signature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SignatureRepository extends JpaRepository<Signature, Integer> {
    List<Signature> findAllByOperation(Optional<Operation> operation);
}
