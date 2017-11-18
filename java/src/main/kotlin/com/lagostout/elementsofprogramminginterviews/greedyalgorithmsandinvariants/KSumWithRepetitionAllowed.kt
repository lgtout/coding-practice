package com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants

/**
 * Problem 18.4.3 page 346
 */

fun canPickKNumbersThatAddUpToSumWithRepetitionAllowed(
        list: List<Int>, k: Int, sum: Int): Boolean =
        findCombinationsOfKNumbersThatAddUpToSumWithRepetitionAllowed(list, k, sum).isNotEmpty()

fun findCombinationsOfKNumbersThatAddUpToSumWithRepetitionAllowed(
        list: List<Int>, k: Int, sum: Int): List<List<Int>> {
    if (list.isEmpty()) return emptyList()
    return list.sorted().flatMap { firstNumber ->
        findCombinationsOfTwoThatAddUpToSumWithRepetitionAllowed(list, sum - firstNumber)
                .map {
                    listOf(firstNumber, it.first, it.second)
                }
    }
}

// TODO Verify
fun permutationsWithRepetition(
        elements: List<Int>, repeatCount: Int = 1):
        List<List<Int>> {
    val permutations = listOf(elements)
    var currentRepeatCount = 1
    val elementToPermutationGroupStartIndex = elements.withIndex()
            .map {
                (index, i) ->
                i to index
            }.toMap().toMutableMap()
    while (currentRepeatCount < repeatCount) {
        val nextPermutations = mutableListOf<List<Int>>()
        elements.forEachIndexed { index, currentElement ->
            val permutationGroupStartIndex =
                    elementToPermutationGroupStartIndex[currentElement]!!
            var permutationCount = 0
            (permutationGroupStartIndex..permutations.lastIndex).forEach { index ->
                nextPermutations.add(listOf(currentElement) + permutations[index])
                permutationCount++
            }
            elementToPermutationGroupStartIndex[currentElement] = permutationCount +
                    (if (index == 0) 0
                    else elementToPermutationGroupStartIndex[elements[index-1]]!!)
        }
        currentRepeatCount++
    }
    return permutations
}