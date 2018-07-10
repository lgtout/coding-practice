package com.lagostout.elementsofprogramminginterviews.honors

import com.lagostout.common.nextInt
import org.apache.commons.math3.random.RandomDataGenerator
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object MaximumProductOfAllEntriesButOneSpek : Spek({

    fun computeByBruteForce(list: List<Int>): Pair<Int, Int>? {
        return if (list.count() <= 1) null
        else {
            list.map {
                (list - it).let {
                    it.fold(1) { acc, i -> acc * i}
                }.let { product -> Pair(product, it) }
            }.maxBy { it.first }
        }
    }

    val randomData = run {
        val caseCount = 100
        val maxEntryCount = 10
        val entryRange = Pair(-5,5)
        val random = RandomDataGenerator().apply { reSeed(1) }
        (1..caseCount).map {
            val entryCount = random.nextInt(0, maxEntryCount)
            (1..entryCount).map {
                random.nextInt(entryRange)
            }.let {
                data(it, computeByBruteForce(it))
            }
        }
    }.toTypedArray()

    val data = listOfNotNull(
        emptyList(),
        listOf(1),
        listOf(1,2),
        listOf(2,1),
        listOf(0,1),
        listOf(0,1,0),
        listOf(0,1,0,-1),
        listOf(0,1,-1),
        listOf(0,1,1),
        listOf(0,1,-1,-1),
        listOf(0,1,-1,-1,-1),
        listOf(-5,2,2,1,-1,-5),
        listOf(-4,-2,-1,-3),
        null
    ).map { list ->
        computeByBruteForce(list).let {
            data(list, it)
        }
    }.toTypedArray()

    describe("maximumProductOfAllEntriesButOne") {
        on("entries %s", with = *randomData) { list, productAndExcludedNumber ->
            it("should return ${productAndExcludedNumber?.first}") {
                assertThat(maximumProductOfAllEntriesButOne(list))
                        .isEqualTo(productAndExcludedNumber?.first)
            }
        }
    }

})
