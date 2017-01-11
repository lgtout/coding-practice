package com.lagostout.leetcode

import spock.lang.Specification
import spock.lang.Unroll

class IslandPerimeterSpec extends Specification {
    @Unroll
    'calculate perimeter'(int[][] map, int expected) {
        expect:
        new IslandPerimeter().islandPerimeter(map) == expected
        where:
        [map, expected] << [
                [[[1,1],[1,1]], 6],
//                [[[1]], 4],
//                [[[1,1]], 6],
//                [[[0,1,0,0],
//                  [1,1,1,0],
//                  [0,1,0,0],
//                  [1,1,0,0]], 16],
//                [[[0,1,0,0],
//                  [1,1,1,1],
//                  [1,1,0,0]], 18]
        ]
    }
}
