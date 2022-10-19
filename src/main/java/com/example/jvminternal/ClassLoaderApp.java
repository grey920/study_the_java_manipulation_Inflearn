package com.example.jvminternal;

import java.util.List;

public class ClassLoaderApp {

    /**
     * static 값 할당
     */
    static String myName;

    // static block
    static{
        myName = "grey";
    }

    private String foo = "bar";

    public static void main ( String[] args ) {
        System.out.println( ClassLoaderApp.class.getClassLoader() );
        System.out.println( List.class.getClassLoader() );
        System.out.println( myName );

        System.out.println( ClassLoaderApp.class.getSuperclass() ); // 부모 클래스

        Thread.currentThread();
    }
}
