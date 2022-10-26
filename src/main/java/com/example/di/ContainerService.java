package com.example.di;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ContainerService {

    /**
     * %%%
     * 테스트코드에서는 소스코드를 참조할 수 있지만, 소스코드에서는 테스트 코드를 참조하지 못한다!!
     * %%%
     *
     * <T> 는 제네릭 타입을 선언한 것.
     * 제네릭 메소드: 리턴할 때 Object를 리턴하지 않고, 파라미터로 받은 클래스의 타입의 인스턴스를 리턴하고 싶을 때 사용.
     * @param classType
     * @return
     * @param <T>
     */
    public static <T> T getObject( Class<T> classType ){
        T instance = createInstance( classType );

        // 클래스가 가진 필드들을 하나씩 보면서 Inject 애노테이션이 붙었는지 확인한다 -> 붙었다면 필드 타입의 인스턴스를 생성해서 셋팅해준다
        Arrays.stream( classType.getDeclaredFields() ).forEach( f -> {
            if ( f.getAnnotation( Inject.class ) != null ) {
                // 필드 타입으로 인스턴스 생성
                Object fieldInstance = createInstance( f.getType() );// BookRepository
                f.setAccessible( true ); // private 필드일 수도 있으니까

                try {
                    // (Service) 필드에 생성한 (Repository) 인스턴스 주입
                    f.set( instance, fieldInstance );
                }
                catch ( IllegalAccessException e ) {
                    throw new RuntimeException( e );
                }
            };
        } );
        return instance;
    }

    private static <T> T createInstance ( Class<T> classType ) {
        try {
            return classType.getConstructor( null ).newInstance();
        }
        catch ( InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e ) {
            throw new RuntimeException( e );
        }
    }

}
