// Copyright (c) 2018 lagostout. All rights reserved.

import Foundation
import Quick
import Nimble

class EPPrettyPrintingExcludingLastLineMessinessSpec : QuickSpec {

    static let cases: [(words: [String], lineLength: Int, expected: Int)] = [
        ([], 0, 0),
        ([""], 0, 0),
        (["a"], 1, 0),
        (["a"], 2, 0),
        (["a"], 3, 0),
        (["a", "b"], 1, 0),
        (["a", "b"], 2, 1),
        (["a", "b"], 3, 0),
        (["a", "b"], 5, 0),
        (["ab", "c"], 2, 0),
        (["ab", "c"], 3, 1),
        (["ab", "cd"], 3, 1),
        (["ab", "cde"], 4, 4)
    ]

    class SharedExamples : QuickConfiguration {
        override class func configure(_ configuration: Configuration!) {
            sharedExamples("prettyPrintingExcludingLastLineMessiness") { context in
                let fn = context()["fn"] as! ([String], Int) -> Int
                for (words, lineLength, expected) in cases {
                    describe("given words \(words) lineLength: \(lineLength)") {
                        it("should return \(expected)") {
                            expect(fn(words, lineLength)).to(equal(expected))
                        }
                    }
                }
            }
        }
    }

    override func spec() {

        describe("computeBottomUpWithMemoization") {
            itBehavesLike("prettyPrintingExcludingLastLineMessiness") {
                ["fn" : EPPrettyPrintingExcludingLastLineMessiness
                        .computeBottomUpWithMemoization]
            }
        }

    }


}
