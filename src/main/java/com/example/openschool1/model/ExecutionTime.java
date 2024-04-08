package com.example.openschool1.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "metrics")
public class ExecutionTime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String methodName;

    @Column
    private long executionTime;

    @Column
    private boolean isAsync;

    @Column
    private ZonedDateTime startTime;

    public ExecutionTime(String methodName, long executionTime, boolean isAsync, ZonedDateTime startTime) {
        this.methodName = methodName;
        this.executionTime = executionTime;
        this.isAsync = isAsync;
        this.startTime = startTime;

    }
}
