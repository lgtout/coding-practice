package com.lagostout.elementsofprogramminginterviews.heaps

import java.util.*

/**
 * Problem 11.4 page 180
 */

object ComputeKClosestStars {

    data class Point(val x: Int, val y: Int, val z: Int) : Comparable<Point> {

        override fun compareTo(other: Point): Int {
            return (this - other).reduce()
        }

        operator fun minus(point: Point): Point {
            return Point(x - point.x, y - point.y, z - point.z)
        }

        fun reduce(): Int {
            return x + y + z
        }

    }

    fun computeKClosestStars(): List<Point> {
        val closestStars = PriorityQueue<Point>()

        return closestStars.toList()
    }

}
