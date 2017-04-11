package com.lagostout.elementsofprogramminginterviews.hashtables

class TestForPalindromicPermutations {

    static boolean canBePermutedToFormPalindrome(String str) {
        def canBePermuted = true
        def charToCharCount = new HashMap<Character, Integer>()
        str.chars.each  { char ch ->
            def count = charToCharCount.getOrDefault(ch, 0) + 1
            charToCharCount.put(ch, count)
        }
        def numberOfCharWithOddCount = charToCharCount.values()
                .count { it % 2 == 1 }
        if (numberOfCharWithOddCount > 1)
            canBePermuted = false
        canBePermuted
    }

}
