package com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants

import com.lagostout.common.nextInt
import org.apache.commons.math3.random.RandomDataGenerator
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object FindMajorityElementSpek : Spek({

    val randomData by memoized {
        val caseCount = 100
        val strings = ('a'..'d').map { it.toString() }
        val stringIndices = 0 until strings.count()
        val random = RandomDataGenerator().apply { reSeed(1) }
        val majorityElement = strings[random.nextInt(stringIndices)]
        val maxSequenceSize = 10
        ((1..caseCount).map {
            val sequenceSize = random.nextInt(1, maxSequenceSize)
            val halfSequenceSize = sequenceSize / 2
            val majorityElementCount = random.nextInt(
                halfSequenceSize + 1, sequenceSize)
            val sequence = (0 until majorityElementCount)
                    .map { majorityElement }
                    .toMutableList()
            while (sequence.size < sequenceSize) {
                val randomIndex = random.nextInt(stringIndices)
                println(randomIndex)
                val element = strings.elementAt(randomIndex)
                if (element != majorityElement) {
                    sequence.add(element)
                }
            }
            sequence.shuffle()
            data(sequence.toList(), majorityElement as String?)
        }.distinct() + listOf(data(emptyList(), null as String?)))
                .toTypedArray()
    }

    val data = listOf(
        data(listOf("a","a","d"), "a")
    ).toTypedArray()

    describe("findMajorityElement") {
//        on("sequence: %s", with = *data) {
        on("sequence: %s", with = *randomData) {
                sequence: List<String>, expected: String? ->
            it("should return $expected") {
                findMajorityElement(sequence).let {
                    assertThat(it).isEqualTo(expected)
                }
            }
        }
    }

})