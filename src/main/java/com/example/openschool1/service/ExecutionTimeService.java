package com.example.openschool1.service;

public interface ExecutionTimeService {

    void saveExecutionTime(String methodName, long executionTime, boolean isAsync);

    double getAverageExecutionTime(String methodName);

    long getTotalCalls(String methodName);
}
