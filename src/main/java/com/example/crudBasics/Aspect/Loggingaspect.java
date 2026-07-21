package com.example.crudBasics.Aspect;


import com.example.crudBasics.annotation.TrackExecTime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
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


    @Pointcut("within(com.example.crudBasics.service.StudentService)")
    public void fnPc(){}

    //instead of com.example.crudBasics.service.StudentService, we wrote reusable pointcuts

    @Before("fnPc()")
    public void beforeE(){
        System.out.println("Before  methods of Service Class . ClassLevel pointcut");
    }

    @Before("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void annBased(){
        System.out.println("Called for each post mapping method,annotation pointcut");
    }

    //We Can Pass the annotation as param and pass the param name to the pointcut ,it's knows as binding Form,var name and param name shoudl be same

    @Around("@annotation(trackExecTime)")
    public Object logTime(ProceedingJoinPoint joinPoint, TrackExecTime trackExecTime) throws Throwable {
        long beginTime = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        }finally {
            long endTime = System.currentTimeMillis();
            System.out.println(trackExecTime.getName() + " executed in " + (endTime - beginTime) + "ms");
        }
    }


}

