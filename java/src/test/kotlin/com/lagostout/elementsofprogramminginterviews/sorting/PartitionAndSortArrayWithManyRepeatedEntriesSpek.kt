package com.lagostout.elementsofprogramminginterviews.sorting

import com.lagostout.common.takeFrom
import com.lagostout.elementsofprogramminginterviews.sorting.PartitionAndSortArrayWithManyRepeatedEntries.Student
import com.lagostout.elementsofprogramminginterviews.sorting.PartitionAndSortArrayWithManyRepeatedEntries.groupByAge
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Condition
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import java.util.function.Predicate

// TODO Test!

private typealias s = PartitionAndSortArrayWithManyRepeatedEntries.Student

object PartitionAndSortArrayWithManyRepeatedEntriesSpek : Spek({

    describe("groupByAge") {

        val data = listOfNotNull(
            Pair(listOf(s(1)), listOf(listOf(s(1)))),
//            Pair(listOf(s(1), s(2)), listOf(listOf(s(1)), listOf(s(2)))),
//            Pair(listOf(s(1), s(2), s(1)), listOf(listOf(s(1), s(1)), listOf(s(2)))),
            null).map { data(it.first.toMutableList(), it.second) }
                .toTypedArray()

        // TODO Create a separate condition for each group, with start/end indices, combine them with [allOf].
        on("students %s", with = *data) { students, expected ->
            groupByAge(students)
            it("should group students as $expected") {
                assertThat(students).has(Condition<List<Student>>(
                    Predicate <List<Student>> { students ->
                        @Suppress("NAME_SHADOWING")
                        var studentsToTest = students
                        var match = false
                        for (group in expected) {
                            val count = group.count()
                            match = !studentsToTest.take(count).containsAll(group)
                            if (!match) break
                            studentsToTest = studentsToTest.takeFrom(count)
                        }
                        match
                    }, "groups in order containing exactly elements in any order"))
            }
        }
    }

})
