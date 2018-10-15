package jssvc.base.interceptor;

import java.lang.annotation.*;

@Target({ ElementType.METHOD })  //表明注解对成员方法起作用
@Documented
@Retention(RetentionPolicy.RUNTIME) //编译后仍然起作用
public @interface LogFace {
    // 描述/备注
    String desc() default "";
}