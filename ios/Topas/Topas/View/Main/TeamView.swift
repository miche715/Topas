//
//  team.swift
//  Topas
//
//  Created by 김경호 on 2022/08/30.
//

import UIKit
import SnapKit

class TeamView: UIView{
    override init(frame: CGRect) {
        super.init(frame: frame)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    private func commoninit(){
        self.backgroundColor = .white
    }
}
