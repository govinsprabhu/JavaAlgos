package com.govind.algo.string;


import com.govind.util.arraylist.ArrayListUtils;
import com.govind.util.linkedlist.ListNode;

import java.math.BigInteger;
import java.util.*;

/**
 * Created by govindp on 9/13/2015.
 */
public class SampleString {
    public static void main(String[] args) {
        int[][] matrix = {{1, 1},
                {0, 0}};
        new SampleString().compressString();
    }

    public void compressString() {
        String string = "aaabbccc";
        char[] array = string.toCharArray();
        int temp = 0;
        for (int i = 0; i < string.length(); ) {
            int count = 0;
            int previous = i;
            while (i < string.length() && array[previous] == array[i]) {
                count++;
                i++;

            }

            array[temp] = array[i - count];
            array[temp + 1] = (char) (count + 48);
            temp += 2;
        }

        array[temp] = '\0';
        System.out.println(array);
    }

    public List<String> addOperators(String num, int target) {
        if (num == null || num.isEmpty()) {
            return null;
        }

        List<String> list = new ArrayList<>();
        char[] result = new char[num.length() + num.length() - 1];
        char[] operators = {'*', '+', '-'};
        addOperators(num, 0, result, target, 0, operators, list);
        return list;

    }

    public void nextSmallestPalindrom() {
        int[] number = {1, 5, 4, 3, 6, 7, 2};


        int i = 0;
        int j = number.length - 1;
        int mid = number.length / 2;
        if (number.length % 2 == 0) {
            i = mid - 1;
            j = mid + 1;
        } else {
            i = mid - 1;
            j = mid;
        }

        while (i >= 0 && number[i] == number[j]) {
            i--;
            j++;
        }

        boolean isCrossed = false;
        if (i < 0) {
            isCrossed = true;
        }


    }

    public void addOperators(String num, int index, char[] currentResult, int target, int currentTarget, char[] operators, List<String> result) {
        if (currentResult.length == index + 1) {
            char operator = currentResult[index - 1];
            currentResult[index] = num.charAt(index / 2);
            if (operate(currentTarget, operator, Integer.parseInt(String.valueOf(num.charAt(index / 2)))) == target) {
                result.add(String.valueOf(currentResult));
            }
            return;
        }

        currentResult[index] = num.charAt(index / 2);

        int currentNumber = Integer.parseInt(String.valueOf(num.charAt(index / 2)));
        if (index != 0) {
            currentTarget = operate(currentTarget, currentResult[index - 1], currentNumber);
        }
        for (int i = 0; i < operators.length; i++) {
            if (index == 0 && operators[i] == '*') {
                currentTarget = 1;
            }
            if (index == 2 && operators[i] == '*') {
                System.out.println();
            }
            currentResult[index + 1] = operators[i];

            if (index == 0) {

                addOperators(num, index + 2, currentResult, target, currentNumber, operators, result);
            } else {
                addOperators(num, index + 2, currentResult, target, currentTarget, operators, result);
            }
            currentResult[index + 1] = '\0';
        }
    }

    private int operate(int a, char operator, int b) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
        }
        return -1;
    }

    static int numberOfPaths(int[][] a, int M, int N) {
        int[][] dpTable = new int[M][N];
        for (int i = 0; i < N; i++) {
            if (a[0][i] == 0) {
                break;
            }
            dpTable[0][i] = a[0][i];
        }

        for (int i = 0; i < M; i++) {
            if (a[i][0] == 0) {
                break;
            }
            dpTable[i][0] = a[i][0];
        }

        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {
                if (a[i][j] != 0) {
                    dpTable[i][j] = (dpTable[i - 1][j] % 1000000007 + dpTable[i][j - 1] % 1000000007);
                }
            }
        }

        return dpTable[M - 1][N - 1] % 1000000007;
    }

    public void replaceWithCharacterCount() {
        String string = "aaabbccdeeabb";
        char[] chars = string.toCharArray();
        if (chars.length == 0 || chars.length == 1) {
            return;
        }
        for (int end = chars.length - 2, start = chars.length - 1; end >= 0; end--) {
            if (start != end && chars[end] != chars[end + 1]) {
                chars[end + 2] = (char) (start - end + 48);
                start = end;
            } else {
                //    chars[end] = chars[]
            }
        }


        System.out.println(chars);
    }

    class LinkedList {
        Character character;
        HashMap<Character, LinkedList> next;
        ArrayList<Character> values;

        public LinkedList(Character value) {
            this.character = value;
            next = new HashMap<>();
            values = new ArrayList<>();
        }
    }


    public void hierarchicalJson() {
        ArrayList<String> strings = new ArrayListUtils<>().getArrayList(new String[]{"a>b=1", "a>b=2", "a>c>d=3", "a>c>e=4"});

        LinkedList root = new LinkedList(strings.get(0).charAt(0));
        LinkedList backup = root;
        for (String s : strings) {
            root = backup;
            if (s.charAt(0) != root.character) {
                System.out.println("Not valid");
                return;
            }
            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) == '>' && i == s.length() - 1) {
                    System.out.println("Not valid");
                    return;
                }

                if (s.charAt(i) == '>') {
                    LinkedList linkedList;

                    if (root.next.containsKey(s.charAt(i + 1))) {
                        linkedList = root.next.get(s.charAt(i + 1));
                    } else {
                        linkedList = new LinkedList(s.charAt(i + 1));
                        root.next.put(s.charAt(i + 1), linkedList);
                    }


                    i++;
                    if (s.charAt(i + 1) == '>' && linkedList.values.size() >= 1) {
                        System.out.println("Not valid");
                        return;
                    }

                    root = linkedList;

                } else if (s.charAt(i) == '=' && i < s.length() - 1) {
                    root.values.add(s.charAt(i + 1));
                    i++;
                }
            }

        }
        SampleString.Result result = new SampleString.Result();
        getJson(backup, result);

        System.out.println(result.result + "\n}");

    }

    class Result {
        String result = "{\n";
    }

    private void getJson(LinkedList root, SampleString.Result result) {
        if (root.values.size() >= 1) {
            result.result += root.character + " ";
            if (root.values.size() == 1) {
                result.result += " : " + root.values.get(0) + " ,\n";
                return;
            }
            result.result += " [ ";
            for (int i = 0; i < root.values.size(); i++) {
                result.result += root.values.get(i) + ((root.values.size() == i + 1) ? "" : ", ");
            }

            result.result += " ] \n";
            return;
        }

        result.result += root.character + " : { \n";
        for (Character character : root.next.keySet()) {
            getJson(root.next.get(character), result);
        }

        result.result += " } " + "\n";
    }

    public void fullJustify() {
        ArrayList<String> words = new ArrayListUtils<>().
                getArrayList(new String[]{"am", "fasgoprn", "lvqsrjylg", "rzuslwan", "xlaui", "tnzegzuzn", "kuiwdc", "fofjkkkm", "ssqjig", "tcmejefj", "uixgzm", "lyuxeaxsg", "iqiyip", "msv", "uurcazjc", "earsrvrq", "qlq", "lxrtzkjpg", "jkxymjus", "mvornwza", "zty", "q", "nsecqphjy"});
        System.out.println(fullJustify2(words, 14));

    }

    public ArrayList<String> fullJustify2(ArrayList<String> a, int b) {

        ArrayList<String> resultArray = new ArrayList<>();

        int len;
        int k = 0;
        for (int i = 0; i < a.size(); i += k) {
            len = 0;
            k = 0;
            for (; i + k < a.size() && len + a.get(i + k).length() <= b - k; k++) {
                len += a.get(i + k).length();

            }

            String result = "";
            for (int j = i; j < i + k; j++) {

                result += a.get(j);

                int space = ((b - len) / (k == 1 ? 1 : (k - 1))) + (((b - len) % (k == 1 ? 1 : (k - 1)) > j - i) ? 1 : 0);

                if (i + k == a.size()) {
                    space = 1;
                }

                if (j + 1 == i + k) {
                    space = b - result.length();
                }


                while (space > 0) {
                    result += " ";
                    space--;
                }
            }

            if (i + k == a.size()) {
                int space = b - result.length();
                while (space > 0) {
                    result += " ";
                    space--;
                }
            }
            resultArray.add(result);

        }

        return resultArray;
    }

    public void countAndSay() {
        System.out.println(countAndSay1(4));
    }

    public String countAndSay1(int a) {

        String currentString = "1";
        for (int i = 1; i < a; i++) {
            Character previous = null;
            int count = 1;
            String result = "";

            for (int j = 0; j < currentString.length(); j++) {
                if (previous != null && previous != currentString.charAt(j)) {
                    result += String.valueOf(count) + previous;
                    count = 1;

                } else if (previous != null) {
                    count++;

                }
                previous = currentString.charAt(j);
            }

            currentString = result + String.valueOf(count) + currentString.charAt(currentString.length() - 1);
        }

        return currentString;
    }

    public void replaceStringWithSubStrin() {
        String original = "abababcdefababcdab";
        String replace = "ab";
        int start = 0;
        int currectUntil = 0;
        String result = "";
        for (int index = 0; index < original.length(); ) {
            if (original.charAt(index) == replace.charAt(0)) {
                int replaceIndex = 0;
                while (replaceIndex < replace.length() && index < original.length() && original.charAt(index) == replace.charAt(replaceIndex)) {
                    index++;
                    replaceIndex++;
                }
                if (replaceIndex == replace.length()) {
                    currectUntil = index;
                }


            } else {
                if (currectUntil != start) {
                    result += "X";
                    start = currectUntil;
                }
                result += original.charAt(index);

                index++;
            }

        }
        if (currectUntil != start) {
            result += "X";
            start = currectUntil;
        }
        System.out.println(result);
    }

    public static ArrayList<String> fullJustify1(ArrayList<String> a, int b) {
        ArrayList<String> arrayList = new ArrayList<>();
        int k = 0, l = 0;
        for (int i = 0; i < a.size(); i += k) {
            for (k = l = 0; k + i < a.size() && l + a.get(i + k).length() <= b - k; k++) {
                l += a.get(k + i).length();
            }
            String result = a.get(i);
            for (int j = 0; j < k - 1; j++) {
                if (i + k >= a.size()) {
                    result += " ";
                } else {
                    int space = (b - l) / (k - 1);
                    if (j < (b - l) % (k - 1)) {
                        space += (b - l) % (k - 1);
                    }
                    while (space > 0) {
                        result += " ";
                        space--;
                    }
                }
                result += a.get(i + j + 1);

            }
            int space = b - result.length();
            while (space > 0) {
                result += " ";
                space--;
            }
            arrayList.add(result);
        }
        return arrayList;
    }

    public static ArrayList<String> fullJustify(ArrayList<String> a, int b) {
        int i = 0;
        int k = 0, currentLength = 0;
        int remaining = 0;
        String currentString = null;
        ArrayList<String> arrayList = new ArrayList<>();
        for (; i < a.size(); i += k) {
            for (k = 0, currentLength = 0; i + k < a.size() && currentLength + a.get(i + k).length() <= b - k; k++) {
                currentLength += a.get(i + k).length();
            }

            currentString = a.get(i);
            remaining = b - currentLength;
            for (int j = i + 1; j < i + k; j++) {
                if (i + k >= a.size()) {
                    currentString += " ";
                } else {
                    int space = remaining % (k - 1) == 0 ? remaining / (k - 1) : currentString.length() == a.get(i).length() ? remaining / (k - 1) + 1 : remaining / (k - 1);
                    while (space > 0) {
                        currentString += " ";
                        space--;
                    }
                }
                currentString += a.get(j);

            }
            remaining = b - currentString.length();
            while (remaining > 0) {
                currentString += " ";
                remaining--;
            }
            arrayList.add(currentString);

        }

        return arrayList;
    }

    public static long reverse(long a) {
        long reverse = 0;
        for (int i = 0; i <= 31; i++) {
            reverse <<= 1;
            if (((1 << i) & a) != 0) {
                reverse |= 1;
            }
        }
        return reverse;
    }

    public static int numSetBits(long a) {
        int count = 0;

        while (a > 0) {
            int current = (int) a % 2;
            if (current == 1) {
                count++;
            }
            a /= 2;
        }

        return count;
    }

    public static String logestPalidrome(String string) {
        int max = Integer.MIN_VALUE;
        int startIndex = -1;
        for (int l = 1; l < string.length(); l++) {
            int i = l - 1;
            int j = l;
            while (i >= 0 && j < string.length() && string.charAt(i) == string.charAt(j)) {
                if (max < j - i + 1) {
                    max = j - i + 1;
                    startIndex = i;
                }
                i--;
                j++;
            }

            i = l - 1;
            j = l + 1;

            while (i >= 0 && j < string.length() && string.charAt(i) == string.charAt(j)) {
                if (max < j - i + 1) {
                    max = j - i + 1;
                    startIndex = i;
                }
                i--;
                j++;
            }


        }
        if (startIndex == -1) {
            return String.valueOf(string.charAt(0));
        }
        return string.substring(startIndex, startIndex + max);
    }

    public static ArrayList<String> prettyJson(String json) {
        ArrayList<String> arrayList = new ArrayList<>();
        int countSpace = 0;
        for (int i = 0; i < json.length() - 1; ) {
            String result = "";
            char next = 0;
            while (i < json.length() - 1) {
                char current = json.charAt(i);
                next = json.charAt(i + 1);
                if (result.isEmpty() && i != 0) {
                    for (int j = 0; j < countSpace; j++) {
                        result += '\t';
                    }
                }
                result += current;
                i++;
                if (current == '[' || current == '{') {
                    countSpace++;
                    break;
                }

                if ((current == ':' && (next == '{' || next == '[')) || current == ',') {
                    break;
                }
                if ((next == '}' || next == ']')) {
                    countSpace--;
                    break;
                }
            }
            arrayList.add(result);
            if (i == json.length() - 1) {
                arrayList.add(String.valueOf(next));
            }
        }
        return arrayList;
    }

    public static String convert(String a, int b) {
        if (b == 0 || b == 1) {
            return a;
        }
        String[] result = new String[b];
        for (int i = 0; i < result.length; i++) {
            result[i] = "";
        }
        int row = 0;
        int decider = 1;
        for (int i = 0; i < a.length(); i++) {
            result[row] += a.charAt(i);
            row = row + decider;
            if (row == b - 1) {
                decider = -1;
            } else if (row == 0) {
                decider = 1;
            }
        }
        String resultString = "";
        for (int i = 0; i < result.length; i++) {
            resultString += result[i];
        }
        return resultString;
    }


    public static String multiply(String a, String b) {
        String reverseA = reverse(a);
        String reverseB = reverse(b);
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < reverseB.length(); i++) {
            int currentI = Integer.parseInt(reverseB.substring(i, i + 1));
            int carry = 0;
            for (int j = 0; j < reverseA.length(); j++) {
                int currentJ = Integer.parseInt(reverseA.substring(j, j + 1));
                int currentValue = 0;

                if (arrayList.size() > (i + j)) {
                    currentValue = arrayList.get(i + j);
                    arrayList.set(i + j, (arrayList.get(i + j) + (currentI * currentJ + carry)) % 10);
                } else {
                    arrayList.add(i + j, (currentI * currentJ + carry) % 10);
                }

                carry = (currentValue + (currentI * currentJ + carry)) / 10;
            }
            if (carry > 0) {
                if (arrayList.size() > (i + a.length())) {
                    arrayList.set(i + a.length(), arrayList.get(i + a.length()) + carry);
                } else {
                    arrayList.add(i + a.length(), carry);
                }
            }
        }
        String multi = "";
        for (int i = arrayList.size() - 1; i >= 0; i--) {
            multi += arrayList.get(i);
        }
        boolean isDigit = false;
        String multiWithhoutZero = "";
        int i = 0;
        for (; i < multi.length(); i++) {
            if (multi.charAt(i) != '0') {
                break;
            }
        }
        if (i == multi.length()) {
            multi = "0";
            i = 0;
        }

        return multi.substring(i, multi.length());
    }


    private static String reverse(String number) {
        char[] reverse = number.toCharArray();
        for (int i = 0, j = number.length() - 1; i < j; i++, j--) {
            swap(i, j, reverse);
        }
        return new String(reverse);
    }

    public static boolean isPower(String number) {
        BigInteger integer = new BigInteger(number);
        BigInteger integer1 = new BigInteger("-1");
        BigInteger integer2 = new BigInteger("0");
        BigInteger result = integer.and(integer.add(integer1));

        if (result.equals(integer2)) {
            return true;
        }
        return false;
    }

    public static String addBinary(String a, String b) {
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        String sumNumber = "";

        while (i >= 0 || j >= 0) {
            int currentA = 0;
            if (i >= 0) {
                currentA = a.charAt(i) - '0';
            }

            int currentB = 0;
            if (j >= 0) {
                currentB = b.charAt(j) - '0';
            }

            //int sum = ;
            sumNumber = (currentA + currentB + carry) % 2 + sumNumber;
            carry = (currentA + currentB + carry) / 2;


            i--;
            j--;

        }
        if (carry > 0) {
            sumNumber = carry + sumNumber;
        }
        return sumNumber;
    }

    public static String toBinarySum(String a, String b) {
        String sumNumber = "";
        int carry = 0;
        try {
            int aBinary = isBinary(a);
            int bBinary = isBinary(b);

            while (aBinary > 0 || bBinary > 0) {
                int currentA = aBinary % 10;
                int currentB = bBinary % 10;
                int sum = currentA + currentB + carry;
                if (sum > 1) {
                    if (sum == 2)
                        sum = 0;
                    else
                        sum = 1;
                    carry = 1;
                } else {
                    carry = 0;
                }
                sumNumber = sum + sumNumber;

                if (aBinary > 0) {
                    aBinary /= 10;
                }
                if (bBinary > 0) {
                    bBinary /= 10;
                }
            }

            if (carry > 0) {
                sumNumber = carry + sumNumber;
            }
        } catch (Exception ex) {
            return "0";
        }
        return sumNumber;
    }

    private static int isBinary(String number) throws Exception {
        String backUp = number;
        for (int i = 0; i < number.length(); i++) {
            char current = number.charAt(i);
            if (current != '1' && current != '0') {
                return toBinary(Integer.parseInt(number));
            }
        }

        return Integer.valueOf(backUp);
    }

    private static int toBinary(int number) throws Exception {
        String binary = "";
        while (number > 0) {
            binary = number % 2 + binary;
            number /= 2;
        }
        return Integer.valueOf(binary);
    }

    public static String intToRoman(int number) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "I");
        map.put(2, "II");
        map.put(3, "III");
        map.put(4, "IV");
        map.put(5, "V");
        map.put(6, "VI");
        map.put(7, "VII");
        map.put(8, "VIII");
        map.put(9, "IX");
        map.put(10, "X");
        map.put(20, "XX");
        map.put(30, "XXX");
        map.put(40, "XL");
        map.put(50, "L");
        map.put(60, "LX");
        map.put(70, "LXX");
        map.put(80, "LXXX");
        map.put(90, "XC");
        map.put(100, "C");
        map.put(200, "CC");
        map.put(300, "CCC");
        map.put(400, "CD");
        map.put(500, "D");
        map.put(600, "DC");
        map.put(700, "DCC");
        map.put(800, "DCCC");
        map.put(900, "CM");
        map.put(1000, "M");
        map.put(2000, "MM");
        map.put(3000, "MMM");

        String roman = "";
        int count = 0;
        while (number > 0) {
            int current = number % 10;
            for (int i = 0; i < count; i++) {
                current *= 10;
            }
            if (map.containsKey(current)) {
                roman = map.get(current) + roman;
            }
            count++;
            number /= 10;
        }

        return roman;
    }

    public static int romanToInt(String number) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int digit = 0;

        int previous = 0;
        int i;
        for (i = number.length() - 1; i >= 0; i--) {
            if (map.containsKey(number.charAt(i))) {
                previous = map.get(number.charAt(i));
                break;
            }
        }

        i--;
        if (previous == 0) {
            return 0;
        }
        digit = previous;
        for (; i >= 0; i--) {
            if (map.containsKey(number.charAt(i))) {
                int current = map.get(number.charAt(i));
                if (previous <= current) {
                    digit += current;
                } else {
                    digit -= current;
                }
                previous = current;
            } else {
                return digit;
            }

        }
        return digit;
    }

    public static ArrayList<String> validIp(String a) {
        ArrayList<String> validIps = new ArrayList<>();
        if (a.length() > 12 || a.length() < 4) {
            return validIps;
        }

        for (int i = 1; i < 4; i++) {
            String first = a.substring(0, i);
            if (!isValid(first)) {
                continue;
            }
            for (int j = 1; j + i < a.length() && j < 4; j++) {
                String second = a.substring(i, i + j);
                if (!isValid(second)) {
                    continue;
                }
                for (int k = 1; k + i + j < a.length() && k < 4; k++) {
                    String third = a.substring(i + j, i + j + k);
                    String fourth = a.substring(i + j + k, a.length());
                    if (!isValid(third) || !isValid(fourth)) {
                        continue;
                    }
                    validIps.add(first + '.' + second + '.' + third + '.' + fourth);
                }
            }
        }
        return validIps;
    }


    private static boolean isValid(String number) {
        if (number.length() > 1 && number.charAt(0) == '0') {
            return false;
        }

        if (Integer.parseInt(number) <= 255 && Integer.parseInt(number) >= 0) {
            return true;
        }

        return false;
    }

    public static String CountAndSay(int number) {
        if (number == 0) {
            return "";
        }
        if (number == 1) {
            return "1";
        }

        String current = "1";
        String previous = current;
        for (int i = 2; i <= number; i++) {
            int count = 1;
            current = "";
            for (int j = 0; j < previous.length(); j++) {
                while (j < previous.length() - 1 && previous.charAt(j) == previous.charAt(j + 1)) {
                    count++;
                    j++;
                }
                current = current.concat(String.valueOf(count) + previous.charAt(j));
            }
            previous = current;
        }
        return current;
    }

    public static int version(String version1, String version2) {
        for (; version1 != version2; version1 = nextStr(version1), version2 = nextStr(version2)) {
            String current1 = currentStr(version1);
            String current2 = currentStr(version2);
            if (current1.length() > current2.length()) {
                return 1;
            } else if (current2.length() > current1.length()) {
                return -1;
            }
            return current1.compareTo(current2);
        }
        return 0;
    }

    private static String currentStr(String version1) {
        int zeroIndex = 0;
        for (int i = 0; i < version1.length(); i++) {
            if (version1.charAt(i) == '.') {
                return version1.substring(zeroIndex, i - zeroIndex);
            }
            if (version1.charAt(i) == '0' && i == zeroIndex) {
                zeroIndex++;
            }
        }

        return version1.substring(zeroIndex, version1.length() - zeroIndex);
    }

    private static String nextStr(String version) {
        for (int i = 0; i < version.length(); i++) {
            if (version.charAt(i) == '.') {
                return version.substring(i + 1);
            }
        }
        return version;
    }

    public static int atoi(String number) {
        String value = "";
        boolean isFirst = false;
        for (int i = 0; i < number.length(); i++) {
            if ((number.charAt(i) >= '0' && number.charAt(i) <= '9') || number.charAt(i) == '-' || number.charAt(i) == '+') {
                value += number.charAt(i);
                isFirst = true;
            } else if (isFirst || number.charAt(i) != ' ') {
                break;
            }
        }
        try {
            if (value.isEmpty()) {
                return 0;
            }
            if (number.charAt(0) == '-' && number.length() > 19) {
                return Integer.MIN_VALUE;
            }

            if (number.length() > 19) {
                return Integer.MAX_VALUE;
            }

            if (Long.valueOf(value) > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }

            if (Long.valueOf(value) < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            return Integer.valueOf(value);
        } catch (NumberFormatException ex) {
            //System.out.println();
            return 0;
        }
    }

    public static int compareVersion(String version1, String version2) {
        if (version1 == null || version2 == null) {
            return 0;
        }
        int i = 0, j = 0;

        while (i < version1.length() && j < version2.length()) {
            if (version1.charAt(i) == version2.charAt(j)) {
                i++;
                j++;
            } else if (version1.charAt(i) == '0') {
                i++;
            } else if (version2.charAt(j) == '0') {
                j++;
            } else {
                break;
            }
        }

        if (i == version1.length() && j == version2.length()) {
            return 0;
        }

        if (i == version1.length() && j != version2.length()) {
            boolean isNotZero = false;
            while (j < version2.length()) {
                if (version2.charAt(j) != '.' && version2.charAt(j) != '0') {
                    isNotZero = true;
                }
                j++;
            }
            if (isNotZero) {
                return -1;
            }
            return 0;
        }

        if (i != version1.length() && j == version2.length()) {
            boolean isNotZero = false;
            while (i < version1.length()) {
                if (version1.charAt(i) != '.' && version1.charAt(i) != '0') {
                    isNotZero = true;
                }
                i++;
            }
            if (isNotZero) {
                return 1;
            }
            return 0;
        }

        if ((version1.charAt(i) > version2.charAt(j))) {
            while (i < version1.length() && j < version2.length()) {
                if (version1.charAt(i) == '.' && version2.charAt(j) != '.') {
                    return -1;
                }
                if (version2.charAt(j) == '.') {
                    return 1;
                }
                i++;
                j++;
            }
            if (i == version1.length() && j != version2.length()) {
                if (version2.charAt(j) == '.') {
                    return 1;
                }
                return -1;
            }
            return 1;
        } else if (version1.charAt(i) < version2.charAt(j)) {
            while (i < version1.length() && j < version2.length()) {
                if (version2.charAt(j) == '.' && version1.charAt(i) != '.') {
                    return 1;
                }
                if (version1.charAt(i) == '.' && version2.charAt(i) != '.') {
                    return -1;
                }
                i++;
                j++;
            }
            if (i != version1.length() && j == version2.length()) {
                if (version1.charAt(i) == '.') {
                    return -1;
                }
                return 1;
            }
            return -1;
        }

        return 0;
    }

    public static int strstr(String main, String sub) {
        if (main == null || sub == null || main.isEmpty() || sub.isEmpty()) {
            return -1;
        }
        if (main.length() < sub.length()) {
            return -1;
        }

        for (int i = 0; i < main.length(); i++) {
            if (main.charAt(i) == sub.charAt(0)) {
                int back_i = i;
                int j = 1;
                for (j = 0; j < sub.length(); j++, i++) {
                    if (main.charAt(i) != sub.charAt(j)) {
                        break;
                    }
                }
                if (j == sub.length()) {
                    return back_i;
                }
                i = back_i;
            }
        }

        return -1;
    }

    public static String reverseSentence(String sentence) {
        int endIndex = sentence.length();
        String reversed = "";
        int end = 0;
        while (sentence.charAt(end) == ' ') {
            end++;

        }
        int i = sentence.length() - 1;
        while (i >= end) {
            if ((sentence.charAt(i) == ' ' && i == endIndex - 1)) {
                endIndex = i;
                i--;
                continue;
            }

            if (sentence.charAt(i) == ' ' || i == end) {
                if (i == end) {
                    i--;
                    reversed += sentence.substring(i + 1, endIndex);
                } else {
                    reversed += sentence.substring(i + 1, endIndex) + " ";
                }
                //arrayList.add(sentence.substring(endIndex,i));
                while (i >= end && sentence.charAt(i) == ' ') {
                    i--;
                }
                endIndex = i + 1;
            } else {
                i--;
            }
        }

        return new String(reversed) + "end";
    }

    private static void swap(int starting, int i, char[] reversed) {
        char temp = reversed[starting];
        reversed[starting] = reversed[i];
        reversed[i] = temp;
    }

    public static void reverse(int starting, int ending, char[] sentence) {
        for (int i = starting; i < (starting + ending) / 2; i++) {
            swap(i, ending - 1 - i + starting, sentence);
            //reversed[i] = sentence.charAt(sentence.length() - 1 - i);
        }

    }

    public static int lengthOfLastWord(final String a) {
        if (a == null || a.isEmpty()) {
            return 0;
        }

        int i = a.length() - 1;

        while (i >= 0 && a.charAt(i) == ' ') {
            i--;
        }
        int last = i;
        for (; i >= 0; i--) {
            if (a.charAt(i) == ' ') {
                break;
            }
        }

        return a.substring(i + 1, last + 1).length();
    }


    private static String countAndSay(int number) {
        if (number == 0) {
            return "";
        }
        if (number == 1) {
            return "1";
        }

        String previous = "1";
        String current = previous;

        for (int i = 2; i <= number; i++) {
            int count = 1;
            current = "";
            for (int j = 0; j < previous.length(); j++) {

                while (j < previous.length() - 1 && previous.charAt(j) == previous.charAt(j + 1)) {
                    count++;
                    j++;
                }
                current = current.concat(count + String.valueOf(previous.charAt(j)));
                //System.out.println(current);
            }
            previous = current;
        }
        return current;
    }

    private static String longestCommonPrefix(ArrayList<String> arrayList) {
        ArrayList<Character> set = new ArrayList<>();
        for (int i = 0; i < arrayList.get(0).length(); i++) {
            set.add(arrayList.get(0).charAt(i));
        }
        int minIndex = Integer.MAX_VALUE;
        for (int i = 1; i < arrayList.size(); i++) {
            //int length = 0;
            for (int j = 0; j < arrayList.get(i).length() && j < set.size(); j++) {
                if (!(set.get(j) == arrayList.get(i).charAt(j)) || (set.get(j) == arrayList.get(i).charAt(j) && j == arrayList.get(i).length() - 1)) {
                    if (set.get(j) == arrayList.get(i).charAt(j) && j == arrayList.get(i).length() - 1) {
                        j++;
                    }
                    minIndex = Math.min(j, minIndex);
                    break;
                }
            }
        }

        StringBuffer stringBuffer = new StringBuffer();
        Iterator iterator = set.iterator();
        int i = 0;
        while (iterator.hasNext() && i < minIndex) {
            stringBuffer.append(iterator.next());
            i++;
        }
        return new String(stringBuffer);
    }
}
