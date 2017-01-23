//
// Created by julian abiodun on 1/18/17.
// Copyright (c) 2017 lagostout. All rights reserved.
//

import Foundation

class NumberComplement {
    class func findComplement(_ num: Int) -> Int {
        let power:Int = Int(log2(Double(num))) + 1
        let decimalPower1 = pow(Double(2), Double(power)) - 1
        let mask:Int = Int(decimalPower1)
        return num ^ mask
    }
}
