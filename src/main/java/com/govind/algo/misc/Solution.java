package com.govind.algo.misc;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by govindp on 11/5/2015.
 */
public class Solution {
    public static void main(String[] args) {
        //new Solution().petrolPump();
        new Solution().digitToDigitInWord();
    }




    public void digitToDigitInWord() {
        String[] ones = {"One", "Two", "Three", "Four", "Five",
                "Six", "Seven", "Eight", "Nine"};
        String[] teens = {"Eleven", "Twelve", "Thirteen",
                "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen",
                "Nineteen"};
        String[] tens = {"Ten", "Twenty", "Thirty", "Forty",
                "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        String[] bigs = {"Hundred", "Thousand"};
        int number = 567;
        String stringBuilder = "";
        if (number / 1000 > 0) {
            stringBuilder = numberLessThanThousand(number / 1000, ones, teens, tens) +" " + bigs[1];
        }
        number %= 1000;
        stringBuilder += " " +numberLessThanThousand(number, ones, teens, tens);

        System.out.println(stringBuilder);
        Math.random();
    }

    public String numberLessThanThousand(int number, String[] ones, String[] teens, String[] tens) {
        String string = "";
        if (number > 100) {
            string += ones[number / 100 - 1] + " Hundred";
        }
        number = number % 100;
        if (number < 20 && number > 10) {
            string += (!string.isEmpty() ? " And " : " ") + teens[number % 10 - 1];
        } else if (number >= 20 || number == 10) {
            string += (!string.isEmpty() ? " And " : " ") + tens[number / 10 - 1];
        }
        number = number % 10;
        return string + " " + (number != 0 ? ones[number - 1] : "");
    }


    public void spiralOrder() {
        int[][] matrix = {{1, 2, 3, 4, 100, 500},
                {5, 6, 7, 8, 200, 600},
                {9, 10, 11, 12, 300, 700},
                {13, 14, 15, 16, 400, 800}};

        int m = matrix.length;
        int n = matrix[0].length;
        int k = 0, l = 0;
        while (k < m && l < n) {
            for (int i = l; i < n; i++) {
                System.out.print(matrix[k][i] + " ");
            }
            k++;
            for (int i = k; i < m; i++) {
                System.out.print(matrix[i][n - 1] + " ");
            }
            n--;

            for (int i = n - 1; i >= l; i--) {
                System.out.print(matrix[m - 1][i] + " ");
            }
            m--;
            for (int i = m - 1; i >= k; i--) {
                System.out.print(matrix[i][l] + " ");
            }
            l++;
        }

    }

    public void nextSmallestPalindrome() {
        int[] number = {6, 5, 3, 5, 3, 8, 1};
        int n = number.length;
        int mid = n / 2;
        int i = mid - 1;
        int j = (((n & 1) == 1) ? mid + 1 : mid);
        while (i >= 0 && number[i] == number[j]) {
            i--;
            j++;
        }

        if (i < 0 || number[i] < number[j]) {
            i = mid - 1;
            int carry = 0;
            if ((n & 1) == 1) {
                number[mid] = number[mid] + 1;
                carry = number[mid] / 10;
                number[mid] %= 10;
                j = mid + 1;
            } else {
                j = mid;
                carry = 1;
            }
            while (i >= 0) {
                number[i] = number[i] + carry;
                carry = number[mid] / 10;
                number[mid] %= 10;
                number[j] = number[i];
                i--;
                j++;
            }
        } else {
            while (i >= 0) {
                number[j] = number[i];
                i--;
                j++;
            }
        }

        for (int k = 0; k < number.length; k++) {
            System.out.print(number[k]);
        }
    }

    public void maxSumSuchThatNoTwoElementAdjacent() {
        int[] array = {5, 5, 10, 40, 50, 35};
        int include = array[0];
        int exclude = 0;
        for (int i = 1; i < array.length; i++) {
            int excludeNew = Math.max(include, exclude);
            include = array[i] + exclude;
            exclude = excludeNew;
        }

        System.out.println(Math.max(include, exclude));
    }

    public void perfectRandom() {


    }

    public void printMajoriy() {
        int[] array = {2, 2, 1, 4, 5, 6, 2, 2};
        findCoordinates();
    }

    public void findCoordinates() {
        int[] array = {5, 6, 7, 9, 10, 3, 2, 4, 8, 1};
        ArrayList<Integer> arrayList = new ArrayList<>();

        System.out.println(findCoordinates(array, arrayList));
    }

    private ArrayList<Integer> findCoordinates(int[] array, ArrayList<Integer> arrayList) {
        Arrays.sort(array);
        for (int i = array.length - 1; i >= 2; i--) {
            if (array[i] < array[i - 1] + array[i - 2]) {
                return null;
            }
        }
        return null;
    }

    public int printMajority(int[] array) {
        int count = 1;
        int majority = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[majority] == array[i]) {
                count++;
            } else {
                count--;
            }

            if (count == 0) {
                majority = i;
                count = 1;
            }

        }

        return array[majority];
    }

    public void pascalsTriangle() {
        int n = 5;
        printPascalTriangle(5);
    }

    private void printPascalTriangle(int n) {
        for (int line = 1; line <= n; line++) {
            int C = 1;
            for (int i = 1; i <= line; i++) {
                System.out.print(C + " ");
                C = C * (line - i) / i;
            }
            System.out.println();
        }
    }

    public void mapToExcel() {
        int number = 702;
        String result = "";
        while (number > 0) {
            int first = number % 26;
            if (first == 0) {
                result = 'z' + result;
            } else
                result = (char) (first + 'a' - 1) + result;
            number = first == 0 ? number / 26 - 1 : number / 26;
        }

        System.out.println(result);
    }

    public void rotateArray() {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        int k = 4;
        for (int i = 0; i < gcd(a.length, k); i++) {
            int j = i;
            int temp = a[i];
            int l = 0;
            while (true) {
                l = j + k;
                if (l > a.length - 1) {
                    l -= a.length;
                }
                if (l == i) {
                    break;
                }

                a[j] = a[l];
                j = l;
            }

            a[j] = temp;
        }
        for (int element : a) {
            System.out.print(element + " ");
        }
    }

    private int gcd(int length, int k) {
        if (length % k == 0) {
            return k;
        }

        return gcd(k, length % k);
    }


    public void petrolPump() {
        PetrolPump[] petrolPumps = {new PetrolPump(3, 4), new PetrolPump(6, 7), new PetrolPump(7, 3), new PetrolPump(4, 5)};
        int start = 0;
        int end = 1;
        int currentDistance = petrolPumps[0].pertrol - petrolPumps[0].distance;
        while (start != end || currentDistance < 0) {
            while (currentDistance < 0 && start != end) {
                currentDistance = currentDistance - (petrolPumps[start].pertrol - petrolPumps[start].distance);
                start = (start + 1) % petrolPumps.length;
                if (start == 0) {
                    System.out.println(-1);
                }
            }

            currentDistance += (petrolPumps[end].pertrol - petrolPumps[end].distance);
            end = (end + 1) % petrolPumps.length;
        }

        System.out.println(start);
    }


    public void power() {
        int a = 2;
        int b = 6;
        int result = 1;
        while (b > 0) {
            if (b % 2 == 1) {
                result = (result * a);
                b--;
            } else {
                a = a * a;
                b /= 2;
            }
        }
        System.out.println(result);
    }

    class PetrolPump {
        int pertrol;
        int distance;

        public PetrolPump(int pertrol, int distance) {
            this.pertrol = pertrol;
            this.distance = distance;
        }
    }

    public String toRoman(int number) {
        String result = "";
        int count = 1;
        while (number != 0) {
            int first = number % 10;
            first *= count;
            result = getRoman(first) + result;
            number /= 10;
            count *= 10;
        }
        return result;
    }

    public void magicNumber(int k) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(5);
        System.out.println(arrayList.get(0));
        for (int i = 1; i < k; i++) {
            int last = arrayList.get(arrayList.size() - 1);
            int next = last * 5;
            System.out.println(next);
            for (int j = 0; j < arrayList.size() && i + j < k; j++, i++) {
                System.out.println(next + arrayList.get(j));
            }
            arrayList.add(next);
        }
    }

    public String getRoman(int number) {
        switch (number) {
            case 1:
                return "I";

            case 2:
                return "II";
            case 3:
                return "III";
            case 4:
                return "IV";
            case 5:
                return "V";
            case 6:
                return "VI";
            case 7:
                return "VII";
            case 8:
                return "VIII";
            case 9:
                return "IX";
            case 10:
                return "X";
            case 20:
                return "XX";
            case 50:
                return "L";

        }
        return "0";
    }

    public boolean robotIsInCircular(String direction) {
        int dir = 0;
        int x = 0, y = 0;
        for (int i = 0; i < direction.length(); i++) {
            char instruction = direction.charAt(i);

            if (instruction == 'R') {
                dir = ((dir + 1) & 3);

            } else if (instruction == 'L') {
                dir = ((4 + dir - 1) & 3);
            } else {
                switch (dir) {
                    case 0:
                        y++;
                        break;
                    case 1:
                        x++;
                        break;
                    case 2:
                        y--;
                        break;
                    case 3:
                        x--;
                        break;
                }
            }
        }
        return (x == 0 && y == 0);
    }
}
