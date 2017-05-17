package com.govind.java.doubts;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by govindp on 11/23/2015.
 */
public class OverrideEqulsWhenUsringHashMap {
    int i;
    int j;

    public OverrideEqulsWhenUsringHashMap(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OverrideEqulsWhenUsringHashMap that = (OverrideEqulsWhenUsringHashMap) o;

        if (i != that.i) return false;
        return j == that.j;

    }

    @Override
    public int hashCode() {
        int result = i;
        result = 31 * result + j;
        return result;
    }

    public static void main(String[] args) {
        Map<OverrideEqulsWhenUsringHashMap, Boolean> mapBooleanMap = new HashMap<>();
        OverrideEqulsWhenUsringHashMap overrideEqulsWhenUsringHashMap1 = new OverrideEqulsWhenUsringHashMap(1, 2);
        OverrideEqulsWhenUsringHashMap overrideEqulsWhenUsringHashMap2 = new OverrideEqulsWhenUsringHashMap(1, 2);
        /*
        without overriding equals object with same values stored in the hashmap
        after overriding equals both the first and second are equal
        but the problem is both of them has different hash value since default hash value is there memory address
        when we overriding hashCode, both object will have same hashcode and equal value
         http://stackoverflow.com/questions/27581/what-issues-should-be-considered-when-overriding-equals-and-hashcode-in-java
        */
        mapBooleanMap.put(overrideEqulsWhenUsringHashMap1, true);
        mapBooleanMap.put(overrideEqulsWhenUsringHashMap2, false);
        System.out.println(mapBooleanMap.get(overrideEqulsWhenUsringHashMap1));
        System.out.println(mapBooleanMap.get(overrideEqulsWhenUsringHashMap2));

    }
}
