package com.govind.algo.logical;

import java.util.ArrayList;

/**
 * Created by govindp on 9/19/2015.
 */
public class NumberToStringConversion {
    public static String[] digits = {"One", "Two", "Three", "Four", "Five",
             "Six", "Seven", "Eight", "Nine"};
    public static String[] teens = {"Eleven", "Twelve", "Thirteen",
            "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen",
            "Nineteen"};
    public static String[] tens = {"Ten", "Twenty", "Thirty", "Forty",
            "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    public static String[] bigs = {"", "Thousand", "Million"};

    public static void main(String[] args) {
        System.out.println(toString(123));

    }



    private static String toString(int number) {
        String numberInString = "";
        if(number == 0){
            return "Zero";
        }

        if( number < 0){
            number -= 1;
        }
        int count = 0;
        while ( number > 0){
            if( number % 1000 != 0 ){
                numberInString = toStringIn100(number % 1000)+ bigs[count]+ numberInString;
            }

            number /= 1000;
            count++;
        }
        return numberInString;

    }

    private static String toStringIn100(int number) {
        String stringToNumber = "";

        if( number > 100){
            stringToNumber  = digits[number/100 - 1]+" Hundrend ";
            number = number % 100;

        }

        if( number < 20 && number > 10){
            stringToNumber += teens[number % 10 -1];
        }else if( number == 10 || number >= 20 ){
            stringToNumber += tens[number/10 - 1];
        }
        number /= 10;

        if( number < 10){
            stringToNumber += digits[ number -1];
        }
        return  stringToNumber;
    }
}
