package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import com.lagostout.common.reproducibleRdg
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.Data2
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object MinimizeMessinessInLinearTimeConstantSpaceSpek : Spek({

    fun computeByBruteForce(words: List<String>, lineCapacity: Int): Int? {
        fun compute(wordIndex: Int, lineMessiness: Int): Int? {
            if (wordIndex >= words.count())
                return if (lineMessiness == lineCapacity) 0
                else lineMessiness
            val word = words[wordIndex]
            if (word.length > lineCapacity) return null
            // Add the word to the current line.
            val lineMessinessWhenAppendingWordToCurrentLine =
                    (lineMessiness - (word.length +
                            if (lineMessiness == lineCapacity) 0 else 1)).let {
                        val nextWordIndex = wordIndex.inc()
                        if (it >= 0) compute(nextWordIndex, it)
                        else null
                    }
            // Add the word to the next line.
            val lineMessinessWhenAppendingWordToNextLine =
                    if (lineMessiness != lineCapacity)
                         compute(wordIndex, lineCapacity)?.let {
                             it + lineMessiness
                         }
                    else null
            return listOfNotNull(lineMessinessWhenAppendingWordToCurrentLine,
                lineMessinessWhenAppendingWordToNextLine).min()
        }
        return compute(0, lineCapacity)
    }

    val randomData = run {
        val maxWordLength = 6
        val maxWordCount = 10
        val maxLineCapacity = 10
        val caseCount = 100
        val random = reproducibleRdg()
        val char = "x"
        (0 until caseCount).map {
            val wordCount = random.nextInt(0, maxWordCount)
            (0 until wordCount).map {
                val wordLength = random.nextInt(1, maxWordLength)
                char.repeat(wordLength)
            }.let {
                val lineCapacity = random.nextInt(maxWordLength, maxLineCapacity)
                Pair(it, lineCapacity)
            }
        }.map {
            data(it.first, it.second, computeByBruteForce(it.first, it.second))
        }
    }.toTypedArray()

    val data = listOfNotNull<Data2<List<String>, Int, Int?>>(
        data(listOf("x"), 0, null),
        data(emptyList(), 1, 1),
        data(listOf("x"), 1, 0),
        data(listOf("x"), 2, 1),
        data(listOf("x"), 3, 2),
        data(listOf("x", "x"), 1, 0),
        data(listOf("x", "x"), 3, 0),
        data(listOf("x", "x"), 5, 2),
        data(listOf("x", "x"), 2, 2),
        data(listOf("xx", "x"), 2, 1),
        data(listOf("x", "xx"), 2, 1),
        data(listOf("xx", "x"), 3, 3),
        data(listOf("x", "xx"), 3, 3),
        data(listOf("x", "xxx", "x"), 3, 4),
        data(listOf("x", "xxxx", "x"), 3, null),
        null
    ).toTypedArray()

    describe("minimizeMessiness") {
//        on("words %s, lineCapacity %s", with = *data) {
        on("words %s, lineCapacity %s", with = *randomData) {
                words, lineCapacity, expected ->
            val messiness = minimizeMessiness(words, lineCapacity)
            it("should return $expected") {
                assertThat(messiness).isEqualTo(expected)
            }
        }
    }

})