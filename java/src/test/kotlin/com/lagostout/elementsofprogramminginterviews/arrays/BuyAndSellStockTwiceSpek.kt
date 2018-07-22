package com.lagostout.elementsofprogramminginterviews.arrays

import com.lagostout.common.nextInt
import com.lagostout.common.rdg
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object BuyAndSellStockTwiceSpek : Spek({

    fun computeByBruteForce(prices: List<Float>): Float? {
        if (prices.count() < 4) return null
        fun compute(prices: List<Float>, buySellCountRemaining: Int): Float {
            if (buySellCountRemaining <= 0) return 0F
            val lastIndex = prices.lastIndex
            var bestProfit: Float? = null
            val lastBuyIndex = prices.count() - (2 * buySellCountRemaining)
            (0..lastBuyIndex).forEach { buyIndex ->
                val lastSellIndex = lastBuyIndex + 1
                (buyIndex + 1..lastSellIndex).forEach { sellIndex ->
                    val currentProfit = prices[sellIndex] - prices[buyIndex]
                    val nextProfit = compute(prices.subList(sellIndex + 1, lastIndex + 1),
                        buySellCountRemaining - 1)
                    (currentProfit + nextProfit).let { profit ->
                        bestProfit = bestProfit?.let {
                            if (profit > it) profit else it
                        } ?: profit
                    }
                }
            }
            return bestProfit!!
        }
        return compute(prices, 2)
    }

    val randomData = {
        val caseCount = 100
        val rdg = rdg()
        val rg = rdg.randomGenerator
        val priceRange = Pair(0F, 5F)
        val priceRangeSpread = priceRange.run { second - first }
        val pricesCountRange = Pair(0, 10)
        (0 until caseCount).map {
            val pricesCount = rdg.nextInt(pricesCountRange)
            (0 until pricesCount).map {
                Math.round(rg.nextFloat() * priceRangeSpread * 10F) / 10F +
                        priceRange.first
            }.let {
                data(it, computeByBruteForce(it))
            }
        }.toTypedArray()
    }

    val data = listOfNotNull(
        listOf(0F, 1F),
        listOf(0F, 1F, 0F),
        listOf(1F, 1F, 1F, 1F),
        listOf(1F, 0F, 1F, 0F),
        listOf(4.2030916F, 1.0070288F, 1.2424576F, 1.49912F),
        null
    ).map {
        data(it, computeByBruteForce(it))
    }.toTypedArray()

    describe("buyAndSellStockTwice") {
        on("prices %s", with = *randomData()) { prices, expected ->
//        on("prices %s", with = *data) { prices, expected ->
            val profit = buyAndSellStockTwice(prices)
            it("should return $expected") {
                assertThat(profit).isEqualTo(expected)
            }
        }
    }

})