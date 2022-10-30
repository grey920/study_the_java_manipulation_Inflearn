package com.example.cglib;


public class BookService {
    public void rent ( Book book ) {
        System.out.println( "book.getTitle() = " + book.getTitle() );
    }

    public void returnBook ( Book book ) {
        System.out.println( "return: " + book.getTitle() );
    }
}
