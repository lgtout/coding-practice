package com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants

import com.lagostout.common.MultilineShortPrefixRecursiveToStringStyle
import org.apache.commons.lang3.builder.ReflectionToStringBuilder

/**
 * Problem 18.1 p339
 */
class ComputeAnOptimumAssignmentOfTasks {

    static class Task implements Comparable<Task> {

        int duration

        @Override
        int compareTo(Task o) {
            duration <=> o.duration
        }

        @Override
        String toString() {
            ReflectionToStringBuilder.toString(
                    this, new MultilineShortPrefixRecursiveToStringStyle())
        }

    }

    static class TaskPair {

        Task task1
        Task task2

        int getDuration() {
            (task1 == null ? 0 : task1.duration) +
                    (task2 == null ? 0 : task2.duration)
        }

        @Override
        String toString() {
            ReflectionToStringBuilder.toString(
                    this, new MultilineShortPrefixRecursiveToStringStyle())
        }

        boolean equals(o) {
            if (this.is(o)) return true
            if (!(o instanceof TaskPair)) return false
            TaskPair otherTaskPair = (TaskPair) o
            return (task1 == otherTaskPair.task1  && task2 == otherTaskPair.task2) ||
                    (task1 == otherTaskPair.task2 && task2 == otherTaskPair.task1)
        }

        int hashCode() {
            int result
            result = task1.hashCode() + task2.hashCode()
            return result
        }
    }

    static List<TaskPair> compute(List<Task> tasks) {
        println tasks
        if (tasks.size() % 2) return null
        def sortedTasks = tasks.sort()
        def assignment = []
        while (!sortedTasks.isEmpty()) {
            assignment << ([
                    task1:sortedTasks.remove(0),
                    task2:sortedTasks.remove(sortedTasks.size() - 1)] as TaskPair)
        }
        assignment
    }

}
