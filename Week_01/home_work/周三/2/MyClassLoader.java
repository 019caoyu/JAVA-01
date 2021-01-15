package com.test.classloader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;

public class MyClassLoader extends ClassLoader {

    private final String loadClsName;

    MyClassLoader(String loadClsName) {
        this.loadClsName = loadClsName;
    }

    @Override
    protected Class findClass(String name) throws ClassNotFoundException {
        if (null == name) {
            throw new IllegalArgumentException(" name is empty");
        }
        byte[] newBytes = getClsFileBytes( name);
        return defineClass(loadClsName, newBytes, 0, newBytes.length);
    }

    private byte[] getClsFileBytes(String name) {
        int fileByteLen = getFileByteLen(name);
        byte[] newBytes = new byte[fileByteLen];

        InputStream classInputStream = getResourceAsStream(name);
        int oneByteVal = 0;
        int i = 0;
        try {
            while ((oneByteVal = classInputStream.read()) >= 0) {
                newBytes[i] = (byte) (255 - oneByteVal);
                i++;
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return newBytes;
    }

    private int getFileByteLen(String name) {
        URL fileUrl = getResource(name);
        if (fileUrl == null) {
            throw new IllegalArgumentException(name + " is not found");
        }
        return (int)new File(fileUrl.getFile()).length();
    }

    public static void main(String[] args) throws Exception {
        ClassLoader myClassLoader = new MyClassLoader("Hello");
        Class<?> helloCls = myClassLoader.loadClass("com/test/loaderfile/Hello.xlass");
        Method method = helloCls.getMethod("hello");
        method.invoke(helloCls.newInstance());
    }

}
