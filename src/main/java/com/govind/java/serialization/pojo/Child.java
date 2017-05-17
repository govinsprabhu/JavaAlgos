package com.govind.java.serialization.pojo;

import java.io.Serializable;

/**
 * Created by 609600403 on 17/05/2017.
 */
public class Child extends Person implements Serializable {
    boolean married;
    Parent parent;

    @Override
    public String toString() {
        return "Child{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", married=" + married +
                ", parent=" + parent +
                '}';
    }
}
