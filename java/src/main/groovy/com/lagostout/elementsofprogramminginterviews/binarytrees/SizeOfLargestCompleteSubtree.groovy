package com.lagostout.elementsofprogramminginterviews.binarytrees

class SubtreeProperties {
    Integer vertex
    Integer height
    int size = 0
    Boolean isComplete
    Boolean isFull
    boolean getIsEmpty() {
        size == 0
    }
}

class SizeOfLargestCompleteSubtree {

    Map<Integer, List<Integer>> tree

    int getSize(Map<Integer, List<Integer>> tree) {
        this.tree = tree
        SubtreeProperties properties =
                getLargestCompleteSubtree(0)
        properties.size
    }

    SubtreeProperties getLargestCompleteSubtree(Integer parent) {
        if (parent == null)
            return new SubtreeProperties()
        def children = tree.get(parent)
        // If there are no subtrees
        if (children == null || children == [null,null])
            return [vertex: parent, height: 0,
                    size: 1, isComplete:  true,
                    isFull: true] as SubtreeProperties
        def leftVertex = children[0]
        def leftSubtreeProperties = getLargestCompleteSubtree(leftVertex)
        def rightVertex = children[1]
        def rightSubtreeProperties = getLargestCompleteSubtree(rightVertex)
        def largestCompleteSubtreeProperties = new SubtreeProperties()
        // We can only merge 2 subtrees if the left subtree is non-empty and
        // the roots of both are children of the current vertex.
        def leftAndRightSubtreesAreChildrenOfCurrentVertex =
                leftVertex == leftSubtreeProperties.vertex &&
                        rightVertex == rightSubtreeProperties.vertex
        if (!leftSubtreeProperties.isEmpty &&
                leftAndRightSubtreesAreChildrenOfCurrentVertex) {
            if (!rightSubtreeProperties.isEmpty &&
                    (leftSubtreeProperties.isComplete &&
                            rightSubtreeProperties.isComplete)) {
                // The fullness of the left subtree places restrictions on the
                // fullness and height of the type of right subtree that can be
                // merged with it:
                // 1. If the left subtree is not full, the right subtree must be
                // full and its height must be one less than that of the left subtree.
                // 2. If the left subtree is full, the right subtree's fullness
                // is irrelevant, but its height must be the same or one less than
                // that of the left subtree.
                def canMergeLeftSubtreeThatIsNotFull =
                        !leftSubtreeProperties.isFull &&
                                rightSubtreeProperties.isFull &&
                                (leftSubtreeProperties.height -
                                        rightSubtreeProperties.height == 1)
                def canMergeLeftSubtreeThatIsFull =
                        leftSubtreeProperties.isFull &&
                                (0..1).contains(leftSubtreeProperties.height -
                                        rightSubtreeProperties.height)
                if (canMergeLeftSubtreeThatIsNotFull ||
                        canMergeLeftSubtreeThatIsFull) {
                    largestCompleteSubtreeProperties.with {
                        vertex = parent
                        height = Math.max(rightSubtreeProperties.height,
                                leftSubtreeProperties.height) +
                                1 // parent vertex
                        size = rightSubtreeProperties.size +
                                leftSubtreeProperties.size +
                                1 // parent vertex
                        isComplete = true
                        isFull = rightSubtreeProperties.isFull &&
                                leftSubtreeProperties.isFull &&
                                rightSubtreeProperties.height ==
                                leftSubtreeProperties.height
                    }
                }
            }
            // The right subtree is empty. But we can still merge the
            // two if the left subtree contains a single vertex.
            else if (leftSubtreeProperties.size == 1) {
                largestCompleteSubtreeProperties.with {
                    vertex = parent
                    // Include the parent vertex in height and size.
                    height = 1
                    size = 2
                    isComplete = true
                    isFull = false  // It can't be full in this case.
                }
            }
        }
        // If we weren't able merge the subtrees, let's examine each and hang
        // on to the complete one of greatest size, if there is one.
        if (!largestCompleteSubtreeProperties.isComplete) {
            // The left subtree is either complete or not complete. Let's
            // retain it as the largest complete subtree found so far.
            largestCompleteSubtreeProperties = leftSubtreeProperties
            // Let's only update the largest complete subtree to the right
            // subtree if the right subtree is comparable (i.e. it is complete)
            // and if we either don't have a valid largest complete subtree yet,
            // or we do and its size is less than that of the right complete
            // subtree.
            if (rightSubtreeProperties.isComplete &&
                    (!largestCompleteSubtreeProperties.isComplete ||
                            (largestCompleteSubtreeProperties.size <
                                    rightSubtreeProperties.size))) {
                largestCompleteSubtreeProperties = rightSubtreeProperties
            }
        }
        largestCompleteSubtreeProperties
    }

}
