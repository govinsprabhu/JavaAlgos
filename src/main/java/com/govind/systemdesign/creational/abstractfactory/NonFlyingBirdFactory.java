package com.govind.systemdesign.creational.abstractfactory;

import com.govind.systemdesign.creational.pojo.Bird;
import com.govind.systemdesign.creational.pojo.NonFlyingBird;

/**
 * Created by 609600403 on 21/04/2016.
 */
public class NonFlyingBirdFactory extends AbstractBirdFactory {
    @Override
    public Bird createBird(String name) {
        if (name.equals("Penguin"))
            return new NonFlyingBird("Penguin", "Black");

        if (name.equals("hen")){
            return new NonFlyingBird("Hen","White");
        }

        return null;

    }
}
