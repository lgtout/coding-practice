package com.lagostout.elementsofprogramminginterviews.honors

import com.lagostout.common.isOdd
import org.apache.commons.collections4.bag.HashBag
import kotlin.math.sign

/* Problem 25.4.1 page 445 */

fun maximumProductOfAllEntriesButOne(entries: List<Int>): Int {
    data class Numbers(val bag: HashBag<Int> = HashBag(),
                       var min: Int? = null, var max: Int? = null) {
        val isNotEmpty: Boolean
            get() = !isEmpty

        val isEmpty: Boolean
                get() = size == 0

        val size: Int
            get() = bag.size

        fun add(number: Int) {
            bag.add(number)
            min = swap(min, number, -1)
            max = swap(max, number, 1)
        }

        fun remove(number: Int) {
            bag.remove(number)
        }

        fun swap(current: Int?, new: Int, comparison: Int): Int {
            return current?.let {
                if (new.compareTo(it) == comparison) new else it } ?: new
        }
    }
    val negativeNumbers = Numbers()
    val positiveNumbers = Numbers()
    val zeroes = Numbers()
    entries.forEach { entry ->
        (when (entry.sign) {
            -1 -> positiveNumbers
            1 -> positiveNumbers
            else -> zeroes
        }).add(entry)
    }
    val product = when {
        (zeroes.isNotEmpty) -> {
            if (zeroes.size > 1 || (negativeNumbers.isNotEmpty &&
                            negativeNumbers.size.isOdd)) {
                // If there is more than one 0, it doesn't matter
                // which number we remove, max product will always be 0.
                // If there's an odd number of negative numbers, the
                // max product is achieved by keeping the 0, and removing
                // any other number.
                // In either case, the max product is 0.
                setOf(0)
            } else {
                // There's 1 zero.
                // There are either no negative numbers,
                // or an even number of them.
                // There may or may not be positive numbers.
                positiveNumbers.bag + negativeNumbers.bag
            }
        }
        else -> setOf(0)
    }.reduce { acc, it -> acc * it }
    return product
}