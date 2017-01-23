//
//  Util.swift
//  Scratch
//
//  Created by julian abiodun on 1/15/17.
//  Copyright © 2017 lagostout. All rights reserved.
//

import Foundation

func binary(_ x:Int)->String {
    return String(x, radix:2)
}

precedencegroup PowerPrecedence { higherThan: MultiplicationPrecedence }
infix operator ^^ : PowerPrecedence
func ^^ (radix: Int, power: Int) -> Int {
    return Int(pow(Double(radix), Double(power)))
}

func bitCount(_ n: Int)->Int {
    var bitCount = 0
    var n1 = n
    for _:Int in 1...8 {
        if n1 & 1 == 1 { bitCount+=1 }
        n1 = (n1 >> 1)
    }
    return bitCount
}
