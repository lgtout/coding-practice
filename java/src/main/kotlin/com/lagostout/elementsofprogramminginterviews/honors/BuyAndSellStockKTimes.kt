package com.lagostout.elementsofprogramminginterviews.honors

/**
 * Problem 25.3.1 page 445
 */
fun buyAndSellStockKTimes(prices: List<Int>, k: Int): Int {
    if (prices.isEmpty()) return 0
    if (k == 0) return 0
    data class BuySell(var startIndex: Int, val endIndex: Int)
    data class Range(var startIndex: Int, val endIndex: Int, var buySell: BuySell)
    data class Prices(val prices: List<Int>) {
        fun profit(buyIndex: Int, sellIndex: Int): Int {
            return prices[sellIndex] - prices[buyIndex]
        }
        fun profit(buySell: BuySell): Int {
            return profit(buySell.startIndex, buySell.endIndex)
        }
        operator fun get(index: Int) = prices[index]
    }
    val requiredWidth = (k * 2).also {
        if (it > prices.size)
            throw IllegalArgumentException(
                    "Prices list must be at least $it in size")
    }
    val ranges = mutableListOf<Range>().apply {
        ((prices.size - requiredWidth) until prices.size step 2).forEach {
            println(it)
            Pair(it, it + 1).apply {
                add(Range(first, second, BuySell(first, second)))
            }
        }
    }
    println(ranges)
    @Suppress("NAME_SHADOWING")
    val prices = Prices(prices)
    var currentTotalProfit = ranges.sumBy { prices.profit(it.buySell) }
    var bestTotalProfit = currentTotalProfit
    println(bestTotalProfit)
    run {
        while (true) {
            ranges.forEach {
                val totalProfitExcludingCurrentRange = currentTotalProfit - prices.profit(it.buySell)
                var bestBuySellCandidate = it.buySell
                val earlierStartIndex = it.startIndex - 1
                // Let's not explore prices left of the earliest possible.
                if (earlierStartIndex < 0) return@run
                // See if we can improve profit by exchanging the current buy-sell by exchanging
                // its buy price with the earliest one made possible by the extra room on the left
                // side of the range.
                if (prices[earlierStartIndex] < prices[it.buySell.startIndex]) {
                    bestBuySellCandidate = bestBuySellCandidate.copy(startIndex = it.startIndex - 1)
                }
                // See if we can improve profit by exchanging the current buy-sell with the
                // earliest buy-sell made possible by the extra room on the left side of the
                // range.
                val earliestPossibleBuySell = BuySell(earlierStartIndex, it.startIndex)
                if (prices.profit(earlierStartIndex, it.startIndex) > prices.profit(bestBuySellCandidate)) {
                    bestBuySellCandidate = earliestPossibleBuySell
                }
                currentTotalProfit = totalProfitExcludingCurrentRange + prices.profit(bestBuySellCandidate)
                bestTotalProfit = maxOf(currentTotalProfit, bestTotalProfit)
                // Push the current range's buy-sell all the way left so we have room to explore
                // improving those of ranges to its right.
                currentTotalProfit = totalProfitExcludingCurrentRange + prices.profit(earliestPossibleBuySell)
                // Let the range reflect the extent of prices explored so far.
                it.buySell = bestBuySellCandidate
                it.startIndex = earlierStartIndex
            }
            currentTotalProfit = ranges.sumBy { prices.profit(it.buySell) }
        }
    }
    return bestTotalProfit
}