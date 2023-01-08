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
    
    var chatList: [chatInfo] = []
    
//    chatList.append(.init(profil))
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.view = vc

//        vc.tableView.dataSource = self
//        vc.tableView.delegate = self
    }
}
//extension Chat : UITableViewDelegate, UITableViewDataSource{
//    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
//        tagList.count
//    }
//
//    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
//        let cell =
//        let cell = tableView.dequeueReusableCell(withIdentifier: MainListCell.identifier) as? MainListCell ?? MainListCell()
//        cell.bind(model: dataSources[indexPath.row])
//        return cell
//    }
//
//
//}
//extension Chat : UICollectionViewDelegate, UICollectionViewDataSource{
//    // cell 개수
//    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
//        return tagList.count
//    }
//
//    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
//        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "TagCell", for: indexPath) as? TagCell ?? TagCell()
//        cell.tagLabel.text = tagList[indexPath.row]
//        return cell
//    }
//}
//extension Chat : UICollectionViewDelegateFlowLayout{
//    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
//
//      let label = UILabel().then {
//          $0.font = .systemFont(ofSize: 14)
//          $0.text = tagList[indexPath.item]
//          $0.sizeToFit()
//      }
//      let size = label.frame.size
//
//      return CGSize(width: size.width + 16, height: size.height + 10)
//    }
//}
