package com.lagostout.common

class RawBinaryTreeNode<T extends Comparable<T>>{
    Integer leftChildIndex = null
    Integer rightChildIndex = null
    Integer parentIndex = null
    T value

    RawBinaryTreeNode(Integer leftChildIndex,
                      Integer rightChildIndex,
                      Integer parentIndex,
                      T value) {
        this.leftChildIndex = leftChildIndex
        this.rightChildIndex = rightChildIndex
        this.parentIndex = parentIndex
        this.value = value
    }
}
