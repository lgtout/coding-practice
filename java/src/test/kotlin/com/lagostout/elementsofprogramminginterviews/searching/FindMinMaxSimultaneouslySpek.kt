package com.lagostout.elementsofprogramminginterviews.searching

import com.lagostout.common.nextInt
import org.apache.commons.math3.random.RandomDataGenerator
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object FindMinMaxSimultaneouslySpek : Spek({
    val data = run {
        val cases = mutableListOf<List<Int>>()
        val caseCount = 1000
        val listSizeRange = (0..10)
        val listEntryRange = (0..10)
        val random = RandomDataGenerator().apply { reSeed(1) }
        (0 until caseCount).forEach {
            val list = mutableListOf<Int>()
            (0 until random.nextInt(listSizeRange)).forEach {
                list.add(random.nextInt(listEntryRange))
            }
            cases.add(list)
        }
        cases
    }.map {
        data(it, Pair(Math.round(it.size * 1.5), if (it.isNotEmpty())
            Pair(it.min(), it.max()) else null))
    }.toTypedArray()
    on("list: %s", with = *data) { list, expected ->
        it("returns $expected") {
            findMinMaxSimultaneously(list).let {
                (comparisonCount, minMax) ->
                assertThat(comparisonCount)
                        .isLessThanOrEqualTo(expected.first.toInt())
                assertThat(minMax).isEqualTo(expected.second)
            }
        }
    }
})