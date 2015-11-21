package com.mp_annotations.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * Created by ajaybhatt on 11/10/15.
 */
@Documented
@Target({TYPE, METHOD, CONSTRUCTOR})
@Retention(CLASS)
public @interface TrackEvent {

    String event() default "category";

}