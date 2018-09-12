package com.curtain.proficient.aop;

import com.curtain.proficient.aop.service.WordService;
import com.curtain.proficient.aop.service.impl.WordServiceImpl;

/**
 * @author Curtain
 * @date 2018/9/12 14:00
 */
public class NormalMode {

    public static void main(String[] args) {
        WordService wordService = new WordServiceImpl();
        wordService.start("张三");
        wordService.start("李四");
        wordService.end("李四");
        wordService.end("张三");
    }
}
