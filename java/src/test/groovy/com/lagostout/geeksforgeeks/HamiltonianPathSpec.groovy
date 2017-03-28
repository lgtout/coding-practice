package com.lagostout.geeksforgeeks

import com.lagostout.common.Graphs
import com.lagostout.common.RandomConnectedGraphBuilder
import org.apache.commons.math3.random.RandomDataGenerator
import spock.lang.Specification
import spock.lang.Unroll
import org.apache.commons.lang3.Range

class HamiltonianPathSpec extends Specification {

    @SuppressWarnings("GrMethodMayBeStatic")
    @Unroll("graph #graph hamiltonianPathExists #hamiltonianPathExists")
    'detects existence of hamiltonian path using backtracking - static cases'(
            Map<Integer, Set<Integer>> graph, boolean hamiltonianPathExists) {
        when:
        List<Integer> path = HamiltonianPathFinder.findUsingBacktracking(graph)

        then:
        resultMatchesExpectation(hamiltonianPathExists, graph, path)

        where:
        [graph, hamiltonianPathExists] << buildStaticCases()
    }

    @SuppressWarnings("GrMethodMayBeStatic")
    @Unroll("graph #graph")
    'finds a hamiltonian path using backtracking - random cases'(
    Map<Integer, Set<Integer>> graph, List<List<Integer>> paths) {
        when:
        List<Integer> path = HamiltonianPathFinder.findUsingBacktracking(graph)

        then:
        if (!paths.isEmpty()) assert paths.contains(path)
        else assert path.isEmpty()

        where:
        [graph, paths] << buildRandomCases(
                1000, Range.between(3,5), Range.between(0,100))

    }

    static List buildRandomCases(
            int caseCount,
            Range<Integer> vertexCountRange,
            Range<Integer> additionalEdgeSaturationPercentRange) {
        List cases = new ArrayList<>()
        RandomDataGenerator random = new RandomDataGenerator()
        random.reSeed(1)
        caseCount.times {
            def builder = new RandomConnectedGraphBuilder(random)
            def vertexCount = random.nextInt(
                    vertexCountRange.minimum,
                    vertexCountRange.maximum)
            def additionalEdgeSaturationPercent = random.nextInt(
                    additionalEdgeSaturationPercentRange.minimum,
                    additionalEdgeSaturationPercentRange.maximum)
            def graph = builder.build(
                    vertexCount, additionalEdgeSaturationPercent)
            def hamiltonianPaths = Graphs.
                    findAllHamiltonianPathsUsingBruteForce(graph)
            cases << [graph, hamiltonianPaths]
        }
        return cases
    }

    static Map<Integer, Set<Integer>> graphFromAdjacencyLists(
            List<List<Integer>> adjacencyLists) {
        Map<Integer, Set<Integer>> graph = new HashMap().withDefault {
            new HashSet<>()
        }
        for (int i = 0; i < adjacencyLists.size(); i++) {
            List<Integer> adjacencyList = adjacencyLists.get(i)
            Set<Integer> adjacentVertices = graph.get(i)
            // Backward edges are already specified in adjacencyLists,
            // so no need to explicitly add them here.
            adjacencyList.each {
                adjacentVertices.add(it)
            }
        }
        graph
    }

    static List<Object> buildStaticCases() {
        // Construct graph adjacency lists from edges.
        Closure<Map<Integer, Set<Integer>>> graphFromEdgeList = {
            List<List<Integer>> edges ->
                Map<Integer, Set<Integer>> graph = new HashMap()
                for (List<Integer> edge : edges) {
                    // Forward edge
                    graph.putIfAbsent(edge[0], new HashSet<Integer>())
                    graph.get(edge[0]).add(edge[1])
                    // Backward edge
                    graph.putIfAbsent(edge[1], new HashSet<Integer>())
                    graph.get(edge[1]).add(edge[0])
                }
                graph
        }
        [
                [[[[]], true],
                 [[[1],[0]], true],
                 [[[1],[0,2],[1]], true],
                 [[[1,2],[0],[0]], true],
                 [[[1,2],[0,2,3],[0,1],[1]], true],
                 [[[1,2],[0,2,3,4],[0,1],[1],[1]], false],
                ].collect {
                    [graphFromAdjacencyLists(
                            it[0] as List<List<Integer>>), it[1]]
                },
                [
                        [[[2,1],[10,2],[6,3],[5,4],
                          [10,5],[10,6],[6,7],[6,8],
                          [10,9],[4,9],[3,8],[3,7],
                          [5,9],[6,5]], true],
                ].collect {
                    [graphFromEdgeList(it[0]), it[1]]
                }
        ].collectMany { it }
    }

    static void resultMatchesExpectation(boolean expected,
                                    Map<Integer, Set<Integer>> graph,
                                    List<Integer> path) {
        if (expected) {
            // Path has the same number of vertices as the graph
            assert path.size() == graph.size()
            for (int i = 0; i < path.size(); i++) {
                if (i < path.size() - 1) {
                    // Graph contains all the expected paths
                    assert graph.get(path.get(i)).contains(path.get(i + 1))
                }
            }
        } else {
            assert path.isEmpty()
        }
    }

}
