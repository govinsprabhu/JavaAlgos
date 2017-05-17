package com.govind.algo.platform.interviewbit.graph;

import java.util.*;

;

/**
 * Created by 609600403 on 10/07/2016.
 */
public class Sample {
    public static void main(String[] args) {
        String val = "Third party service";
        System.out.println(val.replaceAll("\\s+",""));
    }



    public String multiple(int A) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(null, 1 % A, 1));
        Node first = null;
        Set<Integer> set = new HashSet<>();
        while (!queue.isEmpty()) {
            first = queue.poll();
            if (first.val == 0) {
                StringBuilder result = new StringBuilder();
                while (first != null) {
                    result.append(first.flag);
                    first = first.parent;
                }

                return result.reverse().toString();
            }
            int val = (first.val %10)%A;
            if (!set.contains(val)) {
                set.add(val);
                queue.add(new Node(first, val, 0));
            }

            val += 1;
            if (val >= A) {
                val -= A;
            }
            if (!set.contains(val)) {
                set.add(val);
                queue.add(new Node(first, val, 1));
            }
        }

        return null;
    }

    class Node {
        Node parent;
        int val;
        int flag;

        public Node(Node parent, int val, int flag) {
            this.parent = parent;
            this.val = val;
            this.flag = flag;
        }
    }

    public ArrayList<ArrayList<String>> findLadders(String start, String end, ArrayList<String> dict1) {
        dict1.add(end);
        Set<String> dict = new HashSet<>(dict1);
        Queue<WordNode> queue = new LinkedList<>();
        queue.add(new WordNode(0, null, start));
        Set<String> visited = new HashSet<>();
        Set<String> unVisited = new HashSet<>();
        unVisited.addAll(dict);
        int currentLevel = 0;
        int previousLevel = 0;
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            WordNode wordNode = queue.poll();
            currentLevel = wordNode.level;
            if (wordNode.val.equals(end)) {
                ArrayList<String> cResult = new ArrayList<>();
                while (wordNode.parent != null) {
                    cResult.add(wordNode.val);
                    wordNode = wordNode.parent;
                }
                cResult.add(wordNode.val);
                result.add(cResult);
            } else {
                if (previousLevel < currentLevel) {
                    unVisited.removeAll(visited);
                }
                previousLevel = currentLevel;
                char[] array = wordNode.val.toCharArray();
                for (int i = 0; i < wordNode.val.length(); i++) {
                    char temp = array[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (array[i] != c) {
                            array[i] = c;
                        }
                        String word = new String(array);
                        if (dict.contains(word) && unVisited.contains(word)) {
                            queue.add(new WordNode(wordNode.level + 1, wordNode, word));
                            visited.add(word);
                        }
                        array[i] = temp;
                    }
                }

            }
        }

        return result;
    }

    class WordNode {
        int level;
        WordNode parent;
        String val;

        public WordNode(int level, WordNode parent, String val) {
            this.level = level;
            this.parent = parent;
            this.val = val;
        }
    }

    private void addResult(Set<String> parent, ArrayList<ArrayList<String>> result, Map<String, Set<String>> map, ArrayList<String> words) {
        if (parent == null || parent.isEmpty()) {
            Collections.reverse(words);
            result.add(words);
            return;
        }
        for (String word : parent) {
            words.add(word);
            addResult(map.get(word), result, map, (ArrayList<String>) words.clone());
        }
    }

    private Set<String> getWords(String word, Set<String> dictionary) {
        Set<String> words = new HashSet<>();
        for (int i = 0; i < word.length(); i++) {
            for (char j = 'a'; j <= 'z'; j++) {
                char[] array = word.toCharArray();
                if (array[i] != j) {
                    array[i] = j;
                    String cWord = new String(array);
                    if (dictionary.contains(cWord)) {
                        words.add(cWord);
                    }
                }
            }
        }

        return words;
    }

    public int knight(int N, int M, int x1, int y1, int x2, int y2) {
        Queue<Position> queue = new LinkedList<>();
        Position pos = new Position(x1, y1, 0);
        queue.add(pos);
        int[] rows = {1, 1, 2, 2, -1, -1, -2, -2};
        int[] cols = {2, -2, 1, -1, 2, -2, 1, -1};
        boolean[][] isVisited = new boolean[N + 1][M + 1];
        while (!queue.isEmpty()) {
            pos = queue.poll();
            int row = pos.row;
            int col = pos.col;
            int dis = pos.mov;
            if (row < 1 || row > N || col < 1 || col > M || isVisited[row][col]) {
                continue;
            }

            isVisited[row][col] = true;
            if (row == x2 && col == y2) {
                return dis;
            }

            for (int i = 0; i < rows.length; i++) {
                queue.add(new Position(row + rows[i], col + cols[i], dis + 1));

            }
        }

        return -1;
    }

    class Position {
        int row;
        int col;
        int mov;

        public Position(int row, int col, int mov) {
            this.row = row;
            this.col = col;
            this.mov = mov;
        }
    }

    public int ladderLength(String start, String end, ArrayList<String> dictV) {
        if (start.equals(end)) {
            return 1;
        }
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        dictV.add(end);
        Map<String, String> map = new HashMap<>();
        Set<String> dic = new HashSet<>(dictV);
        while (!queue.isEmpty()) {
            String current = queue.poll();
            Set<String> words = getWords(current, dic);
            for (String word : words) {
                if (word.equals(end)) {
                    int count = 2;
                    String key = current;
                    while (!key.equals(start)) {
                        key = map.get(key);
                        count++;
                    }

                    return count;

                } else {
                    if (visited.contains(word)) {
                        continue;
                    }
                    map.put(word, current);
                    queue.add(word);
                    visited.add(word);
                }
            }
        }

        return 0;

    }



    /*private Set<String> getWords(String word, Set<String> dic) {
        Set<String> words = new HashSet<>();
        for (int i = 0; i < word.length(); i++) {
            char[] chars = word.toCharArray();
            for (char j = 'a'; j <= 'z'; j++) {
                if (chars[i] == j) {
                    continue;
                }
                chars[i] = j;
                String cWord = new String(chars);
                if (!words.contains(cWord) && dic.contains(cWord)) {
                    words.add(cWord);
                }
            }
        }

        return words;
    }*/


}
