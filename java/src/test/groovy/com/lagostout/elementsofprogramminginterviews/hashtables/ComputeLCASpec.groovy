
import com.lagostout.common.BinaryTreeNode
import com.lagostout.elementsofprogramminginterviews.hashtables.ComputeLCA
import spock.lang.Specification
import spock.lang.Unroll

class ComputeLCASpec extends Specification {

    @Unroll("node1 #node1.value node2 #node2.value expected #expected.value")
    def "finds LCA, optimizing for close ancestors"(
            BinaryTreeNode node1, BinaryTreeNode node2, BinaryTreeNode expected) {

        expect:
        println node1
        ComputeLCA.findLCA(node1, node2) == expected

        where:
        [node1, node2, expected] << [
            [0, 0, [[null, null, null, "A"]], 0], // 1
            [0, 1, [[null, 1, null, "A"], [null, null, 0, "B"]], 0], // 2
            [0, 2, [[null, 1, null, "A"], [2, null, 0, "B"],
                    [null, null, 1, "C"]], 0], // 3
            [1, 2, [[null, 1, null, "A"], [2, null, 0, "B"],
                    [null, null, 1, "C"]], 1], // 4
            [2, 2, [[1, 2, null, "A"], [null, null, 0, "B"],
                    [null, null, 0, "C"]], 2], // 5
            [1, 3, [[1, null, null, "A"], [null, 2, 0, "B"],
                    [3, null, 1, "C"], [null, null, 2, "D"]], 1], // 6
            [1, 2, [[1, 2, null, "A"], [null, null, 0, "B"],
                    [null, null, 0, "C"]], 0], // 7
            [4, 6, [[1, 2, null, "A"], [3, 4, 0, "B"], [5, 6, 0, "C"],
                    [null, null, 1, "D"], [null, null, 1, "E"],
                    [null, null, 2, "F"], [null, null, 2, "G"]], 0], // 8
            [5, 8, [[1, 2, null, "A"], [null, null, 0, "B"],
                    [3, 4, 0, "C"], [null, 5, 2, "D"],
                    [null, 6, 2, "E"], [null, null, 3, "F"],
                    [7, null, 4, "G"], [8, null, 6, "H"],
                    [null, null, 7, "I"]], 2], // 9
            [4, 6, [[1, 2, null, "A"], [3, 4, 0, "B"],
                    [null, null, 0, "C"], [5, null, 1, "D"],
                    [null, null, 1, "E"], [6, null, 3, "F"],
                    [null, null, 5, "G"]], 1], // 10
        ].collect {
            int node1Index, int node2Index,
            List<List> rawTree, int expectedIndex ->
                List<BinaryTreeNode> nodeTree = []
                BinaryTreeNode.buildBinaryTree(0, rawTree, nodeTree)
                def _node1 = nodeTree[node1Index]
                def _node2 = nodeTree[node2Index]
                def result = [_node1, _node2, nodeTree[expectedIndex]]
                result
        }

    }

}
