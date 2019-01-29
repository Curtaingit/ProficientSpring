package com.curtain.proficient._7classloader;

/**
 * 使我们可以重新加载编译后的class文件
 * @author Curtain
 * @date 2019/1/29 11:04
 */
public class HotSwapClassLoader extends ClassLoader{

    public HotSwapClassLoader(){
        super(HotSwapClassLoader.class.getClassLoader());
    }

    /**
     * 将提交的字节数组 变为Class对象
     * @param classByte
     * @return
     */
    public Class loadByte(byte[] classByte){
        return defineClass(null, classByte, 0, classByte.length);
    }
}
