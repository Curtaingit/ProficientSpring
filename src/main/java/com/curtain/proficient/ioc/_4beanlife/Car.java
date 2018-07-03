package com.curtain.proficient.ioc._4beanlife;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 实现各种生命周期控制访问
 *
 * @author Curtain
 * @date 2018/7/3 15:21
 */
public class Car implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean {

    private String color;

    private BeanFactory beanFactory;
    private String beanName;

    public Car() {
        System.out.println("构造函数被调用");
    }

    public void setColor(String color) {
        System.out.println("调用setColor()设置属性");
        this.color = color;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("调用BeanFactoryAware.setBeanFactory()");
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String beanName) {
        System.out.println("调用BeanAware.setBeanName()");
        this.beanName = beanName;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("调用DisposableBean.destroy()");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("调用InitializingBean.afterPropertiesSet()");
    }

    public void myInit(){
        System.out.println("自定义初始化方法");
    }

    public void myDestroy(){
        System.out.println("自定义销毁方法");
    }

    /**
     * 通过注解的方式在bean销毁前执行该方法   ApplicationContext默认已经装载了该处理器
     */
    @PreDestroy
    private void annotationPostDestroy(){
        System.out.println("annotationPostDestroy()...");
    }

    /**
     * 通过注解的方式在bean初始化后执行该方法   ApplicationContext默认已经装载了该处理器  还有一些其他的
     */
    @PostConstruct
    private void annotationPostConstruct(){
        System.out.println("annotationPostConstruct()...");
    }

}
