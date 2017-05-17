package com.govind.systemdesign.creational.singleton;

/**
 * Created by 609600403 on 28/04/2016.
 */
public class SingletonEager {
    private static final SingletonEager SINGLETON_EAGER = new SingletonEager();

    private SingletonEager(){
        if (SINGLETON_EAGER != null){
            throw new IllegalStateException("Instance is already created");
        }
    }

    public static SingletonEager getSingletonEager(){
        return SINGLETON_EAGER;
    }
}
