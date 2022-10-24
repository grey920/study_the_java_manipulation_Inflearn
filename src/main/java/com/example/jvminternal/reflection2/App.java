package com.example.jvminternal.reflection2;

import com.example.jvminternal.reflection1.Book;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class App {

    public static void main ( String[] args ) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class<?> bookClass = Class.forName( "com.example.jvminternal.reflection1.Book" );

        // 기본 생성자
        Constructor<?> constructor = bookClass.getConstructor( null );
        Book book = ( Book ) constructor.newInstance();
        System.out.println( "book = " + book );

        // 파라미터를 가진 생성자 조회
        Constructor<?> constructorWithParams = bookClass.getConstructor( String.class );
        Book book2 = ( Book ) constructorWithParams.newInstance( "grey" ); // 파라미터에 맞는 값을 넘겨준다
        System.out.println( "book2 = " + book2 );

        // 필드 조회
        // a.get(인스턴스)에서 조회를 원하는 특정한 인스턴스를 넘겨야 하나
        // A는 static 필드이기 때문에 인스턴스 상관없이 모두 같은 값을 공유하므로 null을 넘긴다
        Field a = Book.class.getDeclaredField( "A" );
        System.out.println( "a = " + a.get( null ) );

        // 필드 값 셋팅
        a.set( null, "AAAAAA" );
        System.out.println( "달라진 a = " + a.get( null ) );

        Field b = Book.class.getDeclaredField( "B" );
        b.setAccessible( true ); // B 가 private 필드이기 때문에 접근지시자를 무시하고 가져온다
        System.out.println( "b = " + b.get( book ) ); // B는 인스턴스에 해당하는 필드이기 때문에 null을 넣으면 NPE 발생함.
        b.set( book, "BBBBBBBB" );
        System.out.println( "달라진 b = " + b.get( book ) );

        // 메서드 조회
        Method c = Book.class.getDeclaredMethod( "c" );
        c.setAccessible( true );
        c.invoke( book );

        Method d = Book.class.getDeclaredMethod( "sum", int.class, int.class );
        int sum = (int ) d.invoke( book, 2, 3 );
        System.out.println( "sum = " + sum );

        Method e = Book.class.getDeclaredMethod( "sum2", int[].class );
        int sum2 = ( int ) e.invoke( book, new int[]{ 1, 1, 1, 1, 1 } );
        System.out.println( "sum2 = " + sum2 );
    }
}
