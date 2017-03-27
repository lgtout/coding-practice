//  Copyright © 2017 lagostout. All rights reserved.

import Foundation

//  https://leetcode.com/problems/single-number/

class SingleNumber {
    func singleNumber(_ nums: [Int]) -> Int {
        var accumulation = 0
        for number in nums {
            accumulation = accumulation ^ number
        }
        return accumulation
    }
}
