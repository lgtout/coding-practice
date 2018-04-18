// Copyright (c) 2018 lagostout. All rights reserved.

import Foundation
import Quick
import Nimble

class EPMinimumPalindromicDecompositionSpec : QuickSpec {

    static let cases: Array<(String, Array<String>)> = [
        ("", [""]),
        ("a", ["a"]),
        ("ab", ["a","b"]),
        ("aa", ["aa"]),
        ("aba", ["aba"]),
        ("abac", ["aba", "c"]),
        ("abab", ["a", "bab"]),
        ("baab", ["baab"]),
        ("abcd", ["a","b","c","d"]),
    ]

    class SharedExamples : QuickConfiguration {
        override class func configure(_ configuration: Configuration!) {
            sharedExamples("minimumPalindromicDecomposition") { (context: SharedExampleContext) in
                let fn = (context()["fn"]) as! (String) -> Array<String>
                for (string, expected) in cases {
                    describe("given: string: \(string)") {
                        it("returns \(expected)") {
                            let result = fn(string)
                            expect(result).to(contain(expected))
                        }
                    }
                }
            }
        }
    }
    
    override func spec() {

        describe("EPMinimumPalindromicDecomposition") {

            describe("computeWithBruteForceAndAndRecursion") {
                for (string, expected) in EPMinimumPalindromicDecompositionSpec.cases {
                    describe("given: string: \(string)") {
                        it("returns \(expected)") {
                            let result = EPMinimumPalindromicDecomposition
                                    .computeWithBruteForceAndRecursion_allMinimumDecompositions(string)
                            expect(result).to(contain(expected))
                        }
                    }
                }
            }

            describe("computeWithBruteForceAndAndRecursion") {
                itBehavesLike("minimumPalindromicDecomposition") {
                    ["fn": EPMinimumPalindromicDecomposition
                            .computeWithBruteForceAndRecursion]
                }
            }

            describe("computeWithRecursionAndMemoization") {
                itBehavesLike("minimumPalindromicDecomposition") {
                    ["fn": EPMinimumPalindromicDecomposition
                            .computeWithRecursionAndMemoization]
                }
            }

            describe("computeBottomUpWithMemoization") {
                itBehavesLike("minimumPalindromicDecomposition") {
                    ["fn": EPMinimumPalindromicDecomposition
                            .computeBottomUpWithMemoization]
                }
            }
        }
    }

}
