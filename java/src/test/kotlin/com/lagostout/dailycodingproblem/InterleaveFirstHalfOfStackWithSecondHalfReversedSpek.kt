package com.lagostout.dailycodingproblem

import com.lagostout.common.isOdd
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import java.util.*

object InterleaveFirstHalfOfStackWithSecondHalfReversedSpek : Spek({
    describe("interleaveFirstHalfOfStackWithSecondHalfReversed") {
        val data = listOfNotNull(
                listOf(1),
                listOf(1,2),
                listOf(1,2,3),
                listOf(3,4,5),
                listOf(3,4,5,6),
                listOf(1,2,3,4,5,6,7),
                listOf(1,2,3,4,5,6,7,8),
                null
        ).map { entries ->
            val halfCount = entries.size / 2 + if (entries.size.isOdd) 1 else 0
            Pair(
                    Stack<Int>().apply {
                        entries.forEach { push(it) } },
                    Pair((entries.subList(0, halfCount)),
                            entries.subList(halfCount, entries.size)
                                    .reversed()
                    ).let {
                        it.first.zip(
                                it.second.toMutableList<Int?>()
                                        .apply { add(null) })
                                .flatMap { it.toList() }
                                .filterNotNull()
                                .let { Stack<Int>()
                                        .apply { it.forEach { push(it) } } } }
            ).let { (stack, expected) -> data(stack, expected) }
        }.toTypedArray()

        on("stack: %s", with = *data) { stack, expected ->
            it("returns $expected") {
                interleaveFirstHalfOfStackWithSecondHalfReversed(
                        stack, ArrayDeque<Int>())
                assertThat(stack).isEqualTo(expected)
            }
        }
    }
})