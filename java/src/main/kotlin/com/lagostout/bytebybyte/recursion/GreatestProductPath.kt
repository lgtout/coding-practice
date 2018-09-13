package com.lagostout.bytebybyte.recursion

fun greatestProduct(matrix: Array<Array<Int>>): Int? {
    if (matrix.isEmpty() || matrix[0].isEmpty()) return null
    val lastIndex = matrix.lastIndex
    fun compute(x: Int, y: Int, product: Int): Int? {
        return if (x > lastIndex || y > lastIndex) product
        else {
            val productIncludingCurrentValue = matrix[x][y] * product
            val productByGoingRight = compute(x + 1, y, productIncludingCurrentValue) ?: product
            val productByGoingDown = compute(x, y + 1, productIncludingCurrentValue) ?: product
            maxOf(productByGoingRight, productByGoingDown)
        }
    }
    return compute(0, 0, 1)
}

typealias Coordinate = Pair<Int, Int> /* (row, col) */

fun greatestProductPath(matrix: Array<Array<Int>>): List<Coordinate> {
    if (matrix.isEmpty() || matrix[0].isEmpty()) return emptyList()
    val lastIndex = matrix.lastIndex
    fun compute(row: Int, col: Int, product: Int): Pair<Int, List<Coordinate>> {
        return if (col > lastIndex || row > lastIndex) Pair(product, emptyList())
        else {
            val currentValue = matrix[row][col]
            val productIncludingCurrentValue = currentValue * product
            val productByGoingRight = compute(row, col + 1, productIncludingCurrentValue)
            val productByGoingDown = compute(row + 1, col, productIncludingCurrentValue)
            listOf(productByGoingRight, productByGoingDown)
                    .maxBy { it.first }!!
                    .let {
                        it.copy(second = listOf(Coordinate(row, col)) + it.second)
                    }
        }
    }
    return compute(0, 0, 1).second
}