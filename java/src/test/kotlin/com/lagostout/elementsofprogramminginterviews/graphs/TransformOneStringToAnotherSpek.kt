package com.lagostout.elementsofprogramminginterviews.graphs

import com.lagostout.common.nextInt
import org.apache.commons.math3.random.RandomDataGenerator
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.Data3
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import org.paukov.combinatorics3.Generator

object TransformOneStringToAnother : Spek({

    val randomData by memoized {
        val caseCount = 50
        val letters = listOf("A", "B", "C", "D")
        val random = RandomDataGenerator().apply { reSeed(1) }
        val data = mutableListOf<Data3<String, String, List<String>, Int?>>()
        (1..caseCount).forEach {
            // Picking word length larger than 2 takes too long to compute expected result.
            val wordLength = random.nextInt(1, 2)
            val permutations = Generator.permutation(letters)
                    .withRepetitions(wordLength)
                    .map {
                        it.reduce { acc, s -> acc + s }
                    }.toList()
                    // This introduces the possibility that a path
                    // between words won't exist.  80% of data-sets
                    // will have a path.
                    .filter { random.nextInt(0..9) <= 7 }
            println("wordLength $wordLength")
            println("permutations $permutations")
            fun computeByBruteForce(currentWord: String, to: String,
                                    charIndexToVary: Int, length: Int = 0): Int? {
                if (currentWord == to) return length + 1
                if (charIndexToVary >= currentWord.length) return null
                val commonChars = currentWord.removeRange(charIndexToVary, charIndexToVary + 1)
                val adjacentWords = permutations.filter { commonChars ==
                        it.removeRange(charIndexToVary, charIndexToVary + 1) }
                return if (adjacentWords.isEmpty()) null
                else {
                    adjacentWords.mapNotNull {
                        computeByBruteForce(it, to, charIndexToVary + 1, length + 1)
                    }.min()
                }
            }
            val (from, to) = (0..1).map { permutations[random.nextInt(0, permutations.lastIndex)] }
            val expected = computeByBruteForce(from, to, 0, 0)
            data.add(data(from, to, permutations, expected))
        }
        data.toTypedArray()
    }

    val data by memoized {
        listOfNotNull(
//                data("a", "a", listOf("a"), 1),
            data("a", "a", listOf("c","d"), -1),
//                data("a", "b", listOf("a", "b"), 2),
//                data("a", "b", listOf("c", "b", "a"), 2),
//                data("a", "b", listOf("b", "a"), 2),
//                data("a", "b", listOf("b", "a", "c"), 2),
//                data("ab", "cb", listOf("ab", "ac", "cb"), 2),
//                data("ab", "bc", listOf("ab", "ad", "cb", "bc"), -1),
//                data("cat", "dog", listOf("bat", "cot", "dog", "dag", "dot", "cat"), 4),
////                // TODO More cases
//                data("ab", "bc", listOf("ab", "ac", "bb", "db", "bc"), 3),
//                data("ab", "cd", listOf("ab", "ac", "bb", "db", "bc", "cc", "cd"), 4),
            null
        ).toTypedArray()
    }

    describe("lengthOfShortestProductionSequence") {
        // TODO Test with random data
//        on("from: %s, to: %s, dictionary: %s", with = *randomData) {
        on("from: %s, to: %s, dictionary: %s", with = *data) {
            from: String, to: String, dictionary: List<String>, expected: Int? ->
            it("returns $expected") {
                assertThat(lengthOfShortestProductionSequence(
                        from, to, dictionary) ?: -1).isEqualTo(expected)
            }
        }
    }

})
