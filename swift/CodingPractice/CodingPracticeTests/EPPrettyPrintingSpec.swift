// Copyright (c) 2018 lagostout. All rights reserved.

import Foundation
import Quick
import Nimble

class EPPrettyPrintingSpec : QuickSpec {

    static let cases: [(text: [String], lineLength: Int, expected: Int)] = [
        ([""], 0, 0),
        (["a"], 1, 0),
        (["a"], 2, 1),
        (["a"], 3, 4),
        (["a", "b"], 1, 0),
        (["a", "b"], 2, 2),
        (["a", "b"], 3, 0),
        (["a", "b"], 5, 4),
        (["ab", "c"], 2, 1),
        (["ab", "c"], 3, 5),
        (["ab", "cd"], 3, 2),
        (["aaa", "bbb", "c", "d", "ee", "ff", "gggggg"], 11, 45),
        (["a", "b", "c", "d"], 5, 8),
        (["wvywonydaa", "ilmfkcxdk", "krjafp", "dkhd", "oezuwiqbqw", "awcrwcpacz", "sv", "tbjitzg"], 14, 98),
        (["cxvuh", "lla", "ihqokt", "h", "ai", "crrdgk", "esqtfdmjvh", "dwsp", "ud", "siyczlb", "p", "fcxjrpi", "eqvafyixek", "dpyy", "mud", "dv"], 14, 250),
        (["owcxylsnn", "mfyd"], 12, 73)
    ]

    class SharedExamples : QuickConfiguration {
        override class func configure(_ configuration: Configuration!) {
            sharedExamples("prettyPrinting") { context in
                let fn = context()["fn"] as! ([String], Int) -> Int
                for (words, lineLength, expected) in cases {
                    describe("given text: \(words), lineLength: \(lineLength)") {
                        it("should return \(expected)") {
                            expect(fn(words, lineLength)).to(equal(expected))
                        }
                    }
                }
            }
        }
    }
    override func spec() {
        
        let sharedExamples = "prettyPrinting"
        
        describe("computeWithRecursionAndBruteForce") {
            itBehavesLike(sharedExamples) {
                ["fn": EPPrettyPrinting.computeWithRecursionAndBruteForce]
            }
        }
        
        describe("computeWithRecursionAndMemoization") {
            itBehavesLike(sharedExamples) {
                ["fn": EPPrettyPrinting.computeWithRecursionAndMemoization]
            }
        }

        fdescribe("computeBottomUpWithMemoization") {
            itBehavesLike(sharedExamples) {
                ["fn": EPPrettyPrinting.computeBottomUpWithMemoization]
            }
        }
    }

}
