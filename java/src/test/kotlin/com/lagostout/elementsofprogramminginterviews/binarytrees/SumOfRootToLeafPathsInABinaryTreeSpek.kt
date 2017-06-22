package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.common.BinaryTreeNode
import org.apache.commons.math3.random.RandomDataGenerator
import org.jetbrains.spek.api.Spek

class SumOfRootToLeafPathsInABinaryTreeSpek : Spek({
    given("a binary tree in which each node contains a binary digit") {
        println(randomData(1, numberCountRange = 3..3, numberRange = 1..3))
//        randomData().forEach {
//            on("computing the sum of root to leaf paths") {
//                it("returns ") {
//
//                }
//            }
//        }
    }
})

private data class DataRow(val root: BinaryTreeNode<Boolean>, val expected: Int)

/**
 * It's assumed that all numbers are positive.
 * The root is always the 1-bit in the most significant position
 * of each number.
 * It's expected that numberRange will always contain at least as
 * many possible numbers as numberCountRange.
 */

// TODO Numbers cannot be prefixes of other numbers, otherwise they're lost in the tree.
// Would it be easier to use static test data instead?

private fun randomData(dataCount: Int = 5,
                       numberCountRange: IntRange = 1..5,
                       numberRange: IntRange = 1..9): List<DataRow> {
    if (numberCountRange.last > numberRange.count()) {
        throw IllegalArgumentException("The maximum number " +
                "of numbers that can be randomly picked must be " +
                "less than or equal to the range of numbers that " +
                "can be picked from")
    }
    val random = RandomDataGenerator()
    random.reSeed(1)
    val data: MutableList<DataRow> = mutableListOf()
    1.rangeTo(dataCount).forEach {
        val numberCount = random.nextInt(
                numberCountRange.first, numberCountRange.last)
        val root = BinaryTreeNode(true)
        var sum = 0
        var numbers: MutableSet<Int> = mutableSetOf()
        while (numbers.size < numberCount) {
            numbers.add(random.nextInt(
                    numberRange.first, numberRange.last))
        }
//        println("numbers $numbers")
        numbers.forEach {
            var number = it
//            println("number $number")
            sum += number
            var currNode = root
            var mask = 1
            // Align mask with the most significant bit.
            while (mask < number) {
                mask = mask shl 1
            }
            // Make sure we didn't overshoot.
            mask = if (mask > number) mask shr 1 else mask
            // We've already got most significant 1 bit as the root,
            // so let's start from the second most significant bit.
            mask = mask shr 1
            while (mask > 0) {
                val bit = (number and mask) != 0
                // 0/false bit is always the left node and 1/true
                // bit the right one
                if (!bit) {
                    if (currNode.left == null) {
                        currNode.left = BinaryTreeNode(bit)
                    }
                    currNode = currNode.left
                } else {
                    if (currNode.right == null) {
                        currNode.right = BinaryTreeNode(bit)
                    }
                    currNode = currNode.right
                }
                mask = mask ushr 1
            }
        }
        println(numbers)
        data.add(DataRow(root, sum))
    }
    return data
}