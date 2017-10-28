package com.lagostout.elementsofprogramminginterviews.hashtables

import java.util.*

/**
 * Problem 13.11 page 230
 */
object AverageOfTop3Scores {
    data class StudentScore(val id: String, val score: Int) : Comparable<StudentScore> {
        override fun compareTo(other: StudentScore): Int {
            return score - other.score
        }
    }
    private val PriorityQueue<Int>.averageScore
        get(): Int? {
            return if (size == 3) sum()/3 else null
        }
    fun averageOfTop3Scores(scores: List<StudentScore>): StudentScore? {
        var topStudentAverageScore: StudentScore? = null
        val studentIdToTop3ScoresMap = mutableMapOf<String, PriorityQueue<Int>>()
        scores.forEach { (id, score) ->
            studentIdToTop3ScoresMap.getOrPut(id, { PriorityQueue() }).let {
                if (it.size == 3) it.remove()
                it.add(score)
                it.averageScore?.let { averageScore ->
                    val studentAverageScore = StudentScore(id, averageScore)
                    topStudentAverageScore = topStudentAverageScore?.let {
                        if (it.score <= averageScore) studentAverageScore
                        else it
                    } ?: studentAverageScore
                }
            }
        }
        return topStudentAverageScore
    }
}
