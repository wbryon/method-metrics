package com.example.openschool1.repository;

import com.example.openschool1.model.ExecutionTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExecutionTimeRepository extends JpaRepository<ExecutionTime, Long> {
    List<ExecutionTime> findByMethodName(String methodName);
}