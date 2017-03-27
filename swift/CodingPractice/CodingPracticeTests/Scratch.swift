//  Copyright © 2017 lagostout. All rights reserved.

import Foundation
import Quick
import Nimble

class Scratch : QuickSpec {
    
    var i:Int = 0
    
    override func spec() {
        describe("a") {
            inc(i)
            describe("b") {
                inc(i)
                for _ in 0...0 {
                    it("c") {
                        self.inc(self.i)
                        expect(true).to(equal(true))
                        expect(1).to(equal(1))
                    }
                }
            }
        }
    }
    
    func inc(_ i:Int) {
        self.i += 1
        print(self.i)
    }
    
}
