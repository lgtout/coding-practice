package com.lagostout.datastructures

import com.lagostout.common.BinaryTreeNode
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.*
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class BinarySearchTreeSpek : Spek({
    describe("BinarySearchTree") {

        val tree = BinarySearchTree<Int>()
        val key = 1

        xdescribe("initial state") {
            it("should have null root") {
                assertNull(tree.root)
            }
        }

        // TODO
        // Redo by comparing result trees with expected trees.
        // Use DFS to compare.  Can also just use search paths
        // to the inserted node.
        xdescribe("inserting a key") {
            xcontext("tree is empty") {
                tree.insert(key)
                it("has a non-null root") {
                    assertNotNull(tree.root)
                }
                it("contains the inserted key") {
                    assertTrue(contains(tree.root, key))
                }
                it("contains only one key") {
                    assertEquals(size(tree.root), 1)
                }
            }
            context("tree contains a node before insertion") {
                tree.insert(key)
                val key2 = 2
                tree.insert(key2)
//                println(tree.root)
                xit("contains the inserted key") {
                    assertTrue(contains(tree.root, key2))
                }
                it("contains the existing key") {
//                    println(tree.root)
                    assertTrue(contains(tree.root, key))
                }
                xit("maintains the binary search tree property") {
//                    println(tree.root)
                    assertTrue(maintainsBinarySearchTreeProperty(tree.root!!))
                }
            }
        }

        describe("finding a key") {
            xcontext("tree is empty") {
                it("returns null") {
                    val keyToFind = 3
                    assertNull(tree.find(keyToFind))
                }
            }
            context("tree isn't empty") {
                context("key is in tree") {
                    val nodes = BinaryTreeNode.buildBinaryTree(listOf(
                       RawBinaryTreeNode(leftChildIndex = 1, rightChildIndex = 2, value = 20),
                       RawBinaryTreeNode(leftChildIndex = 3, rightChildIndex = 4, value = 10),
                       RawBinaryTreeNode(leftChildIndex = 5, rightChildIndex = 6, value = 30),
                       RawBinaryTreeNode(value = 5),
                       RawBinaryTreeNode(value = 15),
                       RawBinaryTreeNode(value = 25),
                       RawBinaryTreeNode(value = 35)
                    )).apply {
                        tree.root = left
                    }.right
                    context("key sought is root") {
                        it("returns the root") {
                            assertEquals(nodes[0], tree.find(20)?.first)
                        }
                    }
                    context("path to key sought involves direction changes") {
                        context("path goes left-right") {
                            it("returns the node for that key") {
                                assertEquals(nodes[4], tree.find(15)?.first)
                            }
                        }
                        context("path goes right-left") {
                            it("returns the node for that key") {
                                assertEquals(nodes[5], tree.find(25)?.first)
                            }
                        }
                    }
                }
                xcontext("key isn't in tree") {
                    var nodes: List<BinaryTreeNode<Int>> = emptyList()
                    beforeEachTest {
                        BinaryTreeNode.buildBinaryTree(listOf(
                                RawBinaryTreeNode(leftChildIndex = 1, value = 4),
                                RawBinaryTreeNode(value = 1)
                        )).apply {
                            tree.root = left
                            nodes = right
                        }
                    }
                    it("returns null") {
                        val keyToFind = 11
                        assertNull(tree.find(keyToFind)?.first)
                    }
                }
            }
        }

    }
}) {
    companion object {

        fun <T : Comparable<T>> maintainsBinarySearchTreeProperty(
                root: BinaryTreeNode<T>): Boolean {

            return false
        }

        fun <T : Comparable<T>> contains(
                root: BinaryTreeNode<T>?,
                vararg keys: T): Boolean {
            var found = false
            if (root == null) return found
            data class Frame(val node: BinaryTreeNode<T>, var step: Int = 0)
            run {
                keys.forEach forEachKey@ { key ->
                    val stack = LinkedList<Frame>()
                    stack.add(Frame(root))
                    whileStackIsNotEmpty@ while (stack.isNotEmpty()) {
                        val frame = stack.peek()
                        val (node, step) = frame
                        when (step) {
                            0 -> {
                                found = node.value == key
                                if (found) break@whileStackIsNotEmpty
                            }
                            1 -> {
                                node.left?.apply {
                                    stack.push(Frame(left))
                                }
                            }
                            2 -> {
                                node.right?.apply {
                                    stack.push(Frame(right))
                                }
                            }
                            else -> {
                                stack.pop()
                            }
                        }
                        frame.step++
                    }
                    if (!found) return@forEachKey
                }
            }
            return found
        }

        fun <T : Comparable<T>> size(root: BinaryTreeNode<T>?): Int {
            var count = 0
            if (root == null) return count
            val queue = LinkedList<List<BinaryTreeNode<T>>>()
            queue.push(listOf(root))
            var limit = 0
            while (queue.isNotEmpty()) {
                limit += 1
                if (limit == 10) break
                println(queue)
                val level = queue.poll()
                count += level.size
                val nextLevel = level.fold(mutableListOf<BinaryTreeNode<T>>()) {
                    acc, node ->
                    val childNodes = listOf(node.left, node.right).filterNotNull()
                    if (childNodes.isNotEmpty()) acc.addAll(childNodes)
                    acc
                }
                if (nextLevel.isNotEmpty()) queue.add(nextLevel)
            }
            return count
        }
    }
}