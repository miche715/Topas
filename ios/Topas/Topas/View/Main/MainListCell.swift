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
    
    private var skills : [String] = []
    
    let profile = UIImageView().then{
        $0.layer.borderWidth = 2
        $0.layer.cornerRadius = 64 * 0.5
        $0.contentMode = .scaleAspectFill
        $0.image = UIImage(named: "defaultProfile")
        $0.clipsToBounds = true
    }
    
    // 글자 크기에 맞게 label 크기 증가 해결하기
    let introduce = UILabel().then{
        $0.backgroundColor = UIColor.systemBlue
        $0.textColor = UIColor.white
        $0.layer.borderWidth = 1
        $0.textAlignment = .center
        $0.lineBreakMode = .byWordWrapping
        $0.numberOfLines = 0
        $0.font = UIFont.systemFont(ofSize: 20)
    }
    
    let nickname = UILabel().then{
        $0.textColor = UIColor.black
        $0.font = UIFont.systemFont(ofSize: 20)
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
    
    
    let collectionView = UICollectionView(frame: .zero, collectionViewLayout: .init()).then{
//        let layout = LeftAlignedCollectionViewFlowLayout()
        let layout = MiddleAlignedCollectionViewFlowLayout()
//        layout.minimumLineSpacing = 3
        layout.minimumInteritemSpacing = 3
//        layout.sectionInset = UIEdgeInsets(top: 5, left: 2, bottom: 5, right: 2)
        
        $0.isScrollEnabled = false
        $0.collectionViewLayout = layout
        $0.backgroundColor = .systemBackground
        $0.register(TagCell.self, forCellWithReuseIdentifier: "TagCell")
    }
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?){
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        commoninit()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not implemented")
    }
    
    private func commoninit(){
        self.addSubview(profile)
        profile.snp.makeConstraints{
            $0.leading.equalTo(self.safeAreaLayoutGuide.snp.leading).offset(16)
            $0.top.equalTo(self.safeAreaLayoutGuide.snp.top).offset(10)
//            $0.bottom.equalTo(self.safeAreaLayoutGuide.snp.bottom).offset(-10)
            $0.width.equalTo(64)
            $0.height.equalTo(64)
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
        
        self.addSubview(introduce)
        introduce.snp.makeConstraints{
            $0.leading.equalTo(self.safeAreaLayoutGuide.snp.leading).offset(15)
            $0.trailing.equalTo(self.safeAreaLayoutGuide.snp.trailing).offset(-15)
            $0.top.equalTo(profile.snp.centerY).offset(50)
//            $0.bottom.equalTo(self.safeAreaLayoutGuide.snp.bottom).offset(-10)
            $0.height.equalTo(50)
        }
        
        self.addSubview(collectionView)
        collectionView.snp.makeConstraints{
            $0.leading.equalTo(self.safeAreaLayoutGuide.snp.leading).offset(15)
            $0.trailing.equalTo(self.safeAreaLayoutGuide.snp.trailing).offset(-15)
            $0.top.equalTo(introduce.snp.bottom).offset(10)
            $0.bottom.equalTo(self.safeAreaLayoutGuide.snp.bottom).offset(-10)
            $0.height.equalTo(50)
        }
        
        collectionView.delegate = self
        collectionView.dataSource = self
    }
}

extension MainListCell{
    public func bind(model: MainCellModel){
//        profile.image = model.profile
        nickname.text = model.nickname
        introduce.text = model.introduce
        skills = model.skills
    }
}
extension MainListCell : UICollectionViewDelegate, UICollectionViewDataSource{
    // cell 개수
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        print("-----------------------------")
        print(skills.count)
        return skills.count
    }

    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "TagCell", for: indexPath) as? TagCell ?? TagCell()
        cell.tagLabel.text = skills[indexPath.row]
        return cell
    }
}
extension MainListCell : UICollectionViewDelegateFlowLayout{
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {

        
        let label = UILabel().then {
            $0.font = .systemFont(ofSize: 14)
            $0.text = skills[indexPath.item]
            $0.sizeToFit()
        }
        let size = label.frame.size
        
        return CGSize(width: size.width + 16, height: size.height + 10)
    }
}

//struct MainCellModel{
//    let profile : UIImage
//    let introduce : String
//    let nickname : String
//    let skills : [String]
//}
