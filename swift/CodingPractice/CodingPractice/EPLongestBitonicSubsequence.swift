// Copyright (c) 2018 lagostout. All rights reserved.

import Foundation

class EPLongestBitonicSubsequence {

    static func computeBottomUpWithMemoization(sequence: [Int]) -> [[Int]] {
        if sequence.isEmpty {
            return []
        }
        enum Direction {
            case Ascending
            case Descending
        }
        var cache: [(elements: [(index: Int, value: Int)],
                     direction: Direction)] = []
        var currIndex = 0
        while currIndex < sequence.endIndex {
            let currValue = sequence[currIndex]
            for (elements, direction) in cache {
                let lastElement = elements.last!
                if direction == .Ascending {
                    var direction: Direction? = nil
                    if currValue > lastElement.value {
                        direction = .Ascending
                    } else if elements.count > 1 {
                        direction = .Descending
                    }
                    if let direction = direction {
                        cache.append((elements + [(currIndex, currValue)], direction))
                    }
                } else if direction == .Descending {
                    if currValue < lastElement.value {
                        cache.append((elements + [(currIndex, currValue)], .Descending))
                    }
                }
            }
            cache.append(([(currIndex, currValue)], .Ascending))
            currIndex += 1
        }
        let sortedCache = cache.filter { $0.direction == .Descending }
                .map { $0.elements }
                .sorted { $0.count >= $1.count }
        let result = sortedCache.prefix {
            $0.count == sortedCache.first!.count
        }.map { $0.map { $0.value } }
        return result
    }

}
