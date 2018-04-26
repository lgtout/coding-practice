// Copyright (c) 2018 lagostout. All rights reserved.

import Foundation
import Quick
import Nimble

class EPLongestWeaklyAlternatingSubsequenceSpec: QuickSpec {

    static let cases = [
        ([], []),
        ([1], [1]),
        ([1,2], [1,2]),
        ([1,2,3], [1,2,3]),
        ([1,3,2], [1,3,2]),
        ([3,2,2], [3,2,2]),
        ([3,2,1], [3,2,1]),
        ([3,2,3], [3,2,3]),
        ([3,2,1,1], [3,2,1,1]),
        ([3,2,1,0], [3,2,1]),
        ([3,2,1,0], [2,1,0]),
        ([3,2,2,3], [3,2,2,3]),
        ([4,3,2,1,2,3,4,5], [4,3,2,2,3,4]),
        ([4,3,2,1,2,3,4,5], [4,3,2,2,3,5]),
        ([4,3,2,1,2,3,4,5], [4,3,2,2,4,5]),
    ]

    class SharedExamples : QuickConfiguration {
        override class func configure(_ configuration: Configuration!) {
            sharedExamples("longestWeaklyAlternatingSubsequence") { context in
                let fn = context()["fn"] as! ([Int]) -> [[Int]]
                for (sequence, expected) in cases {
                    describe("sequence \(sequence)") {
                        it("should return \(expected)") {
                            expect(fn(sequence)).to(contain(expected))
                        }
                    }
                }
            }
        }
    }

    override func spec() {
        describe("computeBottomUpWithMemoization") {
            itBehavesLike("longestWeaklyAlternatingSubsequence") {
                ["fn" : EPLongestWeaklyAlternatingSubsequence
                        .computeBottomUpWithMemoization]
            }
        }
    }


}
