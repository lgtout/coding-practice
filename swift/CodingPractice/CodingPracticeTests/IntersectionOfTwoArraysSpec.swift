// Copyright (c) 2017 lagostout. All rights reserved.

import Foundation
import Quick
import Nimble

class IntersectionOfTwoArraysSpec : QuickSpec {

    override func spec() {
        describe("IntersectionOfTwoArrays") {
            describe("intersection") {
                let cases:[(nums:([Int], [Int]),
                            expected:[Int])] = [
                        (([],[]), []),
                        (([1],[]), []),
                        (([1],[2]), []),
                        (([1],[1]), [1]),
                        (([2,1],[2]), [2]),
                        (([1,2],[2]), [2]),
                        (([1,2],[1,2]), [1,2]),
                        (([2,1],[1,2]), [1,2]),
                        (([1,2,2,1],[2,2]), [2]),
                ]
                let intersectionOfTwoArrays = IntersectionOfTwoArrays()
                for (nums, expected) in cases {
                    it("computes the intersection of two arrays") {
                        expect(Set(intersectionOfTwoArrays.intersection(nums.0, nums.1)))
                                .to(equal(Set(expected)))
                    }
                }
            }
        }
    }

}
