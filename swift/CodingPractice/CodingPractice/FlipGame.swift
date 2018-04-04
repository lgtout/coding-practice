//  Copyright © 2017 lagostout. All rights reserved.

import Foundation

/// [Problem description](https://leetcode.com/problems/flip-game)

class FlipGame {
    
    func generatePossibleNextMoves(_ s: String) -> [String] {
        var result = [String]()
        if s.count > 1 {
            for start in s.indices where start < s.index(s.endIndex, offsetBy:-1) {
                let end = s.index(start, offsetBy:2)
                let range = start ..< end
                if (s[range] == "++") {
                    let state = s.replacingCharacters(in: range, with: "--")
                    result.append(state)
                }
            }
        }
        return result
    }
    
}
