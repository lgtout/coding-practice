//  Copyright © 2017 lagostout. All rights reserved.

import Foundation
import Quick
import Nimble

class FlipGameSpec : QuickSpec {
    override func spec() {
        describe("FlipGame") {
            let cases = [
                ("", []),
                ("+", []),
                ("++", ["--"]),
                ("+-", []),
                ("++-", ["---"]),
                ("+++", ["--+","+--"]),
                ("++++", ["--++","+--+","++--"]),
                ("+-+-", []),
                ("+-++", ["+---"]),
                ("+-+++-++-+", ["+---+-++-+","+-+---++-+","+-+++----+"])
            ]
            for (startString, expected) in cases {
                it("predicts state of \(startString) after first move") {
                    let state = FlipGame().generatePossibleNextMoves(startString)
                    print("initial state \(startString)")
                    print("e+pected \(expected)")
                    print("state \(state)")
                    expect(state).to(equal(expected))
                }
            }
        }
    }
}
