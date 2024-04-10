package com.example.openschool1.aspect;

import com.example.openschool1.service.ExecutionTimeService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Aspect
@Slf4j
public class MethodExecutionTrackingAspect {
    private final ExecutionTimeService executionTimeService;

    public MethodExecutionTrackingAspect(ExecutionTimeService executionTimeService) {
        this.executionTimeService = executionTimeService;
    }

    @Pointcut("@annotation(com.example.openschool1.annotation.TrackAsyncTime)")
    public void asyncRunningPointcut() {}

    @Pointcut("@annotation(com.example.openschool1.annotation.TrackTime)")
    public void syncRunningPointcut() {}

    @Around("syncRunningPointcut()")
    public Object trackTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        log.info("Синхронный запуск в MethodExecutionTrackingAspect");
        Object result = joinPoint.proceed();
        long timeTaken = System.currentTimeMillis() - startTime;
        String methodName = ((MethodSignature) joinPoint.getSignature()).getMethod().getName();
        executionTimeService.saveExecutionTime(methodName, timeTaken, false);
        return result;
    }

@Around("asyncRunningPointcut()")
    public Object trackAsyncTime(ProceedingJoinPoint joinPoint) throws Throwable {
    long startTime = System.currentTimeMillis();
    log.info("Асинхронный запуск в MethodExecutionTrackingAspect");
    Object result = joinPoint.proceed();
    long timeTaken = System.currentTimeMillis() - startTime;
    CompletableFuture.runAsync(() -> {
        String methodName = ((MethodSignature) joinPoint.getSignature()).getMethod().getName();
        executionTimeService.saveExecutionTime(methodName, timeTaken, true);
    });
    return result;
    }
}
