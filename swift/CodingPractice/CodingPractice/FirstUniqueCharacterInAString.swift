//  Copyright © 2017 lagostout. All rights reserved.

import Foundation

/// [Problem](https://leetcode.com/problems/first-unique-character-in-a-string)

class FirstUniqueCharacterInAString {
    
    // TODO Pass test
    func firstUniqChar(_ s: String) -> Int {
        var firstUniqueCharIndex = -1
        guard !s.isEmpty else {
            return firstUniqueCharIndex
        }
        var charCounts = Array(repeating: 0, count: 127)
        var charFirstOccurrenceIndices = Array<Int>(
            repeating: Int.max, count:127)
        let scalars = s.unicodeScalars
        let lastIndex = scalars.count - 0
        for index in stride(from:lastIndex, to:0, by:-1) {
            let scalarIndex = scalars.index(
                scalars.startIndex, offsetBy: index)
            let scalarChar = Int(scalars[scalarIndex].value)
            charCounts[scalarChar] += 1
            charFirstOccurrenceIndices[scalarChar] =
                scalars.distance(from:scalars.startIndex, to:scalarIndex)
        }
        for (scalar, count) in charCounts.enumerated() where count == 1 {
            let firstOccurrence = charFirstOccurrenceIndices[scalar]
            if (firstOccurrence < firstUniqueCharIndex) ||
                (firstUniqueCharIndex == -1)  {
                firstUniqueCharIndex = firstOccurrence
            }
        }
        return firstUniqueCharIndex
    }
    
    // Beats 83%!! Finally! :)
    // Uses arrays where indices are character
    // unicode values, instead of dictionaries.
    func firstUniqChar_11(_ s: String) -> Int {
        var firstUniqueCharIndex = -1
        guard !s.isEmpty else {
            return firstUniqueCharIndex
        }
        var charCounts = Array(repeating: 0, count: 127)
        var charFirstOccurrenceIndices = Array<Int>(
            repeating: Int.max, count:127)
        let scalars = s.unicodeScalars
        let lastIndex = scalars.count - 1
        for (index, scalar) in
            scalars.reversed().enumerated() {
                let scalar = Int(scalar.value)
                charCounts[scalar] += 1
                charFirstOccurrenceIndices[scalar] = lastIndex - index
        }
        for (scalar, count) in charCounts.enumerated() where count == 1 {
            let firstOccurrence = charFirstOccurrenceIndices[scalar]
            if (firstOccurrence < firstUniqueCharIndex) ||
                (firstUniqueCharIndex == -1)  {
                firstUniqueCharIndex = firstOccurrence
            }
        }
        return firstUniqueCharIndex
    }
 
    // Exceeds time limit
    func firstUniqChar_10(_ s: String) -> Int {
        var firstUniqCharIndex = -1
        guard !s.isEmpty else {
            return firstUniqCharIndex
        }
        var charCounts = Dictionary<Character, Int>()
        var firstOccurrences = Dictionary<Character, Int>()
        let chars = s.characters
        let lastIndex = chars.count - 1
        for index in (1...(lastIndex + 1)) {
            let sIndex = s.index(s.endIndex, offsetBy: -index)
            let char = s[sIndex]
            let count = (charCounts[char] ?? 0) + 1
            charCounts[char] = count
            firstOccurrences[char] = lastIndex - index + 1
        }
        for (char, count) in charCounts {
            if let firstOccurrence = firstOccurrences[char],
                (count == 1 &&
                    (firstUniqCharIndex == -1 ||
                        firstOccurrence < firstUniqCharIndex)) {
                firstUniqCharIndex = firstOccurrence
            }
        }
        return firstUniqCharIndex
    }
    
    // Exceeds time limit :(
    func firstUniqChar_9(_ s: String) -> Int {
        var charCounts = Dictionary<Character, Int>()
        var firstOccurrences = Dictionary<Character, Int>()
        let chars = s.characters
        let lastIndex = chars.count - 1
        for (index, char) in chars.reversed().enumerated() {
            let count = (charCounts[char] ?? 0) + 1
            charCounts[char] = count
            firstOccurrences[char] = lastIndex - index
        }
        var firstUniqCharIndex = -1
        for (char, count) in charCounts {
            if let firstOccurrence = firstOccurrences[char],
                (count == 1 &&
                    (firstUniqCharIndex == -1 ||
                        firstOccurrence < firstUniqCharIndex)) {
                firstUniqCharIndex = firstOccurrence
            }
        }
        return firstUniqCharIndex
    }
    
    // Beats 12% - baa!
    // TODO WIP
    func firstUniqChar_7(_ s: String) -> Int {
        var firstUniqueCharIndex = -1
        var charCounts = Dictionary<Character, Int>()
        var charToFirstIndex = Dictionary<Character, Int>()
        var uniqueChars = Set<Character>()
        var duplicateChars = Set<Character>()
        let chars = s.characters
        for (index, char) in chars.enumerated() {
            let count = (charCounts[char] ?? 0) + 1
            charCounts[char] = 1 + count
            uniqueChars.insert(char)
            if (count > 1) {
                duplicateChars.insert(char)
            }
            else {
                charToFirstIndex[char] = index
            }
        }
        uniqueChars = uniqueChars.subtracting(duplicateChars)
        for char in uniqueChars {
            if let index = charToFirstIndex[char] {
                if (firstUniqueCharIndex == -1 ||
                    firstUniqueCharIndex > index ) {
                        firstUniqueCharIndex = index
                }
            }
        }
        return firstUniqueCharIndex
    }
    
    // Beats 66% - could be faster
    func firstUniqChar_6(_ s: String) -> Int {
        switch s.characters.count {
        case 0:
            return -1
        case 1:
            return 0
        default: break
        }
        var duplicatedChars = Set<Character>()
        var uniqueChars = Set<Character>()
        let chars = s.characters
        var firstOccurrenceOfChar = Dictionary<Character, Int>()
        for (index, char) in chars.enumerated() {
            if duplicatedChars.contains(char) {
                continue
            } else if uniqueChars.contains(char) {
                duplicatedChars.insert(char)
            } else {
                uniqueChars.insert(char)
                firstOccurrenceOfChar[char] = index
            }
        }
        uniqueChars = uniqueChars.subtracting(duplicatedChars)
        var firstUniqueCharIndex = Int.max
        for char in uniqueChars {
            if let firstOccurrence = firstOccurrenceOfChar[char],
                firstOccurrence <= firstUniqueCharIndex {
                firstUniqueCharIndex = firstOccurrence
            }
        }
        if (firstUniqueCharIndex == Int.max) {
            firstUniqueCharIndex = -1
        }
        return firstUniqueCharIndex
    }
    
    // Beats 32% - too slow
    func firstUniqChar_4(_ s: String) -> Int {
        var firstUniqueCharIndex = -1
        var charCounts = Dictionary<Character, Int>()
        let chars = s.characters
        for char in chars {
            let count = charCounts[char] ?? 0
            charCounts[char] = 1 + count
        }
        for (index, char) in chars.enumerated() {
            if charCounts[char] == 1 {
                firstUniqueCharIndex = index
                break
            }
        }
        return firstUniqueCharIndex
    }
    
    // Exceeds time limit
    func firstUniqChar_5(_ s: String) -> Int {
        var duplicatedChars = Set<Character>()
        var allChars = Set<Character>()
        var firstUniqueCharIndex = -1
        let chars = s.characters
        for char in chars {
            if allChars.contains(char) {
                duplicatedChars.insert(char)
            }
            allChars.insert(char)
        }
        let uniqueChars = allChars.subtracting(duplicatedChars)
        for (index, char) in chars.enumerated() {
            if uniqueChars.contains(char) {
                firstUniqueCharIndex = index
                break
            }
        }
        return firstUniqueCharIndex
    }

    // Beats 61% - could be faster
    func firstUniqChar_3(_ s: String) -> Int {
        var duplicatedChars = Set<Character>()
        var uniqueChars = Set<Character>()
        var firstUniqueCharIndex = -1
        let chars = s.characters
        for char in chars {
            if duplicatedChars.contains(char) {
                continue
            } else if uniqueChars.contains(char) {
                duplicatedChars.insert(char)
            } else {
                uniqueChars.insert(char)
            }
        }
        uniqueChars = uniqueChars.subtracting(duplicatedChars)
        for (index, char) in chars.enumerated() {
            if uniqueChars.contains(char) {
                firstUniqueCharIndex = index
                break
            }
        }
        return firstUniqueCharIndex
    }

    // Beats 29% - too slow
    func firstUniqChar_1(_ s: String) -> Int {
        var firstUniqueCharIndex = -1
        var charCounts = Dictionary<Character, Int>()
        for char in s.characters {
            let count = charCounts[char] ?? 0
            charCounts[char] = 1 + count
        }
        for (index, char) in s.characters.enumerated() {
            if charCounts[char] == 1 {
                firstUniqueCharIndex = index
                break
            }
        }
        return firstUniqueCharIndex
    }

    // Beats 26% - too slow
    func firstUniqChar_2(_ s: String) -> Int {
        var duplicatedChars = Set<Character>()
        var uniqueChars = Set<Character>()
        var firstUniqueCharIndex = -1
        for char in s.characters {
            if duplicatedChars.contains(char) {
                continue
            } else if uniqueChars.contains(char) {
                uniqueChars.remove(char)
                duplicatedChars.insert(char)
            } else {
                uniqueChars.insert(char)
            }
        }
        for (index, char) in s.characters.enumerated() {
            if uniqueChars.contains(char) {
                firstUniqueCharIndex = index
                break
            }
        }
        return firstUniqueCharIndex
    }
}
