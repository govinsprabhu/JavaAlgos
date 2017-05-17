package com.govind.algo.search;

import java.util.ArrayList;

/**
 * Created by govindp on 11/24/2015.
 */
public class PatternSearching {
    public static void main(String[] args) {
        new PatternSearching().findAllAnagramOfPattern();
    }

    public void printKMP() {
        String text = "abababcababac";
        String pattern = "ababac";

        int[] lps = new int[pattern.length()];
        computeLPs(pattern, lps);
        int j = 0;
        for (int i = 0; i < text.length(); ) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }

            if (j == pattern.length()) {
                System.out.println("found at -" + (i - j));
            } else if (i < text.length() && text.charAt(i) != pattern.charAt(j)) {
                if (j == 0) {
                    i++;
                } else {
                    j = lps[j - 1];
                }

            }
        }
    }

    private void computeLPs(String pattern, int[] lps) {
        int length = 0;
        int i = 1;
        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(length)) {
                length++;
                lps[i] = length;
                i++;
            } else if (length != 0) {
                length = lps[length - 1];
            } else {
                lps[i] = 0;
                i++;
            }
        }
    }


    public void findAllAnagramOfPattern() {
        System.out.println(findAllAnagramOfPattern("BACDGABCDA", "ABCD"));
    }

    public ArrayList<Integer> findAllAnagramOfPattern(String s, String p) {
        int[] countP = new int[256];
        int[] countS = new int[256];

        for (int i = 0; i < p.length(); i++) {
            countP[p.charAt(i)]++;
            countS[s.charAt(i)]++;
        }


        ArrayList<Integer> result = new ArrayList<>();
        for (int i = p.length(); i < s.length(); i++) {
            if (check(countP, countS)) {
                result.add(i - p.length());
            }

            countS[s.charAt(i)]++;
            countS[s.charAt(i - p.length())]--;
        }
        if (check(countP, countS)) {
            result.add(s.length() - p.length());
        }

        return result;
    }

    private boolean check(int[] countP, int[] countS) {
        for (int i = 0; i < 256; i++) {
            if (countP[i] != countS[i]) {
                return false;
            }
        }

        return true;
    }
}
