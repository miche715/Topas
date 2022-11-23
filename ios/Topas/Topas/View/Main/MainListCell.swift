//
//  MainListCell.swift
//  Topas
//
//  Created by 김경호 on 2022/11/11.
//

import UIKit
import SnapKit

class MainListCell: UITableViewCell {

    static let identifier = "mainCell"
    
    let profile = UIImageView().then{
        $0.layer.borderWidth = 2
        $0.layer.cornerRadius = 64 * 0.5
        $0.contentMode = .scaleAspectFill
        $0.image = UIImage(named: "defaultProfile")
        $0.clipsToBounds = true
    }
    
    let introduce = UILabel().then{
        $0.backgroundColor = UIColor.blue
        $0.textColor = UIColor.white
        $0.layer.borderWidth = 1
//        $0.translatesAutoresizingMaskIntoConstraints = false
    }
    
    let nickname = UILabel().then{
        $0.textColor = UIColor.black
        $0.font = UIFont.systemFont(ofSize: 20)
//        $0.translatesAutoresizingMaskIntoConstraints = false
    }
    
    let talkButton = UIButton().then{
        $0.setImage(UIImage(named: "talk"), for: .normal)
        $0.contentEdgeInsets = UIEdgeInsets(top: 1, left: 5, bottom: 1, right: 60)
        $0.setTitle("함께하기", for: .normal)
        $0.titleLabel?.font = UIFont.systemFont(ofSize: 12)
        $0.titleEdgeInsets = UIEdgeInsets(top: 0, left: 5, bottom: 0, right: -50)
        $0.backgroundColor = UIColor(named: "BrandColor")
        $0.layer.cornerRadius = 5
        $0.layer.borderWidth = 1
    }
    
    let collectionView:  UICollectionView = {
        let layout = UICollectionViewFlowLayout()
        let cv = UICollectionView(frame: .zero, collectionViewLayout: layout)
        cv.register(TagCell.self, forCellWithReuseIdentifier: "TagCell")
        return cv
    }()
    
//    let tagLabel = UILabel().then{
//        $0.font = .systemFont(ofSize: 24)
//        $0.textColor = .gray
//    }
//    
//    let tagCollection = UICollectionView().then{
//        let flowLayout = UICollectionViewFlowLayout()
//        flowLayout.itemSize = CGSize(width: 100, height: 100)
//
//        $0.collectionViewLayout = flowLayout
//        $0.backgroundColor = UIColor.red
//    }
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?){
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        commoninit()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not implemented")
    }
    
    func commoninit(){
        self.addSubview(profile)
        profile.snp.makeConstraints{
            $0.leading.equalTo(self.safeAreaLayoutGuide.snp.leading).offset(16)
            $0.top.equalTo(self.safeAreaLayoutGuide.snp.top).offset(10)
//            $0.bottom.equalTo(self.safeAreaLayoutGuide.snp.bottom).offset(-10)
            $0.width.equalTo(64)
            $0.height.equalTo(64)
            
        }
        
        self.addSubview(introduce)
        introduce.snp.makeConstraints{
            $0.leading.equalTo(self.safeAreaLayoutGuide.snp.leading).offset(15)
            $0.trailing.equalTo(self.safeAreaLayoutGuide.snp.trailing).offset(-15)
            $0.top.equalTo(profile.snp.centerY).offset(50)
            $0.bottom.equalTo(self.safeAreaLayoutGuide.snp.bottom).offset(-10)
            $0.height.equalTo(40)
        }
        
        self.addSubview(nickname)
        nickname.snp.makeConstraints{
            $0.leading.equalTo(profile.snp.centerX).offset(50)
            $0.centerY.equalTo(profile.snp.centerY)
        }
        
        self.addSubview(talkButton)
        talkButton.snp.makeConstraints{
            $0.top.equalTo(self.safeAreaLayoutGuide.snp.top).offset(10)
            $0.trailing.equalTo(self.safeAreaLayoutGuide.snp.trailing).offset(-10)
            $0.width.equalTo(100)
            $0.height.equalTo(30)
        }
        
//        self.addSubview(tagCollection)
//        tagCollection.snp.makeConstraints{
//            $0.leading.equalTo(self.safeAreaLayoutGuide.snp.leading)
//            $0.trailing.equalTo(self.safeAreaLayoutGuide.snp.trailing)
//            $0.top.equalTo(introduce.snp.bottom)
//            $0.height.equalTo(40)
//        }
        
        self.addSubview(collectionView)
        collectionView.snp.makeConstraints{
            $0.leading.equalTo(self.safeAreaLayoutGuide.snp.leading)
            $0.trailing.equalTo(self.safeAreaLayoutGuide.snp.trailing)
            $0.top.equalTo(introduce.snp.bottom)
        }
    }
}

extension MainListCell{
    public func bind(model: MainCellModel){
//        profile.image = model.profile
        nickname.text = model.nickname
        introduce.text = model.introduce
    }
}


struct MainCellModel{
    let profile : UIImage
    let introduce : String
    let nickname : String
}
