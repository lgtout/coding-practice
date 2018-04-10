//  Copyright © 2017 lagostout. All rights reserved.

import Foundation

class PalindromePermutation {
    class Solution {
        func canPermutePalindrome(_ s: String) -> Bool {
            var charCounts: Dictionary<Character, Int> = [:]
            for char in s {
                charCounts[char] = (charCounts[char] ?? 0) + 1
            }
            var oddCharCountCount = 0
            for count in charCounts.values {
                if count % 2 == 1 {
                    oddCharCountCount += 1
                    if oddCharCountCount > 1 { break }
                }
            }
            return oddCharCountCount <= 1
        }
    }
}
