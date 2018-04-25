// Copyright (c) 2018 lagostout. All rights reserved.

import Foundation

/* Problem 17.12.1 page 335 */

class LengthOfLongestNondecreasingSubsequence {

    static func computeWithRecursionAndBruteForce(sequence: [Int]) -> Int {
        if sequence.isEmpty {
            return 0
        }
        func compute(_ index: Int, _ lowerBound: Int) -> Int {
            let entry = sequence[index]
            let lastIndex = sequence.endIndex - 1
            if index == lastIndex {
                return lowerBound <= entry ? 1 : 0
            }
            let nextIndex = index + 1
            var longestSubsequenceLengthWithEntry: Int = 0
            if entry >= lowerBound {
                longestSubsequenceLengthWithEntry = compute(nextIndex, entry) + 1
            }
            let longestSubsequenceLengthWithoutEntry =
                    compute(nextIndex, lowerBound)
            let longestSubsequenceLength = max(
                    longestSubsequenceLengthWithoutEntry,
                    longestSubsequenceLengthWithEntry)
            return longestSubsequenceLength
        }
        return compute(0, Int.min)
    }

    static func computeBottomUpWithMemoization(sequence: [Int]) -> Int {

        if sequence.isEmpty {
            return 0
        }

        // [ sequence_index : [ lower_bound_value : [ length_of_longest_subsequence ]]
        var cache: [Int : [Int : Int]] = [:]

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

        let lastIndex = sequence.endIndex - 1
        var sequenceIndex = lastIndex
        while sequenceIndex >= 0 {
            var lowerBoundIndex = sequenceIndex
            var lowerBoundsToLongestSubsequences: [Int: Int] = [:]
            let entry = sequence[sequenceIndex]
            cache[sequenceIndex] = lowerBoundsToLongestSubsequences
            while lowerBoundIndex >= 0 {
                let lowerBound = sequence[lowerBoundIndex]
                /* If we've already seen this lowerBound at this index, we
                don't need to recompute longest subsequences, because it
                won't change from what we currently have in the cache. This
                occurs if we have duplicate entries in the sequence. */
                if lowerBoundsToLongestSubsequences[lowerBound] == nil {
                    var subsequencesWithoutEntryLength = 0
                    var subsequencesWithEntryLength = 0
                    let nextSubsequenceIndex = sequenceIndex + 1
                    if entry >= lowerBound {
                        if sequenceIndex == lastIndex {
                            subsequencesWithEntryLength = 1
                        } else {
                            subsequencesWithEntryLength =
                                    cache[nextSubsequenceIndex]![entry]! + 1
                        }
                    }
                    if sequenceIndex < lastIndex {
                        subsequencesWithoutEntryLength =
                                cache[nextSubsequenceIndex]![lowerBound]!
                    }
                    let longestSubsequenceLengths = max(
                            subsequencesWithEntryLength,
                            subsequencesWithoutEntryLength)
                    cache[sequenceIndex]![lowerBound] = longestSubsequenceLengths
                }
                lowerBoundIndex -= 1
            }
            sequenceIndex -= 1
        }

        // Retrieve the longest subsequences from the cache.
        var lowerBoundLongestSubsequenceLengths: [Int] = []
        var index = 0
        while index < sequence.count {
            let lowerBound = sequence[index]
            lowerBoundLongestSubsequenceLengths.append(cache[index]![lowerBound]!)
            index += 1
        }
        let result = lowerBoundLongestSubsequenceLengths.max()
        return result ?? 0
    }
}
