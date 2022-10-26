package com.example.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention( RetentionPolicy.RUNTIME ) // 런타임에 참조할 애노테이션이기 때문에
public @interface Inject {


}
