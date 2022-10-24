package com.example.jvminternal.reflection1;

import com.example.jvminternal.annotation.MyAnnotation;

import java.util.Arrays;

public class Book {
    public static String A = "A";

    private String B = "B";

    public Book () {
    }

    public Book ( String b ) {
        B = b;
    }

    private void c() {
        System.out.println( "C" );
    }

    public int sum ( int left, int right ) {
        return left + right;
    }

    /**
     * three dots parameter
     * see [Arbitrary Number of Arguments] section
     * https://docs.oracle.com/javase/tutorial/java/javaOO/arguments.html#varargs
     * @param ints
     * @return
     */
    public int sum2 ( int ... ints ) {
        return Arrays.stream( ints ).sum();
    }
}
