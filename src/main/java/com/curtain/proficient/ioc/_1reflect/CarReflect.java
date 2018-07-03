package com.curtain.proficient.ioc._1reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射的简单使用
 * @author Curtain
 * @date 2018/7/2 10:08
 */
public class CarReflect {

    public static void main(String[] args) throws Throwable {
        //多种获取 类加载器的方式
        //ClassLoader loader = ClassLoader.getSystemClassLoader();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        //直接获取Class
        //Class clazz = Class.forName("com.curtain.proficient.ioc._1reflect.Car");
        Class clazz = loader.loadClass("com.curtain.proficient.ioc._1reflect.Car");
        //获取实例
        Car car = (Car) clazz.newInstance();
        //获取变量
        Field color = clazz.getDeclaredField("color");

        //取消java语言 检查访问private变量   否则抛出IllegalAccessException
        color.setAccessible(true);
        color.set(car,"蓝色");

        Method driveMtd = clazz.getDeclaredMethod("drive",(Class[])null);

        //取消java语言 检查访问protected变量
        driveMtd.setAccessible(true);
        driveMtd.invoke(car,(Object[])null);




    }
}
