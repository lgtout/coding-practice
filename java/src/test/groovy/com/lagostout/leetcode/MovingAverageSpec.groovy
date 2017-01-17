package com.lagostout.leetcode

import spock.lang.Specification
import spock.lang.Unroll;

class MovingAverageSpec extends Specification {
    @Unroll
    'computes moving average'(int window,
                              List<Integer> numbers,
                              List<Double> expected) {
        def round = {
            double val -> Math.round(val * 100) / 100D
        }

        when:
        def average = new MovingAverage(window)
        List<Double> result = numbers.collect {
            //noinspection ChangeToOperator
            average.next(it)
        }

        then:
        result.collect(round) == expected.collect(round)

        where:
        [window, numbers, expected] << [
                [3, [1,10,3,5], [1,11/2,14/3,18/3]]
        ]
    }

}
