package com.govind.algo.platform.hackerearth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by 609600403 on 21/05/2016.
 */
public class NewSample {
    private static InputReader in;

        public static void main(String args[]) throws Exception {
            in = new InputReader(System.in);
            int n = in.nextInt();
            int m = in.nextInt();
            int k = in.nextInt();
            int[] ar = new int[m];
            int res = 0;
            for ( int i = 0;i < n; i++){
                int pre = in.nextInt() - 1;
                if (ar[pre] >= k) {
                    boolean isFound = false;
                    for (int j = pre + 1; (j + 1) % m != pre; j++) {
                        if (ar[j% m] < k) {
                            isFound = true;
                            ar[j%m]++;
                            break;
                        }
                    }
                    if (!isFound) {
                        ar[pre]++;
                    }
                    res++;
                } else {
                    ar[pre]++;
                }
            }

            System.out.println(res);
        }

    private static int binarySearch(int ele, int[] ar) {
        int l = 0;
        int h = ar.length - 1;
        while (l <= h) {
            int mid = l + (h - l) / 2;
            if (ar[mid] == ele || ((mid == 0 || ele > ar[mid - 1]) && (ele < ar[mid]))) {
                int left = mid;
                while (left > 0 && ar[left - 1] == ele) {
                    left--;
                }
                int f = left - 0;
                int right = mid;
                while (right < ar.length - 1 && ar[right + 1] == ele) {
                    right++;
                }

                int g = ar.length - 1 - right;
                return f * g;
            }

            if (ar[mid] < ele) {
                l = mid + 1;
            } else {
                h = mid - 1;
            }
        }

        return 0;
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


