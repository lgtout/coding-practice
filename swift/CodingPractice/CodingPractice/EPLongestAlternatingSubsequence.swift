// Copyright (c) 2018 lagostout. All rights reserved.

import Foundation

/* Problem 17.12.3 page 336 */

class EPLongestAlternatingSubsequence {

    static func computeByBruteForceWithoutRecursion(sequence: [Int]) -> [[Int]] {
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

    enum Direction {
            case asc, desc
        static func all() -> [Direction]  {
            return [.asc, .desc]
        }
        func next() -> Direction {
            switch self {
                case .asc: return .desc
                case .desc: return .asc
            }
        }
    }

    static func computeWithRecursionAndBruteForce(sequence: [Int]) -> [[Int]] {
        if sequence.isEmpty {
            return [[]]
        }
        let lastIndex = sequence.endIndex - 1
        func compute(_ sequenceIndex: Int, _ direction: Direction, _ bound: Int) ->
                ArraySlice<[Int]> {
            let entry = sequence[sequenceIndex]
            if sequenceIndex == lastIndex {
                var result: ArraySlice<[Int]> = [[]]
                if direction == .asc && entry >= bound ||
                        direction == .desc && entry <= bound {
                    result = ArraySlice([[entry]])
                }
                return result
            }
            var subsequencesWithEntry: [[Int]] = [[]]
            if direction == .asc && entry >= bound ||
                       direction == .desc && entry <= bound {
                subsequencesWithEntry = compute(
                        sequenceIndex + 1, direction.next(), entry).map {
                    [entry] + $0
                }
            }
            let subsequencesWithoutEntry = compute(
                    sequenceIndex + 1, direction, bound)
            let allSubsequences: [[Int]] = subsequencesWithEntry + subsequencesWithoutEntry
            let sortedSubsequences = allSubsequences.sorted { a, b in a.count >= b.count }
            let maxCount = sortedSubsequences.first?.count ?? 0
            let result = sortedSubsequences.prefix { s in s.count == maxCount }
            return result
        }
        return Array(compute(0, .desc, Int.max))
    }

    static func computeBottomUpWithMemoization(sequence: [Int]) -> [[Int]] {
        if sequence.isEmpty {
            return [[]]
        }
        struct Key : Hashable {
            let sequenceIndex: Int
            let direction: Direction
            let bound: Int
            init(_ sequenceIndex: Int, _ direction: Direction, _ bound: Int) {
                self.sequenceIndex = sequenceIndex
                self.direction = direction
                self.bound = bound
            }
        }
        let lastIndex = sequence.endIndex - 1
        var cache: [Key : [[Int]]] = [:]
        var sequenceIndex = lastIndex
        while sequenceIndex >= 0 {
            var boundIndex = sequenceIndex
            let entry = sequence[sequenceIndex]
            while boundIndex >= 0 {
                let bound = sequence[boundIndex]
                for direction in Direction.all() {
                    let key = Key(sequenceIndex, direction, bound)
                    if cache[key] != nil { continue }
                    var subsequencesWithEntry: [[Int]] = [[]]
                    if direction == .asc && entry >= bound ||
                               direction == .desc && entry <= bound {
                        if sequenceIndex == lastIndex {
                            subsequencesWithEntry = [[entry]]
                        } else {
                            let key = Key(sequenceIndex + 1, direction.next(), entry)
                            subsequencesWithEntry = cache[key]!.map {
                                [entry] + $0
                            }
                        }
                    }
                    var subsequencesWithoutEntry: [[Int]] = [[]]
                    if sequenceIndex < lastIndex {
                        let key = Key(sequenceIndex + 1, direction, bound)
                        subsequencesWithoutEntry = cache[key]!
                    }
                    let subsequencesWithoutEntryCount = subsequencesWithoutEntry.first!.count
                    let subsequencesWithEntryCount = subsequencesWithEntry.first!.count
                    var result = subsequencesWithEntry
                    if subsequencesWithEntryCount == subsequencesWithoutEntryCount {
                        result += subsequencesWithoutEntry
                    } else if  subsequencesWithoutEntryCount > subsequencesWithEntryCount {
                        result = subsequencesWithoutEntry
                    }
                    cache[key] = Array(result)
                }
                boundIndex -= 1
            }
            sequenceIndex -= 1
        }
        let allSubsequences: [[Int]] = sequence.indices.flatMap { (index: Int) -> [[Int]] in
            let key = Key(index, .desc, sequence[index])
            return cache[key]!
        }
        let sortedSubsequences = allSubsequences.sorted { a, b in a.count >= b.count }
        let maxCount = sortedSubsequences.first?.count ?? 0
        let result = sortedSubsequences.prefix { s in s.count == maxCount }
        return Array(result)
    }

}

