package com.curtain.proficient._1ioc._5loadbean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Curtain
 * @date 2018/7/4 19:41
 */


@Getter
@Setter
@ToString
public class Car {

    private String color;
    private int speed;
    private String name;

    private <K,C,V> void f(K C){
        K i;
        C x;
    }
}


