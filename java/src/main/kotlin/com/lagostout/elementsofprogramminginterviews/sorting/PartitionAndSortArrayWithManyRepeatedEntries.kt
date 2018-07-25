package com.lagostout.elementsofprogramminginterviews.sorting

/* Problem 14.7 page 251 */

object PartitionAndSortArrayWithManyRepeatedEntries {

    data class Student(val age: Int, val name: String = "")

    /* For each age we store the number of students of that age that have already
    been placed in their final position, and the index at which to place the next
    student of that age.  Storing the number of students that have already been
    placed allows us to know whether to skip the current student - because it was
    already sorted earlier.  Without storing this, we would have to maintain
    additional information, increasing additional memory required from O(m) (in
    the number of distinct ages), to O(n + m) (in the number of students and
    distinct ages) */

    fun groupByAge(students: MutableList<Student>) {
        if (students.isEmpty()) return
        val ageToStudentCountMap = students.groupingBy { it.age }
                .eachCount().toMutableMap()
        var groupStartIndex = 0
        for ((age, count) in ageToStudentCountMap) {
            ageToStudentCountMap[age] = groupStartIndex
            groupStartIndex += count
        }
        var count = 0
        var currentIndex = 0
        while (count < students.count()) {
            val student = students[currentIndex]
            val sortedIndex = ageToStudentCountMap[student.age]!!
            ageToStudentCountMap.computeIfPresent(student.age) { _, value ->
                value.inc()
            }
            if (sortedIndex == currentIndex) {
                currentIndex += 1
            } else {
                val temp = students[sortedIndex]
                students[sortedIndex] = student
                students[currentIndex] = temp
            }
            count += 1
        }
    }

    /* This isn't a stable sort.  To make it stable, we would need O(n)
    space for the destination array, and O(m) space for the count array,
    for a total of O(n + m) space (where n is the number of entries and
    m is the number of distinct entries). Time complexity is O(n + n log m).
    O(n log m) comes from updating the student count in the sorted map n
    times.  We can simplify this to O(n) by realizing that m is bounded by a
    constant - maximum lifespan. So time complexity is more like
    O(n + n log 125) (125 being maximum age), which is effectively O(n). */

    fun unstableSortByAge(students: MutableList<Student>) {
        val ageToStudentCountMap = students.fold(sortedMapOf<Int, Int>()) {
                acc, (age) -> acc.apply { set(age, getOrDefault(age, 0) + 1) }
        }
        var groupStartIndex = 0
        for ((age, count) in ageToStudentCountMap) {
            ageToStudentCountMap[age] = groupStartIndex
            groupStartIndex += count
        }
        var count = 0
        var currentIndex = 0
        while (count < students.count()) {
            val student = students[currentIndex]
            val sortedIndex = ageToStudentCountMap[student.age]!!
            ageToStudentCountMap.computeIfPresent(student.age) { _, value ->
                value.inc()
            }
            if (sortedIndex == currentIndex) {
                currentIndex += 1
            } else {
                val temp = students[sortedIndex]
                students[sortedIndex] = student
                students[currentIndex] = temp
            }
            count += 1
        }
    }

    /* This is a stable sort.  We store counts in the map and allocate
    additional O(n) space for the result (sorted) array.  But we could
    also store the students in the map, giving the same space complexity. */

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
        for (student in students) {
            val sortedIndex = ageToStudentCountMap[student.age]!!
            ageToStudentCountMap.computeIfPresent(student.age) { _, value ->
                value.inc()
            }
            sortedStudents[sortedIndex] = student
        }
        return sortedStudents.filterNotNull()
    }

}