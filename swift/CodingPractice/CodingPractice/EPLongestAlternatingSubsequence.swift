// Copyright (c) 2018 lagostout. All rights reserved.

import Foundation

/* Problem 17.12.3 page 336 */

class EPLongestAlternatingSubsequence {
    
    static func computeBottomUpWithMemoization(sequence: [Int]) -> [[Int]] {
        if sequence.isEmpty {
            return [[]]
        }
        var cache: [[(index: Int, value: Int)]] = []
        var currIndex = 0
        while currIndex < sequence.endIndex {
            let currValue: Int = sequence[currIndex]
            for subsequence in cache {
                if let lastValueInSubsequence = subsequence.last?.value {
                    let isEvenIndex = subsequence.count % 2 == 0
                    var newSubsequence: [(Int, Int)]? = nil
                    if (isEvenIndex && lastValueInSubsequence > currValue) ||
                               (!isEvenIndex && lastValueInSubsequence < currValue) {
                        newSubsequence = subsequence + [(currIndex, currValue)]
                    }
                    if let subsequence = newSubsequence {
                        cache.append(subsequence)
                    }
                }
            }
            cache.append([(currIndex, currValue)])
            currIndex += 1
        }
        let sortedCache = cache.sorted { $0.count >= $1.count }
        let result = sortedCache.prefix { $0.count == sortedCache.first!.count }
                .map { $0.map { $0.value } }
        return result
    }
    
}

