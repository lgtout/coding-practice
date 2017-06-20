package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.common.BinaryTreeNode
import org.apache.commons.math3.random.RandomDataGenerator
import org.jetbrains.spek.api.Spek

class SumOfRootToLeafPathsInABinaryTreeSpek : Spek({
    given("a binary tree in which each node contains a binary digit") {
        println(randomData(3))
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
 */
private fun randomData(dataCount: Int = 5,
                       numberCountRange: IntRange = 1..5,
                       numberRange: IntRange = 1..9): List<DataRow> {
    val random = RandomDataGenerator()
    random.reSeed(1)
    val data: MutableList<DataRow> = mutableListOf()
    1.rangeTo(dataCount).forEach {
        val numberCount = random.nextInt(
                numberCountRange.first, numberCountRange.last)
        val root = BinaryTreeNode(true)
        var sum = 0
        var numbers: MutableList<Int> = mutableListOf()
        1.rangeTo(numberCount) .forEach {
            var number = random.nextInt(
                    numberRange.first, numberRange.last)
            numbers.add(number)
        }
        numbers.forEach {
            var number = it
            numbers.add(number)
            sum += number
            var currNode = root
            var mask = 1
            // Root will always be most significant 1 bit,
            // so let's start from the second most significant bit.
            number = number ushr 1
            // Align mask with the most significant bit.
            while (mask < number) {
                mask shl 1
            }
            mask = if (mask > number) mask shr 1 else mask
            while (mask > 0) {
                val bit = (number and mask) == 1
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