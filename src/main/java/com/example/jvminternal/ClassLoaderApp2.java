package com.example.jvminternal;

public class ClassLoaderApp2{

    public static void main ( String[] args ) {
//        System.out.println( ClassLoaderApp.myName );

        ClassLoader classLoader = ClassLoaderApp2.class.getClassLoader();
        System.out.println( "classLoader = " + classLoader );   // AppClassLoader
        System.out.println( classLoader.getParent() );          // ExtClassLoader
        System.out.println( classLoader.getParent().getParent() );
    }

}
