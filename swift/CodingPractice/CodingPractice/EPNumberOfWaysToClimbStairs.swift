//  Copyright © 2018 lagostout. All rights reserved.

import Foundation

/* Problem 17.10 page 331  */

class EPNumberOfWaysToClimbStairs {
    
    static func computeWithRecursionAndBruteForce(_ n: Int, _ k: Int) -> Int {
        func compute(_ n: Int) -> Int {
            if n == 0 { return 1 }
            else if n < 1 { return 0 }
            else { return (1...k).map { compute(n - $0) }.reduce(0) { $0 + $1 } }
        }
        return compute(n)
    }

    static func computeWithRecursionAndMemoization(_ n: Int, _ k: Int) -> Int {
        print()
        var cache: [Int : Int] = [:]
        func compute(_ n: Int) -> Int {
            if let ways = cache[n] {
                print("hit: \(n) ways: \(ways)")
                return ways
            }
            let ways: Int
            if n == 0 { ways = 1 }
            else if n < 1 { ways = 0 }
            else { ways = (1...k).map { compute(n - $0) }.reduce(0) { $0 + $1 } }
            cache[n] = ways
            return ways
        }
        return compute(n)
    }

    static func computeBottomUpWithMemoization(_ n: Int, _ k: Int) -> Int {
        if n == 0 || k == 0 {
            return 1
        }
        var cache: [Int : Int] = [0:1]
        (1...n).forEach { currentStep in
            cache[currentStep] = (1...k).map { stepStairCount in
                let previousStep = currentStep - stepStairCount
                return previousStep >= 0 ? cache[previousStep]! : 0
            }.reduce(0) {
                $0 + $1
            }
        }
        return cache[n]!
    }

}
