//  Copyright © 2017 lagostout. All rights reserved.

import Foundation

/// [Problem](https://leetcode.com/problems/first-unique-character-in-a-string)

class FirstUniqueCharacterInAString {
    
    func firstUniqChar(_ s: String) -> Int {
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
