package com.govind.java.java8.asynchronous;

import java.util.concurrent.Callable;

/**
 * Created by 609600403 on 11/05/2016.
 */
public class MyCallable implements Callable<String>{
    private long waitTime;


    public MyCallable(long waitTime){
        this.waitTime = waitTime;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(waitTime);
        return Thread.currentThread().getName();
    }
}




