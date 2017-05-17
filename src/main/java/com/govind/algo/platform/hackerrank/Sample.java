package com.govind.algo.platform.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by 609600403 on 21/04/2016.
 */
public class Sample {
    private static InputReader in;
    public static void main(String args[] ) throws Exception {
        in = new InputReader(System.in);
        int test = in.nextInt();
        for (int  t = 0; t < test; t++){
            int n = in.nextInt();
            int k = in.nextInt();
            int[] nums = new int[n];
            long [] muls = new long[n];
            long pre = 1;
            for (int i = 0; i < n; i++){
                nums[i] = in.nextInt();
                muls[i] = pre * nums[i];
                pre = Math.max(muls[i], 1);
            }
            long max = 0;
            int size = n - k ;
            if (size  == 0){
                System.out.println(muls[n - 1]);
            }else{
                for (int  i = size - 1; i < n; i++){
                    int start = i - size ;
                    if (start < 0 || muls[start] == 0){
                        max = Math.max(muls[i], max);
                    }else if (start >= 0 ){
                        max = Math.max(muls[i] / muls[start], max);
                    }
                }
                System.out.println(max);
            }

        }


    }

    static class InputReader {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public InputReader(InputStream inputStream) {
            reader = new BufferedReader(new InputStreamReader(inputStream));
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLng() {
            return Long.parseLong(next());
        }
    }

}
