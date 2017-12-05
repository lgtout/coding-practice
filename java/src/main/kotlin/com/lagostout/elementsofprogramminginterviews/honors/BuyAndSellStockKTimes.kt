package com.lagostout.elementsofprogramminginterviews.honors

/**
 * Problem 25.3.1 page 445
 */
fun buyAndSellStockKTimes(prices: List<Int>, k: Int) {
    data class BuySell(var startIndex: Int, val endIndex: Int)
    data class Range(var startIndex: Int, val endIndex: Int, val buySell: BuySell)
    val requiredWidth = (k * 2).also {
        if (it > prices.size)
            throw IllegalArgumentException(
                    "Prices list must be at least $it in size")
    }
    val ranges = mutableListOf<Range>().apply {
        ((prices.size - requiredWidth) until prices.lastIndex step 2).forEach {
            Pair(it, it + 1).apply {
                add(Range(first, second, BuySell(first, second)))
            }
        }
    }
    var currentTotalProfit = ranges.sumBy { prices[it.startIndex] - prices[it.endIndex] }
    fun profit(buyIndex: Int, sellIndex: Int): Int {
        return prices[buyIndex] - prices[sellIndex]
    }
    fun profit(buySell: BuySell): Int {
        return buySell.run { profit(startIndex, endIndex) }
    }
    while (true) {
        ranges.forEach {
            var baseTotalProfit = currentTotalProfit - profit(it.buySell)
            var bestBuySellCandidate = it.buySell
            if (prices[it.startIndex - 1] < prices[it.buySell.startIndex]) {
                bestBuySellCandidate = bestBuySellCandidate.copy(startIndex = it.startIndex - 1)
            }
            if (profit(it.startIndex - 1, it.startIndex) > profit(it.buySell)) {
                bestBuySellCandidate = BuySell(it.startIndex - 1, it.startIndex)
            }
        }
    }
}