package com.lagostout.common

import org.apache.commons.collections4.iterators.PermutationIterator

class Graphs {

    static List<Set<Integer>> reverse(List<Set<Integer>> adjacencyLists) {
        List<Set<Integer>> reverseAdjacencyLists =
                new ArrayList<Set<Integer>>().withDefault {
                    new HashSet<Integer>()
                }
        adjacencyLists.eachWithIndex {
            Set<Integer> adjacencyList, int index ->
                adjacencyList.each {
                    int vertex ->
                        reverseAdjacencyLists[vertex].add(index)
                }
        }
        reverseAdjacencyLists
    }

    static boolean isATree(Map<Integer, Set<Integer>> adjacencyLists) {
        // Verify graph is a single component
        Set<Integer> visitedVertices = new HashSet<Integer>()
        Deque<Integer> unvisitedVertices = new ArrayDeque<Integer>()
        int vertex = 0
        unvisitedVertices.add(vertex)
        boolean containsALoop = false
        while (!unvisitedVertices.isEmpty()) {
            vertex = unvisitedVertices.pop()
            containsALoop = visitedVertices.contains(vertex)
            if (containsALoop) break
            visitedVertices.add(vertex)
            unvisitedVertices.addAll(adjacencyLists.get(vertex))
        }
        return !containsALoop && visitedVertices.size() == adjacencyLists.size()
    }

    static int edgeCount(Map<Integer, Set<Integer>> adjacencyLists) {
        int count = 0
        for (Set<Integer> adjacencyList : adjacencyLists.values()) {
            if (adjacencyList == null) continue
            count += adjacencyList.size()
        }
        return count
    }

    @SuppressWarnings("ChangeToOperator")
    static List<List<Integer>> findAllHamiltonianPathsUsingBruteForce(
            Map<Integer, Set<Integer>> adjacencyLists) {
        println adjacencyLists
        PermutationIterator<Integer> iterator =
                new PermutationIterator(adjacencyLists.keySet())
        def paths = new ArrayList<List<Integer>>()
        while (iterator.hasNext()) {
            List<Integer> permutation = iterator.next()
            boolean permutationIsAPath = true
            for (int i = 0; i < permutation.size() - 1; i += 2) {
                def vertex = permutation.get(i)
                def nextVertex = permutation.get(i+1)
                if (!adjacencyLists.get(vertex).contains(nextVertex)) {
                    permutationIsAPath = false
                    break
                }
            }
            if (permutationIsAPath) {
                paths.add(permutation)
            }
        }
        return paths
    }

}
