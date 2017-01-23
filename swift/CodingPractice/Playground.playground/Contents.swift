//: Playground - noun: a place where people can play

import Cocoa
//import Dollar
import CodingPractice
import Foundation

var s = "abcd"
//s.substring[1...2]
var str = "Hello, playground"
str.startIndex
let index = str.index(str.startIndex, offsetBy: 5)
str.substring(from:index)
type(of:index)
class A : CustomDebugStringConvertible {
    public let c = 1
    public var debugDescription: String {
       return "A(a:\(c))"
    }
}
class B {
    public let c = 1
    public var d: Int {
        return 4
    }
}
print(String(reflecting:A()))
print(String(reflecting: B()))




