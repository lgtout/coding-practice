package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.common.BinaryTreeNode
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class SumOfRootToLeafPathsInABinaryTreeSpek : Spek({
    data class TestCase(val numbers: List<Int>) {

        val tree = tree(numbers)
        val expected = numbers.sum()
        val label = "computes the sum of the root to leaf paths " +
                "of a binary tree $expected"

        /**
         * It's assumed that all numbers are positive.
         * The root is always the 1-bit in the most significant position
         * of each number.
         * Numbers cannot be prefixes of other numbers, otherwise they'll
         * be lost in the tree.
         */
        private fun tree(numbers: List<Int>): BinaryTreeNode<Boolean> {
            val root = BinaryTreeNode(true)
            numbers.forEach {
                number :Int ->
                //            println("number $number")
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
            return root
        }
    }
    describe("sumOfRootToLeafPaths") {
        listOf(
                TestCase(listOf(0b10, 0b11))
                ,TestCase(listOf(0b110, 0b111, 0b101))
                ,TestCase(listOf(0b10, 0b1100, 0b1101, 0b111))
        ).forEach {
            given("a binary tree where nodes represent bits " +
                    "of the numbers ${it.numbers}") {
                it(it.label) {
                    assertEquals(it.expected, sumOfRootToLeafPaths(it.tree))
                }
            }
        }
    }
})



