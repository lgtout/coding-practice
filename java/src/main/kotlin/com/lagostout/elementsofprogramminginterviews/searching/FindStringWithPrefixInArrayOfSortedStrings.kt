package com.lagostout.elementsofprogramminginterviews.searching

/**
 * Problem 12.1.5 page 193
 */
fun findStringWithPrefix(prefix: String, strings: List<String>): String? {
    var lo = 0
    var hi = strings.size
    var result: String? = null
    if (strings.isEmpty() || prefix.isEmpty()) return result
    run {
        while (lo < hi) {
            val mid = (hi - lo)/2 + lo
            when (prefix.compareTo(strings[mid].substring(0, prefix.length))) {
                0 -> {
                    result = strings[mid]
                    return@run
                }
                1 -> lo = mid + 1
                -1 -> hi = mid - 1
            }
        }
    }
    return result
}
