// Copyright (c) 2018 lagostout. All rights reserved.

import Foundation
import Quick

class EPMinimumPalindromicDecompositionSpec : QuickSpec {

    typealias Case = (String, Array<String>)

    static let cases: Array<Case> = [
        ("", [""]),
        ("a", ["a"]),
        ("ab", ["a","b"]),
        ("aa", ["aa"]),
        ("aba", ["aba"]),
        ("abac", ["aba", "c"]),
    ]

    class SharedExamples : QuickConfiguration {
        override class func configure(_ configuration: Configuration!) {
            sharedExamples("cases") { context in
                let fn = (context()["fn"]) as! (String) -> Array<String>
                for (string, expected) in cases {
                    describe("given: string: \(string)") {
                       it("returns \(expected)") {
                           let result = fn(string)
                           if let expectedValue =
                       }
                    }
                }
            }
        }
    }

    override func spec() {

        describe("EPMinimumPalindromicDecomposition") {

            describe("computeWithBruteForceAndAndRecursion()") {
                itBehavesLike("cases") {
                    ["fn": EPMinimumPalindromicDecomposition
                            .computeWithBruteForceAndAndRecursion]
                }
            }

        }
    }

}
