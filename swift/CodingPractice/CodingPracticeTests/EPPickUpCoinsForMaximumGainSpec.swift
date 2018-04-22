// Copyright (c) 2018 lagostout. All rights reserved.

import Foundation
import Quick
import Nimble
import Combinatorics

class EPPickUpCoinsForMaximumGainSpec : QuickSpec {
    
    static let cases = [
        ([], 0),
        ([1], 1),
        ([1,2], 2),
        ([2,1], 2),
        ([1,2,3], 4),
        ([2,3,1], 3),
        ([1,2,3,4], 6),
    ]
    
    // I can't think of a way to compute expected that
    // isn't more complex than recursion with brute force.
    // So, abandoning random case testing.
    static let randomCases: () -> [([Int], Int)] = {
        let caseCount = 30
        let coinValueRange = Array(1...10)
        let maxCoinCount = 6
        func computeExpected(_ coins: [Int]) -> Int {
            return 0
        }
        let cases: [([Int], Int)] = (1...caseCount).map { k in
            let coinCount = reproducibleRandom(maxCoinCount + 1)
            let coinValues = (0..<coinCount).map { _ in
                coinValueRange[reproducibleRandom(coinValueRange.count)]
            }
            let coins: [Int] = (0..<coinCount).map { _ in
                coinValues[reproducibleRandom(coinValues.count)]
            }
            return (coins, computeExpected(coins))
        }
        return cases
    }
    
    class SharedExamples : QuickConfiguration {
        override class func configure(_ configuration: Configuration!) {
            sharedExamples("pickUpCoinsForMaximumGain") { context in
                let fn = context()["fn"] as! ([Int]) -> Int
                for (coins, expected) in cases {
                    describe("given coins \(coins)") {
                        it("should return \(expected)") {
                            expect(fn(coins)).to(equal(expected))
                        }
                    }
                }
            }
        }
    }
    
    override func spec() {
        
        let examplesName = "pickUpCoinsForMaximumGain"
        
        describe("computeWithRecursionAndBruteForce") {
            itBehavesLike(examplesName) {
                ["fn": EPPickUpCoinsForMaximumGain.computeWithRecursionAndBruteForce]
            }
        }
        
        describe("computeWithRecursionAndMemoization") {
            itBehavesLike(examplesName) {
                ["fn": EPPickUpCoinsForMaximumGain.computeWithRecursionAndMemoization]
            }
        }

        fdescribe("computeBottomUpWithMemoization") {
            itBehavesLike(examplesName) {
                ["fn": EPPickUpCoinsForMaximumGain.computeBottomUpWithMemoization]
            }
        }
    }
    
    
}
