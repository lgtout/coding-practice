package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

/* Problem 17.12.7 page 337 */

/* We interpret "maximum" in the problem to mean "longest". */

object MaximumAscendingSubsetOfPointsInAPlane {

    fun Pair<Int, Int>.ascendsTo(other: Pair<Int, Int>): Boolean {
        return other.first > this.first && other.second > this.second
    }

    fun selectLongest(all: List<List<Int>>): List<List<Int>> {
        return all.fold(mutableListOf()) { acc, curr ->
            when {
                acc.isEmpty() || curr.count() ==
                        acc.first().count() -> acc.apply { add(curr) }
                curr.count() > acc.first().count() -> mutableListOf(curr)
                else -> acc
            }
        }
    }

    fun computeBottomUpWithMemoization(points: List<Pair<Int, Int>>): List<List<Int>> {

        if (points.count() <= 1) return listOf(points.indices.toList())

        // [(previousPointIndex, currentPointIndex) : [[ascendingPointIndex]]]
        val cache = mutableMapOf<Pair<Int, Int>, List<List<Int>>>()

        points.indices.reversed().forEach { currentPointIndex ->
            val currentPoint = points[currentPointIndex]
            val nextPointIndex = currentPointIndex + 1
            (0 until currentPointIndex).forEach { previousPointIndex ->
                val previousPoint = points[previousPointIndex]
                val ascendingSubsetsWhenIncludingCurrentPoint =
                        if (previousPoint.ascendsTo(currentPoint)) {
                            (cache[Pair(currentPointIndex, nextPointIndex)]
                                    ?: listOf(emptyList())).map {
                                listOf(currentPointIndex) + it
                            }
                        } else listOf(emptyList())
                val ascendingSubsetsWhenExcludingCurrentPoint =
                        cache[Pair(previousPointIndex, nextPointIndex)]
                                ?: listOf(emptyList())
                val ascendingSubsets = selectLongest(
                    ascendingSubsetsWhenIncludingCurrentPoint +
                            ascendingSubsetsWhenExcludingCurrentPoint)
                cache[Pair(previousPointIndex, currentPointIndex)] = ascendingSubsets.distinct()
            }
        }

        return selectLongest((0 until points.count() - 1).flatMap { startIndex ->
            cache[Pair(startIndex, startIndex + 1)]!!.map {
                listOf(startIndex) + it
            }
        })

    }

}