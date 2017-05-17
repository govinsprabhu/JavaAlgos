package com.govind.systemdesign.creational.builder;

/**
 * Created by 609600403 on 21/04/2016.
 */
public  class Sample {
    static String name;
    int age;
    public  class InnerStatic{
        String address;

        public InnerStatic(String address) {
            this.address = address;

        }

        @Override
        public String toString() {
            return "InnerStatic{" +
                    "address='" + address + '\'' + name +
                    '}';
        }
    }

}
