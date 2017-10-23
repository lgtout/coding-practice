package com.lagostout.elementsofprogramminginterviews.hashtables

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.data_driven.Data1
import com.lagostout.elementsofprogramminginterviews.hashtables.AverageOfTop3Scores.StudentScore
import com.lagostout.elementsofprogramminginterviews.hashtables.AverageOfTop3Scores.averageOfTop3Scores
import org.jetbrains.spek.data_driven.data

object AverageOfTop3ScoresSpek : Spek({
    describe("") {
        val data = listOf<Data1<List<StudentScore>, StudentScore?>?>(
                data(listOf(), null),
                data(listOf(StudentScore("a", 100)), null),
                data(listOf(StudentScore("a", 100), StudentScore("a", 80)), null),
                data(listOf(StudentScore("a", 100), StudentScore("a", 80),
                        StudentScore("a", 100)), null),
                data(listOf(StudentScore("a", 100), StudentScore("a", 80),
                        StudentScore("a", 30)), expected = StudentScore("a", 70)),
                data(listOf(StudentScore("a", 100), StudentScore("a", 50),
                        StudentScore("a", 30), StudentScore("a", 60)),
                        expected = StudentScore("a", 70)),
                // TODO Continue cases:
                // More than one student.
                // Improving top average score - same/different student.
//                data(listOf(StudentScore("a", 100),
//                        StudentScore("b", 50),
//                        StudentScore("a", 50),
//                        StudentScore("a", 30), StudentScore("a", 60)),
//                        expected = StudentScore("a", 70)),
                null).filterNotNull().toTypedArray()
    }
})