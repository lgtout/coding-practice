package com.lagostout.elementsofprogramminginterviews.honors

import com.lagostout.common.nextInt
import org.apache.commons.math3.random.RandomDataGenerator
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object BuyAndSellStockKTimesSpek : Spek({
    describe("buyAndSellStockKTimes()") {
        fun profit(prices: List<Int>, buyIndex: Int,
                   sellIndex: Int): Int {
            return prices[sellIndex] - prices[buyIndex]
        }
        fun bestProfitByBruteForce(prices: List<Int>, k: Int,
                                   startIndex: Int = 0): Int {
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
                                    bestProfitByBruteForce(prices, k - 1,
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
            val priceCountRange = (0..5)
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
            data(prices, k, bestProfitByBruteForce(prices, k))
        }.toTypedArray()

        on("prices: %s, k: %d", with = *data) { prices, k, expected ->
            it("returns $expected") {
                assertThat(buyAndSellStockKTimes(prices, k))
                        .isEqualTo(expected)
            }
        }
    }
})