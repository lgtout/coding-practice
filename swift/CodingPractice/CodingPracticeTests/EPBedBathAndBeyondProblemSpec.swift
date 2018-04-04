// Copyright (c) 2018 lagostout. All rights reserved.

import Foundation
import Quick
import Nimble
//import Dollar

//typealias $ = Dollar

class EPBedBathAndBeyondProblemSpec: QuickSpec {
    override func spec() {
        let cases: Array<(String, Array<String>, Array<String>?)> = [
            // One word dictionary
            ("bed", ["bed"], ["bed"]),
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
        describe("EPBedBathAndBeyondProblemSpec") {
            describe("computeWithBruteForceAndRecursion()") {
                for (url, dictionary, expected) in cases {
                    describe("given: url \(url), dictionary \(dictionary)") {
//                        print("dictionary \(dictionary) expected \(String(describing: expected))")
                        it("returns \(String(describing: expected))") {
                            let result = EPBedBathAndBeyondProblem
                                    .computeWithBruteForceAndRecursion(
                                    url: url, dictionary: Set(dictionary))
//                            print()
//                            print("result \(result)")
//                            print("\(type(of: result))")
                            if let expectedValue = expected {
                                expect(result).to(containElementSatisfying({ words in
                                    return Set(words) == Set(expectedValue) &&
                                            words.count == expectedValue.count
                                }))
                            } else {
                                expect(result).to(beEmpty())
                            }
                        }
                    }
                }
            }
        }
    }
}
