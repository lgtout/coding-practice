package com.lagostout.bytebybyte.recursion

import com.lagostout.datastructures.BinaryTreeNode
import com.lagostout.datastructures.BinaryTreeNode.Companion.bbtr
import com.lagostout.datastructures.RawBinaryTreeNode.Companion.rbt
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import org.paukov.combinatorics3.Generator

object FindAllPossibleArraysThatCouldResultInBinaryTree : Spek({

    fun <T : Comparable<T>> find(
            root: BinaryTreeNode<T>, compareTo: (T) -> Int): BinaryTreeNode<T> {
        var currentNode = root
        while (true) {
            val comparison = compareTo(currentNode.value)
            when {
                comparison > 0 -> {
                    currentNode.right?.let {
                        currentNode = it
                    }
                }
                comparison < 0 -> {
                    currentNode.left?.let {
                        currentNode = it
                    }
                }
                else -> null
            } ?: break
        }
        return currentNode
    }

    fun <T : Comparable<T>> find(
            value: T, root: BinaryTreeNode<T>): BinaryTreeNode<T> {
        return find(root) { value.compareTo(it) }
    }

    fun <T : Comparable<T>> insert(value: T, root: BinaryTreeNode<T>) {
        val duplicateOrParent = find(value, root)
        if (duplicateOrParent.value == value) return
        duplicateOrParent.let {
            // We know the parent is a leaf, so we don't need to
            // consider its left/right children.
            val node = BinaryTreeNode(value = value, parent = it)
            (if (value > it.value) it::right else it::left).set(node)

        }
    }

    fun computeByBruteForce(root: BinaryTreeNode<Int>): List<List<Int>> {
        val values = BinaryTreeNode.toList(root).map { it.value }
        return Generator.permutation(values).simple().filter { permutation ->
            val candidateTreeRoot = BinaryTreeNode<Int>(value = permutation[0])
            for (value in permutation.drop(1)) {
                insert(value, candidateTreeRoot)
            }
            val candidateTreeValues = BinaryTreeNode.toList(candidateTreeRoot)
                    .map { it.value }
            candidateTreeValues == values
        }
    }

    val data = listOfNotNull(
        bbtr(listOf(rbt(1))),
        bbtr(listOf(rbt(1, right = 1), rbt(2))),
        bbtr(listOf(rbt(2, left = 1), rbt(1))),
        bbtr(listOf(rbt(2, left = 1, right = 2), rbt(1), rbt(3))),
        null
    ).map {
        data(it, computeByBruteForce(it))
    }.toTypedArray()

    describe("findAllPossibleArraysThatCouldResultInBinaryTree") {
        on("tree %s", with = *data) { tree, expected ->
            it("should return $expected") {
                val allPossibleArrays = findAllPossibleArraysThatCouldResultInBinaryTree(tree)
                assertThat(allPossibleArrays).containsExactlyElementsOf(expected)
            }
        }
    }

})