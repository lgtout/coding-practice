package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import com.lagostout.datastructures.BinaryTreeNode
import java.util.*

/**
 * Problem 15.5 page 266
 */

// Alternative approach to using a stack to backtrack up the tree
// is to take advantage of what's revealed by the relative position
// of a pair of nodes in a preorder traversal: A node cannot be the
// right child of another node if the former appears before the
// latter in a preorder traversal.

fun <T : Comparable<T>> reconstructBSTFromTraversal(
        createPathIterator: (List<T>) -> Iterator<T>,
        attachToLeaf: (node: BinaryTreeNode<T>, leaf: BinaryTreeNode<T>) -> Unit,
        attachToLeafOrAncestor: (node: BinaryTreeNode<T>, leaf: BinaryTreeNode<T>) -> Unit
): (path: List<T>) -> BinaryTreeNode<T>? {
    return fun (path: List<T>): BinaryTreeNode<T>? {
        if (path.isEmpty()) return null
        val pathIterator = createPathIterator(path)
        var treeNode = BinaryTreeNode(value = pathIterator.next())
        val ancestors = LinkedList<BinaryTreeNode<T>>()
        while (true) {
            if (!pathIterator.hasNext()) break
            val node = BinaryTreeNode(value = pathIterator.next())
            if (node.value > treeNode.value) {
                attachToLeaf(node, treeNode)
                node.parent = treeNode
                ancestors.push(treeNode)
            } else {
                var parent = treeNode
                while (ancestors.isNotEmpty()) {
                    if (ancestors.peek().value > node.value) {
                        parent = ancestors.pop()
                    } else break
                }
                node.parent = parent
                attachToLeafOrAncestor(node, parent)
            }
            treeNode = node
        }
        while (!treeNode.isRoot) {
            treeNode.parent?.let {
                treeNode = it
            }
        }
        return treeNode
    }
}

@Suppress("NAME_SHADOWING")
fun <T: Comparable<T>> reconstructBSTFromPostorderTraversal(path: List<T>) =
        reconstructBSTFromTraversal(
                { path: List<T> -> path.reversed().iterator() },
                { node, leaf -> leaf.right = node },
                { node, leaf -> leaf.left = node }
        )(path)

// This approach creates an additional function whose body configures
// and calls the reconstruction function.
//@Suppress("NAME_SHADOWING")
//fun <T : Comparable<T>> reconstructBSTFromPreorderTraversal(path: List<T>) =
//        reconstructBSTFromTraversal(
//                { path: List<T> -> path.iterator() },
//                { node, leaf -> leaf.left = node },
//                { node, leaf -> leaf.right = node }
//        )(path)

// This approach configures the reconstruction function and stores it.
// No additional functions are created.
// To avoid having to provide the generic type argument explicitly,
// place this declaration within a class that specifies the generic type
// as a parameter.
val reconstructBSTFromPreorderTraversal: (path: List<Int>) -> BinaryTreeNode<Int>? =
        reconstructBSTFromTraversal(
                { path: List<Int> -> path.iterator() },
                { node, leaf -> leaf.left = node },
                { node, leaf -> leaf.right = node })
