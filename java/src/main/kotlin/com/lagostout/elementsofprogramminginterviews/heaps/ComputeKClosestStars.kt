package com.lagostout.elementsofprogramminginterviews.heaps

import java.util.*

/**
 * Problem 11.4 page 180
 */
object ComputeKClosestStars {

    data class Point(val x: Int, val y: Int, val z: Int) : Comparable<Point> {

        override fun compareTo(other: Point): Int {
            val d1 = distance()
            val d2 = other.distance()
            return if (d1 == d2) 0 else if (d1 < d2) -1 else  1
        }

        private fun distance(): Double {
            return listOf(x, y, z).map {
                Math.pow(it.toDouble(), 2.0)
            }.sum().let {
                Math.sqrt(it)
            }
        }

    }

    fun computeKClosestStars(stars: List<Point>, k: Int): List<Point> {
        val closestStars = PriorityQueue<Point>({
            point1, point2 ->
            point2.compareTo(point1)
        })
        stars.forEach {
            if (closestStars.size < k) {
                closestStars.add(it)
            } else if (closestStars.peek() > it) {
                closestStars.poll()
                closestStars.add(it)
            }
        }
        return closestStars.toList()
    }

}
