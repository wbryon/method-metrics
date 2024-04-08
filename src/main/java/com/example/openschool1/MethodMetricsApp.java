package com.example.openschool1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableAspectJAutoProxy
public class MethodMetricsApp {

	public static void main(String[] args) {
		SpringApplication.run(MethodMetricsApp.class, args);
	}
}
