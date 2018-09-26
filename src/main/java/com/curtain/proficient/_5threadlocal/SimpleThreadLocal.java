package com.curtain.proficient._5threadlocal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Curtain
 * @date 2018/9/26 16:06
 * 简易的ThreadLocal
 */
public class SimpleThreadLocal<T> {

    private Map<Thread,T> valueMap = Collections.synchronizedMap(new HashMap<Thread,T>());

    public void set(T newValue) {
        //键为线程对象  值为本地变量副本
        valueMap.put(Thread.currentThread(), newValue);
    }

    public T get() {
        Thread currentThread = Thread.currentThread();

        //返回本线程对应的变量
        T o = valueMap.get(currentThread);

        //如果在Map中不存在。则放到Map中保存起来
        if (o == null & !valueMap.containsKey(currentThread)) {
            o = initialValue();
            valueMap.put(currentThread, o);
        }

        return o;
    }

    public void remove(){
        valueMap.remove(Thread.currentThread());
    }

    private T initialValue() {
        return null;
    }
}
