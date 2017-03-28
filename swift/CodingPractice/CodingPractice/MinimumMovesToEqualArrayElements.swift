//  Copyright © 2017 lagostout. All rights reserved.

import Foundation

/// [Problem description](https://leetcode.com/problems/minimum-moves-to-equal-array-elements)

class MinimumMovesToEqualArrayElements {
    
    func minMoves(_ nums: [Int]) -> Int {
        let nums = [Int](nums).sorted()
        var moveCount = 0
        var index = nums.count - 1
        while index > 0 {
            moveCount += (nums[index] + moveCount - (nums.first! + moveCount))
            index += -1
        }
        return moveCount
    }
    
}
