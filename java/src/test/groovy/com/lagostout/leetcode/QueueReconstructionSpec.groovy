package com.lagostout.leetcode

import spock.lang.Specification
import spock.lang.Unroll

class QueueReconstructionSpec extends Specification {
    @Unroll
    'reconstructs queue'(int[][] queue, int[][] expected) {
        expect:
//        println queue
//        println expected
        new QueueReconstruction().reconstructQueue(queue) == expected
        where:
        [queue, expected] << [
                [[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]],
                 [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]],
//                [[[9,0],[7,0],[1,9],[3,0],[2,7],[5,3],[6,0],[3,4],[6,2],[5,2]],
//                 [[3,0],[6,0],[7,0],[5,2],[3,4],[5,3],[6,2],[2,7],[9,0],[1,9]]]
        ]
//        [queue, expected] << [
//                [[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]],
//                 [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]]
//        ].collectMany {
//            def q = it[0].collect()
//            def e = it[1]
//            def pairs = []
//            3.times {
//                Collections.shuffle(q)
//                println q.collect()
//                pairs.add([q, e])
//            }
//            pairs
//        }
    }
}
