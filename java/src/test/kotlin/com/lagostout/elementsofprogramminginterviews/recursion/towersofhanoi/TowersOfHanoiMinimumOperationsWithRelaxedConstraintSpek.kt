package com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi

import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.PegPosition
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.PegPosition.*
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.Pegs
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.RingMove
import org.assertj.core.api.Assertions.assertThatCode
import org.assertj.core.api.SoftAssertions
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it

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
                TestCase(LEFT, MIDDLE, listOf(1), 1),
                TestCase(LEFT, MIDDLE, listOf(1,2), 4),
                TestCase(LEFT, MIDDLE, listOf(1,2), 4),
                TestCase(LEFT, MIDDLE, listOf(1,2,3), 11),
                TestCase(LEFT, MIDDLE, listOf(2,1,3), 9),
                TestCase(RIGHT, LEFT, listOf(2,1,3), 9),
                null).filterNotNull()

        testCases.forEachIndexed {
            index, (fromPegPosition, toPegPosition, rings, expectedOperationCount) ->
            given("rings: $rings, from peg: $fromPegPosition, " +
                    "to peg: $toPegPosition") {
                it("#$index moves rings between pegs " +
                        (expectedOperationCount?.let {"in $it moves"} ?: "")) {
                    val pegs = TowersOfHanoi.Pegs(rings, fromPegPosition)
                    val operations = mutableListOf<RingMove>()
                    minimumOperationsWithRelaxedConstraint(
                            pegs, pegs.at(fromPegPosition),
                            pegs.at(toPegPosition), operations)
                    var pegsFromRunningOperations: Pegs? = null
                    assertThatCode({
                        pegsFromRunningOperations = pegsFromRunningOperations(
                                rings, fromPegPosition, operations, { from, to, ring ->
                            if (to.bottom?.let { it.size < ring.size} == true) {
                                throw IllegalStateException("Not allowed to place a " +
                                        "ring on a peg where the peg's bottom ring is " +
                                        "smaller than the ring.  " +
                                        "Details: from: $from, to: $to, ring: $ring")
                            }
                        })
                    }).doesNotThrowAnyException()
                    with (SoftAssertions()) {
                        // Verify that during operations the bottom ring of any peg is always
                        // the largest.
                        pegsFromRunningOperations?.let {
                            assertThat(pegs).isEqualTo(pegsFromRunningOperations)
                        }
                        // I'm only verifying expected operation count for trivial cases since
                        // expected counts have to be computed by hand.
                        expectedOperationCount?.let {
                            assertThat(expectedOperationCount)
                                    .isEqualTo(operations.size)
                        }
                        assertAll()
                    }
                }
            }
        }
    }
})
