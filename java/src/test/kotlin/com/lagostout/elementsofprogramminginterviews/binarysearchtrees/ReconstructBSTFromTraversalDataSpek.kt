package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import com.lagostout.datastructures.BinaryTreeNode
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class ReconstructBSTFromPostorderTraversalDataSpek : Spek({
    describe("reconstructBSTFromPostorderTraversal") {
        testCases.forEach { (path) ->
            given("postorder path: $path") {
                it("returns a BST that has the same postorder path") {
                    val bst = reconstructBSTFromPostorderTraversal(path)
                    println(bst)
                    val bstPath = mutableListOf<Int>()
                    postOrderTraversal(bst, bstPath)
                    assertEquals(path, bstPath)
                }
            }
        }
    }
}) {
    companion object {
        data class TestCase(val path: List<Int> = emptyList())
        val testCases = run {
            listOf(
//                    TestCase(),
//                    TestCase(listOf(10)),
//                    TestCase(listOf(10,20)),
//                    TestCase(listOf(30,20)),
//                    TestCase(listOf(20,30)),
//                    TestCase(listOf(10,20,30)),
                    TestCase(listOf(30,20,10)),
//                    TestCase(listOf(10,30,20)),
//                    TestCase(listOf(20,10,30)),
//                    TestCase(listOf(20,30,10)),
                    null).filterNotNull()
        }
        fun postOrderTraversal(root: BinaryTreeNode<Int>?, path: MutableList<Int>) {
            if (root == null) return
            postOrderTraversal(root.left, path)
            postOrderTraversal(root.right, path)
            path.add(root.value)
        }
    }
}

class ReconstructBSTFromPreorderTraversalDataSpek : Spek({
    describe("reconstructBSTFromPreorderTraversal") {
        testCases.forEach { (path) ->
            given("preorder path: $path") {
                it("returns a BST that has the same preorder path") {
                    val bst = reconstructBSTFromPreorderTraversal(path)
                    val bstPath = mutableListOf<Int>()
                    preorderTraversal(bst, bstPath)
                    assertEquals(path, bstPath)
                }
            }
        }
    }
}) {
    companion object {
        data class TestCase(val path: List<Int> = emptyList())
        val testCases = run {
            listOf(
                    TestCase(),
                    null).filterNotNull()
        }
        fun preorderTraversal(root: BinaryTreeNode<Int>?, path: MutableList<Int>) {
            if (root == null) return
            path.add(root.value)
            preorderTraversal(root.left, path)
            preorderTraversal(root.right, path)
        }
    }
}
