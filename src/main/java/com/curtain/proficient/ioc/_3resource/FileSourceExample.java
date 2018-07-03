package com.curtain.proficient.ioc._3resource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;

import java.io.*;

/**
 * 通过Resource来读写文件
 * @author Curtain
 * @date 2018/7/2 9:53
 */
public class FileSourceExample {

    public static void main(String[] args) throws IOException {
        String filePath = "T:\\Java\\SrpingProject\\ProficientSpring\\src\\main\\resources\\conf\\file.txt";

        //使用系统文件路径方式加载文件
        WritableResource res1 = new PathResource(filePath);

        //使用类路径方式加载文件
        Resource res2 = new ClassPathResource("conf/file.txt");

        //使用WritableResource接口写资源文件
        OutputStream ops = res1.getOutputStream();
//        File file = res1.getFile();
        ops.write("WritableResource".getBytes());
        ops.close();

        //使用Resource接口读资源文件
        InputStream ins1 = res1.getInputStream();
        InputStream ins2 = res2.getInputStream();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i;
        while ((i=ins2.read())!=-1){
            baos.write(i);
        }
        System.out.println(baos.toString());

        System.out.println("res1: "+res1.getFilename());
        System.out.println("res2: "+res2.getFilename());
    }
}
