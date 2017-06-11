package com.lagostout.common

import com.google.common.annotations.VisibleForTesting
import com.lagostout.common.Heaps.HeapPropertyTester
import org.apache.commons.collections4.MultiValuedMap
import org.apache.commons.collections4.multimap.HashSetValuedHashMap

// https://www.hackerearth.com/practice/data-structures/trees/heapspriority-queues/tutorial/
// https://www.hackerearth.com/practice/algorithms/sorting/heap-sort/tutorial/

class Heap<T extends Comparable<?>> implements Heapable<T> {

    HeapPropertyTester<T> heapPropertyTester

    int size

    @VisibleForTesting
    List<T> getCopyOfState() {
        new ArrayList<T>(state).take(size)
    }

    protected List<T> state

    protected MultiValuedMap<T, Integer> itemToIndicesMap

    /**
     * For in-place sorting of items.
     * This constructor is private because it doesn't initialize
     * itemToIndicesMap. That's because it's used only when sorting
     * in place and itemToIndicesMap is only needed when modifying
     * the heap, which sorting in place doesn't do.
     *
     * @param heapPropertyTester
     * @param items Sorted in place.
     */
    private Heap(HeapPropertyTester<T> heapPropertyTester, List<T> items) {
        this(heapPropertyTester)
        // We don't bother to initialize itemToIndicesMap
        // because this constructor is strictly for sorting
        // in place, and we don't want to incur the space hit
        // of itemToIndicesMap.  We know doing this is OK
        // because itemToIndicesMap is used to update heap
        // values, which we don't do when sorting in place.
        // Ideally, we should have two implementations - one
        // modifiable, the other not.  The unmodifiable one
        // wouldn't have itemToIndicesMap.  The modifiable one
        // would.  The modifiable would extend the unmodifiable.
        // And We'd use the unmodifiable for sorting in place.
        // TODO Separate Heap into modifiable/unmodifiable implementations.
        state = items
        size = state.size()
    }

    Heap(HeapPropertyTester<T> heapPropertyTester) {
        this.heapPropertyTester = heapPropertyTester
        state = []
        size = 0
        itemToIndicesMap = new HashSetValuedHashMap<T, Integer>()
    }

    static <T extends Comparable<?>> void sortAscending(List<T> items) {
        def heap = createMaxHeapInPlace(items)
        heap.sort()
    }

    static <T extends Comparable<?>> void sortDescending(List<T> items) {
        def heap = createMinHeapInPlace(items)
        heap.sort()
    }

    static <T extends Comparable<?>> Heap createMinHeap() {
        new Heap(new Heaps.MinHeapPropertyTester<T>())
    }

    static <T extends Comparable<?>> Heap createMaxHeap() {
        new Heap(new Heaps.MaxHeapPropertyTester<T>())
    }

    static int leftIndex(int parentNodeIndex) {
        (parentNodeIndex * 2) + 1
    }

    static int rightIndex(int parentNodeIndex) {
        leftIndex(parentNodeIndex) + 1
    }

    static int parentIndex(int nodeIndex) {
        nodeIndex / 2 - 1
    }

    private static <T extends Comparable<?>> Heap createMinHeapInPlace(List<T> items) {
        new Heap(new Heaps.MinHeapPropertyTester<T>(), items)
    }

    private static <T extends Comparable<?>> Heap createMaxHeapInPlace(List<T> items) {
        new Heap(new Heaps.MaxHeapPropertyTester<T>(), items)
    }

    @Override
    void update(T oldValue, T newValue) {
        remove(oldValue)
        removeItemsOutsideHeap()
        add(newValue)
    }

    @Override
    void add(T comparable) {
        addToState(comparable)
        def lastNodeIndex = size - 1
        pushUp(lastNodeIndex)
    }

    @Override
    T peek() {
        state[0]
    }

    @Override
    T pop() {
        def item = removeHeapTop()
        removeItemsOutsideHeap()
        item
    }

    @Override
    void addAll(Iterable<T> comparables) {
        comparables.each { this.add(it) }
    }

    void sort() {
        if (size == 0) return
        def lastParentNodeIndex = parentIndex(size - 1)
        (lastParentNodeIndex..0).each { int nodeIndex ->
            pushDown(nodeIndex)
        }
        (size as Integer).times {
            removeHeapTop()
        }
    }

    @Override
    void remove(T value) {
        def index = itemToIndicesMap.get(value).first()
        moveOutsideHeapFrom(index)
    }

    @Override
    boolean isEmpty() {
        return size == 0
    }

    @Override
    List<T> asList() {
        List<T> list = []
        // Drain the heap
        while (!isEmpty()) {
            list << pop()
        }
        // Restore the heap
        list.each {
            add(it)
        }
        list
    }

    protected void pushDown(int nodeIndex) {
        def currentNodeIndex = nodeIndex
        while (true) {
            def nextNodeIndex = currentNodeIndex
            def leftNodeIndex = leftNodeIndex(currentNodeIndex)
            if (leftNodeIndex != null &&
                    !heapPropertyTester.satisfiesHeapProperty(
                            state[nextNodeIndex], state[leftNodeIndex])) {
                nextNodeIndex = leftNodeIndex
            }
            def rightNodeIndex = rightNodeIndex(currentNodeIndex)
            if (rightNodeIndex != null &&
                    !heapPropertyTester.satisfiesHeapProperty(
                            state[nextNodeIndex], state[rightNodeIndex])) {
                nextNodeIndex = rightNodeIndex
            }
            if (nextNodeIndex == currentNodeIndex) break
            swapValue(nextNodeIndex, currentNodeIndex)
            currentNodeIndex = nextNodeIndex
        }
    }

    protected void pushUp(int nodeIndex) {
        def currentNodeIndex = nodeIndex
        while (true) {
            def parent = parentNodeIndex(currentNodeIndex)
            if (parent != null &&
                    !heapPropertyTester.satisfiesHeapProperty(
                            state[parent], state[currentNodeIndex])) {
                swapValue(parent, currentNodeIndex)
                currentNodeIndex = parent
            } else {
                break
            }
        }
    }

    protected void swapValue(int nodeIndex, int otherNodeIndex) {
        T otherValue = state[otherNodeIndex]
        T value = state[nodeIndex]
        itemToIndicesMap.removeMapping(otherValue, otherNodeIndex)
        itemToIndicesMap.removeMapping(value, nodeIndex)
        setStateAt(otherNodeIndex, value)
        setStateAt(nodeIndex, otherValue)
    }

    private T removeHeapTop() {
        moveOutsideHeapFrom(0)
    }

    private T moveOutsideHeapFrom(int index) {
        if (size == 0) return null
        def lastNodeIndex = size - 1
        swapValue(index, lastNodeIndex)
        --size
        if (index != 0) {
            pushUp(index)
        }
        pushDown(index)
        def value = state[lastNodeIndex]
        value
    }

    private void removeItemsOutsideHeap() {
        while (state.size() > size) {
            state.removeAt(state.size() - 1)
        }
    }

    private void addToState(T value) {
        state.add(null)
        setStateAt(size, value)
        size++
    }

    private void setStateAt(int index, T value) {
        itemToIndicesMap.put(value, index)
        state[index] = value
    }

    private Integer leftNodeIndex(int parentIndex) {
        def index = leftIndex(parentIndex)
        index >= size ? null : index
    }

    private Integer rightNodeIndex(int parentIndex) {
        def index = rightIndex(parentIndex)
        index >= size ? null : index
    }

    private Integer parentNodeIndex(int childIndex) {
        def index = parentIndex(childIndex)
        index < 0 ? null : index
    }

}
