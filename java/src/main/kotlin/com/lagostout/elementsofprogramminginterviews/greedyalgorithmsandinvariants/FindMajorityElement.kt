package com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants

/**
 * Problem 18.5 page 347
 */

fun findMajorityElement(strings: List<String>): String? {
    when (strings.count()) {
        0 -> return null
        1 -> return strings.first()
    }
    var majorityElement: String? = null
    var majorityElementCount = 0
    var count = 0
    strings.forEach { element ->
        majorityElement?.let {
            count += 1
            val elementIsMajorityElement = it == element
            majorityElementCount += if (elementIsMajorityElement) 1 else 0
            val halfCount = count / 2
            if (majorityElementCount <= halfCount) {
                majorityElement = null
                majorityElementCount = 0
            }
        } ?: run {
            count = 1
            majorityElement = element
            majorityElementCount = 1
        }
    }
    return majorityElement
}