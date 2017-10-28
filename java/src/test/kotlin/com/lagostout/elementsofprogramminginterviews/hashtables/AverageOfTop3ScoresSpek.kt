package com.lagostout.elementsofprogramminginterviews.hashtables

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.data_driven.Data1
import com.lagostout.elementsofprogramminginterviews.hashtables.AverageOfTop3Scores.StudentScore
import com.lagostout.elementsofprogramminginterviews.hashtables.AverageOfTop3Scores.averageOfTop3Scores
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object AverageOfTop3ScoresSpek : Spek({
    describe("") {
        val data = listOf<Data1<List<StudentScore>, StudentScore?>?>(
                data(listOf(), null),
                // 1 student
                data(listOf(StudentScore("a", 100)), null),
                data(listOf(StudentScore("a", 100), StudentScore("a", 80)), null),
                data(listOf(StudentScore("a", 100), StudentScore("a", 80),
                        StudentScore("a", 30)), expected = StudentScore("a", 70)),
                data(listOf(StudentScore("a", 100), StudentScore("a", 50),
                        StudentScore("a", 30), StudentScore("a", 60)),
                        expected = StudentScore("a", 70)),
                // 2 students
                data(listOf(StudentScore("a", 100),
                        StudentScore("b", 50),
                        StudentScore("a", 50),
                        StudentScore("a", 30), StudentScore("a", 60)),
                        expected = StudentScore("a", 70)),
                data(listOf(StudentScore("a", 100),
                        StudentScore("b", 50),
                        StudentScore("a", 50),
                        StudentScore("b", 50),
                        StudentScore("a", 30),
                        StudentScore("a", 60),
                        StudentScore("b", 50)),
                        expected = StudentScore("a", 70)),
                data(listOf(StudentScore("a", 100),
                        StudentScore("b", 90),
                        StudentScore("a", 50),
                        StudentScore("b", 80),
                        StudentScore("a", 30),
                        StudentScore("a", 60),
                        StudentScore("b", 82)),
                        expected = StudentScore("b", 84)),
                data(listOf(StudentScore("a", 100),
                        StudentScore("b", 90),
                        StudentScore("a", 90),
                        StudentScore("b", 100),
                        StudentScore("a", 30),
                        StudentScore("a", 80),
                        StudentScore("b", 80)),
                        expected = StudentScore("b", 90)),
                null).filterNotNull().toTypedArray()
        on("scores: %s", with = *data) { scores, expected ->
            it("returns $expected") {
                assertThat(averageOfTop3Scores(scores)).isEqualTo(expected)
            }
        }
    }
})