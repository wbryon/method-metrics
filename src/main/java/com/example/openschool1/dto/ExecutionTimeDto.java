package com.example.openschool1.dto;

import lombok.Data;

@Data
public class ExecutionTimeDto {
    private Long id;
    private String methodName;
    private long executionTime;
    private boolean isAsync;
    private String formattedStartTime;
}
