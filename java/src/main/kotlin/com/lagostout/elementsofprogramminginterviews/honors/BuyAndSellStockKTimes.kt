package com.lagostout.elementsofprogramminginterviews.honors

/**
 * Problem 25.3.1 page 445
 */
fun buyAndSellStockKTimes(prices: List<Int>, k: Int): Int {
    if (prices.isEmpty()) return 0
    if (k == 0) return 0
    data class BuySell(var startIndex: Int, var endIndex: Int)
    data class Range(var best: BuySell, var explored: BuySell) {
        constructor(explored: BuySell) : this(explored, explored)
    }
    data class Prices(val prices: List<Int>) {
        fun profit(buySell: BuySell): Int {
            return buySell.run {
                prices[endIndex] - prices[startIndex]
            }
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
            Pair(it, it + 1).apply {
                add(Range(BuySell(first, second)))
            }
        }
    }
    @Suppress("NAME_SHADOWING")
    val prices = Prices(prices)
    var currentTotalProfit = ranges.sumBy { prices.profit(it.best) }
    var bestTotalProfit = currentTotalProfit
    run {
        while (true) {
            ranges.forEach {

                println(ranges)
                println()

                val totalProfitExcludingCurrentRange = currentTotalProfit -
                        prices.profit(it.explored)
                // We'll try to improve best.
                var best = it.best
                val earlierStartIndex = it.explored.startIndex - 1
                // Let's not explore prices left of the earliest possible.
                if (earlierStartIndex < 0) return@run
                // See if we can improve profit by exchanging the current buy-sell by exchanging
                // its buy price with the earliest one made possible by the extra room on the left
                // side of the range.
                if (prices[earlierStartIndex] < prices[it.best.startIndex]) {
                    best = best.copy(startIndex = earlierStartIndex)
                }
                // See if we can improve profit by exchanging the current buy-sell with the
                // earliest buy-sell made possible by the extra room on the left side of the
                // range.
                val earliestPossibleBuySell = BuySell(earlierStartIndex,
                        it.explored.startIndex).also {
                    if (prices.profit(it) > prices.profit(best)) {
                        best = it
                    }
                }

                println("currentTotalProfit $currentTotalProfit")

                currentTotalProfit = totalProfitExcludingCurrentRange +
                        prices.profit(best)
                bestTotalProfit = maxOf(currentTotalProfit, bestTotalProfit)

                println("original buySell best: ${it.best}, range: ${it.explored}")
                println("best buySell $best")
                println("currentTotalProfit $currentTotalProfit")
                println("bestTotalProfit $bestTotalProfit")
                println()

                // Push the current range's buy-sell all the way left so we have room to explore
                // improving those of ranges to its right.
                currentTotalProfit = totalProfitExcludingCurrentRange +
                        prices.profit(earliestPossibleBuySell)
                it.explored = it.explored.copy(
                        startIndex = earliestPossibleBuySell.startIndex)
                // Let the range reflect the extent of prices explored so far.
                it.best = best
            }
            // To prepare for exploring alternate buy-sell selections for
            // ranges to the right of the current one, let's update the current
            // total profit to reflect the state of selections that gives us
            // the room we need to be able to alter selections to the right.
            currentTotalProfit = ranges.sumBy { prices.profit(it.explored) }
        }
    }
    return bestTotalProfit
}