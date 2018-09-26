package com.curtain.proficient._2aop;

import com.curtain.proficient._2aop.cglibproxy.CglibProxy;
import com.curtain.proficient._2aop.service.WordService;
import com.curtain.proficient._2aop.service.impl.WordServiceImpl;

/**
 * @author Curtain
 * @date 2018/9/12 17:24
 */
public class CglibProxyTest {
    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        WordService wordService = (WordService) proxy.getProxy(WordServiceImpl.class);

        wordService.start("biu");

    }
}
