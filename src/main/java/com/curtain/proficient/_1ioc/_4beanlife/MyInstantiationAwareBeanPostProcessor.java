package com.curtain.proficient._1ioc._4beanlife;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.lang.Nullable;

import java.beans.PropertyDescriptor;

/**
 * 自定义 实例后处理适配器
 * @author Curtain
 * @date 2018/7/3 15:34
 */
public class MyInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {

    //在实例化之前调用
    @Nullable
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if ("car".equals(beanName)) {
            System.out.println("postProcessBeforeInstantiation()...准备进行初始化Car");
        }
        return super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    //在实例化之后调用
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("car".equals(beanName)) {
            System.out.println("postProcessAfterInitialization()...已进行初始化Car");
        }
        return super.postProcessAfterInitialization(bean, beanName);
    }

    //设置某个属性时调用
    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        if ("car".equals(beanName)) {
            System.out.println("postProcessPropertyValues()...对car属性进行处理");
        }
        return super.postProcessPropertyValues(pvs, pds, bean, beanName);
    }
}
