package com.govind.systemdesign.creational.abstractfactory;

import com.govind.systemdesign.creational.pojo.Bird;
import com.govind.systemdesign.creational.pojo.FlyingBird;

/**
 * Created by 609600403 on 21/04/2016.
 */
public class FlyingBirdFactory extends AbstractBirdFactory {

    @Override
    public Bird createBird(String name) {
        if (name.equals("Dove"))
            return new FlyingBird("Dove", "Black");
        if (name.equals("Eagle"))
            return new FlyingBird("Eagle", "Brown");

        return null;
    }
}
