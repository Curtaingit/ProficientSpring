package com.curtain.proficient.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author Curtain
 * @date 2018/9/17 15:53
 */
@Aspect
public class SayAspect {

    @Pointcut("execution(* com.curtain.proficient.*.*.*(..))")
    public void say() {
    }

    @Around("say()")
    public void aroundSay(JoinPoint joinPoint) {
        System.out.println("args: " + joinPoint.getArgs()[0]);
        System.out.println("signature: " + joinPoint.getSignature());
        System.out.println("class: " + joinPoint.getTarget().getClass());
        System.out.println("this: " + joinPoint.getThis());
    }

}
