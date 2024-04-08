package com.example.openschool1.service;

import com.example.openschool1.model.ExecutionTime;
import com.example.openschool1.repository.ExecutionTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.OptionalDouble;

@Service
@RequiredArgsConstructor
public class ExecutionTimeServiceImpl implements ExecutionTimeService {

    private final ExecutionTimeRepository executionTimeRepository;

    @Async
    public void saveExecutionTime(String methodName, long executionTime, boolean isAsync) {
        ExecutionTime executionTimeRecord = new ExecutionTime(methodName, executionTime, isAsync,
                ZonedDateTime.now());
        executionTimeRepository.save(executionTimeRecord);
    }

    public double getAverageExecutionTime(String methodName) {
        List<ExecutionTime> executionTimes = executionTimeRepository.findByMethodName(methodName);
        OptionalDouble average = executionTimes
                .stream()
                .mapToLong(ExecutionTime::getExecutionTime)
                .average();
        return average.isPresent() ? average.getAsDouble() : 0;
    }

    public long getTotalCalls(String methodName) {
        return executionTimeRepository.findByMethodName(methodName).size();
    }
}
