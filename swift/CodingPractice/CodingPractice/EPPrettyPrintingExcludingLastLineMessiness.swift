// Copyright (c) 2018 lagostout. All rights reserved.

import Foundation

/* Problem 17.11.2 page 334 */

class EPPrettyPrintingExcludingLastLineMessiness {

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
                let messiness = remainingSpace * remainingSpace +
                        cache[wordIndex + 1]!
                messinesses.append(messiness)
                // Handle space between words.
                remainingSpace -= 1
                wordIndex += 1
            }
            let minMessiness = wordIndex == words.endIndex ?
                    0 : messinesses.min()
            cache[lineStartWordIndex] = minMessiness
            lineStartWordIndex -= 1
        }
        return cache[0]!
    }

}
