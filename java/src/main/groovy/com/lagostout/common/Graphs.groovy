package com.lagostout.common

import org.apache.commons.collections4.iterators.PermutationIterator

class Graphs {

    static <T extends Comparable> boolean satisfiesMaxHeapProperty(List<T> tree) {
        false
    }

    static <T extends Comparable> boolean satisfiesMinHeapProperty(List<T> tree) {
        def satisfied = true
        for (int index = 0; index < tree.size(); index++) {
            def parent = tree[index]
            def leftChild = ZeroBasedHeapIndexHelper.getLeftChild(index, tree)
            def rightChild = ZeroBasedHeapIndexHelper.getRightChild(index, tree)
            satisfied = (leftChild == null || parent < leftChild) &&
                    (rightChild == null || parent < rightChild)
            if (!satisfied) {
                break
            }
        }
        satisfied
    }

    interface HeapPropertyTester<T extends Comparable<?>> {
        boolean satisfiesHeapProperty(T parent, T child)
    }

    class MinHeapPropertyTester<T extends Comparable<?>>
            implements HeapPropertyTester<T> {
        @Override
        boolean satisfiesHeapProperty(T parent, T child) {
            // TODO Null handling?
            return parent.compareTo(child) < 0
        }
    }

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

    /**
     * Verifies the adjacency lists represent a tree by verifying
     * that it represents a single connected component that
     * contains no cycles, and that it implements each undirected edge
     * between adjacent vertices as 2 edges, one in each direction.
     *
     * @param adjacencyLists adjacency lists representing a graph.
     * @return Whether the <code>adjacencyLists</code> graph is a tree.
     */
    static boolean isATree(
            Map<Integer, Set<Integer>> adjacencyLists) {
        Set<Integer> visitedVertices = new HashSet<Integer>()
        Deque<Integer> unvisitedVertices = new ArrayDeque<Integer>()
        // Maintain a stack of previous vertices, so we can prevent
        // following a reverse edge, which would cause doubling back
        // on the current path.
        Deque<Integer> previousVertices = new ArrayDeque<Integer>()
        int vertex = 0
        unvisitedVertices.add(vertex)
        final int NULL_PARENT = -1
        previousVertices.add(NULL_PARENT)
        boolean containsACycle = false
        boolean backwardPointingEdgeIsMissing = false
        while (!unvisitedVertices.isEmpty() && !backwardPointingEdgeIsMissing) {
            vertex = unvisitedVertices.pop()
            containsACycle = visitedVertices.contains(vertex)
            if (containsACycle) break
            visitedVertices.add(vertex)
            // Ignore any edge pointing to the most recent vertex
            // on the path.  This prevents reverse edges from behaving
            // like cycles.
            Integer previousVertex = previousVertices.pop()
            backwardPointingEdgeIsMissing = false
            for (int adjacentVertex : adjacencyLists.get(vertex)) {
                // Verify adjacent vertices have backward pointing edge.
                backwardPointingEdgeIsMissing = !adjacencyLists.get(adjacentVertex).contains(vertex)
                if (backwardPointingEdgeIsMissing) break
                // Store next edges to explore
                if (adjacentVertex != previousVertex) {
                    unvisitedVertices.add(adjacentVertex)
                    previousVertices.add(vertex)
                }
            }
        }
        return !containsACycle &&
                !backwardPointingEdgeIsMissing &&
                visitedVertices.size() == adjacencyLists.size()
    }

    static int edgeCount(Map<Integer, Set<Integer>> adjacencyLists) {
        int count = 0
        for (Set<Integer> adjacencyList : adjacencyLists.values()) {
            if (adjacencyList == null) continue
            count += adjacencyList.size()
        }
        // The adjacency data structure represent an undirected edge with 2
        // "edges" - one in each direction between any two vertices.
        // So the number of edges is actually half what's stored
        // by the adjacency data structure
        return count/2
    }

    @SuppressWarnings("ChangeToOperator")
    static List<List<Integer>> findAllHamiltonianPathsUsingBruteForce(
            Map<Integer, Set<Integer>> adjacencyLists) {
        PermutationIterator<Integer> iterator =
                new PermutationIterator(adjacencyLists.keySet())
        def paths = new ArrayList<List<Integer>>()
        while (iterator.hasNext()) {
            List<Integer> permutation = iterator.next()
//            println "permutation $permutation"
            boolean permutationIsAPath = true
            for (int i = 0; i < permutation.size() - 1; i++) {
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
