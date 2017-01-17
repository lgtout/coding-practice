package com.lagostout.leetcode

import org.apache.commons.lang3.time.StopWatch
import spock.lang.Specification
import spock.lang.Unroll

import java.util.concurrent.TimeUnit

class FizzBuzzSpec extends Specification {

    @Unroll('n #n')
    'fizzBuzz'(int n, List<String> expected) {

        expect:
        StopWatch stopWatch = new StopWatch()
        stopWatch.start()
        FizzBuzz.fizzBuzzFaster(n) == expected
        stopWatch.stop()
        println stopWatch.getTime(TimeUnit.MICROSECONDS)

        where:
        [n, expected] << [
                [1, ["1"]],
                [3, ["1",
                     "2",
                     "Fizz"]],
                [6, [ "1",
                       "2",
                       "Fizz",
                       "4",
                       "Buzz",
                       "Fizz"]],
                [15, [ "1",
                       "2",
                       "Fizz",
                       "4",
                       "Buzz",
                       "Fizz",
                       "7",
                       "8",
                       "Fizz",
                       "Buzz",
                       "11",
                       "Fizz",
                       "13",
                       "14",
                       "FizzBuzz"]],
        ]
    }
}
