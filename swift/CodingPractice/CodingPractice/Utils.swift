//  Copyright © 2017 lagostout. All rights reserved.

import Foundation

public func _pow(_ n:Decimal, _ p:Int) -> Int {
    return Int(NSDecimalNumber(decimal: pow(n, p)))
}

public func toBinaryString(_ n:Int) -> String {
    var n1 = n
    if (n1 < 0) {
        // 2's complement
        n1 = (Int(_pow(2, 64)) - 1) ^ abs(n1) + 1
    }
    return String(n1, radix: 2)
}

public func getTwosComplement(_ n:Int) -> Int {
    let m = Int.max << 1 | 1
    print("m \(toBinaryString(m)) \n")
    let result = (n ^ m) + 1
    return result
}
