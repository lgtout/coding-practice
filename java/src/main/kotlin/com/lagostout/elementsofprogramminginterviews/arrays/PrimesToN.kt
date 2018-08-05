package com.lagostout.elementsofprogramminginterviews.arrays

/* Problem 6.8 page 72 */

fun primesToN(n: Int): List<Int> {
    val candidates = MutableList(n + 1) { true }
    return candidates.run {
        indices.drop(2).run {
            forEach {
                if (candidates[it]) {
                    val step = it * it
                    var index = step
                    while (index <= n) {
                        candidates[index] = false
                        index += step
                    }
                }
            }
            filter { candidates[it] }
        }
    }
}