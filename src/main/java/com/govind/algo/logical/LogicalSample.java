package com.govind.algo.logical;

/**
 * Created by govindp on 9/19/2015.
 */
public class LogicalSample {
    public static void main(String[] args) {
        int[] result = nextPalindrome(new int[]{9,9,9});
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]);
        }
    }

    private static int larger(int a, int b) {
        int k = sign(a - b);
        int flip = flip(k);
        return a * flip + b * k;
    }

    private static int flip(int k) {
        return 1 ^ k;
    }

    private static int sign(int i) {
        return ((i >> 31) & 1);

    }

    public static int[] nextPalindrome(int[] number) {
        int i = 0, j;
        int mid = 0;
        int n = number.length;
        mid = n / 2;
        if (n % 2 == 0) {
            i = n / 2 - 1;
            j = n / 2 ;
        } else {
            i = n / 2 - 1;
            j = n / 2 + 1;
        }

        while (i >= 0 && j < n && number[i] == number[j]) {
            i--;
            j++;
        }

        int carry = 0;
        if (i <= 0 && j >= n) {
            if (n % 2 == 0) {
                i = mid - 1;
                carry = 1;
                j = mid;
            } else {
                number[mid] = (number[mid] + 1);
                carry = number[mid] / 10;
                number[mid] = number[mid] % 10;
                i = mid - 1;
                j = mid + 1;
            }
            while (i >= 0 && j < number.length) {
                number[i] = number[i] + carry;
                carry = number[i] / 10;
                number[i] %= 10;
                number[j] = number[i];
                i--;
                j++;
            }
            return number;
        }

        if (number[i] > number[j]) {
            while (i >= 0 && j < number.length) {
                number[j] = number[i];
                j++;
                i--;
            }
            return number;
        }

        carry = 0;
        if (n % 2 == 0) {
            i = mid - 1;
            carry = 1;
            j = mid;
        } else {
            number[mid] = (number[mid] + 1);
            carry = number[mid] / 10;
            number[mid] = number[mid] % 10;
            i = mid - 1;
            j = mid + 1;
        }
        while (i >= 0 && j < number.length) {
            number[i] = number[i] + carry;
            number[j] = number[i];
            carry = number[i] / 10;
            number[i] %= 10;
            i--;
            j++;
        }

        return number;
    }
}
