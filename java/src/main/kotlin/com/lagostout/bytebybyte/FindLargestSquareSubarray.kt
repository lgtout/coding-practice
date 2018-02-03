package com.lagostout.bytebybyte

/*
Given a 2D boolean array, find the largest square subarray of true values.
The return value should be the side length of the largest square subarray
subarray.
Eg.
squareSubmatrix([false, true, false, false]) = 2
                [true,  TRUE, TRUE,  true ]
                [false, TRUE, TRUE,  false]
(the solution is the submatrix in all caps)
*/

object FindLargestSquareSubarray {
    fun computeWithRecursion() {}
    fun computeWithRecursionAndMemoization(array: Array<Int>) {}
    data class Square(val x: Int, val y: Int)
    fun computeWithRecursionAndMemoization(array: Array<Int>, dp: Square) {}
    fun computeWithMemoizationBottomUp() {}

//    fun computeWithRecursion() {}
//    fun computeWithRecursionAndMemoization() {}
//    fun computeWithRecursionAndMemoization() {}
//    fun computeWithMemoizationBottomUp() {}
}