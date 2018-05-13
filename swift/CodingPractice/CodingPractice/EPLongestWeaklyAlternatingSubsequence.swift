// Copyright (c) 2018 lagostout. All rights reserved.

import Foundation
import SwiftyJSON

/* Problem 17.12.4 page 337 */

class EPLongestWeaklyAlternatingSubsequence {

    static let maxDescOrAscCount = 3

    enum Direction : String, Hashable, CustomStringConvertible, Codable {
        case asc, desc, flat
        func reverse() -> Direction {
            switch self {
            case .asc: return .desc
            case .desc: return .asc
            case .flat: return .flat
            }
        }
        func inDirection(_ n1: Int, _ n2: Int) -> Bool {
            switch self {
            case .asc: return n1 < n2
            case .desc: return n1 > n2
            case .flat: return n1 == n2
            }
        }
        static func of(_ n1: Int, _ n2: Int) -> Direction {
            switch true {
            case n1 < n2: return .asc
            case n1 > n2: return .desc
            default: return .flat
            }
        }
        public var description: String { return rawValue }
    }

    static func computeByBruteForceWithRecursion1(sequence: [Int]) -> [[Int]] {
        guard !sequence.isEmpty else {
            return [[]]
        }
        let lastIndex = sequence.endIndex - 1
        func compute(_ sequenceIndex: Int, _ bound: Int?,
                     _ ascCount: Int, _ descCount: Int) -> [[Int]] {

            if sequenceIndex == sequence.endIndex ||
                       ascCount >= maxDescOrAscCount ||
                       descCount >= maxDescOrAscCount {
                return [[]]
            }

            let nextSequenceIndex = sequenceIndex + 1

            // Exclude entry
            let subsequencesWithoutEntry = compute(
                    nextSequenceIndex, bound, ascCount, descCount)

            // Include entry
            let entry = sequence[sequenceIndex]
            var subsequencesWithEntry: [[Int]] = []
            let bound = bound ?? entry
            if entry > bound {
                subsequencesWithEntry += compute(
                        nextSequenceIndex, entry, ascCount + 1, 0)
            } else if entry < bound {
                subsequencesWithEntry += compute(
                        nextSequenceIndex, entry, 0, descCount + 1)
            } else {
                subsequencesWithEntry = compute(
                        nextSequenceIndex, entry, 0, 0)
            }
            subsequencesWithEntry = subsequencesWithEntry.map {
                [entry] + $0
            }

            // Select longest subsequences
            let longestSubsequences = self.longestSubsequences(
                    subsequencesWithoutEntry, subsequencesWithEntry)

            return longestSubsequences
        }
        return compute(0, nil, 0, 0)
    }

    static func computeByBruteForceWithRecursion2(sequence: [Int]) -> [[Int]] {
        guard !sequence.isEmpty else {
            return [[]]
        }
        let lastIndex = sequence.endIndex - 1
        let maxDescOrAscCount = 3
        func compute(_ sequenceIndex: Int, _ bound: Int?,
                     _ ascCount: Int, _ descCount: Int,
                     _ subsequence: [Int]) -> [[Int]] {

            if sequenceIndex == sequence.endIndex ||
                       ascCount >= maxDescOrAscCount ||
                       descCount >= maxDescOrAscCount {
                return [subsequence]
            }

            let nextSequenceIndex = sequenceIndex + 1

            // Exclude entry from current subsequence
            let subsequencesWithoutEntry = compute(
                    nextSequenceIndex, bound, ascCount,
                    descCount, subsequence)

            let entry = sequence[sequenceIndex]

            // Start subsequence at current entry
            let subsequencesStartingAtEntry = compute(
                    nextSequenceIndex, entry, 0, 0, [entry])

            // Continue current subsequence at current entry
            var subsequencesWithEntry: [[Int]] = []
            if let bound = bound {
                if entry > bound {
                    subsequencesWithEntry += compute(
                            nextSequenceIndex, entry,
                            ascCount + 1, 0, subsequence + [entry])
                } else if entry < bound {
                    subsequencesWithEntry += compute(
                            nextSequenceIndex, entry,
                            0, descCount + 1, subsequence + [entry])
                } else {
                    subsequencesWithEntry = compute(
                            nextSequenceIndex, entry, 0, 0,
                            subsequence + [entry])
                }
            }

            // Select longest subsequences
            let longestSubsequences = [
                subsequencesWithEntry,
                subsequencesWithoutEntry,
                subsequencesStartingAtEntry
            ].reduce ([[]]) {
                (acc: [[Int]], curr: [[Int]]) in
                return self.longestSubsequences(acc, curr)
            }

            return longestSubsequences
        }
        return compute(0, nil, 0, 0, [])
    }

    // Broken - doesn't pass all tests.  I don't know why.
    static func computeByBruteForceWithRecursion3(sequence: [Int]) -> [[Int]] {
        guard !sequence.isEmpty else {
            return [[]]
        }
        let lastIndex = sequence.endIndex - 1
        let maxDescOrAscCount = 3
        func compute(_ sequenceIndex: Int, _ boundIndex: Int,
                     _ ascCount: Int, _ descCount: Int,
                     _ subsequence: [Int]) -> [[Int]] {

            if sequenceIndex == sequence.endIndex ||
                       boundIndex == sequence.endIndex ||
                       ascCount >= maxDescOrAscCount ||
                       descCount >= maxDescOrAscCount {
                return [subsequence]
            }

            let nextSequenceIndex = sequenceIndex + 1

            // Exclude current entry if we're not starting a subsequence.
            var subsequencesWithoutEntry: [[Int]] = [[]]
            if !subsequence.isEmpty {
                subsequencesWithoutEntry = compute(
                        nextSequenceIndex, nextSequenceIndex, ascCount,
                        descCount, subsequence + [])
            }

            // Include current entry
            let entry = sequence[sequenceIndex]
            let bound = sequence[boundIndex]
            var subsequencesWithEntry: [[Int]] = []
            if entry > bound {
                subsequencesWithEntry += compute(
                        nextSequenceIndex, sequenceIndex,
                        ascCount + 1, 0, subsequence + [entry])
            } else if entry < bound {
                subsequencesWithEntry += compute(
                        nextSequenceIndex, sequenceIndex,
                        0, descCount + 1, subsequence + [entry])
            } else {
                subsequencesWithEntry = compute(
                        nextSequenceIndex, sequenceIndex, 0, 0,
                        subsequence + [entry])
            }

            // Select longest subsequences
            let result = [
                subsequencesWithEntry, subsequencesWithoutEntry
            ].reduce ([[]]) { acc, curr in
                return longestSubsequences(acc, curr)
            }

            return result
        }

        let result = ((0...0).map { index in
            let result = compute(index, index, 0, 0, [])
            return result
        }).reduce ([[]]) { acc, curr in
            longestSubsequences(acc, curr)
        }

        return result
    }

    static func computeByBruteForceWithRecursion4(sequence: [Int]) -> [[Int]] {
        guard !sequence.isEmpty else {
            return [[]]
        }
        let lastIndex = sequence.endIndex - 1
        let maxDescOrAscCount = 3
        func compute(_ sequenceIndex: Int, _ boundIndex: Int,
                     _ ascCount: Int, _ descCount: Int) -> [[Int]] {

            if sequenceIndex == sequence.endIndex ||
                       boundIndex == sequence.endIndex ||
                       ascCount >= maxDescOrAscCount ||
                       descCount >= maxDescOrAscCount {
                return [[]]
            }

            let nextSequenceIndex = sequenceIndex + 1

            // Exclude current entry
            let subsequencesWithoutEntry = compute(
                    nextSequenceIndex, boundIndex,
                    ascCount, descCount)

            // Include current entry
            let entry = sequence[sequenceIndex]
            let bound = sequence[boundIndex]
            var subsequencesWithEntry: [[Int]] = []
            if entry > bound {
                subsequencesWithEntry = compute(
                        nextSequenceIndex, sequenceIndex,
                        ascCount + 1, 0)
            } else if entry < bound {
                subsequencesWithEntry = compute(
                        nextSequenceIndex, sequenceIndex,
                        0, descCount + 1)
            } else {
                subsequencesWithEntry = compute(
                        nextSequenceIndex, sequenceIndex, 0, 0)
            }
            subsequencesWithEntry = subsequencesWithEntry.map {
                [entry] + $0
            }

            // Select longest subsequences
            let result = [
                subsequencesWithEntry, subsequencesWithoutEntry
            ].reduce ([[]]) { acc, curr in
                return longestSubsequences(acc, curr)
            }

            return result
        }

        let result = ((0...lastIndex).map { index in
            return compute(index, index, 0, 0)
        }).reduce ([[]]) { acc, curr in
            longestSubsequences(acc, curr)
        }

        return result
    }

    static func computeByBruteForceWithRecursion5(sequence: [Int]) -> [[Int]] {
        guard !sequence.isEmpty else {
            return [[]]
        }

        let lastIndex = sequence.endIndex - 1
        let maxDescOrAscCount = 3

        func compute(_ sequenceIndex: Int, _ boundIndex: Int,
                     _ direction: Direction, _ count: Int) -> [[Int]] {

            if sequenceIndex == sequence.endIndex ||
                       boundIndex == sequence.endIndex ||
                       count >= maxDescOrAscCount {
                return [[]]
            }

            let entry = sequence[sequenceIndex]
            let bound = sequence[boundIndex]
            let directionConstraintSatisfied =
                    direction.inDirection(bound, entry) || bound == entry

            if !directionConstraintSatisfied {
                return [[]]
            }

            let atLastIndex = sequenceIndex == lastIndex
            if atLastIndex && directionConstraintSatisfied {
                return [[entry]]
            }

            let nextSequenceIndex = sequenceIndex + 1

            // Include entry in subsequence
            var subsequencesWithEntryInDirection: [[Int]] = [[]]
            if directionConstraintSatisfied {
                subsequencesWithEntryInDirection = compute(
                        nextSequenceIndex, sequenceIndex, direction, count + 1).map {
                    [entry] + $0
                }
            }
            let subsequencesWithEntryInOppositeDirection = compute(
                    nextSequenceIndex, sequenceIndex, direction.reverse(), 0).map {
                [entry] + $0
            }

            // Skip entry - exclude from subsequence
            let subsequencesWithoutEntryInDirection = compute(
                    nextSequenceIndex, boundIndex, direction, count)
            let subsequencesWithoutEntryInOppositeDirection = compute(
                    nextSequenceIndex, boundIndex, direction.reverse(), 0)

            let subsequences = [
                subsequencesWithEntryInDirection,
                subsequencesWithEntryInOppositeDirection,
                subsequencesWithoutEntryInDirection,
                subsequencesWithoutEntryInOppositeDirection].reduce([[]]) {
                acc, curr in longestSubsequences(acc, curr)
            }

            return subsequences
        }

        let result = ((0...lastIndex).compactMap { index -> [[Int]] in
            let result: [[Int]] = [.asc, .desc].compactMap { direction -> [[Int]] in
                compute(index, index, direction, 0)
            }.reduce ([[]]) { acc, curr in
                longestSubsequences(acc, curr)
            }
            return result
        }).reduce ([[]]) { (acc: [[Int]], curr: [[Int]]) in
            return longestSubsequences(acc, curr)
        }

        return result
    }

    private static func longestSubsequences(_ s1: [[Int]], _ s2: [[Int]]) -> [[Int]] {
        let s1Count = s1.first?.count ?? 0
        let s2Count = s2.first?.count ?? 0
        var result = s1
        if s2Count > s1Count {
            result = s2
        } else if s2Count == s1Count {
            result += s2
        }
        return result
    }

    struct Key : Hashable, Codable  {
        let sequenceIndex: Int
        let bound: Int
        let ascCount: Int
        let descCount: Int
        init(_ sequenceIndex: Int, _ bound: Int,
             _ ascCount: Int, _ descCount: Int) {
            self.sequenceIndex = sequenceIndex
            self.bound = bound
            self.ascCount = ascCount
            self.descCount = descCount
        }
    }

    struct Key2 : Hashable, Codable  {
        let sequenceIndex: Int
        let boundIndex: Int
        let directionCount: DirectionCount?

        init(_ sequenceIndex: Int, _ bound: Int,
             _ directionCount: DirectionCount? = nil) {
            self.sequenceIndex = sequenceIndex
            self.boundIndex = bound
            self.directionCount = directionCount
        }

        var hashValue: Int {
            var result = sequenceIndex
            result = 31 * result + boundIndex
            if let directionCount = directionCount {
                result = 31 * result + directionCount.hashValue
            }
            return result
        }

        struct DirectionCount : Hashable, Codable {
            let direction: Direction
            let count: Int
            init(_ direction: Direction, _ count: Int) {
                self.direction = direction
                self.count = count
            }
            var hashValue: Int {
                var result = direction.hashValue
                result = 31 * result + count
                return result
            }
        }
    }

    struct Key3 : Hashable, Codable  {
        let sequenceIndex: Int
        let boundIndex: Int
        let direction: Direction
        let count: Int
        init(_ sequenceIndex: Int, _ boundIndex: Int,
             _ direction: Direction, _ count: Int) {
            self.sequenceIndex = sequenceIndex
            self.boundIndex = boundIndex
            self.direction = direction
            self.count = count
        }
    }

    /* Don't treat .flat direction as distinct from .asc and .desc.  Instead,
     .asc is includes .flat, and .desc includes .flat as well.  So, at each
     position we have 2 choices of direction to take. */
    static func computeBottomUpWithMemoization1(sequence: [Int]) -> [[Int]] {
        guard !sequence.isEmpty else {
            return [[]]
        }
        var cache: [Key3: [[Int]]] = [:]
        let lastIndex = sequence.endIndex - 1
        let maxAscOrDescCount = 3
        for sequenceIndex in stride(from: lastIndex, through: 0, by: -1) {
            let entry = sequence[sequenceIndex]
            let nextSequenceIndex = sequenceIndex + 1
            for boundIndex in stride(from: sequenceIndex, through: 0, by: -1) {
                let bound = sequence[boundIndex]
                for direction: Direction in [.asc, .desc] {
                    for count in 0...maxAscOrDescCount {
                        var subsequencesWithEntry: [[Int]] = []

                        // Include entry
                        let entryDirection = Direction.of(bound, entry)
                        let entryIsInCompatibleDirection =
                                entryDirection == direction || entryDirection == .flat
                        let reachedMaxCount = direction != .flat && count >= maxAscOrDescCount
                        let sameDirectionCount = entryDirection == .flat ? 0 : count + 1
                        let atLastIndex = sequenceIndex == lastIndex
                        if (entryIsInCompatibleDirection && !reachedMaxCount) {
                            if atLastIndex {
                                subsequencesWithEntry = [[entry]]
                            } else {
                                let keyInSameDirection = Key3(
                                        nextSequenceIndex, sequenceIndex,
                                        direction, sameDirectionCount)
                                let keyInOppositeDirection = Key3(
                                        nextSequenceIndex, sequenceIndex,
                                        direction.reverse(), 0)
                                subsequencesWithEntry = [
                                    keyInSameDirection, keyInOppositeDirection
                                ].map { key in
                                    cache[key]!.map {
                                        [entry] + $0
                                    }
                                }.reduce([[]]) { acc, curr in
                                    longestSubsequences(acc, curr)
                                }
                            }
                        }

                        // Exclude entry
                        var subsequencesWithoutEntry: [[Int]] = [[]]
                        if !(atLastIndex || reachedMaxCount) {
                            // We are constrained to only proceed in the same direction.
                            let key = Key3(nextSequenceIndex, boundIndex, direction, count)
                            subsequencesWithoutEntry = cache[key]!
                        }

                        // Combine alternatives and select longest subsequences.
                        let subsequences = longestSubsequences(
                                subsequencesWithEntry, subsequencesWithoutEntry)

                        let key = Key3(sequenceIndex, boundIndex, direction, count)
                        cache[key] = subsequences
                    }
                }
            }
        }

        let result = ((0...lastIndex).map { index in
            cache[Key3(index, index, .asc, 0)]!
        }).reduce ([[]]) { acc, curr in
            longestSubsequences(acc, curr)
        }

        return result

    }

    /* Treat .flat direction separately from .asc and .desc.  So, at each position
      we have 3 choices of direction to take. */
    static func computeBottomUpWithMemoization2(sequence: [Int]) -> [[Int]] {
        guard !sequence.isEmpty else {
            return [[]]
        }
        var cache: [Key3: [[Int]]] = [:]
        let lastIndex = sequence.endIndex - 1
        let maxAscOrDescCount = 3
        for sequenceIndex in stride(from: lastIndex, through: 0, by: -1) {
            let entry = sequence[sequenceIndex]
            let nextSequenceIndex = sequenceIndex + 1
            for boundIndex in stride(from: sequenceIndex, through: 0, by: -1) {
                let bound = sequence[boundIndex]
                for direction: Direction in [.asc, .desc, .flat] {
                    for count in 0...maxAscOrDescCount {
                        var subsequencesWithEntry: [[Int]] = []

                        // Include entry
                        let entryDirection = Direction.of(bound, entry)
                        let entryIsInCompatibleDirection = entryDirection == direction
                        let reachedMaxCount = direction != .flat && count >= maxAscOrDescCount
                        let atLastIndex = sequenceIndex == lastIndex
                        if (entryIsInCompatibleDirection && !reachedMaxCount) {
                            if atLastIndex {
                                subsequencesWithEntry = [[entry]]
                            } else {
                                let keyInSameDirection = Key3(
                                        nextSequenceIndex, sequenceIndex,
                                        .asc, direction == .asc ? count + 1 : 0)
                                let keyInOppositeDirection = Key3(
                                        nextSequenceIndex, sequenceIndex,
                                        .desc, direction == .desc ? count + 1 : 0)
                                let keyInFlatDirection = Key3(
                                        nextSequenceIndex, sequenceIndex,
                                        .flat, 0)
                                subsequencesWithEntry = [
                                    keyInSameDirection,
                                    keyInOppositeDirection,
                                    keyInFlatDirection
                                ].map { key in
                                    cache[key]!.map {
                                        [entry] + $0
                                    }
                                }.reduce([[]]) { acc, curr in
                                    longestSubsequences(acc, curr)
                                }
                            }
                        }

                        // Exclude entry
                        var subsequencesWithoutEntry: [[Int]] = [[]]
                        if !(atLastIndex || reachedMaxCount) {
                            // We are constrained to only proceed in the same direction.
                            let key = Key3(nextSequenceIndex, boundIndex, direction, count)
                            subsequencesWithoutEntry = cache[key]!
                        }

                        // Combine alternatives and select longest subsequences.
                        let subsequences = longestSubsequences(
                                subsequencesWithEntry, subsequencesWithoutEntry)

                        let key = Key3(sequenceIndex, boundIndex, direction, count)
                        cache[key] = subsequences
                    }
                }
            }
        }

        let result = ((0...lastIndex).flatMap { index in
            ([.asc, .desc, .flat] as [Direction]).flatMap {
                direction in
                cache[Key3(index, index, direction, 0)]!
            }.reduce ([[]]) { acc, curr in
                longestSubsequences(acc, curr)
            }
        }).reduce ([[]]) { acc, curr in
            longestSubsequences(acc, curr)
        }

        return result

    }
}

