package com.govind.algo.platform.interviewbit.arrays;

import com.govind.util.arraylist.ArrayListUtils;

import java.math.BigInteger;
import java.util.*;


/**
 * Created by 609600403 on 02/03/2016.
 */
public class Sample {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayListUtils<Integer>().getArrayList(new Integer[]{1, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1});
        ArrayList<Integer> arrayList2 = new ArrayListUtils<Integer>().getArrayList(new Integer[]{0, 1,1,0,1,0,1});
        ArrayList<Integer> arrayList3 = new ArrayListUtils<Integer>().getArrayList(new Integer[]{10, 15});
        ArrayList<Integer> arrayList4 = new ArrayListUtils<Integer>().getArrayList(new Integer[]{1111, 2222, 3333, 4444, 5555});
        ArrayList<Integer> arrayList5 = new ArrayListUtils<Integer>().getArrayList(new Integer[]{-11111, 0, -1, 5, 6, 1111, 2222, 1111, 2222, 3333, 4444, 5555, 23, 30, 34, 50, 10, 11, 16, 20,});
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        result.add(arrayList);
        result.add(arrayList2);
        result.add(arrayList3);
        result.add(arrayList4);
        result.add(arrayList5);
        //System.out.println(arrayList.size());
        new Sample().testSpiralOrder();
    }

    public ArrayList<Integer> spiralOrder(final List<ArrayList<Integer>> a) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        // Populate result;
        int l = 0, m = a.get(0).size() - 1, n = a.size() - 1, o = 0;

        int M = a.size();
        int N = a.get(0).size();

        while (l <= n && o <= m) {
            for (int i = o; i <= m; i++) {
                result.add(a.get(l).get(i));
            }
            l++;
            for (int i = l; i <= n; i++) {
                result.add(a.get(i).get(m));
            }
            m--;
            if (l <= n)
                for (int i = m; i >= o; i--) {
                    result.add(a.get(n).get(i));
                }

            n--;
            if (o <= m)
                for (int i = n; i >= l; i--) {
                    result.add(a.get(i).get(o));
                }
            o++;
        }


        return result;
    }



    public ArrayList<Integer> maxone(ArrayList<Integer> a, int b) {
        int start = 0;
        int maxStart = 0;
        int maxLength = 0;
        int count = b;
        for (int end = 0; end < a.size(); end++) {
            if (a.get(end) == 0) {
                count--;
            }
            if (count < 0) {
                if (maxLength < (end - start)) {
                    maxLength = end - start;
                    maxStart = start;
                }
                while (a.get(start) != 0) {
                    start++;
                }
                count++;
                start++;
            }
        }
        if  (count >= 0){
            if (maxLength < a.size() - start){
                maxStart = start;
                maxLength = a.size() - start;
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = maxStart; i < maxStart + maxLength; i++) {
            result.add(i);
        }

        return result;
    }


    public int minimize(final List<Integer> a, final List<Integer> b, final List<Integer> c) {
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0, j = 0, k = 0; i < a.size() && j < b.size() & k < c.size(); ) {
            minDiff = Math.min(Math.max(Math.abs(a.get(i) - b.get(j)), Math.max(Math.abs(b.get(j) - c.get(k)), Math.abs(c.get(k) - a.get(i)))), minDiff);
            if (Math.abs(a.get(i) - b.get(j)) >= Math.abs(b.get(j) - c.get(k)) && Math.abs(a.get(i) - b.get(j)) >= Math.abs(c.get(k) - a.get(i))) {
                if (a.get(i) < b.get(j)) {
                    i++;
                } else {
                    j++;
                }
            } else if (Math.abs(b.get(j) - c.get(k)) >= Math.abs(a.get(i) - b.get(j)) && Math.abs(b.get(j) - c.get(k)) >= Math.abs(c.get(k) - a.get(i))) {
                if (b.get(j) < c.get(k)) {
                    j++;
                } else {
                    k++;
                }
            } else {
                if (c.get(k) < a.get(i)) {
                    k++;
                } else {
                    i++;
                }
            }
        }

        return minDiff;
    }


    public int maxArea(ArrayList<Integer> a) {
        long maxArea = 0;
        for (int left = 0, right = a.size() - 1; left < right; ) {
            maxArea = Math.max(maxArea, Math.min(a.get(left), a.get(right)) * (right - left));
            if (a.get(left) < a.get(right)) {
                left++;
            } else {
                right--;
            }

        }

        return (int) maxArea;
    }

    public void merge(ArrayList<Integer> a, ArrayList<Integer> b) {
        ArrayList<Integer> result = new ArrayList<>();
        int i, j;
        for (i = 0, j = 0; i < a.size() && j < b.size(); ) {
            if (a.get(i) <= b.get(j)) {
                result.add(a.get(i));
                i++;
            } else {
                result.add(b.get(j));
                j++;
            }
        }
        while (i < a.size()) {
            result.add(a.get(i++));

        }

        while (j < b.size()) {
            result.add(b.get(j++));
        }
        a.clear();
        for (int k = 0; k < result.size(); k++) {
            a.add(result.get(k));
        }
    }

    public ArrayList<Integer> intersect(final List<Integer> a, final List<Integer> b) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0, j = 0; i < a.size() && j < b.size(); ) {
            if (a.get(i).compareTo(b.get(j)) == 0) {
                result.add(a.get(i));
                i++;
                j++;
            } else if (a.get(i) < b.get(j)) {
                i++;
            } else {
                j++;
            }
        }

        return result;
    }


    public void sortColors(ArrayList<Integer> a) {
        int[] countArray = new int[3];
        for (int i = 0; i < a.size(); i++) {
            countArray[a.get(i)]++;
        }

        int count = 0;
        for (int i = 0; i < countArray.length; i++) {
            for (int j = 0; j < countArray[i]; j++) {
                a.set(count++, i);
            }
        }
        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i));
        }
    }

    public int removeElement(ArrayList<Integer> a, int b) {
        int count = 0;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).compareTo(b) == 0) {
                continue;
            }
            a.set(count, a.get(i));
            count++;
        }
        return count;
    }

    public int removeDuplicates(ArrayList<Integer> a) {
        int index = 0;
        if (a.isEmpty() || a.size() < 2) {
            return a.size();
        }
        for (int i = 0; i < a.size() - 1; i++) {
            if (i < a.size() - 2 && a.get(i).compareTo(a.get(i + 1)) == 0 && a.get(i + 2).compareTo(a.get(i + 1)) == 0) {
                continue;
            }
            a.set(index++, a.get(i));
        }

        for (int i = 0; i < index; i++) {
            System.out.print(a.get(i) + " ");
        }


        return index;
    }

    public int diffPossible(ArrayList<Integer> a, int b) {
        int start = 0;
        int end = 1;
        while (end < a.size() && start < end) {
            int diff = a.get(end) - a.get(start);
            if (diff == b) {
                return 1;
            }
            if (end < a.size() && diff < b) {
                end++;
            } else if (end == a.size() - 1 && diff < b) {
                return 0;
            } else if (diff > b) {
                start++;
            }
            if (end < a.size() && start == end) {
                end++;
            }
        }

        return 0;
    }


    public ArrayList<ArrayList<Integer>> threeSum(ArrayList<Integer> a) {
        Collections.sort(a);
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        Set<ArrayList<Integer>> resultSet = new HashSet<>();
        for (int i = 0; i < a.size() - 2; i++) {
            if (a.get(i) > 0) {
                continue;
            }
            for (int j = i + 1, k = a.size() - 1; j < k; ) {
                int currentSum = a.get(j) + a.get(k);
                if (a.get(i) == (a.get(j) + a.get(k)) * -1) {
                    ArrayList<Integer> currentResult = new ArrayList<>();
                    currentResult.add(a.get(i));
                    currentResult.add(a.get(j));
                    currentResult.add(a.get(k));
                    resultSet.add(currentResult);
                }
                if (currentSum * -1 > a.get(i)) {
                    j++;
                } else {
                    k--;
                }

            }
        }

        for (ArrayList<Integer> arrayList : resultSet) {
            result.add(arrayList);
        }
        return result;
    }

    public int threeSumClosest(ArrayList<Integer> a, int b) {
        Collections.sort(a);
        int minDifference = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0; i < a.size() - 2; i++) {
            int diff = b - a.get(i);
            for (int j = i + 1, k = a.size() - 1; j < k; ) {
                if (minDifference > Math.abs(b - (a.get(j) + a.get(k) + a.get(i)))) {
                    minDifference = Math.abs(b - (a.get(j) + a.get(k) + a.get(i)));
                    sum = a.get(i) + a.get(k) + a.get(j);
                }
                if (a.get(j) + a.get(k) <= diff) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return sum;

    }


    public int singleNumber(final List<Integer> a) {
        int twos = 0;
        int ones = 0;
        int commons_in_third = 0;
        for (int i = 0; i < a.size(); i++) {
            twos |= (ones & a.get(i));
            ones ^= a.get(i);
            commons_in_third = ~(ones & twos);
            ones &= commons_in_third;
            twos &= commons_in_third;
        }
        return ones;
    }


    public int cntBits(ArrayList<Integer> A) {
        long result = 0;
        int module = (int) (Math.pow(10, 9) + 7);
        for (int j = 0; j < 31; j++) {
            int countOnes = 0;
            for (int i = 0; i < A.size(); i++) {
                countOnes += ((A.get(i) >> j) & 1);
            }

            result += (2 * countOnes * (A.size() - countOnes)) % (module);
        }

        return (int) result;
    }


    public void toBinary(int a) {
        String result = "";
        for (int i = 0; i <= 31; i++) {
            result = ((a >> i) & 1) + result;

        }
        System.out.println(result);
    }

    public int divide(int dividend, int divisor) {
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }
        boolean isDividendNeg = false;
        boolean isDivisorNeg = false;

        long dividendtNew = dividend;
        if (((dividend >> 31) & 1) == 1) {
            dividendtNew = 0 - dividendtNew;
            isDividendNeg = true;
        }

        if (((divisor >> 31) & 1) == 1) {
            divisor = 0 - divisor;
            isDivisorNeg = true;
        }


        if (dividendtNew < divisor) {
            return 0;
        }

        long quotient = 1;
        long currentDivisor = divisor;
        long totalQuotient = 0;
        while (true) {
            if (dividendtNew >= (currentDivisor << 1)) {
                quotient <<= 1;
                currentDivisor <<= 1;
                if (dividendtNew == currentDivisor) {
                    totalQuotient += quotient;
                    break;
                }
            } else {
                dividendtNew -= currentDivisor;
                currentDivisor = divisor;
                totalQuotient += quotient;
                if (dividendtNew <= divisor) {
                    totalQuotient += (dividendtNew == divisor) ? 1 : 0;
                    break;
                }
                quotient = 1;
            }
        }

        if (!(isDividendNeg ^ isDivisorNeg)) {
            return totalQuotient > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) totalQuotient;
        }

        return (int) (0 - totalQuotient);
    }

    /*public int singleNumber(final List<Integer> a) {
        int result = 0;
        for (int i = 0; i < a.size(); i++) {
            result ^= a.get(i);
        }

        return result;
    }*/

    public long reverse(long a) {
        long reverse = 0;
        int count = 32;
        while (count > 0) {
            reverse <<= 1;
            reverse += (a & 1);
            a >>= 1;
            count--;
        }

        return reverse;
    }

    public int numSetBits(long a) {
        int countOnes = 0;
        while (a > 0) {
            countOnes += (a & 1);
            a >>= 1;
        }
        return countOnes;
    }

    public String longestPalindrome(String a) {
        int maxStart = 0;
        int maxLength = 1;
        for (int i = 1; i < a.length(); i++) {
            int start = i;
            int end = i;
            while (start > 0 && end < a.length() - 1 && a.charAt(start - 1) == a.charAt(end + 1)) {
                start--;
                end++;
            }

            if (end - start + 1 > maxLength) {
                maxLength = end - start + 1;
                maxStart = start;
            }

            start = i;
            end = i - 1;
            while (start > 0 && end < a.length() - 1 && a.charAt(start - 1) == a.charAt(end + 1)) {
                start--;
                end++;
            }

            if (end - start + 1 > maxLength) {
                maxLength = end - start + 1;
                maxStart = start;
            }
        }

        return a.substring(maxStart, maxStart + maxLength);
    }


    public int logestPalidromicSubString(String string) {
        int[][] dp = new int[string.length()][string.length()];
        int maxLength = 0;
        for (int i = 0; i < string.length(); i++) {
            for (int j = 0; j < string.length() - i; j++) {
                int k = i + j;
                if (j == k) {
                    dp[j][k] = 1;
                } else {
                    if (string.charAt(j) == string.charAt(k)) {
                        dp[j][k] = dp[j + 1][k - 1] + dp[j][j] + dp[k][k];
                    } else {
                        dp[j][k] = dp[j + 1][k - 1];
                    }
                    maxLength = Math.max(maxLength, dp[j][k]);
                }
            }
        }

        return maxLength;
    }


    public int isNumber(final String a) {
        int i = 0;
        if (a.charAt(0) == '-' || a.charAt(0) == '+') {
            i++;
        }

        while (i < a.length() && a.charAt(i) == ' ') {
            i++;
        }

        if (i == a.length()) {
            return 0;
        }
        int j = a.length() - 1;
        while (a.charAt(j) == ' ') {
            j--;
        }

        for (; i <= j; i++) {
            if (a.charAt(i) == '.') {
                return checkFloatingPoint(a.substring(i + 1, j + 1));
            }
            if (a.charAt(i) == 'e') {
                return checkExponential(a.substring(i + 1, j + 1));
            }
            if (a.charAt(i) < '0' || a.charAt(i) > '9') {
                return 0;
            }
        }

        return 1;
    }

    private int checkExponential(String substring) {
        if (substring.isEmpty() || substring == null) {
            return 0;
        }
        int i = 0;
        if (substring.charAt(0) == '-' || substring.charAt(0) == '+') {
            i++;
        }

        for (; i < substring.length(); i++) {
            if (substring.charAt(i) < '0' || substring.charAt(i) > '9') {
                return 0;
            }
        }

        return 1;
    }

    private int checkFloatingPoint(String substring) {
        if (substring.isEmpty() || substring == null || substring.charAt(0) == 'e') {
            return 0;
        }
        for (int i = 0; i < substring.length(); i++) {
            if (substring.charAt(i) == 'e') {
                return checkExponential(substring.substring(i + 1));
            }
            if (substring.charAt(i) < '0' || substring.charAt(i) > '9') {
                return 0;
            }
        }

        return 1;
    }


    public int compareVersion(String a, String b) {
        long numberA = 0;
        long numberB = 0;
        for (int i = 0; i < Math.min(a.length(), b.length()); i++) {
            if (a.charAt(i) == '.' && b.charAt(i) == '.') {
                if (numberA > numberB) {
                    return 1;
                } else if (numberB > numberA) {
                    return -1;
                }
            }
            if (a.charAt(i) != '.')
                numberA = a.charAt(i) + numberA * 10;
            if (b.charAt(i) == '.')
                numberB = a.charAt(i) + numberB * 10;
        }

        return 0;
    }

    public int atoi(final String a) {
        long result = 0;
        int i = 0;
        while (a.charAt(i) == ' ') {
            i++;
        }

        boolean isNegative = false;
        if (a.charAt(i) == '-') {
            isNegative = true;
            i++;
        } else if (a.charAt(i) == '+') {
            i++;
        }

        for (; i < a.length(); i++) {
            if (a.charAt(i) < '0' || a.charAt(i) > '9') {
                break;
            }

            if ((a.charAt(i) - '0') + result * 10 >= Integer.MAX_VALUE) {
                if (isNegative && Integer.MAX_VALUE == (a.charAt(i) - '0') + result * 10) {
                    return Integer.MAX_VALUE * -1;
                }

                return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }

            result = (a.charAt(i) - '0') + result * 10;
        }


        return (int) (isNegative ? result * -1 : result);
    }

    public ArrayList<String> prettyJSON(String a) {
        ArrayList<String> result = new ArrayList<>();
        int numberOfTabs = 0;
        String stringResult = "";
        for (int i = 0; i < a.length(); i++) {
            if (stringResult == "")
                for (int j = 0; j < numberOfTabs; j++) {
                    stringResult += "\t";
                }
            stringResult += a.charAt(i);
            if (a.charAt(i) == '{' || a.charAt(i) == '[') {
                result.add(stringResult);
                numberOfTabs++;
                stringResult = "";
            } else if ((a.charAt(i) == ':' && (a.charAt(i + 1) == '{' || a.charAt(i + 1) == '[')) || a.charAt(i) == ',') {
                result.add(stringResult);
                stringResult = "";
            } else if (((a.charAt(i) != '}' && a.charAt(i) != ']') && (a.charAt(i + 1) == '}' || a.charAt(i + 1) == ']')) || (a.charAt(i) == '}' && a.length() - 1 > i && a.charAt(i + 1) != ',') || (a.charAt(i) == ']' && a.length() - 1 > i && a.charAt(i + 1) != ',')) {
                result.add(stringResult);
                numberOfTabs--;
                stringResult = "";
            } else if (i == a.length() - 1) {
                result.add(stringResult);
            }
        }

        return result;
    }

    public String convert(String a, int b) {
        ArrayList<ArrayList<Character>> zigzag = new ArrayList<>();
        for (int i = 0; i < b; i++) {
            ArrayList<Character> newArrayList = new ArrayList<>();
            zigzag.add(newArrayList);
        }

        int count = 0;
        int step = 1;
        for (int i = 0; i < a.length(); i++) {
            zigzag.get(count % b).add(a.charAt(i));
            if (count == 0) {
                step = 1;
            }
            if (count == b - 1) {
                step = -1;
            }
            count += step;
        }

        String result = "";
        for (int i = 0; i < zigzag.size(); i++) {
            for (int j = 0; j < zigzag.get(i).size(); j++) {
                result += zigzag.get(i).get(j);
            }
        }

        return result;
    }

    public ArrayList<String> fullJustify(ArrayList<String> a, int b) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < a.size(); ) {
            int currentLength = 0;
            int numberOfWords = 0;
            for (; i < a.size() && currentLength + a.get(i).length() <= b - numberOfWords; i++) {
                currentLength += a.get(i).length();
                numberOfWords++;
            }

            String word = "";
            int freeSpace = b - currentLength;
            for (int j = i - numberOfWords; j < i; j++) {
                word += a.get(j);
                int extraSpacePerWord;
                if (i != a.size()) {
                    extraSpacePerWord = freeSpace / (numberOfWords == 1 ? 1 : numberOfWords - 1);
                    if (numberOfWords != 1) {
                        extraSpacePerWord += ((freeSpace % (numberOfWords - 1)) >= 1 ? 1 : 0);
                    }
                } else {
                    extraSpacePerWord = (j == i - 1 ? freeSpace / (numberOfWords == 1 ? 1 : numberOfWords - 1) : 1);
                }
                for (int k = 0; k < extraSpacePerWord; k++) {
                    word += " ";
                }

                freeSpace -= extraSpacePerWord;
                numberOfWords--;
            }
            result.add(word);
        }

        return result;
    }

    public String multiply(String a, String b) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = a.length() - 1, shift = 0; i >= 0; i--, shift++) {
            int currentMul = a.charAt(i) - '0';
            int carry = 0;
            int indexToBeAdded = 0;
            for (int j = b.length() - 1, index = 0; j >= 0; j--, index++) {
                indexToBeAdded = shift + index;
                int currentResult = (b.charAt(j) - '0') * currentMul + carry;
                carry = currentResult / 10;
                currentResult %= 10;
                if (result.size() <= indexToBeAdded)
                    result.add(currentResult);
                else {
                    currentResult = currentResult + result.get(indexToBeAdded);
                    carry = (carry * 10 + currentResult) / 10;
                    currentResult %= 10;
                    result.set(indexToBeAdded, currentResult);
                }
            }
            indexToBeAdded = shift + b.length();
            if (carry >= 1) {
                if (result.size() >= indexToBeAdded)
                    result.add(carry);
                else {
                    carry = (carry + result.get(indexToBeAdded)) / 10;
                    result.set(indexToBeAdded, result.get(indexToBeAdded) % 10);
                    if (carry == 1)
                        result.add(carry);
                }
            }
        }

        String finalResult = "";
        for (int i = 0; i < result.size(); i++) {
            finalResult = result.get(i) + finalResult;
        }

        return finalResult;
    }


    public int power(String a) {
        if (a == "1") {
            return 0;
        }
        BigInteger bigInteger = new BigInteger(a);
        BigInteger bigInteger1 = bigInteger.subtract(BigInteger.ONE);
        if ((bigInteger.and(bigInteger1)).equals(BigInteger.ZERO)) {
            return 1;
        }
        return 0;
    }


    public String addBinary(String a, String b) {
        String max = a.length() > b.length() ? a : b;
        String min = a.length() > b.length() ? b : a;
        int diff = max.length() - min.length();
        int carry = 0;
        String result = "";
        for (int i = min.length() - 1; i >= 0; i--) {
            result = String.valueOf((max.charAt(diff + i) - '0' + min.charAt(i) - '0' + carry) % 2) + result;
            carry = (min.charAt(i) - 48 + max.charAt(diff + i) - 48 + carry) / 2;
        }

        for (int i = diff - 1; i >= 0; i--) {
            result = ((max.charAt(i) - 48 + carry) % 2 + result);
            carry = (max.charAt(i) - 48 + carry) / 2;
        }

        if (carry == 1)
            result = carry + result;
        return result;
    }


/*
    public void sendMail(){
        // Recipient's email ID needs to be mentioned.
        String to = "govind.prabhu@bt.com";

        // Sender's email ID needs to be mentioned
        String from = "sample@bt.com";

        // Assuming you are sending email from localhost
        String host = "localhost";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try{
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Send the actual HTML message, as big as you like
            message.setContent("<h1>This is actual message</h1>", "text/html" );

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
*/

    public String intToRoman(int a) {
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
        int aBack = a;
        int numberOfDigit = 0;
        while (a > 0) {
            a /= 10;
            numberOfDigit++;
        }

        a = aBack;
        String result = "";
        while (a > 0) {
            int base = (int) Math.pow(10, numberOfDigit - 1);
            int quotient = a / base;
            numberOfDigit--;
            if (quotient == 0) {
                continue;
            }
            int key = quotient * base;
            result += map.get(key);
            a %= base;
        }
        return result;
    }

    public int romanToInt(String a) {
        if (a == null || a.isEmpty()) {
            return 0;
        }

        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int number = 0;
        int previous = 0;
        for (int i = a.length() - 1; i >= 0; i--) {
            int current = map.get(a.charAt(i));
            if (current < previous) {
                number -= current;
            } else {
                number += current;
            }
            previous = current;
        }

        return number;
    }

    public int strStr(final String haystack, final String needle) {
        if (haystack.isEmpty() || needle.isEmpty()) {
            return -1;
        }

        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                int j = 0;
                while ((i < haystack.length() && j < needle.length()) && haystack.charAt(i) == needle.charAt(j)) {
                    i++;
                    j++;
                }
                if (j == needle.length()) {
                    return i - j;
                }
                i = i - j;


            }
        }
        return -1;
    }

    public String reverseWords(String a) {
        String reversed = "";
        for (int l = a.length() - 1; l >= 0; l--) {
            reversed += a.charAt(l);
        }

        String result = "";

        int i = 0;

        int startingIndexOfWord = i;
        for (; i < reversed.length(); ) {
            if (reversed.charAt(i) == ' ' || i == reversed.length() - 1) {
                if (i == reversed.length() - 1) {
                    result += " " + reverse(startingIndexOfWord, i, reversed);
                    i++;
                } else
                    result += reverse(startingIndexOfWord, i, reversed);
                while ((i < reversed.length() && reversed.charAt(i) == ' ')) {
                    i++;
                }
                startingIndexOfWord = i;
            } else {
                i++;
            }
        }

        i = 0;
        while (i < result.length() && result.charAt(i) == ' ') {
            i++;
        }

        return result.substring(i, result.length());

    }

    private String reverse(int startingIndexOfWord, int i, String reversed) {
        String result = "";
        for (int j = i; j >= startingIndexOfWord; j--) {
            result += reversed.charAt(j);
        }


        return result;
    }

    public int lengthOfLastWord(final String a) {
        int length = 0;
        int i = a.length() - 1;
        for (; i >= 0; i--) {
            if (a.charAt(i) == ' ') {
                continue;
            } else {
                break;
            }
        }


        for (; i >= 0; i--) {
            if (a.charAt(i) == ' ') {
                return length;
            }
            length++;
        }

        return length;
    }

    public String countAndSay(int a) {
        String currentString = "1";
        while (a > 1) {
            int count = 1;
            String nextResult = "";
            char character = currentString.charAt(0);
            for (int i = 1; i < currentString.length(); i++) {
                if (character == currentString.charAt(i)) {
                    count++;
                } else {
                    nextResult += String.valueOf(count) + String.valueOf(character);
                    count = 1;
                    character = currentString.charAt(i);
                }
            }
            nextResult += String.valueOf(count) + String.valueOf(character);
            currentString = nextResult;
            a--;
        }

        return currentString;
    }

    public String longestCommonPrefix(ArrayList<String> a) {
        for (int i = 0; i < a.get(0).length(); i++) {
            char compare = a.get(0).charAt(i);
            for (int j = 0; j < a.size(); j++) {
                if (i >= a.get(j).length() || (a.get(j).charAt(i) != compare)) {
                    return a.get(j).substring(0, i);
                }
            }
        }
        return a.get(0);
    }

    public int isPalindrome(String a) {
        a = a.toLowerCase();
        for (int i = 0, l = a.length() - 1; i < l; ) {
            if (!(Character.isLetter(a.charAt(i)) || Character.isDigit(a.charAt(i)))) {
                i++;
            } else if (!(Character.isLetter(a.charAt(l)) || Character.isDigit(a.charAt(l)))) {
                l--;
            } else if (a.charAt(i) == a.charAt(l)) {
                i++;
                l--;
            } else {
                return 0;
            }

        }

        return 1;
    }


    public int search(final List<Integer> a, int b) {
        int l = 0;
        int h = a.size() - 1;
        while (l <= h) {
            int mid = l + (h - l) / 2;
            if (a.get(mid) == b) {
                return mid;
            }
            if (a.get(l) < a.get(mid) && (a.get(mid) < b || a.get(l) > b)) {
                l = mid + 1;
            } else if (a.get(mid) < a.get(h) && (a.get(mid) > b || a.get(h) < b)) {
                h = mid - 1;
            } else if (a.get(mid) < b) {
                l = mid + 1;
            } else {
                h = mid - 1;
            }
        }

        return -1;
    }

    public int books(ArrayList<Integer> c, int b) {
        int l = 1;
        int h = 0;
        long maxPages = 0;
        for (int i = 0; i < c.size(); i++) {
            h += c.get(i);
            maxPages = Math.max(maxPages, c.get(i));
        }


        int minPages = h;
        while (l <= h) {
            int mid = l + (h - l) / 2;
            if (getMiddle(c, b, mid, maxPages)) {
                minPages = mid;
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return minPages;
    }


    private boolean getMiddle(ArrayList<Integer> c, int k, int mid, long maxBlock) {
        if (mid < maxBlock) {
            return false;
        }

        int currentBlocks = 0;
        int count = 1;
        for (int i = 0; i < c.size(); i++) {
            if (mid >= c.get(i) + currentBlocks) {
                currentBlocks = currentBlocks + c.get(i);
            } else {
                count++;
                currentBlocks = c.get(i);
            }

            if (count > k) {
                return false;
            }
        }

        return true;
    }


    public int paint(int a, int b, ArrayList<Integer> c) {
        int l = 1;
        int h = 0;
        long maxBlock = 0;
        for (int i = 0; i < c.size(); i++) {
            h += c.get(i);
            maxBlock = Math.max(maxBlock, c.get(i));
        }


        long minBlock = h;
        while (l <= h) {
            int mid = l + (h - l) / 2;
            if (getMiddle(c, a, mid, maxBlock)) {
                minBlock = mid;
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return (int) (minBlock * b % 10000003);
    }


    public int sqrt(int a) {
        long h = a;
        long l = 1;
        while (l <= h) {
            long mid = (h + l) / 2;
            long sqr = mid * mid;
            if (sqr <= a && (mid + 1) * (mid + 1) > a) {
                return (int) mid;
            }

            if (sqr < a) {
                l = mid + 1;
            } else {
                h = mid - 1;
            }

        }

        return 0;
    }

    public int pow(int x, int n, int d) {
        if (x == 0) {
            return 0;
        }
        int c = 1;
        long xB = x;
        while (n > 0) {
            if ((n & 1) == 1) {
                c = (int) ((c * xB) % d);
                n--;
            } else {
                xB = (xB * xB) % d;
                n /= 2;
            }
        }

        if (c < 0) {
            int div = (c / d);
            c = (((div + 1) * d) + c);
        }
        return c;
    }

    public int searchInsert(ArrayList<Integer> a, int b) {
        int l = 0;
        int h = a.size() - 1;
        while (l <= h) {
            int mid = (l + h) / 2;
            if (a.get(mid) == b) {
                return mid;
            }
            if (mid == 0 && b < a.get(mid)) {
                return mid;
            }
            if ((mid == 0 || b > a.get(mid)) && (mid == a.size() - 1 || b < a.get(mid + 1))) {
                return mid + 1;
            }
            if (a.get(mid) < b) {
                l = mid + 1;
            } else {
                h = mid - 1;
            }

        }
        return -1;
    }

    public ArrayList<Integer> searchRange(final List<Integer> a, int b) {

        int findBeginning = searchFirst(a, b);
        int findLast = findBeginning == -1 ? -1 : searchLast(a, b);
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(findBeginning);
        arrayList.add(findLast);
        return arrayList;
    }

    private int searchLast(List<Integer> a, int b) {
        int l = 0;
        int h = a.size() - 1;
        while (l <= h) {
            int mid = (l + h) / 2;
            if (a.get(mid) == b && (mid == a.size() - 1 || a.get(mid + 1) != b)) {
                return mid;
            } else if (a.get(mid) <= b) {
                l = mid + 1;
            } else {
                h = mid - 1;
            }
        }

        return 0;
    }

    private int searchFirst(List<Integer> a, int b) {
        int l = 0;
        int h = a.size() - 1;
        while (l <= h) {
            int mid = (l + h) / 2;
            if (a.get(mid) == b && (mid == 0 || a.get(mid - 1) != b)) {
                return mid;
            } else if (a.get(mid) < b) {
                l = mid + 1;
            } else {
                h = mid - 1;
            }
        }

        return -1;
    }

    // DO NOT MODIFY THE LIST


    public int searchMatrix(ArrayList<ArrayList<Integer>> a, int b) {
        int i = 0, j = a.get(0).size() - 1;
        while (i != a.size() && j != -1) {
            if (a.get(i).get(j) == b) {
                return 1;
            } else if (a.get(i).get(j) < b) {
                i++;
            } else {
                j--;
            }
        }

        return 0;
    }

    public int findRank(String a) {
        char[] array = new char[a.length() + 1];
        array[0] = '0';
        for (int i = 1; i < array.length; i++) {
            array[i] = a.charAt(i - 1);
        }

        int totalRank = 0;
        for (int i = 1; i < array.length; i++) {
            int rank = findRank(i, array);
            totalRank += (rank) * fact(array.length - i - 1) % 1000003;
            totalRank = totalRank % 1000003;
        }

        return ++totalRank;
    }


    private int findRank(int i, char[] array) {
        int rank = 0;
        for (int j = i + 1; j < array.length; j++) {
            if (array[j] < array[i]) {
                rank++;
            }
        }

        return rank;
    }


    public void arrange(ArrayList<Integer> a) {
        int n = a.size();
        for (int i = 0; i < a.size(); i++) {
            a.set(i, a.get(i) + (a.get(a.get(i)) % n) * n);
        }

        for (int i = 0; i < a.size(); i++) {
            a.set(i, (a.get(i) - (a.get(i) % n)) / n);
        }
    }


    public int reverse(int a) {
        boolean isNegative = false;
        if (a < 0) {
            isNegative = true;
            a = a * -1;
        }

        int reverse = 0;
        while (a > 0) {
            reverse += a % 10;

            if (reverse > Integer.MAX_VALUE / 10 && a >= 10) {
                return 0;
            }
            reverse = a < 10 ? reverse : reverse * 10;
            a /= 10;
        }

        if (isNegative) {
            reverse *= -1;
        }
        return reverse;
    }

    public boolean isPalindrome(int a) {
        int reverse = 0;
        int reverseBack = a;
        while (a > 0) {
            reverse += a % 10;
            reverse = a < 10 ? reverse : reverse * 10;
            a /= 10;
        }
        return reverse == reverseBack;
    }

    public String convertToTitle(int a) {
        String result = "";
        while (a > 0) {
            result += (char) ((a - 1) % 26 + 'A');
            a = (a - 1) / 26;
        }

        return result;
    }


    public int titleToNumber(String a) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 1; i <= 26; i++) {
            map.put((char) (64 + i), i);
        }

        int n = a.length() - 1;
        int number = 0;

        for (int i = a.length() - 1; i >= 0; i--) {
            number += Math.pow(26, (a.length() - i - 1)) * map.get(a.charAt(i));
        }

        return number;
    }

    public int trailingZeroes(int a) {
        int count = 0;
        for (int i = 5; i <= a; i *= 5) {
            count += a / i;
        }
        return count;
    }

    public int gcd(int a, int b) {
        if (b > a) {
            return gcd(b, a);
        }
        if (b == 0) {
            return a;
        }
        if (a % b == 0) {
            return b;
        }
        return gcd(a - b, b);
    }

    public boolean isPower(int a) {
        if (a == 1) {
            return true;
        }
        int sqrt = (int) Math.sqrt(a);
        boolean[] prims = new boolean[sqrt + 1];
        Arrays.fill(prims, true);
        prims[0] = false;
        prims[1] = false;
        for (int i = 2; i <= sqrt; i++) {
            if (prims[i] == false) {
                continue;
            }
            for (int j = 2 * i; j <= sqrt; j += i) {
                prims[j] = false;
            }
        }

        int aBackUp = a;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < prims.length; i++) {
            if (prims[i] == false) {
                continue;
            }
            while (a % i == 0 && a != 0) {
                a = a / i;
                map.put(i, map.containsKey(i) ? map.get(i) + 1 : 1);
            }

            if (a == 0) {
                break;

            }
        }
        if (map.keySet().isEmpty()) {
            return false;
        }

        int base = 1;
        if (map.keySet().size() == 1) {
            base = map.keySet().iterator().next();
        } else {
            for (int key : map.keySet()) {
                if ((map.get(key) & 1) != 0) {
                    return false;
                }
                base *= Math.pow(key, map.get(key));
            }
        }
        a = aBackUp;
        if (base == 0) {
            return false;
        }
        while (a != base) {
            if (a % base != 0) {
                return false;
            }
            a /= base;
        }

        return true;
    }

    public ArrayList<Integer> primesum(int a) {
        boolean[] array = new boolean[a + 1];
        Arrays.fill(array, true);
        array[0] = false;
        array[1] = false;
        for (int i = 2; i * i <= a; i++) {
            if (array[i] == false)
                continue;
            for (int j = 2 * i; j < a; j += i) {
                array[j] = false;
            }
        }

        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == true && array[a - i] == true) {
                arrayList.add(i);
                arrayList.add(a - i);
                return arrayList;
            }
        }
        return null;
    }

    public boolean hotel(ArrayList<Integer> arrive, ArrayList<Integer> depart, int K) {
        if (arrive.size() == 0) {
            return K == 0 ? true : false;
        }
        ArrayList<Time> time = new ArrayList<>();
        for (int i = 0; i < arrive.size(); i++) {
            time.add(new Time(arrive.get(i), true));
        }

        for (int i = 0; i < depart.size(); i++) {
            time.add(new Time(depart.get(i), false));
        }
        Collections.sort(time, new Comparator<Time>() {
            @Override
            public int compare(Time o1, Time o2) {
                int diff = o1.arrivalOrDepart - o2.arrivalOrDepart;
                if (diff != 0) {
                    return diff;
                }

                if (o1.isStart && !o2.isStart) {
                    return 1;
                }

                if (!o1.isStart && o2.isStart) {
                    return -1;
                }

                return diff;
            }
        });

        System.out.println(time);
        int numberOfRoomsNeeded = 0;
        for (int i = 0; i < time.size(); i++) {
            if (time.get(i).isStart) {
                numberOfRoomsNeeded++;
            } else {
                numberOfRoomsNeeded--;
            }

            if (numberOfRoomsNeeded > K) {
                return false;
            }
        }

        return true;
    }
    class Time {
        int arrivalOrDepart;

        boolean isStart;

        public Time(int arrivalOrDepart, boolean type) {
            this.arrivalOrDepart = arrivalOrDepart;
            this.isStart = type;
        }
        @Override
        public String toString() {
            return
                    "[" + arrivalOrDepart +
                            " , " + isStart +
                            ']';
        }


    }

    public ArrayList<ArrayList<Integer>> generateMatrix(int a) {
        ArrayList<ArrayList<Integer>> arrayList = new ArrayList<>();
        for (int i = 0; i < a; i++) {
            arrayList.add(new ArrayList<>());
        }

        int count = 1;
        int m = 0, n = a - 1, o = a - 1, p = 0;
        while (count <= a * a) {

            for (int j = p; j <= n && count <= a * a; j++, count++) {
                arrayList.get(m).add(j, count);
            }

            m++;
            for (int j = m; j <= o && count <= a * a; j++, count++) {
                arrayList.get(j).add(arrayList.get(j).size() - m + 1, count);
            }

            n--;

            for (int j = n; j >= p && count <= a * a; j--, count++) {
                arrayList.get(o).add(p, count);
            }

            o--;

            for (int j = o; j >= m && count <= a * a; j--, count++) {
                arrayList.get(j).add(p, count);
            }

            p++;

        }

        return arrayList;

    }


    public ArrayList<ArrayList<Integer>> diagonal(ArrayList<ArrayList<Integer>> a) {
        int n = a.size();
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        for (int i = 0; i < 2 * n - 1; i++) {
            arrayLists.add(new ArrayList<>());
        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int index = i + j;
                arrayLists.get(index).add(a.get(i).get(j));
            }
        }

        return arrayLists;
    }


    public String largestNumber(final List<Integer> a) {

        Collections.sort(a, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                String first = String.valueOf(o1);
                String second = String.valueOf(o2);
                BigInteger firstBI = new BigInteger(first + second);
                BigInteger secondBI = new BigInteger(second + first);
                return secondBI.compareTo(firstBI);
            }
        });
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < a.size(); i++) {
            result.append(a.get(i));
        }

        int i = 0;
        for (i = 0; i < result.length() - 1; i++) {
            if (result.charAt(i) == '0') {
                continue;
            } else {
                break;
            }
        }

        result = result.delete(0, i);
        return String.valueOf(result);
    }

    public int compareTo(int o1, int o2) {
        int o1Back = o1;
        int o2Back = o2;
        int n1 = 0;
        int n2 = 0;
        while (o1 != 0) {
            n1++;
            o1 /= 10;
        }
        while (o2 != 0) {
            n2++;
            o2 /= 10;
        }


        int i = n1, j = n2;
        o1 = o1Back;
        o2 = o2Back;
        while (i > 0 && j > 0) {
            int pow10n1 = (int) Math.pow(10, i - 1);
            int pow10n2 = (int) Math.pow(10, j - 1);
            int msd1 = o1Back / pow10n1;
            int msd2 = o2Back / pow10n2;
            o1Back %= pow10n1;
            o2Back %= pow10n2;
            if (msd1 > msd2) {
                return -1;
            } else if (msd2 > msd1) {
                return 1;
            }
            i--;
            j--;
        }
        if (j > 0) {
            while (j > 0) {
                int pow10n1 = (int) Math.pow(10, n1 - 1);
                int pow10n2 = (int) Math.pow(10, j - 1);
                int msd1 = o1 / pow10n1;
                int msd2 = o2Back / pow10n2;
                o2Back %= pow10n2;
                if (msd1 > msd2) {
                    return -1;
                } else if (msd1 < msd2) {
                    return 1;
                }
                j--;
            }
        }

        if (i > 0) {
            while (i > 0) {
                int pow10n1 = (int) Math.pow(10, i - 1);
                int pow10n2 = (int) Math.pow(10, n2 - 1);
                int msd1 = o1Back / pow10n1;
                o1Back %= pow10n1;
                int msd2 = o2 / pow10n2;
                if (msd1 > msd2) {
                    return -1;
                } else if (msd1 < msd2) {
                    return 1;
                }
                i--;
            }
        }
        int lastDigit1 = o1 % 10;
        int firstDigit2 = o2 / (int) Math.pow(10, n2 - 1);
        if (lastDigit1 > firstDigit2) {
            return -1;
        } else if (lastDigit1 < firstDigit2) {
            return 1;
        }

        return n2 - n1;

    }

    public int maximumGap(final List<Integer> a) {

        ArrayList<ElementWithIndex> elementWithIndices = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            elementWithIndices.add(new ElementWithIndex(a.get(i), i));
        }

        Collections.sort(elementWithIndices, new Comparator<ElementWithIndex>() {
            @Override
            public int compare(ElementWithIndex o1, ElementWithIndex o2) {
                return o1.value - o2.value;
            }
        });

        int smallest = Integer.MAX_VALUE;
        int highestDiff = 0;
        for (int i = 0; i < elementWithIndices.size(); i++) {
            if (elementWithIndices.get(i).index < smallest) {
                smallest = elementWithIndices.get(i).index;
            } else if (elementWithIndices.get(i).index - smallest > 0 && highestDiff < elementWithIndices.get(i).index - smallest) {
                highestDiff = elementWithIndices.get(i).index - smallest;
            }
        }
        return highestDiff;
    }
    class ElementWithIndex {
        int value;

        int index;
        public ElementWithIndex(int value, int index) {
            this.value = value;
            this.index = index;
        }

    }

    public int maximumGap1(final List<Integer> a) {
        if (a.size() < 2) {
            return 0;
        }

        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < a.size(); i++) {
            minValue = Math.min(minValue, a.get(i));
            maxValue = Math.max(maxValue, a.get(i));
        }
        int n = a.size() - 1;

        int gap = (maxValue - minValue) / n;
        if (gap == 0) {
            return maxValue - minValue;
        }
        if ((maxValue - minValue) % a.size() != 0) {
            n++;
        }

        BucketValue[] bucketValue = new BucketValue[n + 1];
        for (int i = 0; i < n + 1; i++) {
            bucketValue[i] = new BucketValue();
        }


        for (int i = 0; i < a.size(); i++) {
            int index = (a.get(i) - minValue) / gap;
            bucketValue[index].arrayList.add(a.get(i));
            if (bucketValue[index].minValue > a.get(i) || bucketValue[index].minValue == -1) {
                bucketValue[index].minValue = a.get(i);
            }

            if (bucketValue[index].maxValue < a.get(i)) {
                bucketValue[index].maxValue = a.get(i);
            }
        }

        int maxDifference = Integer.MIN_VALUE;
        int previouseBucketInex = -1;
        for (int i = 0; i < bucketValue.length; i++) {
            if (bucketValue[i].minValue != -1) {
                if (previouseBucketInex != -1)
                    maxDifference = Math.max(bucketValue[i].minValue - bucketValue[previouseBucketInex].maxValue, maxDifference);

                previouseBucketInex = i;
            }

        }

        return maxDifference;
    }
    class BucketValue {
        int maxValue;
        int minValue;

        ArrayList<Integer> arrayList;

        BucketValue() {
            maxValue = 0;
            minValue = -1;
            arrayList = new ArrayList<>();
        }


    }

    public void nextPermutation(ArrayList<Integer> a) {
        int n = a.size();
        int previous = a.get(n - 1);
        int i;
        boolean isBreaked = false;
        for (i = n - 2; i >= 0; i--) {
            if (previous > a.get(i)) {
                isBreaked = true;
                break;
            } else {
                previous = a.get(i);
            }
        }

        if (!isBreaked) {
            Collections.sort(a, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });
            return;
        }

        int smallestLargerNumbersIndex = i;
        int largest = Integer.MAX_VALUE;
        for (int j = i + 1; j < a.size(); j++) {
            if (a.get(i) < a.get(j) && largest > a.get(j)) {
                largest = a.get(j);
                smallestLargerNumbersIndex = j;
            }
        }
        swap(a, i, smallestLargerNumbersIndex);

        Collections.sort(a.subList(i + 1, a.size()));
    }

    private void swap(ArrayList<Integer> a, int i, int k) {
        int temp = a.get(i);
        a.set(i, a.get(k));
        a.set(k, temp);
    }


    public void rotate(ArrayList<ArrayList<Integer>> a) {
        int m = a.size();
        int n = a.get(0).size();
        for (int level = 0; level < m / 2; level++) {
            for (int i = level; i < level + 1; i++) {
                for (int j = level; j < n - 1 - level; j++) {
                    int currentElement = a.get(i).get(j);
                    a.get(i).set(j, a.get(m - 1 - j).get(i));
                    a.get(m - 1 - j).set(i, a.get(m - i - 1).get(n - j - 1));
                    a.get(m - i - 1).set(n - j - 1, a.get(j).get(n - i - 1));
                    a.get(j).set(n - i - 1, currentElement);
                }
            }
        }

    }

    public ArrayList<ArrayList<Integer>> getRow(int a) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        for (int i = 1; i <= a; i++) {
            int c = 1;
            ArrayList<Integer> arrayList = new ArrayList<>();
            for (int j = 1; j <= i; j++) {
                arrayList.add(c);
                c = c * (i - j) / j;
            }

            result.add(arrayList);
        }

        return result;
    }


    public void pascals(int n) {
        for (int i = 1; i <= n; i++) {
            int c = 1;
            for (int j = 1; j <= i; j++) {
                System.out.print(c + " ");
                c = c * (i - j) / j;

            }
            System.out.println();
        }
    }

    public int repeatedNumber(final List<Integer> a) {
        if (a.size() == 0) {
            return -1;
        }
        if (a.size() == 1) {
            return a.get(0);
        }
        System.out.println(a.size());
        int count1 = 1;
        int count2 = -1;
        int majorityElement1 = Integer.MIN_VALUE;
        int majorityElement2 = Integer.MIN_VALUE;

        for (int i = 0; i < a.size(); i++) {
            if (majorityElement1 == Integer.MIN_VALUE && majorityElement2 != a.get(i)) {
                majorityElement1 = a.get(i);
                count1 = 1;
            } else if (majorityElement1 == a.get(i)) {
                count1++;
            } else if (majorityElement2 == Integer.MIN_VALUE) {
                majorityElement2 = a.get(i);
                count2 = 1;
            } else if ((majorityElement2 == a.get(i))) {
                count2++;
            } else {
                count1--;
                count2--;
                if (count1 == 0) {
                    majorityElement1 = Integer.MIN_VALUE;
                    count1 = 0;
                }

                if (count2 == 0) {
                    majorityElement2 = Integer.MIN_VALUE;
                    count2 = 0;
                }
            }
        }

        count1 = 0;
        count2 = 0;
        for (int i = 0; i < a.size(); i++) {
            if (majorityElement1 == a.get(i)) {
                count1++;
            }
            if (majorityElement2 == a.get(i)) {
                count2++;
            }
        }

        if (count1 > a.size() / 3) {
            System.out.println(count1);
            return majorityElement1;
        }

        if (count2 > a.size() / 3) {
            System.out.println(count1);
            return majorityElement2;
        }


        return -1;
    }


    static int countDuplicates(int[] numbers) {


        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            map.put(numbers[i], map.containsKey(numbers[i]) ? map.get(numbers[i]) + 1 : 1);
        }

        int count = 0;
        for (Integer integer : map.keySet()) {
            if (map.get(integer) > 1) {
                count++;
            }
        }

        return count;
    }


    static int diceGame(String[] result) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < result.length; i++) {
            char[] array = result[i].toCharArray();
            Arrays.sort(array);
            result[i] = String.valueOf(array);
            System.out.println(result[i]);
        }

        int[] resultInt = new int[result[0].length()];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length(); j++) {
                if (resultInt[j] < Integer.valueOf(result[i].charAt(j)) - 48) {
                    resultInt[j] = Integer.valueOf(result[i].charAt(j)) - 48;
                }
            }
        }


        int minimumStop = 0;
        for (int i = 0; i < resultInt.length; i++) {
            minimumStop += resultInt[i];
        }


        return minimumStop;

    }


    static void lastLetter(String word) {
        if (word.length() < 2) {
            System.out.println("Word not having enough length!");
            return;
        }
        System.out.println(word.charAt(word.length() - 1) + " " + word.charAt(word.length() - 2));
    }

    public int firstMissingPositive(ArrayList<Integer> a) {
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) > 0 && a.get(i) <= a.size()) {
                setRecursively(a, i);
            }
        }

        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) != Integer.MAX_VALUE) {
                return i + 1;
            }
        }

        return a.size() + 1;
    }

    private int setRecursively(ArrayList<Integer> a, Integer integer) {
        if (a.get(integer) > 0 && a.get(integer) <= a.size()) {
            int backUP = a.get(a.get(integer) - 1);
            a.set(a.get(integer) - 1, Integer.MAX_VALUE);
            return setRecursively(a, backUP);
        }

        a.set(a.get(integer) - 1, Integer.MAX_VALUE);
        return 1;
    }

    /*public int repeatedNumber(final List<Integer> V) {
        int range = (int) Math.sqrt(V.size());
        int[] count = new int[(int) Math.ceil(range)];
        int i, valueRange = V.size();
        for (i = 0; i < V.size(); i++) {
            count[(V.get(i) - 1) / range]++;
        }

        int repeatingRange = -1;
        int numRanges = ((valueRange - 1) / range) + 1;
        for ( i = 0; i < numRanges && repeatingRange == -1; i++) {
            if (i < numRanges - 1 || valueRange % range == 0) {
                if (count[i] > range) repeatingRange = i;
            } else {
                if (count[i] > valueRange % range) repeatingRange = i;
            }
        }

        if (repeatingRange == -1) return -1;

        for ( i = 0; i < V.size(); i++) {
            if ((V.get(i) - 1) / range == repeatingRange) count[(V.get(i) - 1) % range]++;
        }
        for ( i = 0; i < range; i++) {
            if (count[i] > 1) {
                return repeatingRange * range + i + 1;
            }
        }
        return -1;
    }*/

    public void testWave() {
        System.out.println(wave(new ArrayListUtils<Integer>().getArrayList(new Integer[]{1, 2, 3, 4, 5})));
    }


    public ArrayList<Integer> wave(ArrayList<Integer> a) {
        Collections.sort(a);
        for (int i = 0; i < a.size() && i + 1 < a.size(); i += 2) {

            int temp = a.get(i);
            a.set(i, a.get(i + 1));
            a.set(i + 1, temp);
        }

        return a;

    }

    public void testSetZerors() {
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        arrayLists.add(new ArrayListUtils<Integer>().getArrayList(new Integer[]{1, 0, 1}));
        arrayLists.add(new ArrayListUtils<Integer>().getArrayList(new Integer[]{1, 1, 1}));
        arrayLists.add(new ArrayListUtils<Integer>().getArrayList(new Integer[]{1, 1, 1}));
        setZeroes(arrayLists);
        for (ArrayList<Integer> arrayList : arrayLists) {

            System.out.println(arrayList);
        }

    }


    public void setZeroes(ArrayList<ArrayList<Integer>> a) {

        int[] rowZeros = new int[a.size()];
        int[] columnZeros = new int[a.get(0).size()];
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < a.get(0).size(); j++) {
                if (a.get(i).get(j) == 0) {
                    rowZeros[i] = 1;
                    columnZeros[j] = 1;
                }
            }
        }

        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < a.get(0).size(); j++) {
                if (rowZeros[i] == 1 || columnZeros[j] == 1) {
                    a.get(i).set(j, 0);
                }
            }
        }
    }


    public void testMerge() {
        System.out.println(merge(new ArrayListUtils<Interval>().getArrayList(new Interval[]{new Interval(10, 18), new Interval(2, 6), new Interval(3, 10), new Interval(1, 3)})));
    }


    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {

                return Integer.compare(o1.start, o2.start);
            }
        });


        ArrayList<Interval> result = new ArrayList<>();
        result.add(intervals.get(0));
        Interval currentInterval = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            if (currentInterval.end >= intervals.get(i).start) {
                if (currentInterval.end < intervals.get(i).end)
                    currentInterval.end = intervals.get(i).end;
            } else {
                result.add(intervals.get(i));
                currentInterval = intervals.get(i);
            }
        }


        return result;

    }


    public void testInsert() {

        ArrayList<Integer> integers = new ArrayListUtils<Integer>().getArrayList(new Integer[]{2, 4, 14, 16, 18, 20});
        ArrayList<Interval> intervals = new ArrayList<>();
        for (int i = 0; i < integers.size(); i += 2) {
            intervals.add(new Interval(integers.get(i), integers.get(i + 1)));
        }
        System.out.println(insert(intervals, new Interval(17, 27)));
    }

    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        newInterval = newInterval.start > newInterval.end ? new Interval(newInterval.end, newInterval.start) : newInterval;
        ArrayList<Interval> result = new ArrayList<>();
        if (intervals.isEmpty()) {
            result.add(newInterval);
            return result;
        }
        if (intervals.get(0).start > newInterval.end) {
            intervals.add(0, newInterval);
            return intervals;
        }


        if (intervals.get(intervals.size() - 1).end < newInterval.start) {
            intervals.add(newInterval);
            return intervals;
        }


        if (intervals.get(0).start > newInterval.start && intervals.get(intervals.size() - 1).end < newInterval.end) {
            result.add(newInterval);
            return result;
        }

        Integer startInterval = null;
        Integer endInterval = null;
        for (int i = 0; i < intervals.size(); i++) {
            if (intervals.get(i).end >= newInterval.start && intervals.get(i).start <= newInterval.start) {
                startInterval = i;
                break;
            }
        }

        for (int i = 0; i < intervals.size(); i++) {
            if (intervals.get(i).end >= newInterval.end && intervals.get(i).start <= newInterval.end) {
                endInterval = i;
                break;
            }
        }

        if (startInterval != null && endInterval != null) {
            for (int i = 0; i < startInterval; i++) {
                result.add(intervals.get(i));
            }

            Interval interval = new Interval(intervals.get(startInterval).start, intervals.get(endInterval).end);
            result.add(interval);
            for (int i = endInterval + 1; i < intervals.size(); i++) {
                result.add(intervals.get(i));
            }
            return result;
        }


        if (startInterval != null) {
            for (int i = 0; i < startInterval; i++) {
                result.add(intervals.get(i));
            }

            Interval interval = new Interval(intervals.get(startInterval).start, newInterval.end);
            result.add(interval);
            for (int i = startInterval + 1; i < intervals.size(); i++) {
                if (intervals.get(i).start > newInterval.end)
                    result.add(intervals.get(i));
            }
            return result;
        }


        if (endInterval != null) {
            for (int i = 0; i < intervals.size(); i++) {
                if (intervals.get(i).end < newInterval.start)
                    result.add(intervals.get(i));
            }

            Interval interval = new Interval(newInterval.start, intervals.get(endInterval).end);
            result.add(interval);
            for (int i = endInterval + 1; i < intervals.size(); i++) {
                result.add(intervals.get(i));
            }
            return result;
        }


        boolean isFirst = true;
        for (int i = 0; i < intervals.size() - 1; i++) {

            result.add(intervals.get(i));
            if (intervals.get(i).end < newInterval.start && i + 1 < intervals.size() && intervals.get(i + 1).start > newInterval.end) {
                result.add(newInterval);
                isFirst = false;


            }

        }

        if (!isFirst) {
            result.add(intervals.get(intervals.size() - 1));
            return result;
        }

        result.clear();

        for (int i = 0; i < intervals.size(); i++) {

            if (intervals.get(i).end < newInterval.start || intervals.get(i).start > newInterval.end) {
                result.add(intervals.get(i));

            } else if (intervals.get(i).start > newInterval.start && isFirst) {
                result.add(newInterval);
                isFirst = false;
            }

        }
        return result;
    }


    public void testFlip() {
        System.out.println(flip("010"));
    }

    public ArrayList<Integer> flip(String A) {
        ArrayList<Integer> flipString = new ArrayList<>();
        for (int i = 0; i < A.length(); i++) {
            flipString.add(A.charAt(i) == '1' ? -1 : 1);
        }

        int sum = 0;
        int start = 0, max = 0, maxStart = 0, maxEnd = 0;
        int i = 0;


        for (; i < flipString.size(); i++) {
            sum += flipString.get(i);
            if (sum < 0) {
                start = i + 1;
                sum = 0;
            }

            if (max < sum) {
                max = sum;
                maxStart = start + 1;
                maxEnd = i + 1;
            }

        }


        if (maxEnd == 0) {
            return new ArrayList<>();
        }

        flipString.clear();
        flipString.add(maxStart);
        flipString.add(maxEnd);
        return flipString;
    }

    public void testRepeatedNumber() {
        System.out.println(repeatedNumber(new ArrayListUtils<Integer>().getArrayList(new Integer[]{3, 3, 1, 2, 5})));
    }

    /*public ArrayList<Integer> repeatedNumber(final List<Integer> a) {
        int A = 0, B = 0;
        int n = a.size();
        long actualSum = n * (n + 1) / 2;
        long sum = 0;
        for (int i = 0; i < a.size(); i++) {
            sum += a.get(i);
        }

        long difference;
        difference = Math.abs(sum - actualSum);
        long sumSquares = getSquares(a.size());
        long sumSquaresWrong = getSquaresWrong(a);
        long diffOfSquares = Math.abs(sumSquaresWrong - sumSquares);
        long sumOfMissingNumbers = diffOfSquares / difference;
        B = (int) ((sumOfMissingNumbers + difference) / 2);
        A = (int) ((sumOfMissingNumbers - difference) / 2);

        ArrayList<Integer> result = new ArrayList<>();
        result.add(B);
        result.add(A);

        if (actualSum > sum) {
            Collections.reverse(result);
        }
        return result;
    }*/

    private long getSquares(int size) {
        long wrong = 0;
        for (int i = 1; i <= size; i++) {
            wrong += i * i;
        }

        return wrong;
    }

    private long getSquaresWrong(List<Integer> a) {
        long wrong = 0;
        for (int i = 0; i < a.size(); i++) {
            wrong += a.get(i) * a.get(i);
        }

        return wrong;
    }

    private long fact(int size) {
        long fact = 1;
        while (size > 0) {
            fact *= size;
            size--;
        }

        return fact;
    }


    public void testPlusOne() {
        System.out.println(plusOne(new ArrayListUtils<Integer>().getArrayList(new Integer[]{9, 9, 9, 9, 9})));
    }

    public void testSpiralOrder() {
        List<ArrayList<Integer>> arrayLists = new ArrayList<>();
        arrayLists.add(new ArrayListUtils<Integer>().getArrayList(new Integer[]{1, 2, 3}));
        arrayLists.add(new ArrayListUtils<Integer>().getArrayList(new Integer[]{4, 5, 6}));
        arrayLists.add(new ArrayListUtils<Integer>().getArrayList(new Integer[]{7, 8, 9}));
        arrayLists.add(new ArrayListUtils<Integer>().getArrayList(new Integer[]{17, 18, 19}));
        ArrayListUtils.printArray(spiralOrder(arrayLists));
    }


    public ArrayList<Integer> plusOne(ArrayList<Integer> a) {
        int carry = 1;
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = a.size() - 1; i >= 0; i--) {
            int number = a.get(i);
            number = number + carry;
            carry = number / 10;
            number %= 10;
            result.add(number);
        }

        if (carry == 1) {
            result.add(carry);
        }

        while (result.get(result.size() - 1) == 0) {
            result.remove(result.size() - 1);
        }

        Collections.reverse(result);
        return result;
    }

    public void testMaxSubArray() {
        System.out.println(maxSubArray(new ArrayListUtils<Integer>().getArrayList(new Integer[]{-2, 1, -3, 4, -1, 2, 1, -5, 4})));
    }


    public int maxSubArray(final List<Integer> a) {
        int maxSum = 0;
        int sum = 0;
        for (int i = 0; i < a.size(); i++) {
            sum += a.get(i);
            maxSum = Math.max(maxSum, sum);
            if (sum < 0) {
                sum = 0;
            }
        }

        return maxSum;
    }

    public void testCoverPoints() {
        ArrayList<Integer> x = new ArrayListUtils<Integer>().getArrayList(new Integer[]{4, 8, -7, -5, -13, 9, -7, 8});
        ArrayList<Integer> y = new ArrayListUtils<Integer>().getArrayList(new Integer[]{4, -15, -10, -3, -13, 12, 8, -8});
        System.out.println(coverPoints(x, y));
    }


    public int coverPoints(ArrayList<Integer> X, ArrayList<Integer> Y) {
        int coverPoints = 0;
        int preX = X.get(0);
        int preY = Y.get(0);
        for (int i = 0; i < X.size(); i++) {
            int xDiff = Math.abs(X.get(i) - preX);
            int yDiff = Math.abs(Y.get(i) - preY);
            coverPoints += Math.max(xDiff, yDiff);
            preX = X.get(i);
            preY = Y.get(i);
        }

        return coverPoints;
    }

    class Interval {
        int start;
        int end;

        Interval() {
            start = end = 0;
        }

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return start +
                    "," + end
                    ;
        }
    }

}
