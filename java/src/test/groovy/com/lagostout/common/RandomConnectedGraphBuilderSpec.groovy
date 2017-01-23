package com.lagostout.common

import org.apache.commons.math3.random.RandomDataGenerator
import org.apache.commons.math3.util.CombinatoricsUtils
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class RandomConnectedGraphBuilderSpec extends Specification {

    @Shared
    RandomDataGenerator randomDataGenerator

    def setupSpec() {
        randomDataGenerator =
                new RandomDataGenerator()
        randomDataGenerator.reSeed(1)
    }

    @Unroll('vertexCount #vertexCount additionalEdgesPercent #additionalEdgesPercent')
    'has expected edges'(int vertexCount, int additionalEdgesPercent, int expectedEdgeCount) {
        expect:
        def builder = new RandomConnectedGraphBuilder(randomDataGenerator)
        def graph = builder.generate(vertexCount, additionalEdgesPercent)
        Graphs.edgeCount(graph) == expectedEdgeCount

        where:
        [vertexCount, additionalEdgesPercent, expectedEdgeCount] << generateCases()
    }

    def generateCases() {
        (0..5).collect {
            def vertexCount = randomDataGenerator.nextInt(1,5)
            def maxEdgeCount
            def expectedEdgeCount = 0
            def additionalEdgesPercent = 0
            if (vertexCount >= 2) {
                additionalEdgesPercent = [0,50,100].get(randomDataGenerator.nextInt(0,2))
                def spanningTreeEdgeCount = vertexCount - 1
                maxEdgeCount = CombinatoricsUtils.binomialCoefficient(vertexCount, 2)
                expectedEdgeCount = spanningTreeEdgeCount +
                        (maxEdgeCount - spanningTreeEdgeCount) *
                        Math.round(additionalEdgesPercent/100D)
            }
            [vertexCount, additionalEdgesPercent, expectedEdgeCount]
        }
    }

}
