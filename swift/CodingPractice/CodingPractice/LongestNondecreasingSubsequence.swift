// Copyright (c) 2018 lagostout. All rights reserved.

import Foundation

/* Problem 17.12.2 page 336 */

class LongestNondecreasingSubsequence {

    static func computeWithRecursionAndBruteForce(sequence: [Int]) -> [[Int]] {
        if sequence.isEmpty {
            return Array<[Int]>()
        }
        func compute(_ index: Int, _ lowerBound: Int) -> [[Int]] {
            let value = sequence[index]
            if index == sequence.endIndex - 1 {
                return lowerBound <= value ? [[value]] : [[]]
            }
            let nextIndex = index + 1
            var longestSubsequencesWithCurrentValue: [[Int]] = []
            if value >= lowerBound {
                longestSubsequencesWithCurrentValue =
                        compute(nextIndex, value).map {
                            [value] + $0
                        }
            }
            let longestSubsequencesWithoutCurrentValue =
                    compute(nextIndex, lowerBound)
            var subsequences = longestSubsequencesWithCurrentValue +
                    longestSubsequencesWithoutCurrentValue
            subsequences.sort(by: { $0.count >= $1.count })
            let longestSubsequences = subsequences.prefix {
                $0.count == subsequences[0].count }
            return Array(longestSubsequences)
        }
        return compute(0, Int.min)
    }

    /* This following DP approach copies subsequences (solutions of
    sub-sub-problems), when computing solution of the current sub-problem.
    This has O(2^n) space and time complexity.  That's unfortunate, since
    if we ignore this copying, our DP solution improves brute force by
    reducing time complexity from O(2^n) to O(n^2).

    An alternate approach, that would force overall complexity down to O(n^2)
    (at least until we attempt to reconstruct the longest subsequences), would
    be to store the (grid) coordinates of the next entries at any position in a
    longest subsequence.  There would be no more than 2 such coordinates at any
    position in the grid, since there are only 2 ways any subsequence can extended
    - by including or excluding the entry at the current position of the subsequence.
    If we have to retrieve all longest subsequences as a last step our time complexity
    degrades back down to O(2^n).  If we only need to return a single such longest
    subsequence, we can construct that in O(n), leaving our O(n^2) time complexity
    unaffected */

    static func computeBottomUpWithMemoization(sequence: [Int]) -> [[Int]] {

        if sequence.isEmpty {
            return []
        }

        // [ sequence_index : [ lower_bound_value : [[ longest_subsequence_of_values ]]
        var cache: [Int : [Int : [[Int]]]] = [:]

        // Seed the cache.

        var cacheLastColumn: [Int : [[Int]]] = [:]
        let lastIndex = sequence.endIndex - 1
        let lastEntry = sequence.last!
        var lowerBoundIndex = lastIndex
        while lowerBoundIndex >= 0 {
            let lowerBound = sequence[lowerBoundIndex]
            var longestSubsequences = [[]] as [[Int]]
            if lastEntry >= lowerBound {
                longestSubsequences = [[lastEntry]]
            }
            cacheLastColumn[lowerBound] = longestSubsequences
            lowerBoundIndex -= 1
        }
        cache[lastIndex] = cacheLastColumn

        func longestOf(_ s1: [[Int]], _ s2: [[Int]]) -> [[Int]] {
            let s1Count = (s1.isEmpty ?
                    0 : s1.first!.count)
            let s2Count = s2.isEmpty ?
                    0 : s2.first!.count
            let result: [[Int]]
            if s1Count > s2Count {
                result = s1
            } else if s1Count < s2Count {
                result = s2
            } else {
                result = s1 + s2
            }
            return result
        }

        var sequenceIndex = lastIndex - 1
        while sequenceIndex >= 0 {
            var lowerBoundIndex = sequenceIndex
            var lowerBoundsToLongestSubsequences: [Int: [[Int]]] = [:]
            cache[sequenceIndex] = lowerBoundsToLongestSubsequences
            while lowerBoundIndex >= 0 {
                let lowerBound = sequence[lowerBoundIndex]
                /* If we've already seen this lowerBound at this index, we
                don't need to recompute longest subsequences, because it
                won't change from what we currently have in the cache. */
                if lowerBoundsToLongestSubsequences[lowerBound] == nil {
                    let nextSubsequenceIndex = sequenceIndex + 1
                    let subsequencesWithoutEntry = cache[nextSubsequenceIndex]![lowerBound]!
                    let entry = sequence[sequenceIndex]
                    var subsequencesWithEntry: [[Int]] = [[]]
                    if entry >= lowerBound {
                        subsequencesWithEntry = cache[nextSubsequenceIndex]![entry]!.map {
                            [entry] + $0
                        }
                    }
                    cache[sequenceIndex]![lowerBound] = longestOf(
                            subsequencesWithEntry, subsequencesWithoutEntry)
                }
                lowerBoundIndex -= 1
            }
            sequenceIndex -= 1
        }

        // Retrieve the longest subsequences from the cache.
        var lowerBoundLongestSubsequences = [[Int]]()
        var index = 0
        while index < sequence.count {
            let lowerBound = sequence[index]
            lowerBoundLongestSubsequences += cache[index]![lowerBound]!
            index += 1
        }
        let result = lowerBoundLongestSubsequences.reduce([[Int]]()) { result, entry in
            return longestOf(result, [entry])
        }

        return result
    }

}
