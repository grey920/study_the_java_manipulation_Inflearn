package com.example.hat;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

import java.io.File;
import java.io.IOException;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class Magician {
    public static void main ( String[] args ) throws IOException, InstantiationException, IllegalAccessException {

        /**
         * 모자에 토끼를 넣는 작업. 일종의 컴파일작업처럼 클래스파일을 바꿔버린다
         */
        // redefine() : 클래스 재정의
//        new ByteBuddy().redefine( Hat.class )
//                // pullOut() 메서드를 낚아채 고정된 "Rabbit"값을 리턴한다
//                .method( named( "pullOut" ) ).intercept( FixedValue.value( "Rabbit!" ) )
//                .make()
//                .saveIn( new File ("/Users/grey/0.MainFolder/00.study/10.inflearn/40.backkeesun/Users/grey/0.MainFolder/00.study/10.inflearn/40.backkeesun/02.TheJavaCodeManipulation/jvminternal/target/classes/" ) )
//        ;

        System.out.println( new Hat().pullOut() );


        /**
         * class 생성 : https://www.baeldung.com/byte-buddy
         */
        Class<?> InvisiableHat = new ByteBuddy()
                .subclass(Object.class)
                .method( ElementMatchers.isToString())
                .intercept(FixedValue.value("dove!"))
                .make()
                .load( Magician.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded()
                ;

        System.out.println( InvisiableHat.newInstance().toString() );
    }
}
