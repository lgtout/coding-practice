package com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi

import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.PegPosition
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.PegPosition.*
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.RingMove
import org.assertj.core.api.SoftAssertions
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

object TowersOfHanoiMinimumOperationsWithRelaxedConstraintSpek : Spek({

    data class TestCase(val fromPegPosition: PegPosition,
                        val toPegPosition: PegPosition,
                        val rings: List<Int> = emptyList(),
                        val expectedOperationCount: Int? = null)

    describe("minimumOperationsWithRelaxedConstraint()") {
        val testCases = listOf(
                TestCase(LEFT, MIDDLE, expectedOperationCount = 0),
                TestCase(MIDDLE, RIGHT, expectedOperationCount = 0),
                TestCase(RIGHT, LEFT, expectedOperationCount = 0),
                TestCase(LEFT, RIGHT, expectedOperationCount = 0),
                TestCase(RIGHT, MIDDLE, expectedOperationCount = 0),
                TestCase(MIDDLE, LEFT, expectedOperationCount = 0),
                TestCase(LEFT, MIDDLE, listOf(1,2), 2),
                TestCase(MIDDLE, RIGHT, listOf(), 1),
                TestCase(RIGHT, LEFT, listOf(), 1),
                TestCase(LEFT, RIGHT, listOf(), 1),
                TestCase(RIGHT, MIDDLE, listOf(), 1),
                TestCase(MIDDLE, LEFT, listOf(), 1),
                TestCase(LEFT, MIDDLE, listOf(), 7),
                TestCase(LEFT, MIDDLE, listOf()),
                TestCase(RIGHT, RIGHT, listOf()),
                TestCase(MIDDLE, RIGHT, listOf()),
                TestCase(RIGHT, MIDDLE, listOf()),
                TestCase(RIGHT, LEFT, listOf()),
                null).filterNotNull()

        testCases.forEach {
            (fromPegPosition, toPegPosition, rings, expectedOperationCount) ->
            given("rings: $rings, from peg: $fromPegPosition, " +
                    "to peg: $toPegPosition") {
                it("moves rings between pegs ${ expectedOperationCount?.let {"in $it moves"} }") {
                    val pegs = TowersOfHanoi.Pegs(rings, fromPegPosition)
                    val operations = mutableListOf<RingMove>()
                    minimumOperationsWithRelaxedConstraint(
                            pegs, pegs.at(fromPegPosition),
                            pegs.at(toPegPosition), operations)
//                    println(operations)
//                    println(operations.size)
                    assertEquals(pegs, TowersOfHanoiSpek.pegsFromRunningOperations(
                            rings, fromPegPosition, operations))
                    with (SoftAssertions()) {
                        // Verify that during operations the bottom ring of any peg is always
                        // the largest.
                        // TODO
                        // I'm only verifying expected operation count for trivial cases since
                        // expected counts have to be computed by hand.
                        expectedOperationCount?.let {
                            assertThat(expectedOperationCount).isEqualTo(operations.size)
                        }
                        assertAll()
                    }
                }
            }
        }
    }
})
