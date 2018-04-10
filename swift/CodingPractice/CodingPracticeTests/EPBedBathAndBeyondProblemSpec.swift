// Copyright (c) 2018 lagostout. All rights reserved.

import Foundation
import Quick
import Nimble

class EPBedBathAndBeyondProblemSpec : QuickSpec {

    static let cases: Array<(String, Array<String>, Array<String>?)> = [
        // One word dictionary
        ("bed", ["bed"], ["bed"]),
        // Two word dictionary
        ("bedbath", ["bed","bath"], ["bed","bath"]),
        // More words in dictionary than are in domain
        ("bedbath", ["bed","bath","bat"], ["bed","bath"]),
        // Guaranteed cache hits
        ("aaaaaa", ["aa","aaaa","aaa"], ["aa","aaaa"]),
        // Multi-word dictionary
        ("bedbathandbeyond", ["bed", "bat", "hand", "beyond"],
                ["bed", "bat", "hand", "beyond"]),
        // Dictionary word order doesn't matter
        ("bedbathandbeyond", ["bed", "beyond", "bat", "hand"],
                ["bed", "bat", "hand", "beyond"]),
        // Expected word order doesn't matter
        ("bedbathandbeyond", ["bed", "beyond", "bat", "hand"],
                ["hand", "beyond", "bed", "bat"]),
        // No matching concatenation
        ("bedbathandbeyond", ["bed", "beyond", "bad", "hand"], nil as [String]?)
    ]

    class SharedExamples : QuickConfiguration {
        override class func configure(_ configuration: Configuration) {
            sharedExamples("cases") { (context: SharedExampleContext) in
                let fn = (context()["fn"]) as! (String, Set<String>) -> Array<Array<String>>
                for (domain, dictionary, expected) in cases {
                    describe("given: domain \(domain), dictionary \(dictionary)") {
                        it("returns \(String(describing: expected))") {
                            let result = fn(domain, Set(dictionary))
                            if let expectedValue = expected {
                                expect(result).to(containElementSatisfying({ words in
                                    return Set(words) == Set(expectedValue) &&
                                            words.count == expectedValue.count
                                }), description: "expected \(String(describing: expected))" +
                                        " but got \(result)")
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

        describe("EPBedBathAndBeyondProblemSpec") {

            describe("computeWithBruteForceAndRecursion2()") {
                itBehavesLike("cases") {
                    ["context": EPBedBathAndBeyondProblem
                            .computeWithBruteForceAndRecursion2]
                }
            }

            fdescribe("computeWithBruteForceAndRecursion1()") {
                itBehavesLike("cases") {
                    ["fn": EPBedBathAndBeyondProblem
                            .computeWithBruteForceAndRecursion1]
                }
            }
            
            describe("computeWithRecursionAndMemoization()") {
                itBehavesLike("cases") {
                    ["fn": EPBedBathAndBeyondProblem
                            .computeWithRecursionAndMemoization]
                }
            }
            
            describe("computeBottomUpWithMemoization()") {
                itBehavesLike("cases") {
                    ["fn": EPBedBathAndBeyondProblem
                            .computeBottomUpWithMemoization]
                }
            }
            
        }
    }
}
