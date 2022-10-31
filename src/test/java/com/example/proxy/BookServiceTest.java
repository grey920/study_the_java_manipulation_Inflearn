package com.example.proxy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * 클래스에 프록시를 쓸 수 있는 CGlib, ByteBuddy를 쓸 때 주의할 점!
 * -> 상속을 사용하기 방법이기 떄문에 상속을 쓸 수 없는 클래스(final클래스이거나 private default 생성자만 있는 경우)에는 사용할 수 없다.
 */
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

    // ============================ Class에도 적용할 수 있는 CGlib ==========>

    /**
     * handler : jdk 동적 프록시에서 InvocationHandler를 사용한 것처럼 cglib에서도 handler를 사용해야 한다.
     *  MethodInterceptor()로 구현한다
     */
    @Test
    public void di_cglib(){

        MethodInterceptor handler = new MethodInterceptor() {
            com.example.cglib.BookService bookservice = new com.example.cglib.BookService();
            @Override
            public Object intercept ( Object o, Method method, Object[] args, MethodProxy methodProxy ) throws Throwable {

                if ( method.getName().equals( "rent" ) ) {
                    System.out.println("aaaaaa");
                    Object invoke = method.invoke( bookservice, args );
                    System.out.println("bbbbbbb");
                    return invoke;
                }

                return method.invoke( bookservice, args );
            }
        };



        com.example.cglib.BookService bookservice = ( com.example.cglib.BookService ) Enhancer.create( com.example.cglib.BookService.class, handler );

        com.example.cglib.Book book = new com.example.cglib.Book();

        book.setTitle( "spring" );
        bookservice.rent( book );

        bookservice.returnBook( book );
    }

    // ============================ Class에도 적용할 수 있는 ByteBuddy ==========>
    @Test
    public void di_bytebuddy() throws Exception {

        // ByteBuddy로 BookService 클래스를 상속받는 프록시 클래스를 생성한다.
        Class<? extends com.example.bytebuddy.BookService> proxyClass = new ByteBuddy().subclass( com.example.bytebuddy.BookService.class )
                // 뭔가 작업이 필요한 경우 ( 예. rent()라는 이름의 메소드만 가져온다 )
                .method( named( "rent" ) ).intercept( InvocationHandlerAdapter.of( new InvocationHandler() {
                    com.example.bytebuddy.BookService bookService = new com.example.bytebuddy.BookService();
                    @Override
                    public Object invoke ( Object proxy, Method method, Object[] args ) throws Throwable {
                        System.out.println("cccccccc");
                        Object invoke = method.invoke( bookService, args );
                        System.out.println("dddddddd");
                        return invoke;
                    }
                } ) )
                .make().load( com.example.bytebuddy.BookService.class.getClassLoader() ).getLoaded();

        // 프록시 클래스타입의 인스턴스를 만든다.
        com.example.bytebuddy.BookService bookService = proxyClass.getConstructor( null ).newInstance();


        com.example.bytebuddy.Book book = new com.example.bytebuddy.Book();
        book.setTitle( "spring" );
        bookService.rent( book );
        bookService.returnBook( book );
    }



}