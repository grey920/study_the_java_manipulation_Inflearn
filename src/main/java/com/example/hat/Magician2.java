package com.example.hat;

/**
 * 아무것도 없이 꺼내기만 한다.
 *
 * Magician1은 클래스파일을 직접 변경했지만,
 * Magician2는 javaagent가 하는 일이 클래스 로딩할 때 적용된다.
 * 클래스를 로딩할 때 javaagent를 거쳐서 변경된 바이트 코드 자체를 메모리에 올린다
 * => 비침투적인 방식
 */
public class Magician2 {
    public static void main ( String[] args ) {
        System.out.println( new Hat().pullOut() );
    }
}
