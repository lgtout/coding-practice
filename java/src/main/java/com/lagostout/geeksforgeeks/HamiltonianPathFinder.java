package com.lagostout.geeksforgeeks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HamiltonianPathFinder {

    static public void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        int caseCount = scanner.nextInt();
        int[] result = new int[caseCount];
        HamiltonianPathFinder finder = new HamiltonianPathFinder();
        for (int i = 0; i < caseCount; i++) {
            int vertexCount = scanner.nextInt();
            int edgeCount = scanner.nextInt();
            final Map<Integer, Set<Integer>> adjacencyLists = new HashMap<>();
            IntStream.rangeClosed(1,vertexCount).
                    forEach(k -> adjacencyLists.put(k, new HashSet<>()));
            for (int j = 0; j < edgeCount; j++) {
                int v1 = scanner.nextInt();
                int v2 = scanner.nextInt();
                adjacencyLists.get(v1).add(v2);
                adjacencyLists.get(v2).add(v1);
            }
            result[i] = finder.containsPath(adjacencyLists) ? 1 : 0;
        }
        Arrays.stream(result).forEach(System.out::println);
    }

    public boolean containsPath(Map<Integer, Set<Integer>> adjacencyLists) {
        return !findUsingBacktracking(adjacencyLists).isEmpty();
    }

    public List<Integer> findUsingBacktracking(Map<Integer, Set<Integer>> adjacencyLists) {
        Set<Integer> visitedVertices = new HashSet<>();
        Deque<Frame> stack = new ArrayDeque<>();
        boolean pathFound = false;
        for (int vertex : adjacencyLists.keySet()) {
            Frame frame = new Frame(vertex, adjacencyLists.get(vertex));
            stack.push(frame);
            visitedVertices.clear();
            visitedVertices.add(vertex);
            pathFound = false;
            while (!stack.isEmpty()) {
                pathFound = stack.size() == adjacencyLists.size();
                if (pathFound) break;
                frame = stack.peek();
                Integer nextVertex = null;
                boolean foundNext = false;
                while (!frame.adjacentVertices.isEmpty()) {
                    nextVertex = frame.adjacentVertices.pop();
                    foundNext = !visitedVertices.contains(nextVertex);
                    if (foundNext) break;
                }
                if (foundNext) {
                    visitedVertices.add(nextVertex);
                    stack.push(new Frame(nextVertex, adjacencyLists.get(nextVertex)));
                } else {
                    stack.pop();
                }
            }
            if (pathFound) break;
        }
        print(pathFound);
        print(stack);
        @SuppressWarnings("UnnecessaryLocalVariable")
        List<Integer> path = stack.stream()
                .mapToInt(frame -> frame.vertex).boxed()
                .collect(Collectors.toList());
        print(stack);
        print(path);
        return path;
    }

    static private class Frame {
        int vertex;
        Deque<Integer> adjacentVertices = new ArrayDeque<>();
        int nextAction = 0;
        Frame(int vertex, Set<Integer> adjacentVertices) {
            this.vertex = vertex;
            this.adjacentVertices.addAll(adjacentVertices);
        }
        public void next() {
            nextAction++;
        }
    }

    static private void print(Object obj) {
        Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(obj));
    }

}
