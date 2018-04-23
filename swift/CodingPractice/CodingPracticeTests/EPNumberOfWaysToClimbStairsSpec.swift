//  Copyright © 2018 lagostout. All rights reserved.

import Foundation
import Quick
import Nimble

class EPNumberOfWayToClimbStairsSpec : QuickSpec {
    
    static let cases: [(n: Int, k: Int, expected: Int)] = [
        (0, 0, 1),
        (0, 1, 1),
        (1, 1, 1),
        (2, 1, 1),
        (2, 2, 2),
        (4, 2, 5)
    ]
    
    class SharedExamples : QuickConfiguration {
        override class func configure(_ configuration: Configuration) {
            sharedExamples("numberOfWaysToClimbStairs") { context in
                let fn = context()["fn"] as! (Int, Int) -> Int
                for (n, k, expected) in cases {
                    describe("given n: \(n), k: \(k)") {
                        it("should return \(expected)") {
                            expect(fn(n, k)).to(equal(expected))
                        }
                    }
                }
            }
        }
    }
    
    override func spec() {
        
        let sharedExamples = "numberOfWaysToClimbStairs"
        
        describe("computeWithRecursionAndBruteForce") {
            itBehavesLike(sharedExamples) {
                ["fn": EPNumberOfWaysToClimbStairs.computeWithRecursionAndBruteForce]
            }
        }

        describe("computeWithRecursionAndMemoization") {
            itBehavesLike(sharedExamples) {
                ["fn": EPNumberOfWaysToClimbStairs.computeWithRecursionAndMemoization]
            }
        }

        fdescribe("computeBottomUpWithMemoization") {
            itBehavesLike(sharedExamples) {
                ["fn": EPNumberOfWaysToClimbStairs.computeBottomUpWithMemoization]
            }
        }
    }

}
