package com.lagostout.elementsofprogramminginterviews.honors

import com.lagostout.common.nextInt
import com.lagostout.common.second
import com.lagostout.elementsofprogramminginterviews.honors.BuyAndSellStockKTimes.TransactionType
import com.lagostout.elementsofprogramminginterviews.honors.BuyAndSellStockKTimes.TransactionType.BUY
import com.lagostout.elementsofprogramminginterviews.honors.BuyAndSellStockKTimes.TransactionType.SELL
import org.apache.commons.math3.random.RandomDataGenerator
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object BuyAndSellStockKTimesSpek : Spek({

    describe("buyAndSellStockKTimes") {

        fun profit(prices: List<Int>, buyIndex: Int,
                   sellIndex: Int): Int {
            return prices[sellIndex] - prices[buyIndex]
        }

        @Suppress("UnnecessaryVariable")
        fun bestProfitByBruteForce2(prices: List<Int>, k: Int): Pair<Int, List<Int>>? {

            fun compute(k: Int, index: Int = 0,
                        transactionType: TransactionType): Pair<Int, List<Int>>? {

                when {
                    k == 0 -> return Pair(0, emptyList())
                    index > prices.lastIndex -> return null
                }

                fun bestProfitByBuyingOrSelling(
                        k: Int, index: Int,
                        transactionType: TransactionType): Pair<Int, List<Int>>? {
                    return compute(k, index, transactionType)?.let {
                        val transactions = listOf(index) + it.second
                        var bestProfit = it.copy(second = transactions)
                        if (transactionType == BUY) {
                            val profit = it.first + (it.second.firstOrNull()?.let {
                                it - prices[index]
                            } ?: 0)
                            bestProfit = bestProfit.copy(first = profit)
                        }
                        bestProfit
                    }
                }

                val nextIndex = index + 1

                // Buy/sell today.

                val nextK = k - if (transactionType == SELL) 1 else 0
                val bestProfitByBuyingOrSellingToday = bestProfitByBuyingOrSelling(
                    nextK, nextIndex, transactionType.opposite)

                // Buy/sell in the future.

                val bestProfitByBuyingOrSellingInTheFuture = bestProfitByBuyingOrSelling(
                    k, nextIndex, transactionType)

                val bestProfit = listOfNotNull(
                    bestProfitByBuyingOrSellingToday,
                    bestProfitByBuyingOrSellingInTheFuture).let {
                    when (it.count()) {
                        2 -> if (it.first().first > it.second().first)
                            it.first() else it.second()
                        1 -> it.first()
                        else -> Pair(0, emptyList())
                    }
                }

                return bestProfit
            }

            val result = (0..prices.lastIndex).fold(
                Pair(0, emptyList<Int>()) as Pair<Int, List<Int>>?) {
                    acc, index ->
                compute(k, index, BUY)?.let {
                    if (it.first > acc!!.first) it else acc
                }
            }

            return result
        }

        fun bestProfitByBruteForce1(
                prices: List<Int>, k: Int, startIndex: Int = 0): Int {
            if (k == 0) return 0
            @Suppress("NAME_SHADOWING")
            var bestProfit: Int? = null
            val lastPossibleBuyIndex = prices.size - k * 2
            for (buyIndex in (startIndex..lastPossibleBuyIndex)) {
                val firstPossibleSellIndex = buyIndex + 1
                val lastPossibleSellIndex = lastPossibleBuyIndex + 1
                (firstPossibleSellIndex..lastPossibleSellIndex)
                        .map {
                            profit(prices, buyIndex, it) +
                                    bestProfitByBruteForce1(prices, k - 1,
                                            it + 1)
                        }.max()?.let { maxProfit ->
                    bestProfit = bestProfit?.let {
                        maxOf(maxProfit, it)
                    } ?: maxProfit
                }
            }
            return bestProfit ?: 0
        }

        val randomCases = run {
            val random = RandomDataGenerator().apply {
                reSeed(1)
            }
            val cases = mutableSetOf<Pair<List<Int>, Int>>()
            val caseCount = 100000
            val priceRange = (0..3)
            val priceCountRange = (0..6)
//            val priceRange = (0..10)
//            val priceCountRange = (0..10)
            var count = 0
            while (count < caseCount) {
                val prices = mutableListOf<Int>()
                val priceCount = random.nextInt(priceCountRange)
                val k = random.nextInt(0..priceCount/2)
                (0 until priceCount).forEach {
                    prices.add(random.nextInt(priceRange))
                }
                ++count
                cases.add(Pair(prices, k))
            }
            cases
        }
        val data = (
//                randomCases +
                listOfNotNull(
//                        Pair(emptyList<Int>(), 0),
//                        Pair(emptyList<Int>(), 1),
//                        Pair(listOf(0,0), 0),
//                        Pair(listOf(0,0), 1),
//                        Pair(listOf(0,0,1), 1),
//                        Pair(listOf(0,0,0,0), 1),
//                        Pair(listOf(0,0,0,1), 1),
//                        Pair(listOf(0,0,1,0), 1),
//                        Pair(listOf(1,0,0,0), 1),
//                        Pair(listOf(1,0,0,3), 1),
//                        Pair(listOf(0,1,0,0), 1),
//                        Pair(listOf(1,3,0,0), 1),
//                        Pair(listOf(0,1,2,3), 2),
//                        Pair(listOf(0,1,3,3), 2),
//                        Pair(listOf(1,0,0,3), 2),
//                        Pair(listOf(0,0,1,3), 2),
//                        Pair(listOf(0,1,3,3), 2),
//                        Pair(listOf(0,1,0,0), 2),
//                        Pair(listOf(0,0,1,3,3), 2),
//                        Pair(listOf(0,0,0,1,3,3), 2),
//                        Pair(listOf(1,0,1,3,3,0), 2),
//                        Pair(listOf(3,0,0,3,2,0), 2),
//                        Pair(listOf(2,0,0,2,2,1), 2),
//                        Pair(listOf(3,1,0,2,3,1), 2),
//                        Pair(listOf(0,1,2,0,1), 1),
//                        Pair(listOf(0,2,3,0,2), 1),
//                        Pair(listOf(1,2,3,0,0,1), 1),
                        // TODO Debug from here
                        Pair(listOf(0,2,3,3,0,0), 2),
                null
        )).map { (prices, k) ->
            data(prices, k, bestProfitByBruteForce2(prices, k))
        }.toTypedArray()

        on("prices: %s, k: %d", with = *data) { prices, k, expected ->
            it("returns $expected") {
                assertThat(buyAndSellStockKTimes(prices, k))
                        .isEqualTo(expected)
            }
        }
    }
})