// Copyright (c) 2018 lagostout. All rights reserved.

import Foundation
import Quick
import Nimble

/* Problem 17.12.6 page 337 */

class EPLongestBitonicSubsequenceSpec : QuickSpec {

    static let cases = [
        ([], nil),
        ([1], nil),
        ([1,2], nil),
        ([2,1,0], nil),
        ([1,2,1], [1,2,1]),
        ([3,6,3,9,6,12,4,6,3], [3,6,9,12,6,3]),
    ]

    class SharedExamples : QuickConfiguration {
        override class func configure(_ configuration: Configuration!) {
            sharedExamples("longestBitonicSubsequence") { context in
                let fn = context()["fn"] as! ([Int]) -> [[Int]]
                for (sequence, expected) in cases {
                    describe("given sequence \(sequence)") {
                        let result = fn(sequence)
                        it("should return \(expected)") {
                            if let _ = expected {
                                expect(result).to(contain(expected))
                            } else {
                                expect(result).to(beEmpty())
                            }
                        }
                    }
                }
            }
        }
    }

    override func spec() {

        describe("computeBottomUpWithMemoization") {
            itBehavesLike("longestBitonicSubsequence") {
                ["fn" : EPLongestBitonicSubsequence
                        .computeBottomUpWithMemoization]
            }
        }

    }
}
