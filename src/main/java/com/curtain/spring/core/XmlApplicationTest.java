package com.curtain.spring.core;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Curtain
 * @date 2019/9/25 15:57
 */
public class XmlApplicationTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        context.getEnvironment().setActiveProfiles("dev");
        SimpleBean bean = context.getBean(SimpleBean.class);

    }

}
