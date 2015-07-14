package com.newidor.learn.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 * <p>
 * Retention��ע�����͵�ע��Ҫ�������<br/>
 * 1��Ĭ��ΪRetentionPolicy.CLASS��ʾ����ע�⵽class�ļ��׶�<br/>
 * 2������RetentionPolicy.SOURCE��ʾ����ע�⵽Դ�ļ��׶Σ���Override��<br/>
 * </p>
 * <p>
 * Target����ע������ʲô�ط�������ElemenetType.TYPE��ʾ�����ࡢ�ӿ��У�ElemenetType.METHOD��ʾ���ڷ�����
 * </p>
 * <p>
 * Documented������ע������� javadoc��
 * </p>
 * <p>
 * Inherited����������̳и����е�ע�⡣
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