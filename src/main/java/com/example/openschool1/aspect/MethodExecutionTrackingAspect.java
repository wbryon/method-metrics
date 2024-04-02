package com.example.openschool1.aspect;

import com.example.openschool1.service.ExecutionTimeService;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MethodExecutionTrackingAspect {
    private final ExecutionTimeService executionTimeService;

    public MethodExecutionTrackingAspect(ExecutionTimeService executionTimeService) {
        this.executionTimeService = executionTimeService;
    }
}
