//  Copyright © 2017 lagostout. All rights reserved.

import Foundation

class MaxConsecutiveOnes {
    func findMaxConsecutiveOnes(_ nums: [Int]) -> Int {
        var count = 0
        var currentCount = 0
        for num in nums {
            if (num == 0) {
                count = currentCount > count ? currentCount : count
                currentCount = 0
            } else {
                currentCount += 1
            }
        }
        count = currentCount > count ? currentCount : count
        return count
    }
}
