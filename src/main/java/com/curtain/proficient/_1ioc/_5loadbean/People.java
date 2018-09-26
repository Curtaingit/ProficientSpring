package com.curtain.proficient._1ioc._5loadbean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Curtain
 * @date 2018/7/5 17:29
 */

@Getter
@Setter
@ToString
public class People {

    private Car car;

    public void setCar(Car car) {
        this.car = car;
    }
}
