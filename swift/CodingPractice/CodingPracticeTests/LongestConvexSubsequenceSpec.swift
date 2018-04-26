// Copyright (c) 2018 lagostout. All rights reserved.

import Foundation
import Quick
import Nimble

class LongestConvexSubsequenceSpec : QuickSpec {

    static let cases: [([Int], [Int])] = [
        ([], []),
        ([1], []),
        ([1,2], []),
        ([1,2,3], []),
        ([1,3,3], []),
        ([1,3,4], []),
        ([1,4,7], []),
        ([1,4,8], [1,4,8]),
        ([1,1,3], [1,1,3]),
        ([1,1,3,5], [1,1,3]),
        ([1,1,3,6], [1,1,3,6]),
        ([1,1,3,5,6,7], [1,1,3,6]),
        ([1,1,3,5,6,7], [1,1,3,7]),
        ([1,1,3,5,6,7,8,9,12], [1,1,3,7,12]),
    ]

    class SharedExamples : QuickConfiguration {
        override class func configure(_ configuration: Configuration!) {
            sharedExamples("longestConvexSubsequence") { context in
                let fn = context()["fn"] as! ([Int]) -> [[Int]]
                for (sequence, expected) in cases {
                    describe("given \(sequence)") {
                        it("should return \(expected)") {
                            let result = fn(sequence)
                            if expected.isEmpty {
                                expect(result).to(beEmpty())
                            } else {
                                expect(result).to(contain(expected))
                            }
                        }
                    }
                }
            }
        }
    }

    override func spec() {
        describe("computeBottomUpWithMemoization") {
            itBehavesLike("longestConvexSubsequence") {
                ["fn" : LongestConvexSubsequence
                        .computeBottomUpWithMemoization]
            }
        }
    }

}
