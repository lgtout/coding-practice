package com.lagostout.common

import org.apache.commons.math3.random.RandomDataGenerator
import org.apache.commons.math3.util.CombinatoricsUtils

class RandomConnectedGraphBuilder implements GraphTrait {

    RandomDataGenerator randomDataGenerator

    RandomConnectedGraphBuilder(RandomDataGenerator dataGenerator) {
        this.randomDataGenerator = dataGenerator
    }

    /**
     * Use random walk strategy to build a connected graph.
     * We build a spanning tree, then add the provided fraction
     * of additional edges.
     *
     * Derived from <a href="http://stackoverflow.com/a/14618505/369722">Stackoverflow</a>
     *
     * @param vertexCount Number of vertices in the graph
     * @param additionalEdgeSaturationPercent A fraction of the number of edges (percent: 0-100) that can be added to the initial spanning tree to make a complete graph.
     * @param randomDataGenerator
     * @return A connected graph, with no cycles, containing <code>(vertexCount - 1 + additionalEdges)</code> edges
     */
    Map<Integer, Set<Integer>> build(
            int vertexCount, int additionalEdgeSaturationPercent) {
        def randomSpanningTreeGenerator =
                new RandomSpanningTreeBuilder(randomDataGenerator)
        def adjacencyLists = randomSpanningTreeGenerator
                .createByRandomWalk(vertexCount)
        def possibleAdditionalEdgesCount = 0
        if (vertexCount >= 2) {
            int maximumEdgeCount = (vertexCount - 1)
            possibleAdditionalEdgesCount =
                    CombinatoricsUtils.
                            binomialCoefficient(vertexCount, 2) - maximumEdgeCount
        }
        def additionalEdgesCount = possibleAdditionalEdgesCount *
                (additionalEdgeSaturationPercent % 101) / 100D
        while (additionalEdgesCount > 0) {
            def vertex = randomVertex(vertexCount)
            def otherVertex = randomVertex(vertexCount)
            def adjacencyList = adjacencyLists.get(vertex)
            if (!adjacencyList.contains(otherVertex)) {
                adjacencyList.add(otherVertex)
                additionalEdgesCount--
            }
        }
        return adjacencyLists
    }

}
