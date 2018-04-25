// Copyright (c) 2018 lagostout. All rights reserved.

import Foundation
import Quick
import Nimble

class LengthOfLongestNondecreasingSubsequenceSpec: QuickSpec {

    static let cases = [
        ([Int](), 0),
        ([1], 1),
        ([1,2], 2),
        ([2,1], 1),
        ([1,3,2,4], 3),
        ([1,3,3,4], 4),
        ([1,3,3,2,4,1], 4),
        ([0,8,4,12,2,10,6,14,1,9], 4)
    ]

    class SharedExamples : QuickConfiguration {
        override class func configure(_ configuration: Configuration!) {
            sharedExamples("lengthOfLongestNondecreasingSubsequence") { context in
                let fn = context()["fn"] as! ([Int]) -> Int
                for (sequence, expected) in cases {
                    describe("given sequence \(sequence)") {
                        it("should return \(expected)") {
                            expect(fn(sequence)).to(equal(expected))
                        }
                    }
                }
            }
        }
    }

    override func spec() {

        describe("computeWithRecursionAndBruteForce") {
            itBehavesLike("lengthOfLongestNondecreasingSubsequence") {
                ["fn" : EPLengthOfLongestNondecreasingSubsequence.computeWithRecursionAndBruteForce]
            }
        }

        describe("computeBottomUpWithMemoization") {
            itBehavesLike("lengthOfLongestNondecreasingSubsequence") {
                ["fn" : LengthOfLongestNondecreasingSubsequence.computeBottomUpWithMemoization]
            }
        }

    }
}
