package com.curtain.proficient._1ioc._5loadbean;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Curtain
 * @date 2018/7/5 17:35
 */
public class ApplicationContext {

    public static void main(String[] args) {
        //父容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:beans1.xml"});

        //指定context为该容器的父容器
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[]{"classpath:beans2.xml"},context);

        Car car = (Car) context.getBean("car");
        System.out.println(car.toString());

        People people = (People) applicationContext.getBean("people");
        System.out.println(people.toString());

        Car car2 = (Car) applicationContext.getBean("car");
        System.out.println(car2.toString());



    }
}
