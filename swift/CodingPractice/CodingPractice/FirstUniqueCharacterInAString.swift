//  Copyright © 2017 lagostout. All rights reserved.

import Foundation

/// [Problem](https://leetcode.com/problems/first-unique-character-in-a-string)

class FirstUniqueCharacterInAString {
    
    func firstUniqChar(_ s: String) -> Int {
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
    
}
