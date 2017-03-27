//  Copyright © 2017 lagostout. All rights reserved.

import Foundation

/// [Problem description](https://leetcode.com/problems/minimum-moves-to-equal-array-elements)

class MinimumMovesToEqualArrayElements {
    
    func minMoves(_ nums: [Int]) -> Int {
        var nums = [Int](nums)
            
        // Find max, min
        var maxIndex = 0
        var minIndex = 0
        nums.enumerated().forEach { (index, num) in
            if (num > nums[maxIndex]) {
                maxIndex = index
            }
            if (num < nums[minIndex]) {
                minIndex = index
            }
        }
        
        var moveCount = 0
        while nums[minIndex] < nums[maxIndex] {
//            print(nums)
            let moves = nums[maxIndex] - nums[minIndex]
            moveCount += moves
            var nextMaxIndex = maxIndex
            var minIndex = maxIndex
            nums.enumerated().forEach { index, num in
                // Increment all except max
                if (index != maxIndex) {
                    nums[index] += moves
                    if (nums[index] > nums[nextMaxIndex]) {
                        nextMaxIndex = index
                    }
                    if (nums[index] < nums[minIndex]) {
                        minIndex = index
                    }
                }
            }
            maxIndex = nextMaxIndex
        }
    
        return moveCount
    }
    
}
