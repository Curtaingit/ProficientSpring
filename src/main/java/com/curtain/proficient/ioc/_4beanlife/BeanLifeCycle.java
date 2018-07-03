package com.curtain.proficient.ioc._4beanlife;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;


/**
 * 将bean装载到容器 观察其生命周期
 *
 * @author Curtain
 * @date 2018/7/3 15:47
 */
public class BeanLifeCycle {

    public static void main(String[] args) {

        //装载配置文件  并启动容器
        Resource resource = new FileSystemResource("T:\\Java\\SrpingProject\\ProficientSpring\\src\\main\\java\\com\\curtain\\proficient\\ioc\\_4beanlife\\beans.xml");
        BeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader((BeanDefinitionRegistry) beanFactory);
        reader.loadBeanDefinitions(resource);

        //向容器中注册 MyBeanPostProcessor自定义后处理器 和  自定义实例后处理适配器MyInstantiationAwareBeanPostProcessor
        ((ConfigurableBeanFactory) beanFactory).addBeanPostProcessor(new MyBeanPostProcessor());
        ((ConfigurableBeanFactory) beanFactory).addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

        //第一次冲容器中获取car
        Car car1 = (Car) beanFactory.getBean("car");

        //第二次从容器中获取car  因为默认scope=singleton  所以即为 直接从缓存池中获取
        Car car2 = (Car) beanFactory.getBean("car");

        //查看car1和car2是否指向同一个引用
        System.out.println("car1==car2:" + (car1 == car2));

        //关闭容器
        ((ConfigurableBeanFactory) beanFactory).destroySingletons();

    }
}
