package com.lagostout.elementsofprogramminginterviews.arrays

import com.lagostout.common.nextInt
import com.lagostout.common.second
import com.lagostout.elementsofprogramminginterviews.arrays.BuyAndSellStockOnce.maximumProfitByBuyingAndSellingStockOnce
import com.lagostout.elementsofprogramminginterviews.arrays.BuyAndSellStockOnce.profit
import org.apache.commons.math3.random.RandomDataGenerator
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.Data1
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import org.paukov.combinatorics3.Generator

object BuyAndSellStockOnceSpek : Spek({

    val randomData by memoized {
        val caseCount = 100
        val priceRange = Pair(1,10)
        val maxPriceCount = 10
        val random = RandomDataGenerator().apply { reSeed(1) }
        fun maximumProfitByBruteForce(prices: List<Int>):
                Pair<Pair<Int, Int>, Int> {
            return Generator.combination((0..prices.lastIndex).toList())
                    .simple(2).map {
                        Pair(Pair(it.first(), it.second()),
                            profit(prices, it.first(), it.second()))
                    }
                    .maxBy { it.second }!!
        }
        ((1..caseCount).map {
            val priceCount = random.nextInt(2, maxPriceCount)
            (1..priceCount).map {
                random.nextInt(priceRange)
            }.let {
                data<List<Int>, Pair<Pair<Int, Int>, Int>?>(
                    it, maximumProfitByBruteForce(it))
            }
        } + listOf<Data1<List<Int>, Pair<Pair<Int, Int>, Int>?>>(
            data(emptyList(), null),
            data(listOf(1), null)
        )).toTypedArray()
    }

    val data = listOfNotNull(
        data(listOf(3,2,4), Pair(Pair(1,2), 2)),
        null
    ).toTypedArray()

    describe("maximumProfitByBuyingAndSellingStockOnce") {
//        on("prices: %s", with = *data) { prices, expected ->
        on("prices: %s", with = *randomData) { prices, expected ->
            it("should return $expected") {
                val result = maximumProfitByBuyingAndSellingStockOnce(prices)
                expected?.let {
                    assertThat(result).isEqualTo(expected)
                } ?: assertThat(result).isNull()
            }
        }
    }

})