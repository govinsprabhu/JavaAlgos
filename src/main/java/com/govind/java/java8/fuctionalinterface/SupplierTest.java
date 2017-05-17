package com.govind.java.java8.fuctionalinterface;

import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class SupplierTest {
    public static void main(String[] args) {
        Supplier<String> supplier = () -> "Hello";
        String unknownString = supplier.get();
        System.out.println("Unknown String :"+ unknownString);
        Supplier<Date> dateSupplier  = SupplierTest::getDate;
        System.out.println("What is the date :"+ dateSupplier.get());
        Runnable runnable = () -> System.out.println("run");
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        t.start();
    }

    private static Date getDate(){
        return new Date();
    }
}