package com.govind.systemdesign.creational.abstractfactory;

import com.govind.systemdesign.creational.pojo.Bird;

/**
 * Created by 609600403 on 21/04/2016.
 */
public abstract class AbstractBirdFactory {
    public abstract Bird createBird(String name);
}
