package com.lagostout.common

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

    static boolean isATree(List<Set<Integer>> adjacencyLists) {
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

    static int edgeCount(List<Set<Integer>> adjacencyLists) {
        int count = 0
        for (Set<Integer> adjacencyList : adjacencyLists) {
            if (adjacencyList == null) continue
            count += adjacencyList.size()
        }
        return count
    }

}
