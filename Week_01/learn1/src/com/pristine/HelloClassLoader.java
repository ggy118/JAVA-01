package com.pristine;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName : HelloClassLoader
 * @Description :
 * @Author : Pristine
 * @Date: 2021-01-06 22:48
 */
public class HelloClassLoader extends ClassLoader {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> clazz = new HelloClassLoader().findClass("Hello");
        try {
            Method hello = clazz.getMethod("hello");
            hello.invoke(clazz.newInstance());
        } catch (NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String workSpace = System.getProperty("user.dir");
        String filePath = workSpace + File.separator + "Hello.xlass";
        byte[] bytes = searchClass(filePath);
        return defineClass(name, bytes, 0, bytes.length);
    }

    /**
     * 根据文件读取文件
     *
     * @param filePath
     * @return byte[]
     */
    private static byte[] searchClass(String filePath) {
        File file = new File(filePath);
        int length = (int) file.length();
        byte[] result = new byte[length];
        try {
            InputStream input = new FileInputStream(file);
            input.read(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i <= result.length - 1; i++) {
            result[i] = (byte) (255 - result[i]);
        }
        return result;
    }
}
