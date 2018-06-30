// Copyright (c) 2018 lagostout. All rights reserved.

import Foundation
import Quick
import Nimble

/* Problem 17.12.6 page 337 */

class EPLongestBitonicSubsequenceSpec : QuickSpec {

    static let cases = [
//        ([], []),
        ([1], [1]),
        ([1,2], [1,2]),
        ([2,1,0], [2,1,0]),
        ([1,2,1], [1,2,1]),
        ([3,6,3,9,6,12,4,6,3], [3,6,9,12,6,3]),
    ]

    typealias Case = (data: [Int], expected:[Int])

    static let randomCases: [Case] = {
        let numberRange = (first: -5, last: 5)
        let maxNumberCount = 10
        let caseCount = 100
        let numberCount = reproducibleRandom(endInclusive: maxNumberCount)
        let result: [Case] = (1...caseCount).flatMap { _ -> [Case] in
            let sequence: [Int] = (1...numberCount).map { _ in
                numberRange.first + reproducibleRandom(
                        endInclusive: numberRange.last - numberRange.first)
            }
            let allPossibleExpected: [[Int]] = EPLongestBitonicSubsequence
                    .computeWithBruteForceWithoutRecursion(sequence: sequence)
            let result = allPossibleExpected.map { expected in
                (data: sequence, expected: expected)
            }
            return result
        }
        return result
    }()

    class SharedExamples : QuickConfiguration {
        override class func configure(_ configuration: Configuration!) {
            sharedExamples("longestBitonicSubsequence") { context in
                let fn = context()["fn"] as! ([Int]) -> [[Int]]
                for (sequence, expected) in randomCases {
//                for (sequence, expected) in cases {
                    describe("given sequence \(sequence)") {
                        let result = fn(sequence)
                        it("should return \(expected)") {
//                            print("\(sequence) \(expected)")
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

        describe("computeWithBruteForceAndRecursion") {
            itBehavesLike("longestBitonicSubsequence") {
                ["fn" : EPLongestBitonicSubsequence
                        .computeWithBruteForceAndRecursion]
            }
        }

        describe("computeBottomUpWithMemoization") {
            itBehavesLike("longestBitonicSubsequence") {
                ["fn" : EPLongestBitonicSubsequence
                        .computeBottomUpWithMemoization]
            }
        }

    }
}
