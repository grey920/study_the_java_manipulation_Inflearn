package com.example.jvminternal.annotation;

import java.lang.annotation.*;

@Retention( RetentionPolicy.RUNTIME )
@Target( {ElementType.TYPE, ElementType.FIELD} )
@Inherited
public @interface MyBookAnnotation {

    String value();

    String name() default "grey";

    int number() default 100;
}
