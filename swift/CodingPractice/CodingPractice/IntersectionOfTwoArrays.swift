// Copyright (c) 2017 lagostout. All rights reserved.

import Foundation

/// [Problem](https://leetcode.com/problems/intersection-of-two-arrays/#/description)

class IntersectionOfTwoArrays {

    func intersection(_ nums1: [Int], _ nums2: [Int]) -> [Int] {
        return Array(Set(nums1).intersection(Set(nums2)))
    }

}
