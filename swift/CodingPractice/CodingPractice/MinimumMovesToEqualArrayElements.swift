//  Copyright © 2017 lagostout. All rights reserved.

import Foundation

/// [Problem description](https://leetcode.com/problems/minimum-moves-to-equal-array-elements)

class MinimumMovesToEqualArrayElements {
    
    func minMoves(_ nums: [Int]) -> Int {
        var sum = 0
        var minVal = Int.max
        for num in nums {
            sum += num
            if (minVal > num) { minVal = num }
        }
        let moves = sum - minVal * nums.count
        return moves
    }
    
}
