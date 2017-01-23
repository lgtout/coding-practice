import Foundation

open class HammingDistance {
    func hammingDistance(_ x: Int, _ y: Int) -> Int {
        let a:Int = 10 ^^ 2
        print(a)
        let z:Int = x ^ y
        return bitCount(z)
    }
    
    func bitCount(_ n: Int)->Int {
        var bitCount = 0
        var n1 = n
        for _:Int in 1...32 {
            if n1 & 1 == 1 { bitCount += 1 }
            n1 = (n1 >> 1)
        }
        return bitCount
    }
}
