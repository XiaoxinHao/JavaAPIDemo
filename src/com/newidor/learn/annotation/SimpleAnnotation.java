package com.newidor.learn.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 * <p>
 * Retention：注释类型的注释要保留多久<br/>
 * 1、默认为RetentionPolicy.CLASS表示保留注解到class文件阶段<br/>
 * 2、其他RetentionPolicy.SOURCE表示保留注解到源文件阶段，如Override，<br/>
 * </p>
 * <p>
 * Target：该注解用于什么地方，例如ElemenetType.TYPE表示用在类、接口中，ElemenetType.METHOD表示用在方法中
 * </p>
 * <p>
 * Documented：将此注解包含在 javadoc中
 * </p>
 * <p>
 * Inherited：允许子类继承父类中的注解。
 * </p>
 * 
 * @author newidor
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR })
public @interface SimpleAnnotation {
	String name();

	int id() default 0;

	Class<?> gid();
}