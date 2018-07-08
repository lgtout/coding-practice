package com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants

import com.lagostout.common.nextInt
import org.apache.commons.math3.random.RandomDataGenerator
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import org.paukov.combinatorics3.Generator
import kotlin.streams.toList

class KSumWithRepetitionAllowedSpek : Spek({

    fun bruteForceSolution(list: List<Int>, k: Int, sum: Int):
            Pair<List<List<Int>>, Boolean> {
        println("k $k")
        return if (k > 0) {
            if (list.isEmpty()) Pair(emptyList(), false)
            else {
                val pickedNumbers = Generator.combination(list.distinct())
                        .multi(k).stream()
                        .filter { it.sum() == sum }.toList()
                Pair(pickedNumbers, pickedNumbers.isNotEmpty())
            }
        } else Pair(emptyList(), sum == 0)
    }

    @Suppress("NAME_SHADOWING")
    val randomData by memoized {
        val cases = mutableListOf<Triple<List<Int>, Int, Int>>()
        val random = RandomDataGenerator().apply { reSeed(1) }
        val caseCount = 10000
//            val numberCountRange = (3..3)
//            val numberCountRange = (0..5)
//            val numberCountRange = (1..5)
        val numberCountRange = (0..6)
        val numberRange = (-10..10)
//            val numberRange = (-5..5)
        val kRange = (0..4)
//            val kRange = (2..2)
//            val kRange = (0..2)
        (0..caseCount).forEach {
            val numberCount = random.nextInt(numberCountRange)
            val numbers = mutableSetOf<Int>().apply {
                while (size < numberCount) {
                    add(random.nextInt(numberRange))
                }
            }
            val k = random.nextInt(kRange)
            // Make sumRange wide enough to allow for some misses - cases
            // where we know for certain that the sum cannot be derived
            // from the numbers in the list.
            val sumRange = (numberCount + 1).let {
                (numberRange.start * k..numberRange.endInclusive * k)
            }
            cases.add(Triple(numbers.toList(), k, random.nextInt(sumRange)))
        }
        cases.map { (list, k, sum) ->
            data(list, k, sum, bruteForceSolution(list, k, sum))
        }.toTypedArray()
    }

    val data = (
            listOfNotNull(
//                    Triple(listOf(5,9,-2,-8), 4, 6),

//                    Triple(listOf(3,1), 3, 9),

                Triple(listOf(-8,-2,5,9), 4, 6),
//                    Triple(listOf(-10,-6,5,10), 4, 3),
//                    Triple(listOf(-7,-6,0,3), 4, -9),
//                    Triple(listOf(-1,-4,9,10), 4, 13),

//                    Triple(listOf(-8,-5,5,7), 4, -10),

//                    Triple(listOf(8,-6,7), 4, 32),
//
//                    Triple(listOf(-2,0,3,5), 4, 0),
//                    Triple(listOf(-5,-1,2,5), 4, -8),
//                    Triple(listOf(-4,-2,0,3), 4, 3),

//                    Triple(listOf(3, -4, -3, 1), 4, 1),
//                    Triple(listOf(-9,-7,6,9), 6, 10),  // 10 = [[-7, -7, 6, 6, 6, 6]]
//                    Triple(listOf(-9,-7,6,9), 4, 11),
//                    Triple(listOf(-5,-3,2,5), 4, -2),
//                    Triple(listOf(-3,-3,2,2), 4, -2),
//                    Triple(listOf(0,-3,2,2), 4, -2),
//                    Triple(listOf(-5,-3,2,6), 4, -2),
//                    Triple(listOf(-5,-3,2,5), 4, -2),
//                    Triple(listOf(-9,-7,6,9), 4, -2),  // -2 = [[-7, -7, 6, 6]]
//                    Triple(listOf(-4,-3,3,4), 4, -2),
//                    Triple(listOf(-4,-3,3,4), 4, 2),
//                    Triple(listOf(-4,-3,3,4), 4, 0),
//                    Triple(listOf(-7,-7,6,6), 4, -2),  // -2 = [[-7, -7, 6, 6]]
//                    Triple(emptyList(), 2, 0),
//                    Triple(listOf(5,0,-4), 4, 0),
//                    Triple(listOf(5,0,-4), 2, 0),
//                    Triple(listOf(5,0,-4), 3, 0),
//                    Triple(listOf(-9,-7,6,9), 2, -14),
//                    Triple(listOf(-9,-7,6,9), 2, -1),
//                    Triple(listOf(1), 3, -34),
//                    Triple(listOf(1), 0, 3),
//                    Triple(listOf(1), 1, 1),
//                    Triple(listOf(1), 1, 0),
//                    Triple(listOf(1), 3, 3),
//                    Triple(listOf(2), 3, 6),
//                    Triple(listOf(2,3), 3, 6),
//                    Triple(listOf(1,2), 6, 6),
//                    Triple(listOf(1,2), 0, 2),
//                    Triple(listOf(1,2), 0, 3),
//                    Triple(listOf(1,2), 1, 1),
//                    Triple(listOf(1,2), 1, 2),
//                    Triple(listOf(1,2), 1, 3),
//                    Triple(listOf(1,2), 1, 0),
//                    Triple(listOf(1,2), 2, 3),
//                    Triple(listOf(1,2), 2, 4),
//                    Triple(listOf(1,2,3), 2, 3),
//                    Triple(listOf(1,2,3,4), 2, 5),
//                    Triple(listOf(1,2,4,5), 2, 6),
//                    Triple(listOf(1,2,4,5), 6, 12),
                null))
//                .map { it.copy(it.first.shuffled(random)) }
            .map { (list, k, sum) ->
                data(list, k, sum, bruteForceSolution(list, k, sum))
            }.toTypedArray()

    describe("canPickKNumbersThatAddUpToSumWithRepetitionAllowed") {

        on("list: %s, k: %s, sum: %s", with = *data) {
                list: List<Int>, k: Int, sum: Int, expected: Pair<List<List<Int>>, Boolean> ->
            it("returns $expected") {
                // 8 / 8250 fails
                assertThat(canPickKNumbersThatAddUpToSumWithRepetitionAllowed(
                    list, k, sum, ::findClosestSum1)).isEqualTo(expected.second)
                // 115 / 8250 fails
//                assertThat(canPickKNumbersThatAddUpToSumWithRepetitionAllowed(
//                    list, k, sum, ::findClosestSum2)).isEqualTo(expected.second)
                // 8 / 8250 fails
//                assertThat(canPickKNumbersThatAddUpToSumWithRepetitionAllowed(
//                    list, k, sum, ::findClosestSum3)).isEqualTo(expected.second)
                // 4796 / 8250 fails
//                assertThat(canPickKNumbersThatAddUpToSumWithRepetitionAllowed(
//                    list, k, sum, ::findClosestSum4)).isEqualTo(expected.second)
            }
        }

    }
})
