package com.project.domain.sign;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) //파라미터에서 사용되는 Annotation임을 명시.
@Retention(RetentionPolicy.RUNTIME) //어노테이션을 런타임시에까지 사용할 수 있음. JVM이 자바 바이트코드가 담긴 class 파일에서 런타임환경을 구성하고 런타임을 종료할 때까지 메모리는 살아있음을 명시.
public @interface Sign {
}
