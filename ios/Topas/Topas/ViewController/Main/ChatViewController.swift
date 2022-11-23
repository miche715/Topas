//
//  Chat.swift
//  Topas
//
//  Created by 김경호 on 2022/08/30.
//

import UIKit
import SnapKit

class Chat : UIViewController{
    
    let vc = ChatView()
    
    let tagList: [String] = [
        "이창희",
        "김경호",
        "코딩노예",
        "살려줘"
    ]
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.view = vc
        
        vc.collectionView.delegate = self
        vc.collectionView.dataSource = self
    }
}
extension Chat: UICollectionViewDelegate, UICollectionViewDataSource{
    // cell 개수
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return tagList.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "TagCell", for: indexPath) as? TagCell ?? TagCell()
        cell.tagLabel.text = tagList[indexPath.row]
        return cell
    }
}
extension Chat: UICollectionViewDelegateFlowLayout{
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {

      let label = UILabel().then {
          $0.font = .systemFont(ofSize: 14)
          $0.text = tagList[indexPath.item]
          $0.sizeToFit()
      }
      let size = label.frame.size

      return CGSize(width: size.width + 16, height: size.height + 10)
    }
}
