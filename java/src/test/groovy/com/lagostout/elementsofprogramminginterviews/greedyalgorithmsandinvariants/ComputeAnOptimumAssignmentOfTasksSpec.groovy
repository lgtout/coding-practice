package com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants

import com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants.ComputeAnOptimumAssignmentOfTasks.Task
import com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants.ComputeAnOptimumAssignmentOfTasks.TaskPair
import org.apache.commons.math3.util.CombinatoricsUtils
import spock.lang.Specification
import spock.lang.Unroll

import static org.apache.commons.collections4.CollectionUtils.disjunction

class ComputeAnOptimumAssignmentOfTasksSpec extends Specification {

    @Unroll
    """computes an optimum assignment of tasks that minimizes
     the duration to complete all of them"""(List<Task> tasks) {

        given:
        def expected = computeOptimumTaskPairsByBruteForce(new ArrayList<>(tasks))

        when:
        def result = ComputeAnOptimumAssignmentOfTasks.compute(new ArrayList<>(tasks))

        then:
        if (expected != null)
            disjunction(result, expected) == []
        else
            result == expected

        where:
        tasks << [
                [1,2,3,4],
                [5,2,1,6,4,4],
                [5,2,1,6,4,4,2] // odd number of tasks
        ].collect {
            List durations ->
                durations.collect {
                    [duration:it] as Task
                }
        }

    }

    private static List<TaskPair> computeOptimumTaskPairsByBruteForce(List<Task> tasks) {
        if (tasks.size() % 2) return null
        def optimumTaskAssignment = null
        def optimumTaskAssignmentDuration = Integer.MAX_VALUE
        List<List<TaskPair>> combinations = taskPairCombinations(tasks)
        combinations.each {
            List<TaskPair> taskAssignment ->
                // Find the task pair of longest duration.
                TaskPair maxDurationTaskPairInAssignment = taskAssignment.inject {
                    TaskPair maxTaskPair, TaskPair taskPair ->
                        taskPair.duration > maxTaskPair.duration ?
                                taskPair : maxTaskPair
                }
                if (optimumTaskAssignment == null ||
                        maxDurationTaskPairInAssignment.duration
                        < optimumTaskAssignmentDuration) {
                    optimumTaskAssignment = taskAssignment
                    optimumTaskAssignmentDuration =
                            maxDurationTaskPairInAssignment.duration
                }
        }
        optimumTaskAssignment
    }

    private static List<List<TaskPair>> taskPairCombinations(List<Task> tasks) {
        def numberOfTasks = tasks.size()
        if (numberOfTasks == 0)
            return [[]]
        def assignments = []
        def task1 = tasks.removeAt(0)
        tasks.each { task2 ->
            def taskPair = [task1: task1, task2: task2] as TaskPair
            def subTasks = []
            subTasks.addAll(tasks)
            subTasks.remove(task2)
            taskPairCombinations(subTasks).each { subAssignment ->
                def assignment = [taskPair]
                assignment.addAll(subAssignment)
                assignments << assignment
            }
        }
        assignments
    }
}
