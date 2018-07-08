package com.lagostout.elementsofprogramminginterviews.graphs

import com.lagostout.common.removeChar
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
        // Keep caseCount low because computing solution by
        // brute force is very slow.
        val caseCount = 10
        val letters = listOf("A", "B", "C", "D")
        val random = RandomDataGenerator().apply { reSeed(1) }
        val data = mutableListOf<Data3<String, String, List<String>, Int>>()
        // We'll ignore generating cases where there's no production
        // sequence - implementing that became too time-consuming.
        (1..caseCount).forEach {
            // Picking word length larger than 2 takes too long to
            // compute expected result.
            val wordLength = random.nextInt(1, 2)
            val permutations = Generator.permutation(letters)
                    .withRepetitions(wordLength)
                    .map {
                        it.reduce { acc, s -> acc + s }
                    }.toList()
            fun computeByBruteForce(
                    currentWord: String, to: String,
                    words: Set<String>, sequence: List<String>): List<List<String>> {
                if (currentWord == to) return listOf(sequence)
                if (words.isEmpty()) return emptyList()
                return (0 until wordLength).flatMap { charIndexToVary ->
                    val commonChars = currentWord.removeChar(charIndexToVary)
                    val adjacentWords = words.filter { commonChars == it.removeChar(charIndexToVary) }
                    adjacentWords.flatMap {
                        computeByBruteForce(it, to, words - it, sequence + it)
                    }
                }.let {
                    it.fold(0) { acc, curr ->
                        curr.count().let {
                            if (it < acc || acc == 0) it else acc
                        }
                    }.let { minCount ->
                        it.filter { it.count() == minCount }
                    }
                }
            }
            val (from, to) = (0..1).map { permutations[random.nextInt(0, permutations.lastIndex)] }
            val expected = computeByBruteForce(from, to, permutations.toSet(), listOf(from)).let {
                it.first().count()
            }
            data.add(data(from, to, permutations, expected))
        }
        data.toTypedArray()
    }

    val data by memoized {
        listOfNotNull(
                data("a", "a", listOf("a"), 1),
                data("a", "b", listOf("a", "b"), 2),
                data("a", "b", listOf("c", "b", "a"), 2),
                data("a", "b", listOf("b", "a"), 2),
                data("a", "b", listOf("b", "a", "c"), 2),
                data("ab", "cb", listOf("ab", "ac", "cb"), 2),
                data("ab", "bc", listOf("ab", "ad", "cb", "bc"), -1),
                data("ab", "bc", listOf("ab", "cc", "aa", "bc"), -1),
                data("cat", "dog", listOf("bat", "cot", "dog", "dag", "dot", "cat"), 4),
                data("ab", "bc", listOf("ab", "ac", "bb", "db", "bc"), 3),
                data("ab", "cd", listOf("ab", "ac", "bb", "db", "bc", "cc", "cd"), 4),
            null
        ).toTypedArray()
    }

    describe("lengthOfShortestProductionSequence") {
//        on("from: %s, to: %s, dictionary: %s", with = *randomData) {
        on("from: %s, to: %s, dictionary: %s", with = *data) {
            from: String, to: String, dictionary: List<String>, expected: Int ->
            it("returns $expected") {
                assertThat(lengthOfShortestProductionSequence(
                        from, to, dictionary) ?: -1).isEqualTo(expected)
            }
        }
    }

})
