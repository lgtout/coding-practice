//  Copyright © 2017 lagostout. All rights reserved.

import Foundation
import Quick
import Nimble

class NimGameSpec: QuickSpec {
    override func spec() {
        describe("NimGame") {
            let cases = [(1,true),(2,true),
                         (3,true),(4,false),
                         (5,true),(6,true),
                         (7,true)]
            for (testCount, (rockCount, expected)) in cases.enumerated() {
                it("detects the possibility of me winning \(testCount)") {
                    let canWin = NimGame.Solution().canWinNim(rockCount)
                    expect(canWin).to(equal(expected))
                }
            }
        }
    }
}
