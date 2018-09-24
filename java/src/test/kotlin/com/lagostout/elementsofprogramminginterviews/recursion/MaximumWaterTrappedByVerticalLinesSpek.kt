package com.lagostout.elementsofprogramminginterviews.recursion

import com.lagostout.common.nextInt
import com.lagostout.common.rdg
import com.lagostout.common.second
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import org.paukov.combinatorics3.Generator
import java.lang.Math.abs

object MaximumWaterTrappedByVerticalLinesSpek : Spek({

    val randomData = run {
        val lineLengthRange = (1..10)
        val lineCountRange = (1..6)
        val random = rdg()
        val caseCount = 1000
        (0..caseCount).map { _ ->
            val lineCount = random.nextInt(lineCountRange)
            (0..lineCount).map { _ ->
                random.nextInt(lineLengthRange)
            }
        }.map { it ->
            val expected = Generator.combination(it.asSequence().withIndex().toList())
                    .simple(2).map {
                        val first = it.first()
                        val second = it.second()
                        abs(first.index - second.index) * minOf(first.value, second.value)
                    }.max()
            data(it, expected)
        }.toTypedArray()
    }
    val data = listOfNotNull(
        data(listOf(1), 0),
        data(listOf(1, 1), 1),
        data(listOf(1, 2), 1),
        data(listOf(2, 1), 1),
        data(listOf(2, 2), 2),
        data(listOf(1, 2, 1), 2),
        data(listOf(1, 2, 2), 2),
        data(listOf(2, 2, 1), 2),
        data(listOf(1,2,1,2,1), 4),
        data(listOf(1,2,1,1,2), 6),
        data(listOf(1,2,1,1,2,1), 6),
        null
    ).toTypedArray()

    describe("maximumWaterTrappedByVerticalLines") {
        on("lines: %s", with = *randomData) { lines, expected ->
             it("should return $expected") {
                 assertThat(maximumWaterTrappedByVerticalLines(lines))
                         .isEqualTo(expected)
             }
        }
    }

})