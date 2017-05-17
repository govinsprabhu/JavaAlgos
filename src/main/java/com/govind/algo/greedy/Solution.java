package com.govind.algo.greedy;

import com.govind.util.arraylist.ArrayListUtils;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import com.sun.xml.internal.ws.addressing.WsaTubeHelperImpl;

import java.util.*;

/**
 * Created by govindp on 11/16/2015.
 */
public class Solution {
    public static void main(String[] args) {
        //System.out.println(new Solution().majorityElement(new ArrayListUtils<Integer>().getArrayList(new Integer[]{1, 2, 3, 4, 1, 1, 5, 1, 1})));
        //System.out.println(new Solution().canCompleteCircuit(new ArrayListUtils<Integer>().getArrayList(new Integer[]{6, 3, 7}), new ArrayListUtils<Integer>().getArrayList(new Integer[]{4, 6, 3})));
        new Solution().exist();
    }

    public void exist() {
        ArrayList<String> arrayList = new ArrayListUtils<>().getArrayList(new String[]{"ABCE", "SFCS", "ADEE"});
        System.out.println(exist(arrayList, "ABCCET"));
    }

    public int exist(ArrayList<String> a, String b) {
        boolean[][] visited = new boolean[a.size()][a.get(0).length()];
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < a.get(i).length(); j++) {
                if (a.get(i).charAt(j) == b.charAt(0)) {
                    if (exist(a, b, 0, i, j, visited)) {
                        return 1;
                    }
                }
            }
        }

        return 0;
    }

    public boolean exist(ArrayList<String> a, String b, int index, int i, int j, boolean[][] visited) {
        if (index == b.length()) {
            return true;
        }

        if (i < 0 || i >= a.size() || j < 0 || j >= a.get(0).length() || a.get(i).charAt(j) != b.charAt(index) || visited[i][j]) {
            return false;
        }


        visited[i][j] = true;
        boolean resut = exist(a, b, index + 1, i + 1, j, visited)
                || exist(a, b, index + 1, i, j + 1, visited)
                || exist(a, b, index + 1, i - 1, j, visited)
                || exist(a, b, index + 1, i, j - 1, visited);
        visited[i][j] = false;
        return resut;

    }

    public void timeToFinishJob() {
        int k = 2;
        int time = 5;
        int[] job = {4, 10, 5};

        int end = 0;
        for (int i = 0; i < job.length; i++) {
            end += job[i];
        }
        int max = 0;
        for (int i = 0; i < job.length; i++) {
            max = Math.max(job[i], max);
        }

        int start = 0;

        int minJob = end;
        while (start <= end) {
            int mid = (end + start) / 2;
            if (max <= mid && isPossible(job, k, mid)) {
                end = mid - 1;
                minJob = Math.min(minJob, mid);
            } else {
                start = mid + 1;
            }
        }

        System.out.println(minJob);
    }

    private boolean isPossible(int[] job, int k, int mid) {

        int currentJob = 0;
        int count = 0;
        for (int i = 0; i < job.length; ) {
            if (currentJob + job[i] > mid) {
                count++;
                currentJob = 0;
            } else {
                currentJob += job[i];
                i++;
            }
        }

        return count <= k;
    }

    public void maximumNumberOfStations() {
        float[] arraival = {9, (float) 9.40, (float) 9.50, 11, 15, 18};
        float[] departed = {(float) 9.10, (float) 12.00, (float) 11.20, (float) 11.30, 19, 20};

        int next = 1;
        float maxDiparted = departed[0];
        int maxStation = 0;
        int currentStation = 0;
        while (next < arraival.length) {
            if (maxDiparted > arraival[next]) {
                currentStation++;
                maxDiparted = Math.max(maxDiparted, departed[next]);
            } else {
                currentStation = currentStation == 0 ? currentStation : currentStation--;
                maxDiparted = departed[next];
            }
            next++;
            maxStation = Math.max(maxStation, currentStation);
        }
        System.out.println(maxStation);

    }

    public void mergeOverlappedInterval() {
        Interval[] array = {new Interval(3, 7), new Interval(6, 10), new Interval(1, 9), new Interval(2, 4), new Interval(10, 15), new Interval(15, 30)};
        Arrays.sort(array, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });

        int last = 0;
        int index = 0;
        int i;
        for (i = 1; i <= array.length - 1; ) {
            if (array[last].end >= array[i].start) {
                array[last].start = Math.min(array[last].start, array[i].start);
                array[last].end = Math.max(array[last].end, array[i].end);
                i++;
            } else {
                array[index] = array[last];
                last = i;
                index++;
            }
        }

        if (last == i - 1 || last == 0) {
            array[index++] = array[last];
        }

        for (int j = 0; j < index; j++) {
            System.out.println(array[j].start + " " + array[j].end + " ");
        }
    }

    class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public int canCompleteCircuit(final List<Integer> gas, final List<Integer> cost) {
        int start = 0;
        int totalGas = 0;
        int totalCost = 0;
        int currentCost = 0;
        for (int i = 0; i < gas.size(); i++) {
            totalCost += cost.get(i);
            totalGas += gas.get(i);
            currentCost += gas.get(i) - cost.get(i);
            if (currentCost < 0) {
                start = i + 1;
                currentCost = 0;
            }
        }

        if (totalCost > totalGas) {
            return -1;
        }

        return start;
    }

    public int majorityElement(final List<Integer> a) {

        int majority = 0;
        for (int i = 1, count = 1; i < a.size() - 1; i++) {
            if (a.get(majority) == a.get(i)) {
                count++;
            } else {
                count = count - 1;
            }

            if (count == 0) {
                majority = i;
                count = 1;
            }
        }
        return a.get(majority);
    }

    public void candy() {
        ArrayList<Integer> arrayList = new ArrayList<>(new ArrayListUtils<Integer>().getArrayList(new Integer[]{1, 2, 3, 4, 5, 4, 3, 2, 1}));
        int[] array = new int[arrayList.size()];

        //ArraysUtils.fill(array, 1);
        int[] rightArray = new int[arrayList.size()];
        //ArraysUtils.fill(rightArray, 1);
        for (int i = 0; i < arrayList.size(); i++) {
            if (i != 0 && arrayList.get(i - 1) < arrayList.get(i)) {
                array[i] = array[i - 1] + 1;
            } else {
                array[i] = 1;
            }
        }

        for (int i = arrayList.size() - 1; i >= 0; i--) {
            if (i != arrayList.size() - 1 && arrayList.get(i + 1) < arrayList.get(i)) {
                rightArray[i] = rightArray[i + 1] + 1;
            } else {
                rightArray[i] = 1;
            }
        }

        int totalSum = 0;
        for (int i = 0; i < array.length; i++) {
            totalSum += Math.max(array[i], rightArray[i]);
        }
        System.out.println(totalSum);
    }

    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }

        int[] candies = new int[ratings.length];
        candies[0] = 1;

        //from let to right
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            } else {
                // if not ascending, assign 1
                candies[i] = 1;
            }
        }

        int result = candies[ratings.length - 1];

        //from right to left
        for (int i = ratings.length - 2; i >= 0; i--) {
            int cur = 1;
            if (ratings[i] > ratings[i + 1]) {
                cur = candies[i + 1] + 1;
            }

            result += Math.max(cur, candies[i]);
            candies[i] = cur;
        }

        System.out.println(result);

        return result;
    }
}
