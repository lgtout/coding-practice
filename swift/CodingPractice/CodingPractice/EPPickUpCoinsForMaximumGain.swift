// Copyright (c) 2018 lagostout. All rights reserved.

import Foundation

/* Problem 17.9 page 329 */

class EPPickUpCoinsForMaximumGain {

    static func computeWithRecursionAndBruteForce(_ coins: [Int]) -> Int {
        func compute(_ coins: ArraySlice<Int>, _ pickCount: Int) -> (first: Int, second: Int) {
            if (coins.isEmpty) {
                return (0,0)
            }
            let nextPickCount = pickCount + 1
            var gainByFirstCoin = compute(coins.suffix(from: coins.index(
                after: coins.startIndex)), nextPickCount)
            var gainByLastCoin = compute(coins.prefix(upTo: coins.index(before: coins.endIndex)), nextPickCount)
            let firstCoin = coins.first!
            let lastCoin = coins.last!
            let bestChoice: (Int, Int)
            if (pickCount % 2 == 1) {
                gainByFirstCoin = (gainByFirstCoin.first + firstCoin, gainByFirstCoin.second)
                gainByLastCoin = (gainByLastCoin.first + lastCoin, gainByLastCoin.second)
                bestChoice = gainByFirstCoin.first > gainByLastCoin.first ?
                    gainByFirstCoin : gainByLastCoin
            } else {
                gainByFirstCoin = (gainByFirstCoin.first, gainByFirstCoin.second + firstCoin)
                gainByLastCoin = (gainByLastCoin.first, gainByLastCoin.second + lastCoin)
                bestChoice = gainByFirstCoin.second > gainByLastCoin.second ?
                    gainByFirstCoin : gainByLastCoin
            }
            return bestChoice
        }
        return compute(coins.suffix(from: 0), 1).first
    }

    struct Key : Hashable {
        let startIndex: Int
        let endIndex: Int
        init(_ startIndex: Int, _ endIndex: Int) {
            self.startIndex = startIndex
            self.endIndex = endIndex
        }
    }

    static func computeWithRecursionAndMemoization(_ coins: [Int]) -> Int {
        var cache: Dictionary<Key, (Int, Int)> = [:]
        func compute(_ coins: ArraySlice<Int>, _ pickCount: Int) -> (first: Int, second: Int) {
            let key = Key(coins.startIndex, coins.endIndex)
            if let gain = cache[key] {
                print("hit: coins \(Array(coins)) gain \(gain)")
                return gain
            }
            let gain: (Int, Int)
            if (coins.isEmpty) {
                gain = (0,0)
            } else {
                let nextPickCount = pickCount + 1
                var gainByFirstCoin = compute(coins.suffix(from: coins.index(
                        after: coins.startIndex)), nextPickCount)
                var gainByLastCoin = compute(coins.prefix(upTo: coins.index(before: coins.endIndex)), nextPickCount)
                let firstCoin = coins.first!
                let lastCoin = coins.last!
                if (pickCount % 2 == 1) {
                    gainByFirstCoin = (gainByFirstCoin.first + firstCoin, gainByFirstCoin.second)
                    gainByLastCoin = (gainByLastCoin.first + lastCoin, gainByLastCoin.second)
                    gain = gainByFirstCoin.first > gainByLastCoin.first ?
                            gainByFirstCoin : gainByLastCoin
                } else {
                    gainByFirstCoin = (gainByFirstCoin.first, gainByFirstCoin.second + firstCoin)
                    gainByLastCoin = (gainByLastCoin.first, gainByLastCoin.second + lastCoin)
                    gain = gainByFirstCoin.second > gainByLastCoin.second ?
                            gainByFirstCoin : gainByLastCoin
                }
            }
            cache[key] = gain
            return gain
        }
        return compute(coins.suffix(from: 0), 1).first
    }

    static func computeBottomUpWithMemoization(_ coins: [Int]) -> Int {
        var cache: Dictionary<Key, (first: Int, second: Int)> = [:]
        guard coins.count > 0 else {
            return 0
        }
        (0...coins.count).forEach { index in
            cache[Key(index, index)] = (0,0)
        }
        stride(from: coins.count, to: 0, by: -1).forEach { pickCount in
            let coinSubsetCount = coins.count - pickCount + 1
            (0...(coins.count - coinSubsetCount)).forEach { coinSubsetStartIndex in
                let coinSubsetEndIndex = coinSubsetStartIndex + coinSubsetCount
                let coinSubset = coins[coinSubsetStartIndex..<coinSubsetEndIndex]
                let coinSubsetRemainderByFirstCoin = coinSubset.suffix(
                        from: coinSubsetStartIndex + 1)
                let coinSubsetRemainderByLastCoin = coinSubset.prefix(
                        upTo: coinSubsetEndIndex - 1)
                var gainByFirstCoin = cache[Key(
                        coinSubsetRemainderByFirstCoin.startIndex,
                        coinSubsetRemainderByFirstCoin.endIndex)]!
                var gainByLastCoin = cache[Key(
                        coinSubsetRemainderByLastCoin.startIndex,
                        coinSubsetRemainderByLastCoin.endIndex)]!
                let firstCoin = coinSubset.first!
                let lastCoin = coinSubset.last!
                let gain: (Int, Int)
                // The first pickCount is 1; the last is coins.count
                if (pickCount % 2 == 1) {
                    gainByFirstCoin = (gainByFirstCoin.first + firstCoin,
                            gainByFirstCoin.second)
                    gainByLastCoin = (gainByLastCoin.first + lastCoin,
                            gainByLastCoin.second)
                    gain = gainByFirstCoin.first > gainByLastCoin.first ?
                            gainByFirstCoin : gainByLastCoin
                } else {
                    gainByFirstCoin = (gainByFirstCoin.first,
                            gainByFirstCoin.second + firstCoin)
                    gainByLastCoin = (gainByLastCoin.first,
                            gainByLastCoin.second + lastCoin)
                    gain = gainByFirstCoin.second > gainByLastCoin.second ?
                            gainByFirstCoin : gainByLastCoin
                }
                cache[Key(coinSubsetStartIndex, coinSubsetEndIndex)] = gain
            }
        }
        return cache[Key(0, coins.count)]!.first
    }

}
