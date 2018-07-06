package com.lagostout.elementsofprogramminginterviews.sorting

/* Problem 14.7 page 251 */

object PartitionAndSortArrayWithManyRepeatedEntries {

    data class Student(val age: Int)

    // TODO Test!

    fun groupByAge(list: MutableList<Student>) {
        val ageToStudentCountMap = list.fold(mutableMapOf<Int, Int>()) {
                acc, (age) ->
            acc.apply { set(age, getOrDefault(age, 0) + 1) }
        }
        var groupStartIndex = 0
        for ((age, count) in ageToStudentCountMap) {
            ageToStudentCountMap[age] = groupStartIndex
            groupStartIndex += count
        }
        var count = 0
        var student = list[0]
        while (count < list.count()) {
            val sortedIndex = ageToStudentCountMap[student.age]!!
            ageToStudentCountMap.computeIfPresent(student.age) { _, value ->
                value.dec()
            }
            val nextStudent = list[sortedIndex]
            list[sortedIndex] = student
            student = nextStudent
            count += 1
        }
    }

    fun sortByAge(list: MutableList<Student>) {
        val ageToStudentCountMap = list.fold(sortedMapOf<Int, Int>()) {
                acc, (age) ->
            acc.apply { set(age, getOrDefault(age, 0) + 1) }
        }
        var groupStartIndex = 0
        for ((age, count) in ageToStudentCountMap) {
            ageToStudentCountMap[age] = groupStartIndex
            groupStartIndex += count
        }
        var count = 0
        var student = list[0]
        while (count < list.count()) {
            val sortedIndex = ageToStudentCountMap[student.age]!!
            ageToStudentCountMap.computeIfPresent(student.age) { _, value ->
                value.dec()
            }
            val nextStudent = list[sortedIndex]
            list[sortedIndex] = student
            student = nextStudent
            count += 1
        }
    }

}