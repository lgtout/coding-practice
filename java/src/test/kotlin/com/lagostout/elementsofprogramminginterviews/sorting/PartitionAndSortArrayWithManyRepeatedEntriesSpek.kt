package com.lagostout.elementsofprogramminginterviews.sorting

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

private typealias s = PartitionAndSortArrayWithManyRepeatedEntries.Student

object PartitionAndSortArrayWithManyRepeatedEntriesSpek : Spek({

    describe("groupByAge") {

        val data = listOfNotNull(
//            Pair(listOf(s(1)), listOf(listOf(s(1)))),
//            Pair(listOf(s(1), s(2)), listOf(listOf(s(1)), listOf(s(2)))),
//            Pair(listOf(s(1), s(2), s(1)), listOf(listOf(s(1), s(1)), listOf(s(2)))),
            Pair(listOf(s(1), s(2), s(1), s(2)), listOf(listOf(s(1), s(1)), listOf(s(2), s(2)))),
            null
        ).map {
            data(it.first.toMutableList(), it.second)
        }.toTypedArray()

        on("students %s", with = *data) { students, expected ->
            groupByAge(students)
            it("should group students as $expected") {
                assertThat(students).has(Condition<List<Student>>(
                    Predicate <List<Student>> { students ->
                        students.fold(Pair(mutableSetOf<List<Student>>(), mutableListOf<Student>())) { (groupedStudents, currentGroup), student ->
                            println(student)
                            if (currentGroup.isEmpty() || currentGroup.first().age == student.age) {
                                currentGroup.apply {
                                    add(student)
                                }
                            } else {
                                groupedStudents.add(currentGroup)
                                mutableListOf(student)
                            }.let {
                                Pair(groupedStudents, it)
                            }
                        }.let { (groupedStudents, currentGroup) ->
                            groupedStudents.add(currentGroup)
                            groupedStudents.toSet() == expected.toSet()
                        }
                    }, "groups $expected, but was $students"))
            }
        }
    }

})
