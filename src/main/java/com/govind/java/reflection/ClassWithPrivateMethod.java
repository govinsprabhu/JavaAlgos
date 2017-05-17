package com.govind.java.reflection;

/**
 * Created by 609600403 on 23/04/2016.
 */
public class ClassWithPrivateMethod {
    private String name;
    private String val;

    private ClassWithPrivateMethod() {
        this.name = "Govind";
    }
    ClassWithPrivateMethod(String name, String val){
        this.name = name;
        this.val = val;
    }
    private String getName(){
        return name;
    }

    @Override
    public String toString() {
        return "ClassWithPrivateMethod{" +
                "name='" + name + '\'' +
                ", val='" + val + '\'' +
                '}';
    }
}
