package com.curtain.proficient.ioc._3resource;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
 * 加载资源文件
 * @author Curtain
 * @date 2018/7/2 11:00
 */
public class PatternResolver {

    public static void main(String[] args) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        //加载所有类包 com.curtain.proficient.ioc.resource下的以.java结尾的所有文件
        Resource resources[] = resolver.getResources("file:T:/Java/SrpingProject/ProficientSpring/src/main/java/com/curtain/proficient/ioc/_3resource/*.java");

        for (Resource res:resources) {
            System.out.println(res.getDescription());
            //直接获取文件   在资源文件被打包成jar包后 这种方式获取 会抛出FileNotFoundException
            res.getFile();

            //通过InputStream获取
            res.getInputStream();
        }
    }
}
