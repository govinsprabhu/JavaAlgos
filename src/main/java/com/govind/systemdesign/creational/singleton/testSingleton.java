package com.govind.systemdesign.creational.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by 609600403 on 19/04/2016.
 */
public class testSingleton {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        SingletonLazy singleton = SingletonLazy.getSingletonLazy();
        System.out.println(singleton);
        Class clazz = SingletonLazy.class;
        Constructor constructor =clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        SingletonLazy singletonEager1 = (SingletonLazy) constructor.newInstance();
        System.out.println(singletonEager1);
        /*SingletonEager singletonEager = SingletonEager.getSingletonEager();
        System.out.println(singletonEager);
        Class clazz = SingletonEager.class;
        Constructor constructor =clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        SingletonEager singletonEager1 = (SingletonEager) constructor.newInstance();
        System.out.println(singletonEager1);*/
    }
}
