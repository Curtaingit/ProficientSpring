package com.curtain.proficient._1ioc._2factory;

import com.curtain.proficient._1ioc._1reflect.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Curtain
 * @date 2018/7/2 19:06
 */

@Configuration
public class Beans {

    @Bean("car")
    public Car bulidCar(){
        Car car = new Car();
        car.setColor("蓝色");
        return car;
    }
}
