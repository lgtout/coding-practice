// Copyright (c) 2018 lagostout. All rights reserved.

import Foundation

class EPLongestBitonicSubsequence {

    enum Direction {
        case ascending
        case descending
    }

    static func computeWithBruteForceWithoutRecursion(sequence: [Int]) -> [[Int]] {
        if sequence.isEmpty {
            return []
        }
        var cache: [(elements: [(index: Int, value: Int)],
                     direction: Direction)] = []
        var currIndex = 0
        while currIndex < sequence.endIndex {
            let currValue = sequence[currIndex]
            for (elements, direction) in cache {
                let lastElement = elements.last!
                if direction == .ascending {
                    var direction: Direction? = nil
                    if currValue > lastElement.value {
                        direction = .ascending
                    } else if elements.count > 1 {
                        direction = .descending
                    }
                    if let direction = direction {
                        cache.append((elements + [(currIndex, currValue)], direction))
                    }
                } else if direction == .descending {
                    if currValue < lastElement.value {
                        cache.append((elements + [(currIndex, currValue)], .descending))
                    }
                }
            }
            cache.append(([(currIndex, currValue)], .ascending))
            currIndex += 1
        }
        let sortedCache = cache.filter { $0.direction == .descending
                }
                .map { $0.elements }
                .sorted { $0.count >= $1.count }
        let result = sortedCache.prefix {
            $0.count == sortedCache.first!.count
        }.map { $0.map { $0.value } }
        return result
    }

    static func computeWithBruteForceAndRecursion(sequence: [Int]) -> [[Int]] {
        if sequence.isEmpty {
            return []
        }

        let lastIndex = sequence.endIndex - 1

        let selectLongest = { (acc: [[Int]], curr: [Int]) -> [[Int]] in
            var acc = acc
            if acc.isEmpty || curr.count > acc.first!.count {
                acc = [curr]
            } else {
                acc += [curr]
            }
            return acc
        }

        func compute(_ index: Int, _ bound: Int, _ direction: Direction) -> [[Int]] {
            if index > lastIndex {
                return [[]]
            }

            let number = sequence[index]
            let nextIndex = index + 1

            // Include current number
            var subsequencesByIncludingCurrentNumber: [[Int]] = []
            if direction == Direction.ascending ||
                       (direction == Direction.descending && bound > number) {
                var direction = direction
                if direction == Direction.ascending && bound > number {
                    direction = Direction.descending
                }
                subsequencesByIncludingCurrentNumber =
                        compute(nextIndex, number, direction).map {
                            [number] + $0
                        }
            }

            // Skip current number
            let subsequencesBySkippingCurrentNumber = compute(nextIndex, bound, direction)

            // Select longest subsequences
            let longestSubsequences = (subsequencesByIncludingCurrentNumber +
                    subsequencesBySkippingCurrentNumber).reduce([[]], selectLongest)

            return longestSubsequences
        }

        let result: [[Int]] = (0...lastIndex).flatMap { startIndex -> [[Int]] in
            let startNumber = sequence[startIndex]
            return compute(startIndex + 1, startNumber, Direction.ascending).map {
                [startNumber] + $0
            }
        }.reduce([[]], selectLongest)

        return result
    }

    static func computeBottomUpWithMemoization(sequence: [Int]) -> [[Int]] {
        if sequence.count <= 1 {
            return [sequence]
        }

        let lastIndex = sequence.endIndex - 1

        let selectLongest = { (acc: [[Int]], curr: [Int]) -> [[Int]] in
            var acc = acc
            if acc.isEmpty || curr.count > acc.first!.count {
                acc = [curr]
            } else {
                acc += [curr]
            }
            return acc
        }

        // [index : [bound : [Direction : [subsequence]]]]
        var cache: [Int : [Int : [Direction : [[Int]]]]] = [:]

        // Initialize the cache.

        let endIndex = sequence.endIndex
        cache[endIndex] = [:]
        for boundIndex in 0...lastIndex {
            let bound = sequence[boundIndex]
            cache[endIndex]![bound] = [Direction.ascending : [[]],
                                       Direction.descending : [[]]]
        }

        // Build the cache.

        if sequence.count > 1 {
            for numberIndex in (1...lastIndex).reversed() {
                let number = sequence[numberIndex]
                cache[numberIndex] = [:]
                let nextIndex = numberIndex + 1
                for boundIndex in 0..<numberIndex {
                    let bound = sequence[boundIndex]
                    cache[numberIndex]![bound] = [:]
                    for direction in [Direction.ascending, Direction.descending] {

                        // Include current number
                        var subsequencesByIncludingCurrentNumber: [[Int]] = []
                        if direction == Direction.ascending ||
                                   (direction == Direction.descending && bound > number) {
                            var direction1 = direction
                            if direction1 == Direction.ascending && bound > number {
                                direction1 = Direction.descending
                            }
                            subsequencesByIncludingCurrentNumber =
                                    cache[nextIndex]![number]![direction1]!.map {
                                        [number] + $0
                                    }
                        }

                        // Skip current number
                        let subsequencesBySkippingCurrentNumber =
                                cache[nextIndex]![bound]![direction]!

                        // Select longest subsequences
                        let longestSubsequences = (subsequencesByIncludingCurrentNumber +
                                subsequencesBySkippingCurrentNumber).reduce([[]], selectLongest)

                        cache[numberIndex]![bound]![direction] = longestSubsequences
                    }
                }
            }
        }

        // Find the longest of the longest bitonic subsequences of each suffix.

        let result: [[Int]] = (0...lastIndex).flatMap { index -> [[Int]] in
            let bound = sequence[index]
            return cache[index + 1]![bound]![Direction.ascending]!.map {
                [bound] + $0
            }
        }.reduce([[]], selectLongest)

        return result

    }
}
