//  Copyright © 2017 lagostout. All rights reserved.

import Foundation

//  Binary tree node
class TreeNode {
    public var val: Int
    public var left: TreeNode?
    public var right: TreeNode?
    public init(_ val: Int) {
      self.val = val
      self.left = nil
      self.right = nil
    }
}

class Frame {
    var node:TreeNode
    var step:Int
    init(_ node:TreeNode, _ step:Int) {
        self.node = node
        self.step = step
    }
}

/// [Problem description](https://leetcode.com/problems/maximum-depth-of-binary-tree)

class MaxDepthOfBinaryTree {
    
    // Non-recursive implementation
    func maxDepth(_ root: TreeNode?) -> Int {
        var maxDepth = 0
        var depth = 0
        var stack: [Frame] = []
        guard let node = root else {
            return 0
        }
        stack.append(Frame(node, 0))
        while let frame = stack.last {
            switch frame.step {
            case 0:
                let node = frame.node
                if case let left? = node.left {
                    stack.append(Frame(left, 0))
                }
                if case let right? = node.right {
                    stack.append(Frame(right, 0))
                }
                frame.step = 1
                depth += 1
                maxDepth = max(depth, maxDepth)
            case 1:
                _ = stack.popLast()
                depth -= 1
            default:
                break
            }
        }
        return maxDepth
    }
    
}
