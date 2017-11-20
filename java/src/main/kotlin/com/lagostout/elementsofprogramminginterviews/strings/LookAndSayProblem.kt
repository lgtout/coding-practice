package com.lagostout.elementsofprogramminginterviews.strings

/**
 * Problem 7.8 page 104
 */
fun computeLookAndSaySequence(n: Int): String {
    var lookSay = "1"
    (1 until n).forEach {
        lookSay = lookSay.let {
            var index = 0
            var count = 0
            val nextLookSayBuilder = StringBuilder()
            do {
                val nextIndex = index + 1
                val char = it[index]
                ++count
                if (nextIndex > it.lastIndex || it[nextIndex] != char) {
                    nextLookSayBuilder.append("$count$char")
                    count = 0
                }
                index = nextIndex
            } while (index <= it.lastIndex)
            nextLookSayBuilder.toString()
        }
    }
    return lookSay
}