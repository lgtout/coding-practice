package com.lagostout.elementsofprogramminginterviews.honors

import com.lagostout.common.nextInt
import org.apache.commons.math3.random.RandomDataGenerator
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.Data2
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object BuyAndSellStockKTimesSpek : Spek({
    describe("buyAndSellStockKTimes()") {
        val randomCases = run {
            fun profit(prices: List<Int>, buyIndex: Int,
                       sellIndex: Int): Int {
                return prices[sellIndex] - prices[buyIndex]
            }
            fun bestProfitByBruteForce(prices: List<Int>, k: Int,
                                       startIndex: Int = 0,
                                       bestProfit: Int): Int {
                @Suppress("NAME_SHADOWING")
                var bestProfit: Int = bestProfit
                val lastPossibleBuyIndex = prices.size - k * 2
                for (buyIndex in (startIndex..lastPossibleBuyIndex)) {
                    ((buyIndex + 1)..(lastPossibleBuyIndex + 1))
                            .map {
                                profit(prices, buyIndex, it) +
                                        bestProfitByBruteForce(prices, k - 1,
                                                it + 1, bestProfit)
                            }
                            .forEach { bestProfit = maxOf(bestProfit, it) }
                }
                return bestProfit
            }
            val random = RandomDataGenerator().apply {
                reSeed(1)
            }
            val cases = mutableListOf<Data2<List<Int>, Int, Int>>()
            val caseCount = 1000
            val priceRange = (0..10)
            val priceCountRange = (0..10)
            (1..caseCount).forEach {
                val prices = mutableListOf<Int>()
                val priceCount = random.nextInt(priceCountRange)
                val k = random.nextInt(0..priceCount)
                (0 until priceCount).forEach {
                    prices.add(random.nextInt(priceRange))
                }
                cases.add(data(prices, k,
                        bestProfitByBruteForce(prices, k, 0, 0)))
            }
            cases
        }
        val data = (
                randomCases +
                listOfNotNull(
//                data(emptyList<Int>(), 0, 0),
//                data(emptyList<Int>(), 1, 0),
//                data(listOf(0,0), 1, 0),
//                data(listOf(0,0,0,0), 0, 0),
//                data(listOf(0,0,0,0), 1, 0),
//                data(listOf(0,0,0,1), 1, 1),
//                data(listOf(0,0,1,0), 1, 1),
//                data(listOf(0,1,0,0), 1, 1),
//                data(listOf(1,0,0,0), 1, 0),
//                data(listOf(1,3,0,0), 1, 2),
//                data(listOf(1,0,0,3), 1, 3),
//                data(listOf(0,1,2,3), 2, 2),
//                data(listOf(0,1,3,3), 2, 1),
//                data(listOf(1,0,0,3), 2, 2),
//                data(listOf(0,0,1,3,3), 2, 2),
                data(listOf(0,0,0,1,3,3), 2, 3),
                null
        )).toTypedArray()
        on("prices: %s, k: %d", with = *data) { prices, k, expected ->
            it("returns $expected") {
                assertThat(buyAndSellStockKTimes(prices, k))
                        .isEqualTo(expected)
            }
        }
    }
})