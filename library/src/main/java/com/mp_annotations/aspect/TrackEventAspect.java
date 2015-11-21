package com.mp_annotations.aspect;

import com.mp_annotations.AnalyticsManager;
import com.mp_annotations.annotation.TrackEvent;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.json.JSONObject;

/**
 * Created by ajaybhatt on 11/10/15.
 */
@Aspect
public class TrackEventAspect {

    @Pointcut("within(@com.mp_annotations.annotation.TrackEvent *)")
    public void withinAnnotatedClass() {}

    @Pointcut("execution(* *(..)) && withinAnnotatedClass()")
    public void methodInsideAnnotatedType() {}

    @Pointcut("execution(*.new(..)) && withinAnnotatedClass()")
    public void constructorInsideAnnotatedType() {}

    @Pointcut("execution(@com.mp_annotations.annotation.TrackEvent * *(..)) || methodInsideAnnotatedType()")
    public void method() {}

    @Pointcut("execution(@com.mp_annotations.annotation.TrackEvent *.new(..)) || constructorInsideAnnotatedType()")
    public void constructor() {}

    @Around("method() || constructor()")
    public Object sendTrack(ProceedingJoinPoint joinPoint) throws Throwable {
        TrackEvent myAnnotation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(TrackEvent.class);

        AnalyticsManager.getInstance().trackEvent(myAnnotation.event(), new JSONObject());

        return joinPoint.proceed();
    }
}
