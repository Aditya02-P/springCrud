package com.example.crudBasics.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

//Custom Annotation
@Target(ElementType.METHOD) //<-- it is used to define the scope like where the ann, can be applied
public @interface TrackExecTime {

    String getName() default "";
}
