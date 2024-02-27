package com.example.legacylog;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class LogInterceptor {

    @Pointcut("execution(* com.example.legacylog..*(..))")
    public void pointcut() {}

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        log.info("Request: [{}]", getLog(joinPoint).toString());
    }

    @After("pointcut()")
    public void after(JoinPoint joinPoint) {
        log.info("Response: [{}]", getLog(joinPoint).toString());
    }

    private StringBuilder getLog(JoinPoint joinPoint) {
        StringBuilder sb = new StringBuilder(0);
        Arrays.stream(joinPoint.getArgs()).forEach(o -> {
            sb.append(o.toString());
        });
        return sb;
    }
    
}
