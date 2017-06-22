package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.common.BinaryTreeNode
import org.jetbrains.spek.api.Spek
import kotlin.test.assertEquals

class SumOfRootToLeafPathsInABinaryTreeSpek : Spek({
    staticData.forEach {
        given("a binary tree in which each node contains a binary digit") {
            on("computing the sum of root to leaf paths") {
                it("returns ${it.expected}") {
                    assertEquals(it.expected, sumOfRootToLeafPaths(it.root))
                }
            }
        }
    }
})

private data class DataRow(val root: BinaryTreeNode<Boolean>, val expected: Int)

/**
 * It's assumed that all numbers are positive.
 * The root is always the 1-bit in the most significant position
 * of each number.
 * Numbers cannot be prefixes of other numbers, otherwise they'll
 * be lost in the tree.
 */

private val staticData: List<DataRow>
    get() {
        return listOf(listOf(0b110, 0b111, 0b101),
                listOf(0b10, 0b1100, 0b1101, 0b111)).map {
            numbers: List<Int> ->
            val root = BinaryTreeNode(true)
            var sum = 0
            numbers.forEach {
                number :Int ->
//                println("number $number")
                sum += number
                var shiftedNumber = number
                val bits: MutableList<Boolean> = mutableListOf()
                while (shiftedNumber > 1) {
//                println("shiftedNumber")
                    bits.add(shiftedNumber and 1 == 1)
                    shiftedNumber = shiftedNumber ushr 1
                }
                var currNode = root
//            println("bits ${bits.reversed()}")
                bits.reversed().forEach {
                    bit: Boolean ->
                    when (bit) {
                        false -> {
                            if (currNode.left == null) {
                                currNode.left = BinaryTreeNode(bit)
                            }
                            currNode = currNode.left
                        }
                        else -> {
                            if (currNode.right == null) {
                                currNode.right = BinaryTreeNode(bit)
                            }
                            currNode = currNode.right
                        }
                    }
                }
            }
            DataRow(root, sum)
        }
    }