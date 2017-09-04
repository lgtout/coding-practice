package com.lagostout.elementsofprogramminginterviews.graphs

data class Point<out T> (val column: Int, val row: Int, val value: T? = null)
