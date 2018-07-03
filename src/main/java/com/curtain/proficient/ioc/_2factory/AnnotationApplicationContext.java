package com.curtain.proficient.ioc._2factory;

import com.curtain.proficient.ioc._1reflect.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 使用注解@Configuration  来装载Bean
 * @author Curtain
 * @date 2018/7/2 19:15
 */
public class AnnotationApplicationContext {

    public static void main(String[] args) {
        //AnnotationConfigApplicationContext 将 加载Beans.class 中的Bean定义并调用
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Beans.class);

        Car car = ctx.getBean("car",Car.class);

        System.out.println(car.getColor());

    }


}
