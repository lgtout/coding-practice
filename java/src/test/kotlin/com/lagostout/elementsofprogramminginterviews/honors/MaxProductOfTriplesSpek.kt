package com.lagostout.elementsofprogramminginterviews.honors

import com.lagostout.common.nextInt
import com.lagostout.common.product
import com.lagostout.common.reproducibleRdg
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import org.paukov.combinatorics3.Generator

object MaxProductOfTriplesSpek : Spek({

    fun computeByBruteForce(list: List<Int>): Int? {
        return list.toSet().let {
            if (it.size < 3) null
            else {
                Generator.combination(it).simple(3).reduce { acc, curr ->
                    if (acc.product() > curr.product()) acc else curr
                }.product()
            }
        }
    }

    val randomData = run {
        val caseCount = 30
        val numberCountRange = Pair(3,10)
        val numberRange = Pair(-20, 20)
        val random = reproducibleRdg()
        (0 until caseCount).map { _ ->
            (0 until random.nextInt(numberCountRange)).map { _ ->
                random.nextInt(numberRange)
            }
        }.map {
            data(it, computeByBruteForce(it))
        }.toTypedArray()
    }

    describe("maxProductOfTriples") {
        on("list %s", with = *randomData) { list, expected ->
            val maxProduct = maxProductOfTriples(list)
            it("should return $expected") {
                assertThat(maxProduct).isEqualTo(expected)
            }
        }
    }

})