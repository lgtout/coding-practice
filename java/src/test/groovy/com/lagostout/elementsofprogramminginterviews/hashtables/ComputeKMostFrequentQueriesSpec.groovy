package com.lagostout.elementsofprogramminginterviews.hashtables

import spock.lang.Specification
import spock.lang.Unroll

class ComputeKMostFrequentQueriesSpec extends Specification {

    @Unroll
    def 'computes k most frequent queries'(
            List<String> queries,
            int k, List<String> expected) {

        expect:
        ComputeKMostFrequentQueries.compute(queries, k) == expected

        where:
        [queries, k, expected] << [
            [[], 0, []],
            [[], 1, []],
            [[], -1, []],
            [['A'], 0, []],
            [['A'], 1, ['A']],
            [['A'], 2, ['A']],
            [['A'], -1, []],
            [['A','B'], 1, ['A']],
            [['A','B'], 2, ['A','B']],
            [['A','B','B'], 1, ['B']],
            [['B','A','A'], 1, ['A']],
            [['A','A','C','B','C','B','B'], 2, ['A','B']],
        ]
    }

}
