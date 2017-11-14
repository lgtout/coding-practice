package com.lagostout.elementsofprogramminginterviews.strings

/**
 * Problem 7.8 page 104
 */
fun computeLookAndSaySequence(n: Int): String {
    var lookSay = "1"
    (1..n).forEach {
        println(it)
        lookSay = lookSay.let {
            var index = 1
            var char = it[0]
            var count = 1
            val nextLookSayBuilder = StringBuilder()
            while (index <= it.lastIndex) {
                if (it[index] != char) {
                    nextLookSayBuilder.append("$count$char")
                    char = it[index]
                    count = 1
                }
                ++index
            }
            nextLookSayBuilder.toString()
        }
    }
    return lookSay
}