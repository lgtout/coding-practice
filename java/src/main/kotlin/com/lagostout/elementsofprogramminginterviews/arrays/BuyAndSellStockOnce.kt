package com.lagostout.elementsofprogramminginterviews.arrays

/* Problem 6.6.1 page 70 */

object BuyAndSellStockOnce {

    fun profit(prices: List<Int>, buyIndex: Int, sellIndex: Int): Int {
        return prices[sellIndex] - prices[buyIndex]
    }

    fun maximumProfitByBuyingAndSellingStockOnce(prices: List<Int>) :
            Pair<Pair<Int, Int>, Int>? {
        if (prices.count() < 2) return null
        var buyIndex = 0
        var alternateBuyIndex = buyIndex
        var sellIndex = 1
        fun profit(buyIndex: Int, sellIndex: Int) =
                profit(prices, buyIndex, sellIndex)
        (1..prices.lastIndex).forEach { index ->
            if (profit(buyIndex, index) > profit(buyIndex, sellIndex)) {
                sellIndex = index
            }
            if (profit(buyIndex, sellIndex) < profit(alternateBuyIndex, index)) {
                buyIndex = alternateBuyIndex
                sellIndex = index
            }
            if (prices[index] < prices[alternateBuyIndex]) {
                alternateBuyIndex = index
            }
        }
        @Suppress("UnnecessaryVariable")
        val result = Pair(Pair(buyIndex, sellIndex), profit(buyIndex, sellIndex))
        return result
    }

}
