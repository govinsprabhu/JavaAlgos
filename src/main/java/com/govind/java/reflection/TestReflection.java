package com.govind.java.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by 609600403 on 23/04/2016.
 */
public class TestReflection {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class clazz = Class.forName("com.govind.java.reflection.ClassWithPrivateMethod");
        Constructor constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        Object object = constructor.newInstance();
        Method method = clazz.getDeclaredMethod("getName",null);
        method.setAccessible(true);
        System.out.println(method.invoke(object));
        invokeUsingConstructor();
    }

    private static void invokeUsingConstructor() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clazz = Class.forName("com.govind.java.reflection.ClassWithPrivateMethod");
        Class[] argsTypes = {String.class,String.class};
        Constructor constructor = clazz.getDeclaredConstructor(argsTypes);
        Object[] args = {"Govind", "Prabhu"};
        ClassWithPrivateMethod classWithPrivateMethod = (ClassWithPrivateMethod) constructor.newInstance(args);
        System.out.println(classWithPrivateMethod);
    }
}
