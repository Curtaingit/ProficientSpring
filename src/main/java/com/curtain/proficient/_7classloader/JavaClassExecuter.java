package com.curtain.proficient._7classloader;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * 提供外部调用的路口，加载类 并发射调用它的方法   测试是否修改常量池成功。
 * @author Curtain
 * @date 2019/1/29 15:51
 */
public class JavaClassExecuter {

    public static String execute(byte[] classByte) {
        HackSystem.clearBuffer();
        ClassModify cm = new ClassModify(classByte);
        byte[] modifyBytes = cm.modifyUTF8Constant("java/lang/System", "com/curtain/proficient/_7classloader/HackSystem");
        HotSwapClassLoader loader = new HotSwapClassLoader();
        Class clazz = loader.loadByte(modifyBytes);
        try {
            Method method = clazz.getMethod("main", new Class[]{String[].class});
            method.invoke(null, new String[]{null});
        } catch (Throwable e) {
            e.printStackTrace(HackSystem.out);
        }
        return HackSystem.getBufferString();
    }

    //测试运行
    public static void main(String[] args) throws IOException {
        InputStream is = new FileInputStream("T:\\Java\\SrpingProject\\ProficientSpring\\src\\main\\java\\com\\curtain\\proficient\\_7classloader\\TestClass.class");
        byte[] b = new byte[is.available()];
        is.read(b);
        is.close();

        System.out.println(execute(b));

    }



}
