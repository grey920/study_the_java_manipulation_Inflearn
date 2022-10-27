package com.example.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public class DefaultBookService implements BookService {


    @Override
    public void rent ( Book book ) {
        System.out.println( "book.getTitle() = " + book.getTitle() );
    }

    @Override
    public void returnBook ( Book book ) {
        System.out.println( "return: " + book.getTitle() );
    }
}
