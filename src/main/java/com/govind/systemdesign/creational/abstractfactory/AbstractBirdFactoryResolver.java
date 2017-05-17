package com.govind.systemdesign.creational.abstractfactory;

/**
 * Created by 609600403 on 21/04/2016.
 */
public class AbstractBirdFactoryResolver {

    public static AbstractBirdFactory getBirdFactory(String birdNature){
        if (birdNature.equals("flying")){
            return new FlyingBirdFactory();
        }
        if (birdNature.equals("nonflying")){
            return new NonFlyingBirdFactory();
        }
        return null;
    }
}
