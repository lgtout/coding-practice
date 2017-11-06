package com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi

import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.PegPosition
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.PegPosition.*
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.RingMove
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.Pegs
import org.assertj.core.api.Assertions.assertThatCode
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
//                TestCase(LEFT, MIDDLE, expectedOperationCount = 0),
//                TestCase(MIDDLE, RIGHT, expectedOperationCount = 0),
//                TestCase(RIGHT, LEFT, expectedOperationCount = 0),
//                TestCase(LEFT, RIGHT, expectedOperationCount = 0),
//                TestCase(RIGHT, MIDDLE, expectedOperationCount = 0),
//                TestCase(MIDDLE, LEFT, expectedOperationCount = 0),
                TestCase(LEFT, MIDDLE, listOf(1,2), 4),
                // TODO More cases
                null).filterNotNull()

        testCases.forEachIndexed {
            index, (fromPegPosition, toPegPosition, rings, expectedOperationCount) ->
            given("rings: $rings, from peg: $fromPegPosition, " +
                    "to peg: $toPegPosition") {
                it("#$index moves rings between pegs " +
                        "${ expectedOperationCount?.let {"in $it moves"} }") {
                    val pegs = TowersOfHanoi.Pegs(rings, fromPegPosition)
                    val operations = mutableListOf<RingMove>()
                    minimumOperationsWithRelaxedConstraint(
                            pegs, pegs.at(fromPegPosition),
                            pegs.at(toPegPosition), operations)
                    // TODO Are soft assertions appropriate, since an exception thrown obviates assertions that follow?
                    with (SoftAssertions()) {
                        // Verify that during operations the bottom ring of any peg is always
                        // the largest.
                        var pegsFromRunningOperations: Pegs? = null
                        assertThatCode({
                            pegsFromRunningOperations = TowersOfHanoiSpek.pegsFromRunningOperations(
                                    rings, fromPegPosition, operations)
                        }).doesNotThrowAnyException()
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
