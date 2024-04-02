package com.example.openschool1.repository;

import com.example.openschool1.model.ExecutionTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExecutionTimeRepository extends JpaRepository<ExecutionTime, Long> {
}