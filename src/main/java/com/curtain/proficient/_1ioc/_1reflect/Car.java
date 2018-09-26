package com.curtain.proficient._1ioc._1reflect;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Curtain
 */

@Getter
@Setter
public class Car {

    private String color;

    protected void drive(){
        System.out.println("drive private car! the color is " + color);
    }
}
