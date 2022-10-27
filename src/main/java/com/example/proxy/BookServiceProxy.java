package com.example.proxy;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Slf4j
public class BookServiceProxy implements BookService {

    /**
     * proxy가 받을 리얼 서브젝트
     */
    BookService bookService;

    // 생성자로 리얼 서브젝트를 받아온다
    public BookServiceProxy ( BookService bookService ) {
        this.bookService = bookService;
    }

    @Override
    public void rent ( Book book ) {
    // 여기서 부가기능을 처리한다
        long s = System.nanoTime();
        log.info( "시작시간 : {}", s );

        bookService.rent( book );

        long e = System.nanoTime();
        log.info( "종료시간 : {}", e );
        System.out.println( e - s / 1000000000 + "초 걸림");
    }

    @Override
    public void returnBook ( Book book ) {
        bookService.returnBook( book );
    }
}
