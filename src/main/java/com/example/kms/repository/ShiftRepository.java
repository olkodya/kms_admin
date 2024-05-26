package com.example.kms.repository;

import com.example.kms.entity.Shift;
import com.example.kms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Integer> {
    List<Shift> findByWatchman(User watchman);
}
