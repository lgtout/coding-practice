//
// Created by julian abiodun on 1/18/17.
// Copyright (c) 2017 lagostout. All rights reserved.
//

import Foundation
import Quick
import Nimble

class NumberComplementSpec : QuickSpec {
    override func spec() {
        let cases = [[5,2],[1,0]]
        for aCase in cases {
            it("computes complement of \(aCase[0])") {
                expect(NumberComplement
                    .findComplement(aCase[0])).to(equal(aCase[1]))
            }
        }

    }
}
