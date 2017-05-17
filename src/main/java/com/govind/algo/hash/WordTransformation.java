package com.govind.algo.hash;

import com.govind.util.arraylist.ArrayListUtils;
import com.govind.util.linkedlist.ListNode;

import java.util.*;

/**
 * Created by govindp on 11/23/2015.
 */

class WordNode {
    String word;
    WordNode previous;
    int numSteps;

    public WordNode(String word, WordNode previous, int numSteps) {
        this.word = word;
        this.numSteps = numSteps;
        this.previous = previous;
    }
}

public class WordTransformation {
    public static void main(String[] args) {
        List<String> dictionary = new ArrayList<>();
        String[] dict = {"hot", "dot", "dog", "lot", "log"};
        dictionary.addAll(Arrays.asList(dict));
        System.out.println(new WordTransformation().ladderLength("hit", "cog", (ArrayList<String>) dictionary));
    }

    public ArrayList<ArrayList<String>> ladderLength(String beginWord, String endWord, ArrayList<String> wordDict) {
        Queue<WordNode> queue = new LinkedList<>();
        queue.add(new WordNode(beginWord, null, 1));
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Set<String> unvisited = new HashSet<>();
        wordDict.add(endWord);
        unvisited.addAll(wordDict);

        int previousDistance = 0;
        while (!queue.isEmpty()) {
            WordNode top = queue.poll();
            if (top.word.equals(endWord)) {
                ArrayList<String> resultArray = new ArrayList<>();
                resultArray.add(endWord);
                WordNode previous= top.previous;
                while (previous != null){
                    resultArray.add(previous.word);
                    previous = previous.previous;
                }

                Collections.reverse(resultArray);
                result.add(resultArray);

            }

            if (previousDistance < top.numSteps) {
                unvisited.removeAll(visited);
            }
            previousDistance = top.numSteps;
            char[] array = top.word.toCharArray();
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < 26; j++) {
                    char character = (char) (j + 'a');
                    if (array[i] == character) {
                        continue;
                    }
                    char cur = array[i];
                    array[i] = character;
                    String modified = String.valueOf(array);
                    if (unvisited.contains(modified)) {
                        queue.add(new WordNode(modified, top, top.numSteps + 1));
                        visited.add(modified);
                    }
                    array[i] = cur;
                }

            }

        }

        return result;

    }

    private Set<String> modifiedWord(WordNode currentWord, Set<String> wordDicationary, Set<String> visited) {
        char[] array = currentWord.word.toCharArray();
        Set<String> result = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < 26; j++) {
                char character = (char) (array[i] + 'a');
                if (array[i] == character) {
                    continue;
                }

                array[i] = character;
                String modified = String.valueOf(array);
                if (wordDicationary.contains(modified)) {
                    result.add(modified);
                    visited.add(modified);
                }
            }
        }

        return result;
    }
}

