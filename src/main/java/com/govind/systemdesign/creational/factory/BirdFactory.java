package com.govind.systemdesign.creational.factory;

import com.govind.systemdesign.creational.pojo.Bird;
import com.govind.systemdesign.creational.pojo.FlyingBird;
import com.govind.systemdesign.creational.pojo.NonFlyingBird;

/**
 * Created by 609600403 on 19/04/2016.
 */
public class BirdFactory {

    public static Bird getBird(BirdEnum birdEnum){
        switch (birdEnum){
            case FLYING:
                return new FlyingBird("Dove","Black");
            case NON_FLYING:
                return new NonFlyingBird("Penguin","White");
        }
        return null;
    }
}
