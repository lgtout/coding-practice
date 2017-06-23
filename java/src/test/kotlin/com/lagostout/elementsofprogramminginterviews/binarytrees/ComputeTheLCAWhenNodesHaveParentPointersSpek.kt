package com.lagostout.elementsofprogramminginterviews.binarytrees

import com.lagostout.common.BinaryTreeNode
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import kotlin.test.assertEquals

class ComputeTheLCAWhenNodesHaveParentPointersSpek : Spek({
    given("two binary tree nodes with parent pointers") {
        (rawListData.map { toBinaryTree(it) }).forEach {
            (firstNode, secondNode, expected) ->
            on("computing their lowest common ancestor using brute force") {
                val lcaNode = computeTheLCAWhenNodesHaveParentPointers(
                        firstNode, secondNode)
                it("returns $expected") {
                    assertEquals(expected, lcaNode)
                }
            }
        }
    }
})

val rawListData: List<List<Any>> = listOf(
        listOf(0, 0, listOf(listOf(null, null, null, "A")), 0), // 1
        listOf(0, 1, listOf(listOf(null, 1, null, "A"), listOf(null, null, 0, "B")), 0), // 2
        listOf(0, 2, listOf(listOf(null, 1, null, "A"), listOf(2, null, 0, "B"),
                listOf(null, null, 1, "C")), 0), // 3
        listOf(1, 2, listOf(listOf(null, 1, null, "A"), listOf(2, null, 0, "B"),
                listOf(null, null, 1, "C")), 1), // 4
        listOf(2, 2, listOf(listOf(1, 2, null, "A"), listOf(null, null, 0, "B"),
                listOf(null, null, 0, "C")), 2), // 5
        listOf(1, 3, listOf(listOf(1, null, null, "A"), listOf(null, 2, 0, "B"),
                listOf(3, null, 1, "C"), listOf(null, null, 2, "D")), 1), // 6
        listOf(1, 2, listOf(listOf(1, 2, null, "A"), listOf(null, null, 0, "B"),
                listOf(null, null, 0, "C")), 0), // 7
        listOf(4, 6, listOf(listOf(1, 2, null, "A"), listOf(3, 4, 0, "B"), listOf(5, 6, 0, "C"),
                listOf(null, null, 1, "D"), listOf(null, null, 1, "E"),
                listOf(null, null, 2, "F"), listOf(null, null, 2, "G")), 0), // 8
        listOf(5, 8, listOf(listOf(1, 2, null, "A"), listOf(null, null, 0, "B"),
                listOf(3, 4, 0, "C"), listOf(null, 5, 2, "D"),
                listOf(null, 6, 2, "E"), listOf(null, null, 3, "F"),
                listOf(7, null, 4, "G"), listOf(8, null, 6, "H"),
                listOf(null, null, 7, "I")), 2), // 9
        listOf(4, 6, listOf(listOf(1, 2, null, "A"), listOf(3, 4, 0, "B"),
                listOf(null, null, 0, "C"), listOf(5, null, 1, "D"),
                listOf(null, null, 1, "E"), listOf(6, null, 3, "F"),
                listOf(null, null, 5, "G")), 1) // 10
)

data class Data(val firstNode:BinaryTreeNode<Char>,
                val secondNode:BinaryTreeNode<Char>,
                val expected:BinaryTreeNode<Char>)

fun toBinaryTree(rawTree: List<Any>): Data {
    val nodeTree: List<BinaryTreeNode<Char>> = mutableListOf()
    @Suppress("UNCHECKED_CAST")
    BinaryTreeNode.buildBinaryTree<Char>(
            rawTree[2] as List<List<Any>>, nodeTree)
    return Data(nodeTree[rawTree[0] as Int], nodeTree[rawTree[1] as Int],
            nodeTree[rawTree[3] as Int])
}

