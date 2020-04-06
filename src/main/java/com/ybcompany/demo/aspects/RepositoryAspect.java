package com.ybcompany.demo.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class RepositoryAspect {


    /**
     * Aspect for logging execution(* PACKAGE.*.*(..))
     *
     * @param joinPoint
     */
    @Before(value = "execution(* com.ybcompany.demo.repositories.*.*(..))")
    public void before(JoinPoint joinPoint) {
        log.info("Request to data base " + joinPoint);
    }

    /**
     * Aspect for logging exceptions in execution(* PACKAGE.*.*(..))
     *
     * @param joinPoint
     * @param error
     */
    @AfterThrowing(pointcut = "execution(* com.ybcompany.demo.repositories.*.*(..))", throwing = "error")
    public void afterThrowing(JoinPoint joinPoint, Throwable error) {
        log.info(joinPoint + " " + error);
    }

    /**
     * Aspect for logging methods using com.ybcompany.demo.interfaces.LogExecutionTime
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     * @see com.ybcompany.demo.interfaces.LogExecutionTime
     */
    @Around("@annotation(com.ybcompany.demo.interfaces.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        // Proceed method
        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        log.info(joinPoint.getSignature() + " executed in " + executionTime + "ms");

        return proceed;
    }

}
