package com.example.crudBasics.Aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect

public class Loggingaspect {

    @Around("execution(* com.example.crudBasics.controller.StudentController.*(..) )")
    public Object studentContJP(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature =
                (MethodSignature) joinPoint.getSignature();

        System.out.println(signature.getMethod().getName());
        System.out.println(signature.getDeclaringTypeName());
        Object response = joinPoint.proceed();

        System.out.println("Method executed successfully");
        return response;

    }
}

