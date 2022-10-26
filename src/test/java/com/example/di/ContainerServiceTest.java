package com.example.di;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;

public class ContainerServiceTest {

    @Test
    public void getObject_Repository(){

        BookRepository bookRepository = ContainerService.getObject( BookRepository.class );
        assertNotNull( bookRepository );

    }


    /**
     * @Inject 를 했기 때문에 bookService도 null이 아니여야 하고, bookService가 가진 bookRepository도 null이 아니어야 함
     */
    @Test
    public void getObject_Service(){

        BookService bookService = ContainerService.getObject( BookService.class );
        assertNotNull( bookService );
        assertNotNull( bookService.bookRepository );

    }

}
