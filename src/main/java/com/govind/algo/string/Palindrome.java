package com.govind.algo.string;

/**
 * Created by govindp on 9/12/2015.
 */
public class Palindrome {
    public static void main(String[] args) {
        String string = "121";
        boolean[] array = new boolean[30];
        for (int i = 0; i < array.length; i++){
            System.out.println(array[i]);
        }
        System.out.println(isPalindrome(string));
    }

    private static boolean isPalindrome(String string) {
        String newString = string.toLowerCase();
        for( int  i = 0, j = newString.length() -1 ; i <= (newString.length()-1)/2  ;){

            if(!((newString.charAt(i) >= 97 && newString.charAt(i) <= 132) || (newString.charAt(i) >= 48 && newString.charAt(i) <= 57))){
                i++;
            }

            else if(!((newString.charAt(j) >= 97 && newString.charAt(j) <= 132) || (newString.charAt(j) >= 48 && newString.charAt(j) <= 57))){
                j--;
            }

            else if(newString.charAt(i) == newString.charAt(j)){
                i++;
                j--;

            }else{
                return false;
            }

        }
        return true;
    }

}
