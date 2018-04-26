// Copyright (c) 2018 lagostout. All rights reserved.

import Foundation
import Quick
import Nimble

class EPLongestNondecreasingSubsequenceSpec: QuickSpec {

    static let cases = [
        ([Int](), [[Int]]()),
        ([1], [[1]]),
        ([1,2], [[1,2]]),
        ([2,1], [[1],[2]]),
        ([1,3,2], [[1,2],[1,3]]),
        ([1,3,2,4], [[1,2,4],[1,3,4]]),
        ([1,3,3,4], [[1,3,3,4]]),
        ([1,3,3,2,4,1], [[1,3,3,4]]),
        ([0,8,4,12,2,10,6,14,1,9],
                [[0,8,12,14],[0,4,12,14],
                 [0,4,10,14],[0,2,6,14]])
    ]

    class SharedExamples : QuickConfiguration {
        override class func configure(_ configuration: Configuration!) {
            sharedExamples("longestNondecreasingSubsequence") { context in
                let fn = context()["fn"] as! ([Int]) -> [[Int]]
                for (sequence, expected) in cases {
                    describe("given sequence \(sequence)") {
                        it("should return \(expected)") {
                            let result = fn(sequence)
                            if expected.isEmpty {
                                expect(result).to(beEmpty())
                            } else {
                                for expectedSequence in expected {
                                    expect(result).to(contain(expectedSequence))
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    override func spec() {

        fdescribe("computeBottomUpWithMemoization") {
            itBehavesLike("longestNondecreasingSubsequence") {
                ["fn" : EPLongestNondecreasingSubsequence.computeBottomUpWithMemoization]
            }
        }

        describe("computeWithRecursionAndBruteForce") {
            itBehavesLike("longestNondecreasingSubsequence") {
                ["fn" : EPLongestNondecreasingSubsequence.computeWithRecursionAndBruteForce]
            }
        }

    }
}

