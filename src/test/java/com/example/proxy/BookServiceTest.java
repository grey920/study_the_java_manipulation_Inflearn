package com.example.proxy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static org.junit.Assert.*;

public class BookServiceTest {

    //    BookService bookService = new BookServiceProxy( new DefaultBookService() );

    /**
     * 실행되는 시점에 동적으로 프록시 만들기 ( 다이나믹 프록시 )
     * 인자
     * 1. 클래스로더 : 어떤 클래스로더를 써서 만들것인지 ( BookService.class를 읽어들인 로더)
     * 2. 인터페이스 목록(서브젝트) : 이 프록시가 어떤 인터페이스의 구현체인지
     * 3. InvocationHandler : 이 프록시의 어떤 메서드가 호출될 때, 그 메서드 호출을 어떻게 처리할 것인지에 대한 설명
     *      -> 리얼 서브젝트가 여기 안에 있어야 한다
     *
     * @return Object. 하지만 이 프록시가 어떤 인터페이스의 구현체인지 명시했기 때문에 casting이 가능하다
     */
    BookService bookService = ( BookService ) Proxy.newProxyInstance( BookService.class.getClassLoader(), new Class[]{ BookService.class }, new InvocationHandler() {
        BookService bookService = new DefaultBookService();
        @Override
        public Object invoke ( Object proxy, Method method, Object[] args ) throws Throwable {
            // 만약 메소드가 엄청 많고 그 중에 몇몇개만 적용하고 싶다면?? -> 여기에서 분기처리를 다 해주거나 InvocationHandler를 감싸는 프록시를 또 만들거나.. InvocationHandler가 유연하지 못하다
            // => 이러한 단점때문에 스프링에서 나온 기술이 AOP (프록시 기반의 AOP)

            /* rent()만 적용하고 나머지 메서드들은 그대로 반환한다 */
            if ( method.getName().equals( "rent" ) ) {
                System.out.println("aaaaaaaa");
                Object invoke = method.invoke( bookService, args );
                System.out.println("aaaaaaaa");
                return invoke;
            }

            return method.invoke( bookService, args );

        }
    } );

    @Test
    public void di(){
        Book book = new Book();
        book.setTitle( "spring" );
        bookService.rent( book );

        bookService.returnBook( book );
    }



}