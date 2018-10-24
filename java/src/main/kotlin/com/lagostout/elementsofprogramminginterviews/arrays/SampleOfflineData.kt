package com.lagostout.elementsofprogramminginterviews.arrays

import com.lagostout.common.swap
import org.apache.commons.math3.random.RandomDataGenerator

/* Problem 6.11.1 page 78 */

fun sampleOfflineData(list: MutableList<Int>, n: Int) {
    var leftIndex = 0
    while (leftIndex < n) {
        val selectedIndex = RandomDataGenerator()
                .nextInt(leftIndex, list.lastIndex)
        list.swap(leftIndex, selectedIndex)
        leftIndex += 1
    }
}