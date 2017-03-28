//  Copyright © 2017 lagostout. All rights reserved.

import Foundation
import Quick
import Nimble

class MinimumMovesToEqualArrayElementsSpec : QuickSpec {
    
    override func spec() {
        describe("MinimumMovesToEqualArrayElements") {
            describe("minMoves") {
                let minimumMoves = MinimumMovesToEqualArrayElements()
                let cases: [(nums: [Int], expected: (num:Int, moves:Int))] = [
//                    ([1], (1, 0)),
//                    ([2,2], (2, 0)),
//                    ([1,2,3], (4, 3)),
//                    ([1,2], (2, 1)),
//                    ([1,2,4], (5, 4)),
//                    ([1,2147483647], (2147483647, 2147483646)),
                    ([83,86,77,15,93,35,86,92,49,21], (419, 487)),
                ]
                for (nums, expected) in cases {
                    it("finds the minimum number of moves required " +
                        "to make all array elements equal, where a " +
                        "move is incrementing n-1 elements by 1")
                    {
                        expect(minimumMoves.minMoves(nums)).to(equal(expected.moves))
                    }
                }
            }
        }
    }
    
}
