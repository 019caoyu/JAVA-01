package com.test.classloader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader {
    @Override
    protected Class findClass(String name) throws ClassNotFoundException {
        if (null == name) {
            throw new IllegalArgumentException("name is empty");
        }
        File file = new File(getResource(name).getFile());
        InputStream classInputStream = getResourceAsStream(name);
        try {
            int oneByteVal = 0;
            byte[] newBytes = new byte[(int)file.length()];
            int i = 0;
            while ((oneByteVal = classInputStream.read()) > 0) {
                newBytes[i] = (byte)(255 - oneByteVal);
                i++;
            }


            Class clazz = defineClass("Hello", newBytes, 0, newBytes.length);

            return clazz;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> helloCls = myClassLoader.findClass("com/test/loaderfile/Hello.xlass");
        helloCls.newInstance();

    }

}
