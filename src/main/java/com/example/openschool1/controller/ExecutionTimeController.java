package com.example.openschool1.controller;

import com.example.openschool1.service.ExecutionTimeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/metrics")
public class ExecutionTimeController {

    private final ExecutionTimeService executionTimeService;

    public ExecutionTimeController(ExecutionTimeService executionTimeService) {
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
