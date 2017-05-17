package com.govind.systemdesign.creational.pojo;

/**
 * Created by 609600403 on 19/04/2016.
 */
public abstract class Bird {
    String scientificName;
    String color;
    String[] continent;

    public Bird(String scientificName, String color) {
        this.scientificName = scientificName;
        this.color = color;
    }

    @Override
    public String toString(){
        return "scientificName "+ scientificName +" color "+color;
    }
}
