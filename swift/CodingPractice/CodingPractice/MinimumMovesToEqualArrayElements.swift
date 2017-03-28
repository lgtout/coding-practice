//  Copyright © 2017 lagostout. All rights reserved.

import Foundation

/// [Problem description](https://leetcode.com/problems/minimum-moves-to-equal-array-elements)

class MinimumMovesToEqualArrayElements {
    
    func minMoves(_ nums: [Int]) -> Int {
        let min = nums.min()
        var sum = 0
        for num in nums {
            sum += num
        }
        let moves = sum - min! * nums.count
        return moves
    }
    
}
