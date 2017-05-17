package com.govind.systemdesign.creational.builder;

/**
 * Created by 609600403 on 21/04/2016.
 */
public class TestBuilder {
    public static void main(String[] args) {
        Computer computer = new Computer.ComputerBuilder("Lenovo","Windows")
                .setHasGraphicCard(true)
                .setHashBluetoothEnabled(true).build();
        System.out.println(computer.toString());

        //TestClass.InnerStatic innerStatic = new TestClass.InnerStatic("sAMPLE aDDRESS");

    }
}
