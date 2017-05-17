package com.govind.systemdesign.creational.singleton;

/**
 * Created by 609600403 on 19/04/2016.
 */
public class SingletonLazy {
    private static  SingletonLazy SINGLETON_LAZY = null;

    private SingletonLazy() {

    }

    public static SingletonLazy getSingletonLazy() {
        if (SINGLETON_LAZY == null) {
            synchronized (SingletonLazy.class) {
                if (SINGLETON_LAZY == null) {
                    SINGLETON_LAZY = new SingletonLazy();
                }
            }
        }
        return SINGLETON_LAZY;
    }

    public static void printHello() {
        System.out.println("Print Hello");
    }
}
