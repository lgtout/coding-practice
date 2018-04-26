// Copyright (c) 2018 lagostout. All rights reserved.

import Foundation

/* Problem 17.12.5 page 337 */

class EPLongestConvexSubsequence {

    static func computeBottomUpWithMemoization(sequence: [Int]) -> [[Int]] {
        if sequence.isEmpty {
            return []
        }
        var cache: [[(index: Int, value: Int)]] = []
        var currIndex = 0
        while currIndex < sequence.endIndex {
            let currValue = sequence[currIndex]
            for subsequence in cache {
                var appendCurrValue = true
                if subsequence.count >= 2 {
                    let firstValue = subsequence[
                            subsequence.endIndex - 2].value
                    let secondValue = subsequence[
                            subsequence.endIndex - 1].value
                    appendCurrValue = Double(secondValue)
                            < Double(firstValue + currValue) / 2.0
                }
                if appendCurrValue {
                    let newSubsequence = subsequence + [(currIndex, currValue)]
                    cache.append(newSubsequence)
                }
            }
            cache.append([(currIndex, currValue)])
            currIndex += 1
        }
        let sortedCache = cache.filter { $0.count >= 3 }
                .sorted { $0.count >= $1.count }
        let result = sortedCache.prefix {
            $0.count == sortedCache.first!.count
        }.map { $0.map { $0.value } }
        return result
    }

}
