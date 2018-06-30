// Copyright (c) 2018 lagostout. All rights reserved.

import Foundation
import Quick
import Nimble

class EPLongestConvexSubsequenceSpec: QuickSpec {

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
        ([1,2,3,6], [1,3,6]),
        ([1,2,3,6], [1,2,6]),
        ([1,1,3,5], [1,1,3]),
        ([1,1,3,5], [1,1,5]),
        ([1,1,3,6], [1,1,3,6]),
        ([1,1,3,5,6,7], [1,1,3,6]),
        ([1,1,3,5,6,7], [1,1,3,7]),
        ([1,1,3,5,6,7,8,9,12], [1,1,3,6,12]),
        ([1,1,3,5,6,7,8,9,12], [1,1,3,7,12]),
        ([2,9,3,6,6], [2,3,6]),
        ([2,9,3,6,6], [9,3,6]),
        ([1,2,5,3,8,2], [1,3,8]),
        ([1,2,5,3,8], [1,2,5]),
    ]

    static let randomCases: [([Int], [Int])] = {
        let caseCount = 10
        let maxNumberCount = 20
        let numberRange = -5...5
        let numberRangeCount = numberRange.count
        let cases: [([Int], [Int])] = (0...caseCount).flatMap {
            num -> [([Int], [Int])] in
            let numberCount = reproducibleRandom(endExclusive: maxNumberCount)
            let sequence: [Int] = (0...numberCount).map { num in
                numberRange.min()! + reproducibleRandom(endExclusive: numberRangeCount)
            }
            let solutions: [[Int]] = EPLongestConvexSubsequence
//                    .computeByBruteForceWithRecursion1(sequence: sequence)
                    .computeWithBruteForceWithoutRecursion(sequence: sequence)
            let result: [([Int], [Int])] = solutions.map { (solution: [Int]) in
                (sequence, solution)
            }
            return result
        }
        return cases
    }()

    class SharedExamples : QuickConfiguration {
        override class func configure(_ configuration: Configuration!) {
            sharedExamples("longestConvexSubsequence") { context in
                let fn = context()["fn"] as! ([Int]) -> [[Int]]
                for (sequence, expected) in randomCases {
//                for (sequence, expected) in cases {
                    describe("given \(sequence)") {
                        it("should return \(expected)") {
                            let result = fn(sequence)
//                            print("final result: \(result)")
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
        // Works
        describe("computeByBruteForceWithoutRecursion") {
            itBehavesLike("longestConvexSubsequence") {
                ["fn" : EPLongestConvexSubsequence
                        .computeWithBruteForceWithoutRecursion]
            }
        }
        // Works
        describe("computeByBruteForceWithRecursion1") {
            itBehavesLike("longestConvexSubsequence") {
                ["fn" : EPLongestConvexSubsequence
                        .computeByBruteForceWithRecursion1]
            }
        }
        // Works
        describe("computeByBruteForceWithRecursion2") {
            itBehavesLike("longestConvexSubsequence") {
                ["fn" : EPLongestConvexSubsequence
                        .computeByBruteForceWithRecursion2]
            }
        }
        // Works
        describe("computeByBruteForceWithRecursion3") {
            itBehavesLike("longestConvexSubsequence") {
                ["fn" : EPLongestConvexSubsequence
                        .computeByBruteForceWithRecursion3]
            }
        }
        // Works
        describe("computeByBruteForceWithRecursion4") {
            itBehavesLike("longestConvexSubsequence") {
                ["fn" : EPLongestConvexSubsequence
                        .computeByBruteForceWithRecursion4]
            }
        }
        // Works
        describe("computeByBruteForceWithRecursion5") {
            itBehavesLike("longestConvexSubsequence") {
                ["fn" : EPLongestConvexSubsequence
                        .computeByBruteForceWithRecursion5]
            }
        }
        // Works
        describe("computeByBruteForceWithRecursion6") {
            itBehavesLike("longestConvexSubsequence") {
                ["fn" : EPLongestConvexSubsequence
                        .computeByBruteForceWithRecursion6]
            }
        }
        // Works
        describe("computeByBruteForceWithRecursion7") {
            itBehavesLike("longestConvexSubsequence") {
                ["fn" : EPLongestConvexSubsequence
                        .computeByBruteForceWithRecursion7]
            }
        }
        // Works
        describe("computeByBruteForceWithRecursion8") {
            itBehavesLike("longestConvexSubsequence") {
                ["fn" : EPLongestConvexSubsequence
                        .computeByBruteForceWithRecursion8]
            }
        }
        // Works
        describe("computeByBruteForceWithRecursion9") {
            itBehavesLike("longestConvexSubsequence") {
                ["fn" : EPLongestConvexSubsequence
                        .computeByBruteForceWithRecursion9]
            }
        }
        // Works
        describe("computeByBruteForceWithRecursion10") {
            itBehavesLike("longestConvexSubsequence") {
                ["fn" : EPLongestConvexSubsequence
                        .computeByBruteForceWithRecursion10]
            }
        }
        // Works
        describe("computeByBruteForceWithRecursion11") {
            itBehavesLike("longestConvexSubsequence") {
                ["fn" : EPLongestConvexSubsequence
                        .computeByBruteForceWithRecursion11]
            }
        }
        // Works
        describe("computeByBruteForceWithRecursion12") {
            itBehavesLike("longestConvexSubsequence") {
                ["fn" : EPLongestConvexSubsequence
                        .computeByBruteForceWithRecursion12]
            }
        }
        // Works
        describe("computeByBruteForceWithRecursion13") {
            itBehavesLike("longestConvexSubsequence") {
                ["fn" : EPLongestConvexSubsequence
                        .computeByBruteForceWithRecursion13]
            }
        }
        // Works
        describe("computeByBruteForceWithRecursion14") {
            itBehavesLike("longestConvexSubsequence") {
                ["fn" : EPLongestConvexSubsequence
                        .computeByBruteForceWithRecursion14]
            }
        }
        // Works
        describe("computeWithRecursionAndMemoization1") {
            itBehavesLike("longestConvexSubsequence") {
                ["fn" : EPLongestConvexSubsequence
                        .computeWithRecursionAndMemoization1]
            }
        }
        // Works
        describe("computeWithRecursionAndMemoization2") {
            itBehavesLike("longestConvexSubsequence") {
                ["fn" : EPLongestConvexSubsequence
                        .computeWithRecursionAndMemoization2]
            }
        }
        // Works
        fdescribe("computeBottomUpWithMemoization1") {
            itBehavesLike("longestConvexSubsequence") {
                ["fn" : EPLongestConvexSubsequence
                        .computeBottomUpWithMemoization1]
            }
        }
    }

}
