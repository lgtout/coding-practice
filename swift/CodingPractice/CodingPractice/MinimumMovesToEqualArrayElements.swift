//  Copyright © 2017 lagostout. All rights reserved.

import Foundation

/// [Problem description](https://leetcode.com/problems/minimum-moves-to-equal-array-elements)

class MinimumMovesToEqualArrayElements {
    
    func minMoves(_ nums: [Int]) -> Int {
        let nums = [Int](nums).sorted().reversed()
        var moveCount = 0
        for num in nums {
            let max = num + moveCount
            let delta = max - (nums.last! + moveCount)
            moveCount += delta
        }
        return moveCount
    }
    
}
