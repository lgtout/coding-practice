//  Copyright © 2017 lagostout. All rights reserved.

import Foundation

class SumOfTwoIntegers {
    
    // Note - may also be solved recursively.
    
    func getSum(_ n1: Int, _ n2: Int) -> Int {
        var a = 0
        var sum = 0
        var mask = 1
        var bitPosition = 1
        // Make no assumption about the size
        // of the system-dependent size of the
        // type of Int actually in use.
        let maxBitPosition = Int(log2(Double(Int.max))) + 1
        // Making an assumption about the size of Int 
        // cuts runtime by half, from 32ms to 16ms
        // let maxBitPosition = 64
        while (bitPosition <= maxBitPosition) {
            let b = n1 & mask
            let c = n2 & mask
            let d = a ^ b ^ c
            sum = sum | d
            a = (a & b) | (b & c) | (a & c)
            bitPosition += 1
            a <<= 1
            mask <<= 1
        }
        return sum
    }
    
}
