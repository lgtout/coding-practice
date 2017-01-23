//
//  Operators.swift
//  CodingPractice
//
//  Created by julian abiodun on 1/17/17.
//  Copyright © 2017 lagostout. All rights reserved.
//

import Foundation

precedencegroup PowerPrecedence { higherThan: MultiplicationPrecedence }
infix operator ^^ : PowerPrecedence
func ^^ (radix: Int, power: Int) -> Int {
    return Int(pow(Double(radix), Double(power)))
}
