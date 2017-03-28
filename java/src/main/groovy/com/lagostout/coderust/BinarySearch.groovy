package com.lagostout.coderust

// https://www.educative.io/collection/page/5642554087309312/5679846214598656/240002

class BinarySearch {
    static Optional<Integer> findIndexOf(List<Integer> integers, int key) {
        int i = 0
        int j = integers.size() - 1
        while (i <= j) {
            int mid = (i + j) / 2
            int currentInteger = integers[mid]
            if (currentInteger > key) j = mid - 1
            else if (currentInteger < key) i = mid + 1
            else return Optional.of(mid)
        }
        return Optional.empty()
    }
}
