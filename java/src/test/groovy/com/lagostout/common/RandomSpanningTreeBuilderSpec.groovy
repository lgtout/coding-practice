package com.lagostout.common

import org.apache.commons.math3.random.RandomDataGenerator
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll
import static com.lagostout.common.Graphs.*

class RandomSpanningTreeBuilderSpec extends Specification {

    @Shared
    RandomDataGenerator randomDataGenerator

    def setupSpec() {
        randomDataGenerator =
                new RandomDataGenerator()
        randomDataGenerator.reSeed(1)
    }

    @Unroll('vertexCount #vertexCount')
    'tree is valid'(int vertexCount) {

        expect:
        def builder = new RandomSpanningTreeBuilder(randomDataGenerator)
        def tree = builder.createByRandomWalk(vertexCount)
        isATree(tree)

        where:
        vertexCount << [(0..10).collect {
            randomDataGenerator.nextInt(1, 10)
        }, [1] // Make sure this case is handled
        ].collectMany { it }
//        vertexCount << [2]

    }

}
