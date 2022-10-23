package com.example.jvminternal.reflection0;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith( SpringRunner.class )
@SpringBootTest
public class BookServiceTest {

    @Autowired
    BookService bookService;

    /**
     * bookService, bookService.bookRepository가 어떻게 null이 아닐 수 있을까..??!!!
     */
    @Test
    public void di(){
        Assert.assertNotNull( bookService );
        Assert.assertNotNull( bookService.bookRepository );
    }

}