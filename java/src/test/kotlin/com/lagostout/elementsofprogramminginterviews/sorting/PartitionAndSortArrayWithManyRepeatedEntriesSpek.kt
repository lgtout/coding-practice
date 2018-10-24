package com.lagostout.elementsofprogramminginterviews.sorting

import com.lagostout.common.nextInt
import com.lagostout.common.reproducibleRdg
import com.lagostout.elementsofprogramminginterviews.sorting.PartitionAndSortArrayWithManyRepeatedEntries.Student
import com.lagostout.elementsofprogramminginterviews.sorting.PartitionAndSortArrayWithManyRepeatedEntries.groupByAge
import com.lagostout.elementsofprogramminginterviews.sorting.PartitionAndSortArrayWithManyRepeatedEntries.stableSortByAge
import com.lagostout.elementsofprogramminginterviews.sorting.PartitionAndSortArrayWithManyRepeatedEntries.unstableSortByAge
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object PartitionAndSortArrayWithManyRepeatedEntriesSpek : Spek({

    fun groupByAgeInLinearSpace(students: List<Student>) =
            students.groupBy { it.age }.values.map { it.toSet() }

    val randomData = run {
        val caseCount = 100
        val studentCountRange = Pair(0, 5)
        val studentAgeRange = Pair(1, 5)
        val random = reproducibleRdg()
        (0 until caseCount).map {
            val studentCount = random.nextInt(studentCountRange)
            (0 until studentCount).map {
                Student(random.nextInt(studentAgeRange), "s$it")
            }
        }.map {
            data(it.toMutableList(), groupByAgeInLinearSpace(it),
                it.sortedBy { it.age })
        }.toTypedArray()
    }

    val data = listOfNotNull (
        listOf(1),
        listOf(1, 2),
        listOf(1, 2, 1),
        listOf(1, 2, 1, 2),
        listOf(4, 6, 7, 7, 6),
        listOf(4, 6, 6, 7, 7),
        listOf(7, 7),
        listOf(3, 5, 5, 4),
        null
    ).map {
        it.mapIndexed { index, age ->
            Student(age, "s$index")
        }.let {
            data(it.toMutableList(), groupByAgeInLinearSpace(it),
                it.sortedBy { it.age })
        }
    }.toTypedArray()

    describe("unstableSortByAge") {
//        on("students %s", with = *randomData) { students, _, expected ->
        on("students %s", with = *data) { students, _, expected ->
            unstableSortByAge(students)
            it("should group students as $expected") {
                assertThat(students).apply {
                    if (expected.isEmpty()) {
                        isEmpty()
                    } else {
                        usingElementComparatorOnFields("age").isEqualTo(expected)
                    }
                }
            }
        }
    }

    describe("stableSortByAge") {
        on("students %s", with = *randomData) { students, _, expected ->
//        on("students %s", with = *data) { students, _, expected ->
            val sortedStudents = stableSortByAge(students)
            it("should group students as $expected") {
                assertThat(sortedStudents).apply {
                    if (expected.isEmpty()) {
                        isEmpty()
                    } else {
                        sortedStudents.map { it.age }
                        containsExactlyElementsOf(expected)
                    }
                }
            }
        }
    }

    /* We simplify testing by introducing the constraint that no two students
    may have the same name and age.  This allows us to use sets for comparison. */

    describe("groupByAge") {
//        on("students %s", with = *data) { students, expected, _ ->
        on("students %s", with = *randomData) { students, expected, _ ->
            groupByAge(students)
            it("should group students as any arrangement of $expected") {
                assertThat(students).apply {
                    if (expected.isEmpty()) {
                        isEmpty()
                    } else {
                        val groupedStudents = students.fold(
                            Pair(mutableSetOf<Set<Student>>(), mutableSetOf<Student>())) {
                                (groupedStudents, currentGroup), student ->
                            Pair(groupedStudents,
                                if (currentGroup.isEmpty() || currentGroup.first().age == student.age) {
                                    currentGroup.apply { add(student) }
                                } else {
                                    groupedStudents.add(currentGroup)
                                    mutableSetOf(student)
                                })
                        }.let { (groupedStudents, currentGroup) ->
                            groupedStudents.apply {
                                add(currentGroup)
                            }
                        }.toSet()
                        assertThat(groupedStudents).isEqualTo(expected.toSet())
                    }
                }
            }
        }
    }

})
