package com.govind.algo;

/**
 * Created by govindp on 9/3/2015.
 */
public class NCR {
    public static int getNcR(int n, int r){
        int nominator = fact(n);
        int denominator = fact(r) * fact(n-r);
        return nominator/denominator;
    }

    private static int fact(int n) {
        int fact = 1;
        for(int i = 1; i <= n; i ++ ){
            fact *= fact * i;
        }
        return fact;
    }
}
