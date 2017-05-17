package com.govind.systemdesign.creational.factory;

import com.govind.systemdesign.creational.pojo.Bird;

import java.util.Arrays;

/**
 * Created by 609600403 on 19/04/2016.
 */
public class TestFactory {
    public static void main(String[] args) {
        Bird bird = BirdFactory.getBird(BirdEnum.FLYING);
        System.out.println(BirdEnum.valueOf("FLYING"));
        "abc".intern();
        Arrays.asList();
        //Map<String, BirdEnum> map = EnumSet.allOf(BirdEnum.class).stream().map(x -> x).collect(Collectors.toMap(x->x.getName(), x->x));


        //System.out.println(map);
    }
}
