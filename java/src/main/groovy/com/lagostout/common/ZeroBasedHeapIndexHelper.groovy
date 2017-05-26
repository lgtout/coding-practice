package com.lagostout.common

class ZeroBasedHeapIndexHelper {

    static int getParentIndex(int nodeIndex) {
        (nodeIndex - 1) / 2
    }

    static int getLeftChildIndex(int nodeIndex) {
        (nodeIndex * 2) + 1
    }

    static int getRightChildIndex(int nodeIndex) {
        getLeftChildIndex(nodeIndex) + 1
    }

    static <T> T getRightChild(int parentIndex, List<T> heap) {
        heap[getRightChildIndex(parentIndex)]
    }

    static <T> T getLeftChild(int parentIndex, List<T> heap) {
        heap[getLeftChildIndex(parentIndex)]
    }

    static <T> T getParent(int childIndex, List<T> heap) {
        heap[getParentIndex(childIndex)]
    }

}
