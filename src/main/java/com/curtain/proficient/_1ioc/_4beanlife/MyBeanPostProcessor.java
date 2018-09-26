package com.curtain.proficient._1ioc._4beanlife;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;

/**
 * 自定义bean  后处理器
 * @author Curtain
 * @date 2018/7/3 15:41
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("car".equals(beanName)){
            Car car = (Car) bean;
            car.setColor("黑色");
            System.out.println("postProcessBeforeInitialization()..设置color的颜色为黑色");
        }
        return bean;
    }

    @Nullable
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("car".equals(beanName)){
            Car car = (Car) bean;
            car.setColor("白色");
            System.out.println("postProcessAfterInitialization()...设置color的颜色为白色");
        }
        return bean;
    }
}
