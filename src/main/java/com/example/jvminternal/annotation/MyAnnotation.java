package com.example.jvminternal.annotation;

import java.lang.annotation.*;

/**
 * 애노테이션은 기본적으로 "주석"과 같다.
 * 좀 기능이 많은 주석..
 * -> 바이트코드를 로딩했을때 이 정보는 빼고 읽어오기 때문에 메모리상에는 남지 않는다.
 * => 메모리까지 남게하고 싶다면?? -> @Retention 전략 사용
 * @Retention의 기본값은 CLASS라서 컴파일하고 바이트코드를 열어보면 남아있다.
 *
 * 애노테이션은 제한된 값들을 가질 수 있다.
 * - 기본값을 주려면 default 키워드를 사용
 * - 기본값을 주지 않으면 애노테이션을 붙일 때 파라미터로 꼭 값을 넣어줘야 한다
 * - value라는 키는 애노테이션 붙일때 따로 키를 명시하지 않아도 된다. 그냥 값만 적으면 됨 예,( @MyAnnotation("grey") )
 *  하지만 여러개의 파라미터를 보낸다면  value도 명시해야 함. 따라서 값을 하나만 필요로 할 떄 유용.
 *
 *  기본적으로 상속받은 객체는 부모의 애노테이션이 적용되지 않는다.
 *  부모의 애노테이션도 함께 적용되게 하려면 @Inherited를 붙인다.
 */
@Retention( RetentionPolicy.RUNTIME ) // 해당 애노테이션을 언제까지 유지할 지 설정
@Target( {ElementType.TYPE , ElementType.FIELD , ElementType.CONSTRUCTOR }) // 해당 애노테이션을 붙일 위치를 지정
@Inherited // 상속받은 객체들도 해당 애노테이션을 사용하게 하려면 지정
public @interface MyAnnotation {
    String value() default "grey";

    String name() default "grey";

    int number() default 100;


}
