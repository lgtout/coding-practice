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
                beforeEachTest {
                    tree.root = BinaryTreeNode.buildBinaryTree(listOf(
                            RawBinaryTreeNode(leftChildIndex = 1, value = 4),
                            RawBinaryTreeNode(value = 1)
                    )).left
//                    val keys = listOf(1,5,6,2,3,4)
//                    keys.forEach { tree.insert(it) }
//                    TODO("continue")
                }
                context("key is in tree") {
                    val keyToFind = 1
                    it("returns the node containing the key") {
                        assertEquals(keyToFind, tree.find(keyToFind)?.value)
                    }
                }
                xcontext("key isn't in tree") {
                    it("returns null") {
                        val keyToFind = 11
                        assertNull(tree.find(keyToFind))
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