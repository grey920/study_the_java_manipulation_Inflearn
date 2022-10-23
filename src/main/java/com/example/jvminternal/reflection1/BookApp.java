package com.example.jvminternal.reflection1;

import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * 패키지 내 다른 클래스들에 접근하려면 Class가 있어야 함
 *
 * [클래스 인스턴스에 접근하는 방법]
 * 1. 타입을 통해 .class로 Class<type>으로 가져오는 방법
 * 2. 인스턴스를 통해 인스턴스.getClass()로 가져오는 방법
 * 3. FQCN 문자열밖에 모르는 경우 -> Class.forName("FQCN");
 */
public class BookApp {

    public static void main ( String[] args ) throws ClassNotFoundException {

        // 1.
        Class<Book> bookClass = Book.class;// Book.class는 인스턴스는 클래스가 로딩만 해도 만들어진다.

        // 2.
        Book book = new Book();
        Class<? extends Book> aClass = book.getClass();

        // 3.
//        Class<?> aClass1 = Class.forName( "com.example.jvminternal.reflection1" );

        System.out.println("\n=========================== field 가져오기 ===================================");

        // public한 필드만 가져옴
//        Arrays.stream( bookClass.getFields() ).forEach( System.out::println );
        // 접근제어자 상관없이 모든 필드 가져옴
//        Arrays.stream( bookClass.getDeclaredFields() ).forEach( System.out::println );

        // 값 가져오기 ( 값을 가져오려면 인스턴스가 있어야 함)
        Arrays.stream( bookClass.getDeclaredFields() ).forEach( f -> {
            try {
//                Class com.example.jvminternal.reflection1.BookApp can not access a member of class com.example.jvminternal.reflection1.Book with modifiers "private static"
                f.setAccessible( true ); // 이 옵션을 켜야 모든 필드에 접근할 수 있다
                System.out.printf( "%s %s\n", f,  f.get( book ) );
            }
            catch ( IllegalAccessException e ) {
                throw new RuntimeException( e );
            }
        });
        System.out.println("\n=========================== field modifier ===================================");
        Arrays.stream( bookClass.getDeclaredFields() ).forEach( f -> {
            int modifiers = f.getModifiers();
            System.out.println( "f = " + f );
            System.out.println( "isPrivate = " + Modifier.isPrivate( modifiers ) );
            System.out.println( "isStatic = " + Modifier.isStatic( modifiers ) );
        } );

        System.out.println("\n=========================== method 가져오기 ===================================");
        Arrays.stream( bookClass.getMethods() ).forEach( System.out::println );
        System.out.println("\n=========================== method modifier ===================================");
        Arrays.stream( bookClass.getMethods() ).forEach( m -> {
            int modifiers = m.getModifiers();
            System.out.println( "m = " + m );
            System.out.println( "getParameterCount = " + m.getParameterCount() );
            System.out.println( "getReturnType = " + m.getReturnType() );
        } );

        System.out.println("\n=========================== 생성자 가져오기 ===================================");
        Arrays.stream( bookClass.getDeclaredConstructors() ).forEach( System.out::println );

        System.out.println("\n=========================== 상위 클래스 가져오기 ===================================");
        System.out.println( MyBook.class.getSuperclass() );

        System.out.println("\n=========================== 인터페이스 가져오기 ===================================");
        Arrays.stream( MyBook.class.getInterfaces() ).forEach( System.out::println );
    }
}
