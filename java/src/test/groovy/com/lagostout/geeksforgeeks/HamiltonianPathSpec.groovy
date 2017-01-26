package com.lagostout.geeksforgeeks

import com.lagostout.common.Graphs
import com.lagostout.common.RandomConnectedGraphBuilder
import org.apache.commons.math3.random.RandomDataGenerator
import spock.lang.Specification
import spock.lang.Unroll
import org.apache.commons.lang3.Range

class HamiltonianPathSpec extends Specification {

    @Unroll("graph #graph expected #expected")
    'finds hamiltonian path using backtracking - static cases'(
            Map<Integer, Set<Integer>> graph, boolean expected) {
        when:
        List<Integer> path = new HamiltonianPathFinder().findUsingBacktracking(graph)

        then:
        resultMatchesExpectation(expected, graph, path)

        where:
        [graph, expected] << buildStaticCases()
    }

    @Unroll("graph #graph paths #paths")
    'finds hamiltonian path using backtracking - random cases'(
    Map<Integer, Set<Integer>> graph, List<List<Integer>> paths) {
        when:
        List<Integer> path = new HamiltonianPathFinder().findUsingBacktracking(graph)

        then:
        if (!paths.isEmpty()) assert paths.contains(path)
        else assert path.isEmpty()

        where:
        [graph, paths] << buildRandomCases(
                3, Range.between(1,4), Range.between(0,100))

    }

    List buildRandomCases(int caseCount,
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
            println vertexCount
            println additionalEdgeSaturationPercent
            def graph = builder.build(vertexCount, additionalEdgeSaturationPercent)
            println graph
            def hamiltonianPaths = Graphs.findAllHamiltonianPathsUsingBruteForce(graph)
            cases << [graph, hamiltonianPaths]
        }
        return cases
    }

    List<Object> buildStaticCases() {
        [
//                [[[[]], true],
//                 [[[1],[0]], true],
//                 [[[1],[0,2],[1]], true],
//                 [[[1,2],[0],[0]], true],
//                 [[[1,2],[0,2,3],[0,1],[1]], true],
//                 [[[1,2],[0,2,3,4],[0,1],[1],[1]], false],
//                ].collect {
//                    Map<Integer, Set<Integer>> graph = new HashMap()
//                    List<Integer> adjancencyList = it[0] as List<Integer>
//                    if (!adjancencyList.isEmpty()) {
//                        it[0].eachWithIndex {
//                            List adjacencies, vertex ->
//                                graph.put(vertex, new HashSet<>(adjacencies))
//                        }
//                    }
//                    [graph, it[1]]
//                },
//                [
//                        [[[2,1],[10,2],[6,3],[5,4],
//                          [10,5],[10,6],[6,7],[6,8],
//                          [10,9],[4,9],[3,8],[3,7],
//                          [5,9],[6,5]], true],
//                ].collect {
//                    List<List<Integer>> edges = it[0] as List
//                    Map<Integer, Set<Integer>> graph = new HashMap()
//                    for (List<Integer> edge : edges) {
//                        graph.putIfAbsent(edge[0], new HashSet<Integer>())
//                        graph.get(edge[0]).add(edge[1])
//                        graph.putIfAbsent(edge[1], new HashSet<Integer>())
//                        graph.get(edge[1]).add(edge[0])
//                    }
////                    println graph
//                    [graph, it[1]]
//                }
        ].collectMany { it }
    }

    def resultMatchesExpectation(boolean expected,
                               Map<Integer, Set<Integer>> graph,
                               List<Integer> path) {
        if (expected) {
            assert path.size() == graph.size()
            for (int i = 0; i < path.size(); i++) {
                if (i < path.size() - 1) {
                    assert graph.get(path.get(i)).contains(path.get(i+1))
                }
            }
        } else {
            assert path.isEmpty()
        }
    }

}
