package com.curtain.hash;

import java.util.*;

/**
 * @author Curtain
 * @date 2019/10/8 16:08
 */
public class TestConsistentHash {

    public static void main(String[] args) {
        AbstractConsistentHash map = new TreeMapConsistentHash();
        Map<String, Integer> t = new TreeMap<>();

        List<String> strings = new ArrayList<>();
        long l = System.currentTimeMillis();

        for (int i = 0; i < 10; i++) {
            strings.add("127.0.0." + i);
        }

        for (int i = 0; i < 10000; i++) {
            String process = map.process(strings, "zhangsan" + i);
//            System.out.println(process);
            if (t.containsKey(process)) {
                Integer c = t.get(process);
                t.put(process, ++c);
            } else {
                t.put(process, 1);
            }
        }
        t.forEach((s, integer) -> System.out.println("key = " + s + "value = " + integer));

        System.out.println("time->" + (System.currentTimeMillis() - l));
    }
}
