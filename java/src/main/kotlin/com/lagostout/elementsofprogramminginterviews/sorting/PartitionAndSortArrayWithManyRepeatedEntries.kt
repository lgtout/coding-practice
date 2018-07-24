package com.lagostout.elementsofprogramminginterviews.sorting

/* Problem 14.7 page 251 */

object PartitionAndSortArrayWithManyRepeatedEntries {

    data class Student(val age: Int, val name: String = "")

    fun groupByAge(students: MutableList<Student>) {
        val ageToStudentCountMap = students.fold(mutableMapOf<Int, Int>()) {
                acc, (age) ->
            acc.apply { merge(age, 1) { t, u -> t + 1 } }
        }
        println(ageToStudentCountMap)
        var groupStartIndex = 0
        for ((age, count) in ageToStudentCountMap) {
            ageToStudentCountMap[age] = groupStartIndex
            groupStartIndex += count
        }
        println(ageToStudentCountMap)
        var studentIndex = 0
        while (studentIndex < students.lastIndex) {
            val student = students[studentIndex]
            val sortedIndex = ageToStudentCountMap[student.age]!!
            ageToStudentCountMap.compute(student.age) { _, value ->
                value!!.inc()
            }
            if (sortedIndex == studentIndex) {
                studentIndex += 1
            } else {
                students[studentIndex] = students[sortedIndex]
                students[sortedIndex] = student
            }
        }
    }

    /* This isn't a stable sort.  To make it stable, we would need
     O(n) space for the destination array, and O(m) space for the
     count array, for a total of O(n + m) space. (Where n is the
     number of entries and m is the number of distinct entries). */
    fun sortByAge(students: MutableList<Student>) {
        val ageToStudentCountMap = students.fold(sortedMapOf<Int, Int>()) {
                acc, (age) ->
            acc.apply { set(age, getOrDefault(age, 0) + 1) }
        }
        var groupStartIndex = 0
        for ((age, count) in ageToStudentCountMap) {
            ageToStudentCountMap[age] = groupStartIndex
            groupStartIndex += count
        }
        var count = 0
        var student = students[0]
        while (count < students.count()) {
            val sortedIndex = ageToStudentCountMap[student.age]!!
            ageToStudentCountMap.computeIfPresent(student.age) { _, value ->
                value.dec()
            }
            val nextStudent = students[sortedIndex]
            students[sortedIndex] = student
            student = nextStudent
            count += 1
        }
    }

    /* A stable sort.  We store counts in the map and allocate additional
    O(n) space for the result array.  But we could also store the students
    in the map.  This would result in the same space complexity. */
    fun stableSortByAge(students: List<Student>): List<Student> {
        val ageToStudentCountMap = students.fold(sortedMapOf<Int, Int>()) {
                acc, (age) ->
            acc.apply { set(age, getOrDefault(age, 0) + 1) }
        }
        var groupStartIndex = 0
        for ((age, count) in ageToStudentCountMap) {
            ageToStudentCountMap[age] = groupStartIndex
            groupStartIndex += count
        }
        val sortedStudents = MutableList<Student?>(students.count()) { null }
        for (student in students.asReversed()) {
            val sortedIndex = ageToStudentCountMap[student.age]!!
            ageToStudentCountMap.computeIfPresent(student.age) { _, value ->
                value.dec()
            }
            sortedStudents[sortedIndex] = student
        }
        return sortedStudents.filterNotNull()
    }

}