// Copyright (c) 2018 lagostout. All rights reserved.

import Foundation

/* Problem 17.8 page 328 */

class EPMinimumWeightPathInTriangle {

    typealias Result = (path: [Int], weight: Int)

    /* To simplify debugging, let's return the path (indices of triangle levels),
     in addition to the total weight of the path. */
    static func computeWithRecursionAndBruteForce(_ triangle: Array<Array<Int>>) -> Result {
        if triangle.isEmpty { return ([], 0) }
        func compute(_ levelIndex: Int, _ rootIndex: Int) -> Result {
            let rootWeight = triangle[levelIndex][rootIndex]
            if levelIndex == triangle.indices.last {
                return ([rootIndex], rootWeight)
            }
            let nextLevelIndex = levelIndex + 1
            let leftRootIndex = rootIndex
            let rightRootIndex = rootIndex + 1
            let leftTreeResult = compute(nextLevelIndex, leftRootIndex)
            let rightTreeResult = compute(nextLevelIndex, rightRootIndex)
            let minimumNextLevelResult = leftTreeResult.weight <=
                    rightTreeResult.weight ? leftTreeResult : rightTreeResult
            return ([rootIndex] + minimumNextLevelResult.path,
                    rootWeight + minimumNextLevelResult.weight)
        }
        return compute(0, 0)
    }

    struct Key : Hashable {
        let levelIndex: Int
        let rootIndex: Int
        init (_ levelIndex: Int, _ rootIndex: Int) {
            self.levelIndex = levelIndex
            self.rootIndex = rootIndex
        }
    }

    static func computeWithRecursionAndMemoization(_ triangle: Array<Array<Int>>) -> Result {
        if triangle.isEmpty { return ([], 0) }
        var cache: [Key : (path: [Int], weight: Int)] = [:]
        func compute(_ levelIndex: Int, _ rootIndex: Int) -> Result {
            let key = Key(levelIndex, rootIndex)
            if let cachedResult = cache[key] {
                return cachedResult
            }
            let rootWeight = triangle[levelIndex][rootIndex]
            if levelIndex == triangle.indices.last {
                return ([rootIndex], rootWeight)
            }
            let nextLevelIndex = levelIndex + 1
            let leftRootIndex = rootIndex
            let rightRootIndex = rootIndex + 1
            let leftTreeResult = compute(nextLevelIndex, leftRootIndex)
            let rightTreeResult = compute(nextLevelIndex, rightRootIndex)
            let minimumNextLevelResult = leftTreeResult.weight <=
                    rightTreeResult.weight ? leftTreeResult : rightTreeResult
            let result = ([rootIndex] + minimumNextLevelResult.path,
                    rootWeight + minimumNextLevelResult.weight)
            cache[key] = result
            return result
        }
        return compute(0, 0)
    }

    static func computeBottomUpWithMemoization(_ triangle: Array<Array<Int>>) -> Result {
        if triangle.isEmpty { return ([], 0) }
        var cache: [Key : (path: [Int], weight: Int)] = [:]
        let lastLevelIndex = triangle.count - 1
        for (index, weight) in triangle.last!.enumerated() {
            cache[Key(lastLevelIndex, index)] = ([index], weight)
        }
        for (levelIndex, level) in triangle.enumerated().reversed().suffix(triangle.count - 1) {
            let nextLevelIndex = levelIndex + 1
            for (weightIndex, weight) in level.enumerated() {
                let minimumNextLevelResult = [weightIndex, weightIndex + 1].compactMap { index in
                    cache[Key(nextLevelIndex, index)]
                    }.sorted { (first: Result, second: Result) in
                        first.weight < second.weight
                }.first!
                let minimumResult = Result([weightIndex] + minimumNextLevelResult.path, weight + minimumNextLevelResult.weight)
                cache[Key(levelIndex, weightIndex)] = minimumResult
            }
        }
        return cache[Key(0, 0)]!
    }

}
