package com.example.openschool1.controller;

import com.example.openschool1.service.ExecutionTimeServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/metrics")
public class ExecutionTimeController {

    private final ExecutionTimeServiceImpl executionTimeService;

    public ExecutionTimeController(ExecutionTimeServiceImpl executionTimeService) {
        this.executionTimeService = executionTimeService;
    }

    @GetMapping("/average")
    public Double getAverageExecutionTime(@RequestParam String methodName) {
        return executionTimeService.getAverageExecutionTime(methodName);
    }

    @GetMapping("/calls/{methodName}")
    public long getTotalCalls(@PathVariable String methodName) {
        return executionTimeService.getTotalCalls(methodName);
    }
}
