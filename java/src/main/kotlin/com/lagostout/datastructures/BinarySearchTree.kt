package com.lagostout.datastructures

import com.google.common.annotations.VisibleForTesting
import com.lagostout.common.BinaryTreeNode
import java.util.*

interface BinarySearchTreeable <T : Comparable<T>> {
    fun find(key: T): BinaryTreeNode<T>?
    fun findOrInsertionPoint(key: T): BinaryTreeNode<T>
    fun next(key: T): BinaryTreeNode<T>
    fun insert(key: T)
    fun delete(key: T)
    fun nearestNeighbors(key: T): List<BinaryTreeNode<T>>
    fun rangeSearch(startKey: T, endKey: T): List<BinaryTreeNode<T>>
    fun contains(key: T): Boolean
    @VisibleForTesting
    var root: BinaryTreeNode<T>?
}

class BinarySearchTree<T : Comparable<T>> : BinarySearchTreeable<T> {

    override var root: BinaryTreeNode<T>? = null

    class Iter<T : Comparable<T>>  : Iterator<BinaryTreeNode<T>> {

        override fun hasNext(): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun next(): BinaryTreeNode<T> {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }

    override fun find(key: T): BinaryTreeNode<T>? {
        var nodeSought: BinaryTreeNode<T>? = null
        root?.let {
            var currentNode: BinaryTreeNode<T>? = it
            while (currentNode != null && nodeSought == null) {
                nodeSought = currentNode.run {
                    if (value == key) {
                        currentNode
                    } else {
                        currentNode = if (value > key) {
                            if (right != null) right else null
                        } else {
                            if (left != null) left else null
                        }
                        null
                    }
                }
            }
        }
        return nodeSought
    }

    // AKA modified find
    override fun findOrInsertionPoint(key: T): BinaryTreeNode<T> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insert(key: T) {
        val newNode = BinaryTreeNode<T>(key)
        val insertionPoint = find(key)
        insertionPoint?.apply {
            if (value > key) {
                left = newNode
            } else {
                right = newNode
            }
            newNode.parent = this
            return@insert
        }
        root = newNode
    }

    override fun contains(key: T): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun next(key: T): BinaryTreeNode<T> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(key: T) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun nearestNeighbors(key: T): List<BinaryTreeNode<T>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun rangeSearch(startKey: T, endKey: T): List<BinaryTreeNode<T>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}