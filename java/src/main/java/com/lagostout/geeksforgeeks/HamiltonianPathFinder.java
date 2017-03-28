package com.lagostout.geeksforgeeks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.*;
import java.util.stream.IntStream;

public class HamiltonianPathFinder {

    static public void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int caseCount = scanner.nextInt();
        int[] result = new int[caseCount];
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
            result[i] = containsPath(adjacencyLists) ? 1 : 0;
        }
        for (int i : result) {
            System.out.println(i);
        }
    }

    static private boolean containsPath(Map<Integer, Set<Integer>> adjacencyLists) {
        return !findUsingBacktracking(adjacencyLists).isEmpty();
    }

    static private class Frame {
        int vertex;
        int step = 1;
        Frame(int vertex) {
            this.vertex = vertex;
        }
    }

    // Take a frame from the stack.
    // If next step is explore, add frames for its node's
    // children to the stack and add its node to the path.
    // If next step is remove, remove it from the stack
    // and remove its node from the path.

    static public List<Integer> findUsingBacktracking(
            Map<Integer, Set<Integer>> adjacencyLists) {
        List<Integer> result = new ArrayList<>();
        for (int vertex : adjacencyLists.keySet()) {
            Deque<Frame> frameStack = new ArrayDeque<>();
            Deque<Integer> path = new ArrayDeque<>();
            Set<Integer> visited = new HashSet<>();
            frameStack.push(new Frame(vertex));
            // DFS on vertex
            while (!frameStack.isEmpty() &&
                    path.size() < adjacencyLists.size()) {
                Frame frame = frameStack.peek();
                switch (frame.step) {
                    case 1:
                        Set<Integer> adjacencyList =
                                adjacencyLists.get(frame.vertex);
                        path.push(frame.vertex);
                        visited.add(frame.vertex);
                        for (int adjacentVertex : adjacencyList) {
                            if (visited.contains(adjacentVertex)) continue;
                            frameStack.push(new Frame(adjacentVertex));
                        }
                        frame.step = 2;
                        break;
                    case 2:
                        path.pop();
                        frameStack.pop();
                        visited.remove(frame.vertex);
                        break;
                }
            }
            if (path.size() == adjacencyLists.size()) {
                result.addAll(path);
                break;
            }
        }
        return result;
    }

    static private void print(Object obj) {
        Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(obj));
    }

}
