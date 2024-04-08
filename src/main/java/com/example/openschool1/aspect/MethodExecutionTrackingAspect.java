package com.example.openschool1.aspect;

import com.example.openschool1.service.ExecutionTimeServiceImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Aspect
public class MethodExecutionTrackingAspect {
    private final ExecutionTimeServiceImpl executionTimeService;

    public MethodExecutionTrackingAspect(ExecutionTimeServiceImpl executionTimeService) {
        this.executionTimeService = executionTimeService;
    }

    @Around("@annotation(com.example.openschool1.aspect.TrackTime)")
    public Object trackTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long timeTaken = System.currentTimeMillis() - startTime;
        String methodName = ((MethodSignature) joinPoint.getSignature()).getMethod().getName();
        executionTimeService.saveExecutionTime(methodName, timeTaken, false);
        return result;
    }

    @Around("@annotation(com.example.openschool1.aspect.TrackAsyncTime)")
    public Object trackAsyncTime(ProceedingJoinPoint joinPoint) throws Throwable {
        CompletableFuture.runAsync(() -> {
            long startTime = System.currentTimeMillis();
            try {
                joinPoint.proceed();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
            long timeTaken = System.currentTimeMillis() - startTime;
            String methodName = ((MethodSignature) joinPoint.getSignature()).getMethod().getName();
            executionTimeService.saveExecutionTime(methodName, timeTaken, true);
        });
        return joinPoint.proceed();
    }
}
