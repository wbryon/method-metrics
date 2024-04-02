package com.example.openschool1.service;

import com.example.openschool1.repository.ExecutionTimeRepository;
import org.springframework.stereotype.Service;

@Service
public class ExecutionTimeServiceImpl implements ExecutionTimeService {

    private final ExecutionTimeRepository executionTimeRepository;

    public ExecutionTimeServiceImpl(ExecutionTimeRepository executionTimeRepository) {
        this.executionTimeRepository = executionTimeRepository;
    }

    @Override
    public void saveExecutionTime(String methodName, long executionTime, boolean isAsync) {

    }

    @Override
    public double getAverageExecutionTime(String methodName) {
        return 0;
    }

    @Override
    public long getTotalCalls(String methodName) {
        return 0;
    }
}
