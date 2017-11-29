package com.lagostout.elementsofprogramminginterviews.honors

import org.apache.commons.collections4.iterators.ListIteratorWrapper
import java.util.*

/**
 * Problem 25.3.1 page 445
 */
fun buyAndSellStockKTimes(prices: List<Int>, k: Int) {
    data class BuySell(val prices: List<Int>,
                       val buyIndex: Int, val sellIndex: Int) {
        val profit: Int
            get() {
                return sellPrice - buyPrice
            }
        val buyPrice: Int
            get() {
                return prices[buyIndex]
            }
        val sellPrice: Int
            get() {
                return prices[sellIndex]
            }
    }
    data class Range(val prices: List<Int>, val startIndex: Int, val endIndex: Int, val bestBuySell: BuySell) {
        constructor (prices: List<Int>, startIndex: Int, endIndex: Int) :
                this(prices, startIndex, endIndex, BuySell(prices, startIndex, endIndex))
        val bestProfit = bestBuySell.profit
    }
    data class Ranges(val ranges: List<Range>) {
        val iterator = ListIteratorWrapper(ranges.listIterator())
        val bestProfit = ranges.sumBy { it.bestProfit }
    }
    data class Frame(val range: Range, val index: Int, val step: Int = 0)
    val requiredWidth = (k * 2).also {
        if (it > prices.size)
            throw IllegalArgumentException(
                    "Prices list must be at least $it in size")
    }
    val ranges = mutableListOf<Range>().apply {
        (prices.size - requiredWidth until prices.lastIndex step 2).forEach {
            add(Range(prices, it, it + 1))
        }
    }
    val stack = LinkedList<Frame>().apply { add(Frame(ranges[0], 0))}
    while (true) {
        if (stack.isEmpty()) break
        stack.pop().let {
            (range, index, step) ->
            when (step) {
                0 -> {
                    if (range.startIndex > 0 && index == 0 ||
                            ranges[index - 1].endIndex < range.startIndex - 1) {
                        // Improve range best BuySell, if possible.
//                        val nextRange = range.copy(startIndex = range.startIndex - 1)
//                        if (nextRange.buySell)
                    }
                }
                1 -> {

                }
                else -> {

                }
            }

        }
    }
}