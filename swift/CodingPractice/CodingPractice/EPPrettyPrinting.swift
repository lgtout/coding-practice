// Copyright (c) 2018 lagostout. All rights reserved.

import Foundation

/* Problem 17.11.1 page 332 */

class EPPrettyPrinting {

    /* For simplicity, we'll assume none of the words is longer
    than lineLength. If we don't, the messiness would be infinity,
    because we'd keep adding new lines as long as the current word
    is too long to fit in the remaining space.  Otherwise, we'd
    need a special return value to indicate this case. */

    static func computeWithRecursionAndBruteForce(
            _ words: [String], lineLength: Int) -> Int {
        func compute(_ words: ArraySlice<String>) -> Int {
            if words.isEmpty {
                return 0
            }
            var remainingSpace = lineLength
            var remainingWords = words
            var messinesses: [Int] = []
            var messiness: Int
            while remainingSpace > 0 && !remainingWords.isEmpty &&
                          remainingWords.first!.count <= remainingSpace {
                remainingSpace -= remainingWords.first!.count
                remainingWords = remainingWords[(remainingWords.startIndex + 1)...]
                messiness = compute(remainingWords)
                messinesses.append(remainingSpace * remainingSpace + messiness)
                remainingSpace -= 1
            }
            return messinesses.min() ?? 0
        }
        return compute(words[0...])
    }

    struct Key : Hashable {
        let startIndex: Int
        let endIndex: Int
        init (_ startIndex: Int, _ endIndex: Int) {
            self.startIndex = startIndex
            self.endIndex = endIndex
        }
    }

    static func computeWithRecursionAndMemoization(
            _ words: [String], lineLength: Int) -> Int {
        var cache: [Int : Int] = [:]
        func compute(_ words: ArraySlice<String>) -> Int {
            let wordIndex = words.startIndex
            if let messiness = cache[wordIndex] {
                return messiness
            }
            if words.isEmpty {
                return 0
            }
            var remainingSpace = lineLength
            var remainingWords = words
            var messinesses: [Int] = []
            var messiness: Int
            while remainingSpace > 0 && !remainingWords.isEmpty &&
                          remainingWords.first!.count <= remainingSpace {
                remainingSpace -= remainingWords.first!.count
                remainingWords = remainingWords[(remainingWords.startIndex + 1)...]
                messiness = compute(remainingWords)
                messinesses.append(remainingSpace * remainingSpace + messiness)
                remainingSpace -= 1
            }
            messiness = messinesses.min() ?? 0
            cache[wordIndex] = messiness
            return messiness
        }
        let messiness = compute(words[0...])
        return messiness
    }

    static func computeBottomUpWithMemoization(
            _ words: [String], lineLength: Int) -> Int {
        if words.isEmpty {
            return 0
        }
        var cache: [Int : Int] = [words.endIndex : 0]
        var lineStartWordIndex = words.endIndex - 1
        while lineStartWordIndex >= 0 {
            var remainingSpace = lineLength
            var wordIndex = lineStartWordIndex
            var messinesses: [Int] = []
            while wordIndex < words.endIndex {
                let word = words[wordIndex]
                if remainingSpace < word.count {
                    break
                }
                remainingSpace -= word.count
                let messiness = remainingSpace * remainingSpace + cache[wordIndex + 1]!
                // Handle space between words.
                remainingSpace -= 1
                messinesses.append(messiness)
                wordIndex += 1
            }
            cache[lineStartWordIndex] = messinesses.min()
            lineStartWordIndex -= 1
        }
        return cache[0]!
    }

}
