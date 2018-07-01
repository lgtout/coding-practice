@file:Suppress("unused")

package com.lagostout.elementsofprogramminginterviews.honors

import com.lagostout.elementsofprogramminginterviews.honors.BuyAndSellStockKTimes.BuySell

/**
 * Problem 25.3.1 page 445
 */
object BuyAndSellStockKTimes {

    enum class TransactionType {
        BUY, SELL;
        val opposite: TransactionType
            get() = when (this) {
                BUY -> SELL
                SELL -> BUY
            }
    }

    data class BuySell(val startIndex: Int, val endIndex: Int)

}

fun buyAndSellStockKTimes(prices: List<Int>, k: Int): Int {
    if (prices.isEmpty()) return 0
    if (k == 0) return 0
    data class Range(val best: BuySell, val explored: BuySell,
                     val indexOfHighestPossibleSellPrice: Int) {

        constructor(best: BuySell, indexOfHighestPossibleSellPrice: Int) :
                this(best, best, indexOfHighestPossibleSellPrice)

        constructor(startIndex: Int, endIndex: Int,
                    indexOfHighestPossibleSellPrice: Int) :
                this(BuySell(startIndex, endIndex),
                        indexOfHighestPossibleSellPrice)
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
                add(Range(BuySell(first, second),
                        listOf(first, second)
                                .sortedBy {prices[it]}.last()))
            }
        }
    }
    @Suppress("NAME_SHADOWING")
    val prices = Prices(prices)
    var currentTotalProfit = ranges.sumBy { prices.profit(it.best) }
    var bestTotalProfit = currentTotalProfit
    run {
        while (true) {
            ranges.forEachIndexed { index, range ->
                println(ranges)
                println()

                val totalProfitExcludingCurrentRange = currentTotalProfit -
                        prices.profit(range.explored)

                val earlierStartIndex = range.explored.startIndex - 1

                // We're done when there's no more space left to explore.
                if (earlierStartIndex < 0) return@run

                // See if we can improve profit by exchanging the current buy-sell
                // with the earliest one made possible by the extra room on the
                // left side of the range.  We'll try to improve best.
                val best = range.best.copy(startIndex = earlierStartIndex,
                        endIndex = range.indexOfHighestPossibleSellPrice)
                        .let {
                            if (prices.profit(it) > prices.profit(range.best))
                                it else range.best
                        }

                println("currentTotalProfit $currentTotalProfit")

                currentTotalProfit = totalProfitExcludingCurrentRange +
                        prices.profit(best)
                bestTotalProfit = maxOf(currentTotalProfit, bestTotalProfit)

                println("original buySell best: ${range.best}, range: ${range.explored}")
                println("best buySell $best")
                println("currentTotalProfit $currentTotalProfit")
                println("bestTotalProfit $bestTotalProfit")
                println()

                // Push the current range's buy-sell all the way left so we have
                // room to explore improving those of ranges to its right.
                val earliestPossibleBuySell = BuySell(earlierStartIndex,
                        range.explored.startIndex)

                // Total profit should reflect that the current range's buy-sell
                // is pushed all the way left.
                currentTotalProfit = totalProfitExcludingCurrentRange +
                        prices.profit(earliestPossibleBuySell)

                // Let the range reflect the extent of prices explored so far.
                ranges[index] = range.copy(
                        best = best,
                        explored = range.explored.copy(
                                startIndex = earliestPossibleBuySell.startIndex),
                        indexOfHighestPossibleSellPrice = listOf(
                                earliestPossibleBuySell.startIndex,
                                range.indexOfHighestPossibleSellPrice)
                                .sortedBy { prices[it] }.last())
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