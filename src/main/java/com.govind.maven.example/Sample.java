package com.govind.maven.example;

import payload.AnotherSampleInterface;
import payload.SampleSuper;

import java.util.Calendar;

/**
 * Created by govindp on 8/23/2015.
 */
public class Sample  extends SampleSuper implements AnotherSampleInterface{
    public static void main(String[] args){
        new Sample().getName();
        System.out.println();
    }

    @Override
    public void print() {
        System.out.println("hi");
    }

    @Override
    public void getName() {
    }
}
