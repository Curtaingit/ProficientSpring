package com.curtain.proficient._1ioc._3resource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;


/**
 * 读取资源文件  并改变其编码
 * @author Curtain
 * @date 2018/7/2 10:47
 */
public class EncodedResourceExample {

    public static void main(String[] args) throws IOException {
        Resource resource = new ClassPathResource("conf/file.txt");
        EncodedResource encodedResource = new EncodedResource(resource,"UTF-8");
        String content = FileCopyUtils.copyToString(encodedResource.getReader());
        System.out.println(content);

    }
}
