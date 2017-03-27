//  Copyright © 2017 lagostout. All rights reserved.

import Foundation
import Quick
import Nimble

class MaxDepthOfBinaryTreeSpec : QuickSpec {
    
    override func spec() {
        describe("MaxDepthOfBinaryTree") {
            let cases:[(adjacencies:Dictionary<Int, (left:Int?, right:Int?)>, expected:Int)] = [
                ([0: (nil, nil)], 1),
                ([0: (1, nil), 1:(nil, nil)], 2),
                ([0: (nil, 1), 1:(nil, nil)], 2),
                ([0: (1, 2), 1:(nil, nil), 2:(nil, nil)], 2),
                ([0: (1, nil), 1: (nil, 2), 2: (nil, nil)], 3),
                ([0: (1, nil), 1: (2, nil), 2: (nil, nil)], 3),
                ([0: (nil, 1), 1: (nil, 2), 2: (nil, nil)], 3),
                ([0: (nil, 1), 1: (2, nil), 2: (nil, nil)], 3),
                ([0: (1, 2), 1: (3, 4), 2: (nil, nil), 3: (nil, nil),
                  4: (nil, nil)], 3),
                ([0: (1, 2), 1: (3, 4), 2: (5, 6), 3: (nil, nil),
                  4: (nil, nil), 5: (nil, nil), 6: (nil, nil)], 3),
                ([0: (1, 2), 2: (3, 4), 3: (nil, nil), 4: (nil, nil),], 3),
            ]
            for (adjacencies, expected) in cases {
                let tree = toTree(adjacencies)
                let maxDepthCounter = MaxDepthOfBinaryTree()
                it("finds the max depth of a binary tree") {
                    let maxDepth = maxDepthCounter.maxDepth(tree)
                    expect(maxDepth).to(equal(expected))
                }
            }
        }
    }
    
    func toTree(_ adjacencies: Dictionary<Int, (left:Int?, right:Int?)>) -> TreeNode {
        var nodes: Dictionary<Int, TreeNode> = [:]
        // Create the nodes
        for vertex in adjacencies.keys {
            let node = TreeNode(vertex)
            nodes[vertex] = node
        }
        // Connect the nodes in tree
        for (vertex, children) in adjacencies {
            if let node = nodes[vertex] {
                if let left = children.left {
                    node.left = nodes[left]
                }
                if let right = children.right {
                    node.right = nodes[right]
                }
            }
        }
        return nodes[0]!
    }
    
}
