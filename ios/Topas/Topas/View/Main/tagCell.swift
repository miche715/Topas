//
//  tagCell.swift
//  Topas
//
//  Created by 김경호 on 2022/11/21.
//

import UIKit
import SnapKit

class TagCell : UICollectionViewCell{
    let tagLabel = UILabel().then{
        $0.font = .systemFont(ofSize: 14)
        $0.textColor = .gray
        
    }
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        
        commonInit()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(corder:) has not been implemented")
    }
    
    func commonInit(){
        self.addSubview(tagLabel)
        tagLabel.snp.makeConstraints{
            $0.center.equalToSuperview()
        }
    }
}
