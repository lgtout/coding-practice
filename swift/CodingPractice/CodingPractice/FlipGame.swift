//  Copyright © 2017 lagostout. All rights reserved.

import Foundation

/// [Problem description](https://leetcode.com/problems/flip-game)

class FlipGame {
    
    func generatePossibleNextMoves(_ s: String) -> [String] {
        var result = [String]()
        if s.characters.count > 1 {
            for start in s.characters.indices where start < s.index(s.endIndex, offsetBy:-1) {
                let end = s.index(start, offsetBy:2)
                let range = start ..< end
                if (s.substring(with: range) == "++") {
                    let state = String(s)!.replacingCharacters(in: range, with: "--")
                    result.append(state)
                }
            }
        }
        return result
    }
    
}
