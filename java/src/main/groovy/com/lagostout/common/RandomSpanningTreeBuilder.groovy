package com.lagostout.common

import org.apache.commons.math3.random.RandomDataGenerator

class RandomSpanningTreeBuilder implements GraphTrait {

    RandomDataGenerator randomDataGenerator

    RandomSpanningTreeBuilder(RandomDataGenerator randomDataGenerator) {
        this.randomDataGenerator = randomDataGenerator
    }

    // http://stackoverflow.com/a/14618505/369722
    Map<Integer, Set<Integer>> createByRandomWalk(int vertexCount) {
        def lastVertex = vertexCount - 1
        Set<Integer> unvisitedVertices =
                new HashSet<>(new IntRange(0, lastVertex))
        Set<Integer> visitedVertices = new HashSet<>()
        Map<Integer, Set<Integer>> adjacencyLists = new HashMap<>()
        // Make sure every vertex is in the map, even if the vertex has no edges.
        (0..lastVertex).each { adjacencyLists.put(it, new HashSet<Integer>()) }
        def vertex = 0
        unvisitedVertices.remove(vertex)
        visitedVertices.add(vertex)
        while (!unvisitedVertices.isEmpty()) {
            def otherVertex = this.randomVertex(vertexCount)
            if (!visitedVertices.contains(otherVertex)) {
                adjacencyLists.get(vertex).add(otherVertex)
                adjacencyLists.get(otherVertex).add(vertex)
                unvisitedVertices.remove(otherVertex)
                visitedVertices.add(otherVertex)
            }
            vertex = otherVertex
        }
        adjacencyLists
    }

}
