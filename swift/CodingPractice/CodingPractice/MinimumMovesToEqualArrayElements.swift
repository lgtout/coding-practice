//  Copyright © 2017 lagostout. All rights reserved.

import Foundation

/// [Problem description](https://leetcode.com/problems/minimum-moves-to-equal-array-elements)

class MinimumMovesToEqualArrayElements {
    
    func minMoves(_ nums: [Int]) -> Int {
        let min = nums.min()
        var moves = 0
        for num in nums {
            moves += (num - min!)
        }
        return moves
    }
    
}
