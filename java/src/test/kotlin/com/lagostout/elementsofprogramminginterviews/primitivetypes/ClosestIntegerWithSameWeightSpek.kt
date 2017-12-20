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
    describe("findClosestIntegerWithSameWeight()") {
        fun findClosestIntegerWithBruteForce(number: Int):
                Pair<String, Int> {
            val bits = number.toBigInteger()
            val bitsChars = listOf('0') + bits.toString(2).toList()
//            println(bitsChars)
            return Generator.permutation(bitsChars)
                    .simple()
                    .map {
                        val s = it.joinToString(separator = "")
                        val n = BigInteger(s, 2)
//                        println(s)
                        Pair(s, n)
                    }
                    .sortedBy { it.second }
                    .withIndex()
                    .let { all ->
//                        println(all.toList())
                        all.find {
                            it.value.second == bits
                        }!!.let { (numberIndex, _) ->
//                            println(numberIndex)
                            mutableListOf<Pair<String, BigInteger>>()
                                    .apply {
                                        if (numberIndex > 0)
                                            add(all.elementAt(numberIndex - 1).value)
                                        add(all.elementAt(numberIndex).value)
                                        if (numberIndex < all.last().index)
                                            add(all.elementAt(numberIndex + 1).value)
                                    }
                                    .map {
                                        println(it)
                                        Pair(it, it.second - all.elementAt(numberIndex).value.second)
                                    }
                                    .filterNot { it.second == 0.toBigInteger() }
                                    .sortedBy { it.second }
                                    .first().first
                        }
                    }
                    .run {
                        Pair(first, second.toInt())
                    }
        }
        val data = (1..1).map {
            data(it, findClosestIntegerWithBruteForce(it))
        }.toTypedArray()
        data.forEach { (number, expected) ->
            given("number: ${number.toBinaryString()}") {
                it("returns ${expected.first}") {
                    assertThat(findClosestIntegerWithSameWeight(number))
                            .isEqualTo(expected.second)
                }
            }
        }
    }
})