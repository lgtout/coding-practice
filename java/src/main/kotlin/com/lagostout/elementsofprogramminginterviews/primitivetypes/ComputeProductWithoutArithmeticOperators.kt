package com.lagostout.elementsofprogramminginterviews.primitivetypes

/* EP 5.5 page 51 */

/* We apply long multiplication to binary digits
 (https://www.mathsisfun.com/numbers/multiplication-long.html). */

fun productWithoutArithmeticOperators(left: Int, right: Int): Int {

    fun sum(left: Int, right: Int): Int {
        var result = 0
        var carryOver = 0
        var currentBitMask = 1
        var leftOrRight = left or right
        while (leftOrRight > 0) {
            val currentLeft = left and currentBitMask
            val currentRight = right and currentBitMask
            val currentSum = currentLeft xor currentRight xor carryOver
            carryOver = ((currentLeft and currentRight) or
                    (currentLeft and carryOver) or
                    (currentRight and carryOver)) shl 1
            result = result or currentSum
            currentBitMask = currentBitMask shl 1
            leftOrRight = leftOrRight shr 1
        }
        result = result or carryOver
        return result
    }

    var currentSummand = left
    val rightBitMask = 1
    var currentRight = right
    var product = 0
    while (currentRight > 0) {
        if (currentRight and rightBitMask == 1) {
            product = sum(product, currentSummand)
        }
        currentSummand = currentSummand shl 1
        currentRight = currentRight shr 1
    }
    return product

}