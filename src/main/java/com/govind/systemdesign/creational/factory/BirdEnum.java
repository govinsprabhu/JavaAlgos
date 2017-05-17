package com.govind.systemdesign.creational.factory;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

//     * Created by 609600403 on 19/04/2016.


    public enum BirdEnum {
        FLYING("flying"), NON_FLYING("nonFlying");
        private static Set<String> set;
        private String name;

        public String getName() {
            return name;
        }

        BirdEnum(String name) {
            this.name = name;
        }

        static {
            set = Arrays.asList(BirdEnum.values()).stream().map(x -> x.toString()).collect(Collectors.toSet());
        }

        public void get() {
            Arrays.asList(BirdEnum.values()).stream().filter(x -> x.equals("ss")).findFirst().get();
        }


}
