//  Copyright © 2017 lagostout. All rights reserved.

import Foundation
import Quick
import Nimble

class FirstUniqueCharacterInAStringSpec : QuickSpec {
    
    override func spec() {
        describe("FirstUniqueCharacterInAString") {
            let cases:[(str:String, expected:Int)] = [
                ("", -1),
                ("leetcode", 0),
                ("loveleetcode", 2),
                ("cc", -1)
            ]
            let firstUnique = FirstUniqueCharacterInAString()
            for (str, expected) in cases {
                it("finds the index of the first non-repeating character") {
                    expect(firstUnique.firstUniqChar(str)).to(equal(expected))
                }
            }
        }
    }
    
}
