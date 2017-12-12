package com.lagostout.elementsofprogramminginterviews.primitivetypes

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.paukov.combinatorics3.Generator
import java.math.BigInteger

object ClosestIntegerWithSameWeightSpek : Spek({
    describe("findClosestIntegerWithSameWeight()") {
        fun findClosestIntegerWithBruteForce(number: Int):
                Pair<String, BigInteger> {
            val bits = number.toBigInteger()
            return Generator.permutation(bits.toString(2).toCharArray())
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
                        }!!.let { (numberIndex, _) ->
                            mutableListOf<Pair<String, BigInteger>>()
                                    .apply {
                                        if (numberIndex > 0)
                                            add(all.elementAt(numberIndex - 1).value)
                                        add(all.elementAt(numberIndex).value)
                                        if (numberIndex < all.last().index)
                                            add(all.elementAt(numberIndex + 1).value)
                                    }
                                    .map {
                                        Pair(it, it.second - all.elementAt(numberIndex).value.second)
                                    }
                                    .filterNot { it.second == 0.toBigInteger() }
                                    .sortedBy { it.second }
                                    .first().first
                        }
                    }

        }
    }
})