package com.lagostout.elementsofprogramminginterviews.honors

import org.apache.commons.math3.util.ArithmeticUtils
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.xdescribe
import kotlin.test.assertEquals

class ComputeGCDSpek : Spek({
    describe("computeGCD") {
        testCases.forEach { (integers, gcd) ->
            given("integers: $integers") {
                it("returns gcd $gcd") {
                    assertEquals(
                            gcd, computeGCD(
                            integers.first, integers.second))
                }
            }
        }
    }
    describe("computeGCDRecursively") {
        testCases.forEach { (integers, gcd) ->
            given("integers: $integers") {
                it("returns gcd $gcd") {
                    assertEquals(
                            gcd, computeGCDRecursively(
                            integers.first, integers.second))
                }
            }
        }
    }
}) {
    companion object {
        data class TestCase(val integers: Pair<Int, Int>) {
            private val gcd = ArithmeticUtils.gcd(integers.first, integers.second)
            operator fun component2() = gcd
        }
        val testCases = listOf(
//                TestCase(Pair(1,1)),
//                TestCase(Pair(15,10)),
//                TestCase(Pair(10,15)),
//                TestCase(Pair(3,5)),
//                TestCase(Pair(5,3)),
                // TODO Handle negative values
                TestCase(Pair(-15,10)),
                null).filterNotNull()
    }
}
