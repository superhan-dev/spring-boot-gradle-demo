package com.example.springbootgradledemo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TimeTraceAop {

    /**
     *  aop 동작방식
     *  spring은 proxy방식의 aop를 사용하여,
     *  메소드가 호출되기 전 하나의 proxy가 정해진 일을 수행한 후
     *  실제 메소드를 실행하는 방식을 사용하고있다.
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.example.springbootgradledemo..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();

        log.info("START: " + joinPoint.toString());

        try {
            return joinPoint.proceed();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            log.info("END: " + joinPoint.toString() + " "+timeMs + "ms");
        }
    }
}
