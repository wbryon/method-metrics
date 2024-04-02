package com.example.openschool1.service;

import com.example.openschool1.repository.ExecutionTimeRepository;
import org.springframework.stereotype.Service;

@Service
public class ExecutionTimeService {

    private final ExecutionTimeRepository executionTimeRepository;

    public ExecutionTimeService(ExecutionTimeRepository executionTimeRepository) {
        this.executionTimeRepository = executionTimeRepository;
    }
}
