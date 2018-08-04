package com.lagostout.elementsofprogramminginterviews.honors

import com.lagostout.common.product
import org.paukov.combinatorics3.Generator
import java.util.*

/* Problem 25.4.3 page 448 */

fun maxProductOfTriples(list: List<Int>): Int? {

    val mins = TreeSet<Int> { o1: Int, o2: Int ->
        o1 - o2
    }

    val maxes = TreeSet<Int> { o1: Int, o2: Int ->
        o2 - o1
    }

    list.forEach {
        mins.add(it)
        if (mins.size > 3) mins.remove(mins.last())
        maxes.add(it)
        if (maxes.size > 3) maxes.remove(maxes.last())
    }

    // The simplest way to find the max product is to compute the
    // product of all combinations of 3 numbers from [mins] and [maxes],
    // and select the largest of these products. We could also find the
    // max product in fewer operations by using some subtle logical
    // reasoning. But the time complexity be the same: O(1). (Number of
    // combinations of C(6,3) is 24.)

    (maxes + mins).toSet().let {
        return if (it.size < 3) null
        else {
            Generator.combination(it).simple(3).fold(emptyList<Int>()) {
                    acc, curr ->
                if (acc.isNotEmpty() && acc.product() > curr.product()) acc else curr
            }.product()
        }
    }

}