package com.lagostout.leetcode

import spock.lang.Specification
import spock.lang.Unroll

class LoggerSpec extends Specification {

    @Unroll
    'should print message'(List params, List<Boolean> expected) {

        when:
        def logger = new Logger()
        def result = []
        println params
        params.forEach {
            println it
            result << logger.shouldPrintMessage2(it[0] as int, it[1] as String)
        }

        then:
        result == expected

        where:
        [params, expected] << [
                [[[], [1, 'foo'], [2, 'bar'],
//                  [3, 'foo'], [8, 'bar'],
//                  [10, 'foo'], [11, 'foo']
                 ], [true, true, false, false, false, true]]
        ]

    }
}
