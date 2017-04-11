package com.lagostout.elementsofprogramminginterviews.hashtables

class IsAnAnonymousLetterConstructible {

    static boolean isConstructible(String letter, String magazine) {
        def magazineCharToCount = new HashMap<Character, Integer>()
        for (char ch : magazine.chars) {
            def count = (magazineCharToCount.containsKey(ch) ?
                    magazineCharToCount.get(ch) : 0) + 1
            magazineCharToCount.put(ch, count)
        }
        def isConstructible = true
        for (char ch : letter.chars) {
            if (!magazineCharToCount.containsKey(ch) ||
                    magazineCharToCount.get(ch) == 0) {
                isConstructible = false
                break
            }
            def count = magazineCharToCount.get(ch) - 1
            magazineCharToCount.put(ch, count)
        }
        isConstructible
    }

}
