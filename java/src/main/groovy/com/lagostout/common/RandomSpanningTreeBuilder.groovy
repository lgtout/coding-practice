package com.lagostout.common

import org.apache.commons.math3.random.RandomDataGenerator

class RandomSpanningTreeBuilder implements GraphTrait {

    RandomDataGenerator randomDataGenerator

    RandomSpanningTreeBuilder(RandomDataGenerator randomDataGenerator) {
        this.randomDataGenerator = randomDataGenerator
    }

    List<Set<Integer>> createByRandomWalk(int vertexCount) {
        Set<Integer> unvisitedVertices =
                new HashSet<>(new IntRange(0, vertexCount - 1))
        Set<Integer> visitedVertices = new HashSet<>()
        List<Set<Integer>> adjacencyLists = new ArrayList<Set<Integer>>()
                .withDefault { new HashSet<Integer>() }
        def vertex = 0
        unvisitedVertices.remove(vertex)
        visitedVertices.add(vertex)
        while (!unvisitedVertices.isEmpty()) {
            def otherVertex = this.randomVertex(vertexCount)
            if (!visitedVertices.contains(otherVertex)) {
                unvisitedVertices.remove(otherVertex)
                adjacencyLists.get(vertex).add(otherVertex)
                visitedVertices.add(otherVertex)
            }
            vertex = otherVertex
        }
        adjacencyLists
    }

}
