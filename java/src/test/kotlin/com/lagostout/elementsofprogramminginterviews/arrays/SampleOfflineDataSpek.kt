package com.lagostout.elementsofprogramminginterviews.arrays

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.paukov.combinatorics3.Generator
import java.lang.Math.abs

object SampleOfflineDataSpek : Spek({
    val data = listOfNotNull(
        Pair(mutableListOf(1), 0),
        Pair(mutableListOf(1), 1),
        Pair(mutableListOf(1,2,3), 0),
        Pair(mutableListOf(1,2,3), 1),
        Pair(mutableListOf(1,2,3), 2),
        null
    )
    describe("sampleOfflineData") {
        data.forEach { (list, n) ->
            val combinationCount = Generator.combination(list)
                    .simple(n).count()
            on("list: $list, n: $n") {
                val sampleToCount = mutableMapOf<Set<Int>, Int>()
                val totalCount = 1_000_000 //  Faster - 10_0000
                repeat(totalCount) {
                    sampleOfflineData(list, n)
                    val selection = list.take(n).toSet()
                    sampleToCount.compute(selection) {
                            _, value -> value?.plus(1) ?: 1 }
                }
                val frequencies = sampleToCount.values.map {
                    it / totalCount.toFloat()
                }
                val expectedFrequency = 1 / combinationCount.toFloat()
                it("should randomly select a sample") {
                    // Each possible combination should have been generated.
                    assertThat(sampleToCount.size).isEqualTo(combinationCount)
                    // The frequencies of all combinations should approach equality.
                    assertThat(frequencies).allMatch {
                        abs(it - expectedFrequency) < 0.001 // Faster - 0.01
                    }
                }
            }
        }
    }
})