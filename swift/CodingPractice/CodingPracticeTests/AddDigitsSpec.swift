//  Copyright © 2017 lagostout. All rights reserved.

import Foundation
import Quick
import Nimble

class AddDigitsSpec : QuickSpec {
    override func spec() {
        describe("AddDigits") {
            let addDigits = AddDigits()
            let cases: [(number: Int, expected:Int)] = [
                (0,0),(1,1),(2,2),(9,9),(10,1),(11,2),(12,3)
            ]
            describe("repeatedly adds digits of number until result has only one digit ") {
                for (number, expected) in cases {
                    it("reduces \(number) to \(expected)") {
                        expect(addDigits.addDigits(number)).to(equal(expected))
                    }
                }
            }
        }
    }
}
