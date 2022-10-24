package com.example.jvminternal.reflection1;

import com.example.jvminternal.annotation.MyAnnotation;

@MyAnnotation("grey")
public class Book {

    private static String B = "BOOK";

    private static final String C = "BOOK";

    @MyAnnotation
    private String a = "a";

    public String d = "d";

    protected String e = "e";

    @MyAnnotation
    public Book () {
    }

    public Book ( String a, String d, String e ) {
        this.a = a;
        this.d = d;
        this.e = e;
    }

    private void f(){
        System.out.println( "F");
    }

    public void g(){
        System.out.println( "g" );
    }

    public int h(){
        return 100;
    }
}
