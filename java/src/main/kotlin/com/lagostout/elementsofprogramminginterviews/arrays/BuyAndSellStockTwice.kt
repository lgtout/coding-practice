package com.lagostout.elementsofprogramminginterviews.arrays

/* Problem 6.7.1 page 71 */

fun buyAndSellStockTwice(prices: List<Float>): Float? {
    if (prices.count() < 4) return null

    val firstBuySellProfits = run {
        var buyIndex = 0
        var sellIndex = 1
        var buyPrice = prices[buyIndex]
        var sellPrice = prices[sellIndex]
        var profit = sellPrice - buyPrice
        var indexOfLowestPrice = if (buyPrice < sellPrice) buyIndex
        else sellIndex
        val profits = mutableMapOf(sellIndex to profit)
        (sellIndex + 1 until prices.lastIndex - 1).forEach { index ->
            val lowestBuyPrice = prices[indexOfLowestPrice]
            val price = prices[index]
            if (price - lowestBuyPrice > profit) {
                buyIndex = indexOfLowestPrice
                sellIndex = index
                buyPrice = prices[buyIndex]
                sellPrice = prices[sellIndex]
                profit = sellPrice - buyPrice
            }
            profits[index] = profit
            if (price < lowestBuyPrice) {
                indexOfLowestPrice = index
            }
        }
        profits
    }

    val secondBuySellProfits = run {
        var buyIndex = prices.lastIndex - 1
        var sellIndex = prices.lastIndex
        var buyPrice = prices[buyIndex]
        var sellPrice = prices[sellIndex]
        var profit = sellPrice - buyPrice
        var indexOfHighestPrice = if (buyPrice > sellPrice) buyIndex
        else sellIndex
        val profits = mutableMapOf(buyIndex to profit)
        (buyIndex - 1 downTo 2).forEach { index ->
            val highestSellPrice = prices[indexOfHighestPrice]
            val price = prices[index]
            if (highestSellPrice - price > profit) {
                sellIndex = indexOfHighestPrice
                buyIndex = index
                buyPrice = prices[buyIndex]
                sellPrice = prices[sellIndex]
                profit = sellPrice - buyPrice
            }
            profits[index] = profit
            if (price > highestSellPrice) {
                indexOfHighestPrice = index
            }
        }
        profits
    }

    println(firstBuySellProfits)
    println(secondBuySellProfits)

    @Suppress("UnnecessaryVariable")
    val maxProfit = (1..prices.lastIndex - 2).fold(null as Float?) { acc, curr ->
        (firstBuySellProfits[curr]!! + secondBuySellProfits[curr + 1]!!).let {
            if (acc == null || it > acc) it else acc
        }
    }

    return maxProfit
}