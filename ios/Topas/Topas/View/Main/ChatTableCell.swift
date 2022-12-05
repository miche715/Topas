//
//  ChatTableCell.swift
//  Topas
//
//  Created by 김경호 on 2022/12/05.
//

import UIKit
import SnapKit

class ChatTableCell: UITableViewCell{
    let profile = UIImageView().then{
        $0.layer.borderWidth = 2
        $0.layer.cornerRadius = 64 * 0.5
        $0.contentMode = .scaleAspectFill
        $0.image = UIImage(named: "defaultProfile")
        $0.clipsToBounds = true
    }
    
    let nickname = UILabel().then{
        $0.textColor = UIColor.black
        $0.font = UIFont.systemFont(ofSize: 15)
    }
    
    let lastMessage = UILabel().then{
        $0.textColor = UIColor.black
        $0.font = UIFont.systemFont(ofSize: 15)
    }
    
    private func commoninit(){
        self.addSubview(profile)
        profile.snp.makeConstraints{
            $0.leading.equalTo(self.safeAreaLayoutGuide.snp.leading).offset(15)
            $0.top.equalTo(self.safeAreaLayoutGuide.snp.top).offset(5)
        }
        
        self.addSubview(nickname)
        nickname.snp.makeConstraints{
            $0.top.equalTo(profile.snp.top)
            $0.leading.equalTo(profile.snp.trailing).offset(15)
        }
        
        self.addSubview(lastMessage)
        lastMessage.snp.makeConstraints{
            $0.top.equalTo(nickname.snp.bottom)
            $0.leading.equalTo(nickname.snp.leading)
        }
    }
}
