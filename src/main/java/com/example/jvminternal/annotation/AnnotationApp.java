package com.example.jvminternal.annotation;

import com.example.jvminternal.reflection1.Book;
import com.example.jvminternal.reflection1.MyBook;

import java.util.Arrays;

public class AnnotationApp {
    public static void main ( String[] args ) {
//        Arrays.stream( Book.class.getAnnotations() ).forEach( System.out::println );

        // @MyAnnotation에서 @Inherited를 분여주었기 때문에 조회가 된다.
//        Arrays.stream( MyBook.class.getAnnotations() ).forEach( System.out::println );

        // MyBook에 직접 붙은 애노테이션만 조회하고 싶다면 getDeclaredAnnotations()
//        Arrays.stream( MyBook.class.getDeclaredAnnotations() ).forEach( System.out::println );

        // 필드에 붙은 애노테이션 찾기 & 값 조회
        Arrays.stream( Book.class.getDeclaredFields() ).forEach( f -> {
            Arrays.stream( f.getAnnotations() ).forEach( a -> {
                if ( a instanceof MyAnnotation ) {
                    MyAnnotation myAnnotation = ( MyAnnotation ) a;
                    System.out.println( myAnnotation.value() );
                    System.out.println( myAnnotation.name() );
                    System.out.println( myAnnotation.number() );
                }
            } );
        } );
    }
}
