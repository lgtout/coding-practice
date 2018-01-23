package com.lagostout.elementsofprogramminginterviews.primitivetypes

import com.lagostout.common.toBinaryString
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.paukov.combinatorics3.Generator
import java.math.BigInteger

object ClosestIntegerWithSameWeightSpek : Spek({
    fun findClosestIntegerWithBruteForce(number: Int):
            Pair<String, Int> {
        if (number == 0) return Pair(0.toString(), 0)
        val bits = number.toBigInteger()
        val bitsChars = listOf('0') + bits.toString(2).toList()
        return Generator.permutation(bitsChars)
                .simple()
                .map {
                    val s = it.joinToString(separator = "")
                    val n = BigInteger(s, 2)
                    Pair(s, n)
                }
                .sortedBy { it.second }
                .withIndex()
                .let { all ->
                    all.find {
                        it.value.second == bits
                    }!!.let { (index, _) ->
                        val numberPair = all.elementAt(index).value
                        mutableListOf<Pair<String, BigInteger>>()
                                .apply {
                                    if (index > 0) add(all.elementAt(index - 1).value)
                                    /* There will always be a permutation that's greater
                                    _number_ because of the 0 we prepended to _bitChars_ */
                                    add(all.elementAt(index + 1).value)
                                }
                                .map {
                                    Pair(it, (it.second - numberPair.second).abs())
                                }
                                .sortedBy { it.second }
                                .first().first
                    }
                }
                .run {
                    Pair(first, second.toInt())
                }
    }

    val data by memoized { (0..100).map {
        data(it, findClosestIntegerWithBruteForce(it))
    }.toTypedArray() }

    describe("findClosestIntegerWithSameWeight()") {
        data.forEach { (number, expected) ->
            given("number: ${number.toBinaryString()}") {
                it("returns ${expected.first}") {
                    assertThat(findClosestIntegerWithSameWeight(number))
                            .isEqualTo(expected.second)
                }
            }
        }
    }

    describe("findClosestIntegerWithSameWeightInConstantSpaceTime()") {
        data.forEach { (number, expected) ->
            given("number: ${number.toBinaryString()}") {
                it("returns ${expected.first}") {
                    assertThat(findClosestIntegerWithSameWeightInConstantSpaceTime(number))
                            .isEqualTo(expected.second)
                }
            }
        }
    }
})