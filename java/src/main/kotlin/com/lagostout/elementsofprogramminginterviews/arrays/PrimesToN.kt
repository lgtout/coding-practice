package com.lagostout.elementsofprogramminginterviews.arrays

/* Problem 6.8 page 72 */

fun primesToN(n: Int): List<Int> {
    val candidates = MutableList(n + 1) { true }
    return candidates.run {
        indices.drop(2).run {
            forEach {
                if (candidates[it]) {
                    var index = it * it
                    while (index <= n) {
                        candidates[index] = false
                        index += it
                    }
                }
            }
            filter { candidates[it] }
        }
    }
}