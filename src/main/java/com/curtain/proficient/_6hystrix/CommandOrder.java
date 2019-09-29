package com.curtain.proficient._6hystrix;

import com.netflix.hystrix.*;

import java.util.concurrent.TimeUnit;

/**
 * 表示订单服务
 *
 * @author Curtain
 * @date 2018/11/16 8:19
 */
public class CommandOrder extends HystrixCommand<String> {

    private String orderName;

    public CommandOrder(String orderName){
        super(Setter.withGroupKey(
                //服务分组
                HystrixCommandGroupKey.Factory.asKey("OrderGroup"))
                //线程分组
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("OrderPool"))
                //线程池配置
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                    .withCoreSize(10)
                    .withKeepAliveTimeMinutes(5)
                    .withMaxQueueSize(10)
                    .withQueueSizeRejectionThreshold(10000))

                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD))
        );

        this.orderName = orderName;
    }

    @Override
    protected String run() throws Exception {
        System.out.println(Thread.currentThread().getName()+"orderName = " + orderName);
        TimeUnit.MILLISECONDS.sleep(100);
        return "OrderName=" + orderName;
    }
}
