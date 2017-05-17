package com.govind.algo.platform.spoj;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    private static InputReader in;
    private static PrintWriter out;

    public static void main(String[] args) throws java.lang.Exception {
        in = new InputReader(System.in);
        out = new PrintWriter(System.out, true);
        int test = in.nextInt();
        List<Integer> lows = new ArrayList<>(test);
        List<Integer> highs = new ArrayList<>(test);
        for (int t = 0; t < test; t++) {
            lows.add(in.nextInt());
            highs.add(in.nextInt());
        }

        int maxHigh = highs.stream().max(Integer::compare).get();
        int minLow = lows.stream().min(Integer::compare).get();
        int root = (int) Math.sqrt(maxHigh);
        boolean[] isPrime = new boolean[root + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 2; i <= root; i++) {
            if (isPrime[i]) {
                for (int j = 2 * i; j <= root; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        boolean[] prims = new boolean[maxHigh - minLow + 1];
        Arrays.fill(prims, true);
        for (int i = 0; i < isPrime.length; i++) {
            if (isPrime[i]) {
                int j = i * ((minLow % i == 0)? (minLow / i ) :( (minLow / i)  + 1 ));
                if (j < minLow) {
                    j = minLow + i - minLow;
                }
                if (j == i) {
                    j = 2 * i;
                }
                for (; j <= maxHigh; j += i) {
                    prims[j - minLow] = false;

                }
            }
        }

        for (int i = 0; i < test; i++) {
            int low = lows.get(i) - minLow;
            int high = highs.get(i);
            for (int j = Math.max(low, 2); j <= high; j++) {

                if (prims[j]) {
                    out.println(j + lows.get(i));
                }
            }
            if (i != test - 1)
                out.println();
        }

    }


    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public InputReader(InputStream inputStream) {
            reader = new BufferedReader(new InputStreamReader(inputStream));
            tokenizer = null;

        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException io) {

                }
            }

            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}