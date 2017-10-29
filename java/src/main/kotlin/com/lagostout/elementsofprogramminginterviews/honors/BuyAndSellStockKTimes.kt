package com.lagostout.elementsofprogramminginterviews.honors

/**
 * Problem 25.3.1 page 445
 */
fun buyAndSellStockKTimes(prices: List<Double>, k: Int) {
    data class BuySell(val buy: Int, val sell: Int)
    val requiredWidth = (k * 2).also {
        if (it > prices.size)
            throw IllegalArgumentException(
                    "Prices list must be at least $it in size")
    }
    val buySellPairs = mutableListOf<BuySell>().apply {
        (0 until requiredWidth step 2).forEach {
            add(BuySell(it, it + 1))
        }
    }
    val rightBound = prices.lastIndex
    val buySellPairsIterator = buySellPairs.reversed().iterator()
    val buySellPair = buySellPairsIterator.next()
    while (true) {
        val profit = prices[buySellPair.buy] -
                prices[buySellPair.sell]
        while (true) {
//            if ()
        }
    }
}