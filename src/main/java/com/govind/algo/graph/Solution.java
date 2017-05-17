package com.govind.algo.graph;

import com.govind.algo.tree.TreeNode;
import com.govind.util.arraylist.ArrayListUtils;
import com.govind.util.graph.Graph;
import com.govind.util.graph.GraphBuildWithEdgeArray;
import com.govind.util.graph.UndirectedGraphNode;
import com.govind.util.linkedlist.ListNode;

import java.util.*;
import java.util.jar.JarEntry;

/**
 * Created by govindp on 11/5/2015.
 */
public class Solution {

    public static Graph getGraph() {
        Graph graph = new Graph(7);
        graph.addEdge(1, 2);
        graph.addEdge(2, 1);
        graph.addEdge(1, 3);
        graph.addEdge(3, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 2);
        graph.addEdge(3, 4);
        graph.addEdge(4, 3);
        graph.addEdge(3, 5);
        graph.addEdge(5, 3);
        graph.addEdge(4, 5);
        graph.addEdge(5, 4);
        graph.addEdge(3, 6);
        graph.addEdge(6, 3);
        return graph;
    }


    private UndirectedGraphNode getUndirectedGraph() {
        UndirectedGraphNode undirectedGraphNode1 = new UndirectedGraphNode(1);
        UndirectedGraphNode undirectedGraphNode2 = new UndirectedGraphNode(2);
        UndirectedGraphNode undirectedGraphNode3 = new UndirectedGraphNode(3);
        UndirectedGraphNode undirectedGraphNode4 = new UndirectedGraphNode(4);
        undirectedGraphNode1.neighbors.add(undirectedGraphNode3);
        undirectedGraphNode3.neighbors.add(undirectedGraphNode1);
        undirectedGraphNode1.neighbors.add(undirectedGraphNode2);
        undirectedGraphNode2.neighbors.add(undirectedGraphNode1);
        undirectedGraphNode2.neighbors.add(undirectedGraphNode4);
        undirectedGraphNode4.neighbors.add(undirectedGraphNode2);
        undirectedGraphNode3.neighbors.add(undirectedGraphNode4);
        undirectedGraphNode4.neighbors.add(undirectedGraphNode3);
//        undirectedGraphNode1.neighbors.add(undirectedGraphNode3);
//        undirectedGraphNode1.neighbors.add(undirectedGraphNode3);
        return undirectedGraphNode1;


    }

    public static void main(String[] args) throws Exception {

        ArrayList<ArrayList<Character>> arrayList = new ArrayList<>();
        arrayList.add(new ArrayListUtils<Character>().getArrayList(new Character[]{'X', 'O', 'X'}));
        arrayList.add(new ArrayListUtils<Character>().getArrayList(new Character[]{'X', 'O', 'X'}));
        arrayList.add(new ArrayListUtils<Character>().getArrayList(new Character[]{'X', 'O', 'X'}));
        arrayList.add(new ArrayListUtils<Character>().getArrayList(new Character[]{'X', 'O', 'X'}));
        new Solution().ladderLength();
        //System.out.println(arrayList);
    }

    public void ladderLength() {
        ArrayList<String> arrayList = new ArrayListUtils<>().getArrayList(new String[]{ "baba", "abba", "aaba", "bbbb", "abaa", "abab", "aaab", "abba", "abba", "abba", "bbba", "aaab", "abaa", "baba" ,"baaa"});
        System.out.println(findLadder("bbaa", "babb", arrayList));
    }



    public  ArrayList<ArrayList<String>> findLadder(String start, String end, ArrayList<String> dictV) {
        ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();
        if (start.equals(end)){
            return arrayLists;
        }
        Queue<String> queue = new LinkedList<>();
        TreeMap<String, String> treeMap = new TreeMap<>();
        queue.add(start);
        Set<String> visited = new HashSet<>();
        ArrayList<String> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            String first = queue.poll();
            Set<String> allModifiedWords = getModifiedWords(first, dictV);
            for (String modifiedWord : allModifiedWords) {
                if (modifiedWord.equals("baba")){
                    System.out.println();
                }
                if (modifiedWord.equals(end)) {
                    result = new ArrayList<>();
                    result.add(end);
                    String previous = first;
                    result.add(first);
                    while (!previous.equals(start)) {
                        String key = previous;
                        previous = treeMap.get(previous);
                        treeMap.remove(key);
                        result.add(previous);
                    }
//                    treeMap = new TreeMap<>();
                    Collections.reverse(result);
                    arrayLists.add(result);


                } else if (dictV.contains(modifiedWord)) {
                    if (!visited.contains(modifiedWord)) {
                        treeMap.put(modifiedWord, first);
                        queue.add(modifiedWord);
                        visited.add(modifiedWord);
                    }

                }
            }
        }
        return arrayLists;
    }


    public int ladderLength(String start, String end, ArrayList<String> dictV) {
        if (start.equals(end)){
            return 1;
        }
        Queue<String> queue = new LinkedList<>();
        TreeMap<String, String> treeMap = new TreeMap<>();
        queue.add(start);
        ArrayList<String> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            String first = queue.poll();
            Set<String> allModifiedWords = getModifiedWords(first, dictV);
            for (String modifiedWord : allModifiedWords) {
                if (modifiedWord.equals(end)) {
                    result.add(end);
                    String previous = first;
                    result.add(first);
                    while (!previous.equals(start)) {
                        previous = treeMap.get(previous);
                        result.add(previous);
                    }
                    System.out.println(result);
                    return result.size();

                } else if (dictV.contains(modifiedWord)) {
                    if (!treeMap.containsKey(modifiedWord)) {
                        treeMap.put(modifiedWord, first);
                        queue.add(modifiedWord);
                    }

                }
            }
        }
        return 0;
    }

    private Set<String> getModifiedWords(String start, ArrayList<String> dictV) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < start.length(); i++) {
            for (int j = 0; j < 26; j++) {
                char character = (char) (j + 'a');
                if (character == start.charAt(i)) {
                    continue;
                }

                String modified = start.substring(0, i) + character + start.substring(i + 1, start.length());
                //           if (dictV.contains(modified))
                set.add(modified);
            }
        }

        return set;
    }


    public void fibSum() {
        int a = 28;
        System.out.println(fibsum(a));
        System.out.println(fibSumOptimezed(a));
    }

    private int fibsumDp(int a) {
        int[] dp = new int[a + 1];
        dp[0] = 1;
        dp[1] = 1;
        Arrays.fill(dp, a);
        int previous = 0;
        int current = 1;
        while (previous + current <= a) {
            int temp = previous;
            previous = current;
            current = temp + current;
            dp[current] = 1;
        }

        for (int i = 2; i <= a; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] + dp[i - j] < dp[i]) {
                    dp[i] = dp[j] + dp[i - j];
                }
            }
        }

        return dp[a];
    }

    public int fibSumOptimezed(int a) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(0);
        arrayList.add(1);
        int i = 1;
        while (arrayList.get(i) + arrayList.get(i - 1) <= a) {
            arrayList.add(arrayList.get(i) + arrayList.get(i - 1));
            i++;
        }


        int count = 0;
        int index = arrayList.size() - 1;
        while (a > 0) {
            while (arrayList.get(index) > a) {
                index--;
            }

            a -= arrayList.get(index);
            count++;
        }

        return count;

    }

    public int fibsum(int a) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(0);
        arrayList.add(1);
        int i = 1;
        while (arrayList.get(i) + arrayList.get(i - 1) <= a) {
            arrayList.add(arrayList.get(i) + arrayList.get(i - 1));
            i++;
        }

        if (arrayList.get(arrayList.size() - 1) == a) {
            return 1;
        }


        int first = arrayList.get(arrayList.size() - 1);
        int current = first;
        int count = 1;
        for (int j = arrayList.size() - 2; j >= 0; j--) {
            if (current + arrayList.get(j) == a) {
                return ++count;
            }

            if (current + arrayList.get(j) < a) {
                current += arrayList.get(j);
                count++;
                count += fibsum(a - current);
                break;
            }

        }
        return count;
    }


    public void knight() {
        int N = 13;
        int M = 17;
        int x1 = 8;
        int y1 = 9;
        int x2 = 4;
        int y2 = 6;

        System.out.println(knight(N, M, x1, y1, x2, y2));
    }

    int[] xCordinate = {-1, -2, -2, -1, 1, 2, 2, 1};
    int[] yCordinate = {2, 1, -1, -2, -2, -1, 1, 2};

    public int knight(int N, int M, int x1, int y1, int x2, int y2) {
        int[][] visited = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], -1);
        }

        if (x1 == x2 && y2 == y1) {
            return 0;
        }
        int result = knightUtil(N, M, x1 - 1, y1 - 1, x2 - 1, y2 - 1, visited);
        return result == Integer.MAX_VALUE
                ? -1
                : result;
    }

    private int knightUtil(int n, int m, int x1, int y1, int x2, int y2, int[][] visited) {


        Queue<Coordinates> queue = new LinkedList<>();
        queue.add(new Coordinates(x1, y1, 0));
        while (!queue.isEmpty()) {
            Coordinates coordinates = queue.poll();
            for (int i = 0; i < 8; i++) {
                int nextX = coordinates.x + xCordinate[i];
                int nextY = coordinates.y + yCordinate[i];
                int distance = coordinates.distance + 1;
                if (nextX < 0 || nextX > n - 1 || nextY < 0 || nextY > m - 1 || visited[nextX][nextY] != -1) {
                    continue;
                }
                if (x2 == nextX && y2 == nextY) {
                    return distance;
                }
                visited[nextX][nextY] = 0;
                queue.add(new Coordinates(nextX, nextY, distance));

            }
        }

        return -1;
    }

    class Coordinates {
        int x;
        int y;
        int distance;

        public Coordinates(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }


    public void cloneGraph() {
        UndirectedGraphNode undirectedGraphNode;
        undirectedGraphNode = cloneGraph(getUndirectedGraph());
        undirectedGraphNode.Bfs();
    }


    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {

        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        return cloneGraphUtilImprovedVersion(node);

    }

    private UndirectedGraphNode cloneGraphUtilImprovedVersion(UndirectedGraphNode node) {
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        UndirectedGraphNode cloned = new UndirectedGraphNode(node.label);
        map.put(node, cloned);
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            UndirectedGraphNode undirectedGraphNode = queue.poll();
            for (int i = 0; i < undirectedGraphNode.neighbors.size(); i++) {
                UndirectedGraphNode neighbour = undirectedGraphNode.neighbors.get(i);
                if (map.containsKey(neighbour)) {
                    map.get(undirectedGraphNode).neighbors.add(map.get(neighbour));
                } else {
                    UndirectedGraphNode neighbourDuplicate = new UndirectedGraphNode(neighbour.label);
                    map.get(undirectedGraphNode).neighbors.add(neighbourDuplicate);
                    map.put(neighbour, neighbourDuplicate);
                    queue.add(neighbour);
                }
            }
        }
        return cloned;

    }

    private UndirectedGraphNode cloneGraphUtil(UndirectedGraphNode node, Map<UndirectedGraphNode, UndirectedGraphNode> map) {
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        Set<UndirectedGraphNode> visited = new HashSet<>();
        UndirectedGraphNode undirectedGraphNode = new UndirectedGraphNode(node.label);
        queue.add(node);
        while (!queue.isEmpty()) {
            UndirectedGraphNode first = queue.poll();
            if (visited.contains(first)) {
                continue;
            }
            UndirectedGraphNode duplicate;
            if (!map.containsKey(first)) {
                duplicate = new UndirectedGraphNode(first.label);
            } else {
                duplicate = map.get(first);
            }
            if (map.size() == 0)
                map.put(first, duplicate);
            visited.add(first);
            for (int i = 0; i < first.neighbors.size(); i++) {
                UndirectedGraphNode neighbour = first.neighbors.get(i);
                UndirectedGraphNode neighbourDuplicate = new UndirectedGraphNode(neighbour.label);
                if (map.containsKey(neighbour)) {
                    neighbourDuplicate = map.get(neighbour);
                } else {
                    map.put(neighbour, neighbourDuplicate);
                }

                queue.add(neighbour);
                duplicate.neighbors.add(neighbourDuplicate);
            }
        }

        return map.get(node);
    }

    public void sortedListToBst() {
        ListNode listNode = ListNode.toListNode(new int[]{1, 2, 3, 4, 5});
        TreeNode.inorder(sortedListToBST(listNode));
    }


    public TreeNode sortedListToBST(ListNode a) {
        int length = 0;
        this.head = a;
        while (a != null) {
            length++;
            a = a.next;
        }


        //return sortedListToBST(head, 0, length - 1);
        return sortedListToBSTWithoudTraversingToFindMid(length);
    }

    ListNode head;

    private TreeNode sortedListToBSTWithoudTraversingToFindMid(int length) {
        if (length == 0) {
            return null;
        }


        TreeNode treeNode = new TreeNode(0);

        treeNode.left = sortedListToBSTWithoudTraversingToFindMid(length / 2);
        treeNode.val = head.val;
        head = head.next;
        treeNode.right = sortedListToBSTWithoudTraversingToFindMid(length - length / 2 - 1);
        return treeNode;
    }

    private TreeNode sortedListToBST(ListNode a, int start, int end) {
        if (start > end) {
            return null;
        }

        ListNode current = a;
        int mid = start + (end - start) / 2;
        int len = 0;
        while (len != mid) {
            current = current.next;
            len++;
        }
        TreeNode treeNode = new TreeNode(current.val);
        treeNode.left = sortedListToBST(a, start, mid - 1);
        treeNode.right = sortedListToBST(a, mid + 1, end);
        return treeNode;
    }


    public void stepnum() {
//        System.out.println(stepnum(100, 1000));
        int a = 100;
        int b = 200;
        ArrayList<Integer> result = new ArrayList<>();

        int back = a;
        int len = 0;
        while (a > 0) {
            a /= 10;
            len++;
        }

        stepNumUsingGraphConcept(a, b, result, back % 10, len);
        Collections.sort(result);
        System.out.println(result);

    }

    private void stepNumUsingGraphConcept(int x, int y, ArrayList<Integer> arrayList, int number, int len) {
        if (number > x && number < y) {
            arrayList.add(number);
        }

        if (len == 0) {
            return;
        }

        if (number == 0) {
            for (int i = 1; i <= 9; i++) {
                stepNumUsingGraphConcept(x, y, arrayList, i, len);
            }
            len--;
            return;
        }

        if (number % 10 == 0) {
            stepNumUsingGraphConcept(x, y, arrayList, number * 10 + number % 10 + 1, len - 1);
        }
        if (number % 10 == 9) {
            stepNumUsingGraphConcept(x, y, arrayList, number * 10 + number % 10 - 1, len - 1);
        } else {
            stepNumUsingGraphConcept(x, y, arrayList, number * 10 + number % 10 - 1, len - 1);
            stepNumUsingGraphConcept(x, y, arrayList, number * 10 + number % 10 + 1, len - 1);
        }
    }

    public ArrayList<Integer> stepnum(int a, int b) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = a; i <= b; i++) {
            int first = 0;
            int previous = -1;
            int j = 0;
            for (j = i; j != 0; j /= 10, previous = first) {
                first = j % 10;

                if (previous != -1 && Math.abs(previous - first) == 1) {
                    previous = first;
                    continue;
                } else if (previous != -1) {
                    break;
                }

            }
            if (j == 0) {
                arrayList.add(i);
            }
        }

        return arrayList;
    }


    public void solve(ArrayList<ArrayList<Character>> a) {
        int m = a.size();
        int n = a.get(0).size();
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).get(0) == 'O') {
                bfs(a, i, 0);
            }
            if (a.get(i).get(n - 1) == 'O') {
                bfs(a, i, n - 1);
            }
        }

        for (int i = 0; i < a.get(0).size(); i++) {
            if (a.get(0).get(i) == 'O') {
                bfs(a, 0, i);
            }
            if (a.get(m - 1).get(i) == 'O') {
                bfs(a, m - 1, i);
            }
        }

        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < a.get(0).size(); j++) {
                if (a.get(i).get(j) == 'O') {
                    a.get(i).set(j, 'X');
                } else if (a.get(i).get(j) == '#') {
                    a.get(i).set(j, 'O');
                }
            }
        }
    }


    private void bfs(ArrayList<ArrayList<Character>> a, int i, int j) {
        Queue<Cordinates> queue = new LinkedList<>();
        queue.add(new Cordinates(i, j));
        while (!queue.isEmpty()) {
            Cordinates cordinates = queue.poll();
            if (cordinates.i < 0 || cordinates.i > a.size() - 1 || cordinates.j < 0 || cordinates.j > a.get(cordinates.i).size() - 1 || a.get(cordinates.i).get(cordinates.j) == 'X' || a.get(cordinates.i).get(cordinates.j) == '#') {
                continue;
            }
            a.get(cordinates.i).set(cordinates.j, '#');

            queue.add(new Cordinates(cordinates.i, cordinates.j + 1));
            queue.add(new Cordinates(cordinates.i, cordinates.j - 1));
            queue.add(new Cordinates(cordinates.i + 1, cordinates.j));
            queue.add(new Cordinates(cordinates.i - 1, cordinates.j));
        }
    }

    public class Cordinates {
        int i;
        int j;

        public Cordinates(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    private void visit(boolean[][] visited, ArrayList<String> a, int i, int j) {
        if (i < 0 || i > a.size() - 1 || j < 0 || j > a.get(i).length() - 1 || visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        if (a.get(i).charAt(j) == 'X') {
            visit(visited, a, i, j + 1);
            visit(visited, a, i + 1, j);
            visit(visited, a, i, j - 1);
            visit(visited, a, i - 1, j);
        }
    }


    public int black(ArrayList<String> a) {
        int count = 0;
        boolean[][] visited = new boolean[a.size()][a.get(0).length()];
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < a.get(0).length(); j++) {
                if (!visited[i][j] && a.get(i).charAt(j) == 'X') {
                    visit(visited, a, i, j);
                    count++;
                }
            }

        }

        return count;
    }

    public static GraphBuildWithEdgeArray getGraphWithEdgeArray() {
        GraphBuildWithEdgeArray graphBuildWithEdgeArray = new GraphBuildWithEdgeArray(4, 4);
        graphBuildWithEdgeArray.addEdge(0, 1);
        graphBuildWithEdgeArray.addEdge(1, 2);
        graphBuildWithEdgeArray.addEdge(2, 3);
        graphBuildWithEdgeArray.addEdge(3, 0);

        return graphBuildWithEdgeArray;
    }

    private void getCount() {
        return;
    }


    class Subset {
        int rank;
        int parent;
    }

    public void findCycleThroughUnion() {
        GraphBuildWithEdgeArray graph = getGraphWithEdgeArray();
        int vertices = graph.getV();
        Subset[] subsets = new Subset[vertices];
        for (int i = 0; i < vertices; i++) {
            subsets[i] = new Subset();
            subsets[i].rank = 0;
            subsets[i].parent = i;
        }

        for (int i = 0; i < graph.getEdge().length; i++) {
            int x = findParent(graph.getEdge()[i].start, subsets);
            int y = findParent(graph.getEdge()[i].end, subsets);

            if (x == y) {
                System.out.println("Cycle is found");
                break;
            }
            union(x, y, subsets);
        }

        System.out.println("No cycle");
    }

    private void union(int x, int y, Subset[] subsets) {
        if (subsets[x].rank > subsets[y].rank) {
            subsets[y].parent = x;
        } else if (subsets[x].rank < subsets[y].rank) {
            subsets[x].parent = y;
        } else {
            subsets[y].parent = x;
            subsets[x].rank++;
        }
    }

    private int findParent(int child, Subset[] subsets) {
        if (subsets[child].parent != child) {
            subsets[child].parent = findParent(subsets[child].parent, subsets);
        }

        return subsets[child].parent;
    }

    public void bridge() {
        Graph graph = getGraph();
        int V = graph.numberOfVertices();
        Integer[] parent = new Integer[V];
        boolean[] visited = new boolean[V];
        boolean[] articulated = new boolean[V];
        int[] low = new int[V];
        int[] disc = new int[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i])
                findBridge(graph, i, visited, parent, articulated, low, disc, new Time());
        }


    }


    private void findBridge(Graph graph, int index, boolean[] visited, Integer[] parent, boolean[] articulated, int[] low, int[] disc, Time time) {
        visited[index] = true;
        disc[index] = low[index] = ++time.discoveryTime;
        int children = 0;
        for (int neighbour : graph.getAdjacentVertices(index)) {
            if (!visited[neighbour]) {
                children++;
                parent[neighbour] = index;
                findBridge(graph, neighbour, visited, parent, articulated, low, disc, time);
                //check if subtree rooted in neighbour has a backedge to the ancestor of index
                low[index] = Math.min(low[index], low[neighbour]);

                if (low[neighbour] > disc[index]) {
                    System.out.println(index + " -> " + neighbour);
                }

            } else if (neighbour != parent[index]) {
                low[index] = Math.min(low[index], disc[neighbour]);
            }

        }
    }


    public void articulatedPoint() {
        Graph graph = getGraph();
        int V = graph.numberOfVertices();
        Integer[] parent = new Integer[V];
        boolean[] visited = new boolean[V];
        boolean[] articulated = new boolean[V];
        int[] low = new int[V];
        int[] disc = new int[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i])
                findArticulted(graph, i, visited, parent, articulated, low, disc, new Time());
        }


        for (int i = 0; i < articulated.length; i++) {
            if (articulated[i]) {
                System.out.print(i + " ");
            }
        }
    }


    private void findArticulted(Graph graph, int index, boolean[] visited, Integer[] parent, boolean[] articulated, int[] low, int[] disc, Time time) {
        visited[index] = true;
        disc[index] = low[index] = ++time.discoveryTime;
        int children = 0;
        for (int neighbour : graph.getAdjacentVertices(index)) {
            if (!visited[neighbour]) {
                children++;
                parent[neighbour] = index;
                findArticulted(graph, neighbour, visited, parent, articulated, low, disc, time);
                //check if subtree rooted in neighbour has a backedge to the ancestor of index
                low[index] = Math.min(low[index], low[neighbour]);
                if (parent[index] == null && children > 1) {
                    articulated[index] = true;
                }

                if (parent[index] != null && low[neighbour] >= disc[index]) {
                    articulated[index] = true;
                }

            } else {
                low[index] = Math.min(low[index], disc[neighbour]);
            }

        }


    }

    class Time {
        int discoveryTime;
    }


    public void detectCycleInNonDirectedGraph() {
        Graph graph = getGraph();
        boolean[] visited = new boolean[graph.numberOfVertices()];
    }

    public void detectCycle() {
        Graph graph = getGraph();
        boolean[] visited = new boolean[graph.numberOfVertices()];
        for (int i = 0; i < graph.numberOfVertices(); i++) {
            if (!visited[i]) {
                if (dfs(graph, i, visited)) {
                    break;
                }
            }
        }
    }

    private boolean dfs(Graph graph, int vertex, boolean[] visited) {
        visited[vertex] = true;
        for (int ver : graph.getAdjacentVertices(vertex)) {
            if (!visited[ver]) {
                System.out.print("Cycle is detected");
                return true;
            }
            dfs(graph, ver, visited);

        }
        return false;
    }


    public int parseSample(String sentence, int start, int end, Set<String> dictionary) {
        if (end >= sentence.length()) {
            return end - start;
        }
        String word = sentence.substring(start, end + 1);
        if (word.equals("th")) {
            System.out.println();
        }
        int exactWord = parseSample(sentence, start + 1, end + 1, dictionary);
        if (!dictionary.contains(word)) {
            exactWord += word.length();
        }

        int extractedWord = parseSample(sentence, start, end + 1, dictionary);
        return Math.min(exactWord, extractedWord);
    }

    public int parseSimple(int wordStart, int wordEnd, String sentence, Set<String> dictionary) {
        if (wordEnd >= sentence.length()) {
            return wordEnd - wordStart;
        }

        String word = sentence.substring(wordStart, wordEnd + 1);

        if (word.equals("th")) {
            System.out.println();
        }
         /* break current word */
        int bestExact = parseSimple(wordEnd + 1, wordEnd + 1, sentence, dictionary);
        if (!dictionary.contains(word)) {
            bestExact += word.length();
        }

         /* extend current word */
        int bestExtend = parseSimple(wordStart, wordEnd + 1, sentence, dictionary);

         /* find best */
        return Math.min(bestExact, bestExtend);
    }


    public void lexography(String[] words, int alpha) {
        Graph graph = new Graph(alpha);
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = 0; j < Math.min(words[i].length(), words[i + 1].length()); j++) {
                if (words[i].charAt(j) != words[i + 1].charAt(j)) {
                    graph.addEdge(words[i].charAt(j) - 'a', words[i + 1].charAt(j) - 'a');
                    break;
                }
            }
        }

        topologicalSort(graph);
    }

    public void topologicalSort(Graph graph) {
        boolean[] visited = new boolean[graph.numberOfVertices()];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < graph.numberOfVertices(); i++) {
            if (!visited[i]) {
                topologicalSort(graph, i, visited, stack);
            }
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }

    }

    private void topologicalSort(Graph graph, int top, boolean[] visited, Stack<Integer> stack) {
        visited[top] = true;
        for (int adj : graph.getAdjacentVertices(top)) {
            if (!visited[adj]) {
                topologicalSort(graph, adj, visited, stack);
            }
        }

        stack.push(top);
    }


    public void DFS(Graph graph) {
        Stack<Integer> stack = new Stack<>();
        stack.add(0);
        boolean[] visited = new boolean[graph.numberOfVertices()];
        visited[0] = true;
        while (!stack.isEmpty()) {
            int top = stack.pop();
            System.out.print(top + " ");
            for (int adj : graph.getAdjacentVertices(top)) {
                if (!visited[adj]) {
                    visited[adj] = true;
                    stack.push(adj);

                }
            }
        }
    }

    public void BFS(Graph graph) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] isVisited = new boolean[graph.numberOfVertices()];
        queue.add(0);
        isVisited[0] = true;

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            System.out.println(vertex);
            List<Integer> adjacent = graph.getAdj()[vertex];
            for (int i = 0; i < adjacent.size() && (!isVisited[adjacent.get(i)]); i++) {
                queue.add(adjacent.get(i));
                isVisited[adjacent.get(i)] = true;
            }
        }
    }
}
