package com.govind.systemdesign.creational.abstractfactory;

import com.govind.systemdesign.creational.pojo.FlyingBird;
import com.govind.systemdesign.creational.pojo.NonFlyingBird;

/**
 * Created by 609600403 on 21/04/2016.
 */
public class TestAbstractFactory {
    public static void main(String[] args) {
        FlyingBird flyingBird = (FlyingBird) AbstractBirdFactoryResolver.getBirdFactory("flying").createBird("Dove");
        System.out.println(flyingBird.toString());
        NonFlyingBird nonFlyingBird = (NonFlyingBird) AbstractBirdFactoryResolver.getBirdFactory("nonflying").createBird("hen");
        System.out.println(nonFlyingBird.toString());

    }
}
