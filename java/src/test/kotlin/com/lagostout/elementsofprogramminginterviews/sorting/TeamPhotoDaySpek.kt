package com.lagostout.elementsofprogramminginterviews.sorting

import com.lagostout.common.nextInt
import com.lagostout.common.rdg
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import org.paukov.combinatorics3.Generator

object TeamPhotoDaySpek : Spek({

    val data = listOfNotNull(
        Triple(listOf(1), listOf(1), false),
        Triple(listOf(1), listOf(2), true),
        Triple(listOf(1,1), listOf(1,1), false),
        Triple(listOf(1,1), listOf(1,2), false),
        Triple(listOf(2,1), listOf(1,2), false),
        Triple(listOf(1,2), listOf(2,1), false),
        Triple(listOf(2,2), listOf(1,1), true),
        Triple(listOf(1,1), listOf(2,2), true),
        Triple(listOf(1,1), listOf(2,3), true),
        Triple(listOf(1,2), listOf(2,3), true),
        null).map { (team1, team2, expected) ->
        data(team1, team2, expected)
    }.toTypedArray()

    fun computeByBruteForce(team1: List<Int>, team2: List<Int>): Boolean {
        val team1Permutations = Generator.permutation(team1).simple()
        val team2Permutations = Generator.permutation(team2).simple()
        var placementIsPossible = false
        for (team1Permutation in team1Permutations) {
            for (team2Permutation in team2Permutations) {
                val pairedPlayers = team1Permutation.zip(team2Permutation)
                if (pairedPlayers.all { it.first < it.second } ||
                        pairedPlayers.all { it.second < it.first }) {
                    placementIsPossible = true
                    break
                }
            }
        }
        return placementIsPossible
    }

    val randomData = run {
        val rdg = rdg()
        val caseCount = 100
        val teamSize = 5
        val heightRange = Pair(0, 10)
        (0 until caseCount).map {
            val team1 = (0 until teamSize).map { _ ->
                rdg.nextInt(heightRange)
            }
            val team2 = (0 until teamSize).map { _ ->
                rdg.nextInt(heightRange)
            }
            data(team1, team2, computeByBruteForce(team1, team2))
        }.toTypedArray()

    }

    describe("playerPlacementSubjectToConstraintIsPossible") {
        on("team1 %s, team2 %s", with = *randomData) { team1, team2, expected ->
            it("should return $expected") {
                val placementIsPossible = playerPlacementSubjectToConstraintIsPossible(
                    team1, team2)
                assertThat(placementIsPossible).isEqualTo(expected)
            }
        }
    }

})