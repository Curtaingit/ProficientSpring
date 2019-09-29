package com.curtain.proficient._6hystrix;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Curtain
 * @date 2018/11/16 8:50
 */
public class Test {

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        CommandOrder phone = new CommandOrder("手机");
        CommandOrder tv = new CommandOrder("电视");

        //阻塞方式运行
        String execute = phone.execute();
        System.out.println(Thread.currentThread().getName() + "Execute = " + execute);

        //异步非阻塞方式
        Future<String> queue = tv.queue();
        String value = queue.get(200, TimeUnit.MILLISECONDS);
        System.out.println(Thread.currentThread().getName() + "value = " + value);

        CommandUser user = new CommandUser("小明");
        String name = user.execute();
        System.out.println(Thread.currentThread().getName() + "name = " + name);

        System.out.println(0.45f/3);

  
    }
}
