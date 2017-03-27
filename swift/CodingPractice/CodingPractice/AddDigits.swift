//  Copyright © 2017 lagostout. All rights reserved.

import Foundation

/// [Problem description](https://leetcode.com/problems/add-digits/)

class AddDigits {
    func addDigits(_ num: Int) -> Int {
        let numMod9 = num % 9
        return num != 0 && numMod9 == 0 ? 9 : numMod9
    }
}
