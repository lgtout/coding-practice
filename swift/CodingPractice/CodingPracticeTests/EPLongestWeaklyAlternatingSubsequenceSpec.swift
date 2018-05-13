// Copyright (c) 2018 lagostout. All rights reserved.

import Foundation
import Quick
import Nimble

class EPLongestWeaklyAlternatingSubsequenceSpec: QuickSpec {

    typealias Solution = EPLongestWeaklyAlternatingSubsequence
    typealias Direction = Solution.Direction

    static let cases = [
        ([], []),
        ([1], [1]),
        ([1,2], [1,2]),
        ([2,1], [2,1]),
        ([1,2,3], [1,2,3]),
        ([1,3,2], [1,3,2]),
        ([3,2,2], [3,2,2]),
        ([3,2,1], [3,2,1]),
        ([3,2,3], [3,2,3]),
        ([3,2,1,1], [3,2,1,1]),
        ([3,2,1,0], [3,2,1,0]),
        ([4,3,2,1,0], [3,2,1,0]),
        ([4,3,2,1,0], [4,3,2,1]),
        ([0,1,2,3,4], [1,2,3,4]),
        ([0,1,2,3,4,5], [1,2,3,4]),
        ([3,2,2,3], [3,2,2,3]),
        ([4,3,2,1,2,3,4,5], [4,3,2,2,3,4,5]),
        ([4,3,1,2,3,4,5], [4,3,1,2,3,4]),
        ([4,1,2,3,4,5], [4,1,2,3,4]),
    ]

    class SharedExamples : QuickConfiguration {
        override class func configure(_ configuration: Configuration!) {
            sharedExamples("longestWeaklyAlternatingSubsequence") { context in
                let fn = context()["fn"] as! ([Int]) -> [[Int]]
                for (sequence, expected) in cases {
                    describe("sequence \(sequence)") {
                        var result: [[Int]]!
                        beforeEach {
                            result = fn(sequence)
                        }
                        it("should return \(expected)") {
                            expect(result).to(contain(expected))
                        }
                        it("should return subsequences that are weakly alternating") {
                            var subsequencesRespectConstraint = true
                            var violatingSubsequence: [Int]? = nil
                            for subsequence in result where subsequencesRespectConstraint {
                                guard subsequence.count >= 2 else { continue }
                                let first = subsequence.first!
                                let second = subsequence[1]
                                var previousDirection = Direction.of(first, second)
                                var directionCount = previousDirection != .flat ? 0 : 1
                                var bound = second
                                for entry in subsequence[2...] {
                                    let direction = Direction.of(bound, entry)
                                    directionCount = direction == previousDirection ?
                                            directionCount + 1 : 0
                                    if directionCount >= Solution.maxDescOrAscCount {
                                        subsequencesRespectConstraint = false
                                        violatingSubsequence = subsequence
                                        break
                                    }
                                    bound = entry
                                    previousDirection = direction
                                }
                            }
                            expect(subsequencesRespectConstraint).to(beTrue(),
                                    description: "\nConstraint violated by: \(violatingSubsequence)")
                        }
                        it("should return subsequences that are all the same size") {
                            let expectedCount = result.first!.count
                            let subsequencesAreAllTheSameSize = !result.contains { subsequence in
                                subsequence.count != expectedCount
                            }
                            expect(subsequencesAreAllTheSameSize).to(beTrue())
                        }
                    }
                }
            }
        }
    }

    override func spec() {
        describe("computeByBruteForceWithRecursion1") {
            itBehavesLike("longestWeaklyAlternatingSubsequence") {
                ["fn" : Solution.computeByBruteForceWithRecursion1]
            }
        }
        describe("computeByBruteForceWithRecursion2") {
            itBehavesLike("longestWeaklyAlternatingSubsequence") {
                ["fn" : Solution.computeByBruteForceWithRecursion2]
            }
        }
        // Broken - doesn't pass all tests.  I don't know why.
        xdescribe("computeByBruteForceWithRecursion3") {
            itBehavesLike("longestWeaklyAlternatingSubsequence") {
                ["fn" : Solution.computeByBruteForceWithRecursion3]
            }
        }
        describe("computeByBruteForceWithRecursion4") {
            itBehavesLike("longestWeaklyAlternatingSubsequence") {
                ["fn" : Solution.computeByBruteForceWithRecursion4]
            }
        }
        describe("computeByBruteForceWithRecursion5") {
            itBehavesLike("longestWeaklyAlternatingSubsequence") {
                ["fn" : Solution.computeByBruteForceWithRecursion5]
            }
        }
        describe("computeBottomUpWithMemoization1") {
            itBehavesLike("longestWeaklyAlternatingSubsequence") {
                ["fn" : Solution.computeBottomUpWithMemoization1]
            }
        }
        describe("computeBottomUpWithMemoization2") {
            itBehavesLike("longestWeaklyAlternatingSubsequence") {
                ["fn" : Solution.computeBottomUpWithMemoization2]
            }
        }
    }

}
