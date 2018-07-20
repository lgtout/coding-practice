package com.lagostout.elementsofprogramminginterviews.honors

/* Problem 25.4.2 page 448 */

/* We'll simplify cases very slightly by assuming that the input
 list always has more than one entry. */

fun productsOfAllEntriesButOne(list: List<Int>): List<Int> {
    val products = list.fold(mutableListOf<Int>()) { acc, i ->
        ((acc.lastOrNull() ?: 1) * i).let {
            acc.apply { add(it) }
        }
    }
    var rightProduct = 1
    (list.lastIndex downTo 0).forEach { index ->
        val leftProduct = if (index > 0) products[index - 1] else 1
        rightProduct *= (if (index < list.lastIndex) list[index + 1] else 1)
        products[index] = leftProduct * rightProduct
    }
    return products
}