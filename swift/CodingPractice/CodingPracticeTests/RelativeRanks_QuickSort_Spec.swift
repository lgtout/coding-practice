//  Copyright © 2017 lagostout. All rights reserved.

import Foundation
import Quick
import Nimble

class RelativeRanks_QuickSort_Spec : QuickSpec {
    
    override func spec() {
        let relativeRanks = RelativeRanks_QuickSort()
        describe("RelativeRanks_QuickSort") {
            describe("partition") {
                let cases:[
                    (params:(nums:Array<Int>, lo:Int, hi:Int),
                    expected:(nums:Array<Int>, partitionIndex:Int))] = [
                        (([1], 0, 0), ([1], 0)),
                        (([1,2], 0, 1), ([1,2], 0)),
                        (([1,2,3], 0, 1), ([1,2,3], 0)),
                        (([1,2,0], 0, 1), ([1,2,0], 0)),
                        (([1,2,3], 1, 2), ([1,2,3], 1)),
                        (([4,2], 0, 1), ([2,4], 1)),
                        (([1,4,2], 1, 2), ([1,2,4], 2)),
                        (([4,2,1], 0, 1), ([2,4,1], 1)),
                        (([3,2,4], 0, 2), ([2,3,4], 1)),
                        (([4,3,2], 0, 2), ([2,3,4], 2))]
                for (params, expected) in cases {
                    it("puts pivot in final position and reorders values " +
                        "less than pivot to the left of it, and values greater than pivot to " +
                        "the right of it")
                    { [nums = params.nums] in
                        var nums = Array(nums)
                        let partitionIndex = relativeRanks.partition(&nums, params.lo, params.hi)
                        expect(nums).to(equal(expected.nums))
                        expect(partitionIndex).to(equal(expected.partitionIndex))
                    }
                }
            }
            describe("findRelativeRanks") {
                let cases:[(scores:[Int], expected:[String])] = [
                    ([5,4,3,2,1],
                     ["Gold medal", "Silver medal", "Bronze medal", "2", "1"])]
                for (scores, expected)in cases {
                    it("sorts athletes by score and awards" +
                        " medals to the highest 3 scores")
                    { [scores = scores] in
                        var scores = Array(scores)
                        expect(relativeRanks.findRelativeRanks(&scores))
                            .to(equal(expected))
                    }
                }
            }
        }
    }
    
}
